package haneum.troller.dataflow.api.updateRecord;


import haneum.troller.dataflow.dto.MemberDto;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name="SignUp",description = "회원가입시에 유저전적 정보 저장 및 정보로 머신러닝 진행")
@RequestMapping("/api/dataflow/update/")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UpdateGameRecord {
    @Autowired
    public KafkaScheduler kafkaScheduler;

    @Operation(summary = "전적갱신 api", description = "회원가입시에는 새로 유저전적 등록/ 로그인 및 전적갱신시에는 update로 기능 구현 (consumer에서)")
    @PostMapping("record")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED"),
            @ApiResponse(responseCode = "400", description = "서버에서 해당 parameter를 binding하지 못함" +
                    "오타 체크")
    })
    public ResponseEntity updateGameRecord(@RequestBody MemberDto memberDto) throws IOException, InterruptedException {

        String lolName = memberDto.getLolName();

        log.info("등록된 회원의 롤 닉네임:{}", lolName);

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

        return new ResponseEntity(HttpStatus.OK);

    }
}
