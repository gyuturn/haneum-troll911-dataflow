package haneum.troller.dataflow.api.updateRecord;


import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.dto.LolNameDto;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.repository.UserInfoRepository;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import haneum.troller.dataflow.service.KafkaService;
import haneum.troller.dataflow.service.UserInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Tag(name="SignUp",description = "회원가입시에 유저전적 정보 저장 및 정보로 머신러닝 진행")
@RequestMapping("/api/dataflow/record/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UpdateGameRecord {

    private final KafkaScheduler kafkaScheduler;
    private final GameRecordRepository gameRecordRepository;
    private final UserInfoService userInfoService;
    private final KafkaService kafkaService;


    @Operation(summary = "전적갱신 api", description = "회원가입시에는 새로 유저전적 등록" +
            "비동기로 처리해야함 application에서")
    @PostMapping("update")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이미 유저에 등록된 회원이기에 저장하지 않고 200"),
            @ApiResponse(responseCode = "201", description = "새로 등록된 롤 회원가입 db에 새로 저장 201")
    })
    public ResponseEntity updateGameRecord(@RequestBody LolNameDto lolnameDto) throws IOException, InterruptedException, JSONException {


        String lolName = lolnameDto.getLolName();
        log.info("전적갱신 api 호츌:{}:", lolName);

        Optional<GameRecord> gameRecordOptional = gameRecordRepository.findById(lolName);


        /**
         * gamerecord에 이미 존재하는 유저인경우
         */
        if (!gameRecordOptional.isEmpty()) {
            log.info("db에 이미 등록된 롤 닉네임-update:{}", lolName);
            kafkaService.produceGameRecordAndML(lolName,gameRecordOptional.get());
            return new ResponseEntity(HttpStatus.OK);
        }

        log.info("등록할 회원의 롤 닉네임-created:{}", lolName);

        GameRecord newGameRecord = GameRecord.builder().lolName(lolName).build();

        GameRecord createdGameRecord = gameRecordRepository.save(newGameRecord);
        kafkaService.produceGameRecordAndML(lolName,createdGameRecord);

        return new ResponseEntity(HttpStatus.CREATED);

    }
}
