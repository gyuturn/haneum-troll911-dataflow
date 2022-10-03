package haneum.troller.dataflow.api.updateRecord;


import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.dto.LolNameDto;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@Tag(name="SignUp",description = "회원가입시에 유저전적 정보 저장 및 정보로 머신러닝 진행")
@RequestMapping("/api/dataflow/update/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UpdateGameRecord {

    private final KafkaScheduler kafkaScheduler;
    private final GameRecordRepository gameRecordRepository;

    @Operation(summary = "전적갱신 api", description = "회원가입시에는 새로 유저전적 등록")
    @PostMapping("record")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "이미 유저에 등록된 회원이기에 저장하지 않음"),
            @ApiResponse(responseCode = "201", description = "새로 등록된 롤 회원가입 db에 새로 저장")
    })
    public ResponseEntity updateGameRecord(@RequestBody LolNameDto lolnameDto) throws IOException, InterruptedException {

        String lolName = lolnameDto.getLolName();

        Optional<GameRecord> gameRecordOptional = gameRecordRepository.findById(lolName);

//        if (!gameRecordOptional.isEmpty()) {
//            log.info("db에 이미 등록된 롤 닉네임:{}", lolName);
//            return new ResponseEntity(HttpStatus.OK);
//        }

        log.info("등록할 회원의 롤 닉네임:{}", lolName);

        /**
         * 유저 정보 저장  -> 비동기로 처리해야함 application에서
         */
        kafkaScheduler.produceUserInfo(lolName);
        kafkaScheduler.produceMostChampionRank(lolName);
        Thread.sleep(10000);
        kafkaScheduler.produceLineInfoRank(lolName);
        Thread.sleep(10000);
        kafkaScheduler.produceFullSearch(lolName);

        /**
         * 머신러닝 적용
         */

        return new ResponseEntity(HttpStatus.CREATED);

    }
}
