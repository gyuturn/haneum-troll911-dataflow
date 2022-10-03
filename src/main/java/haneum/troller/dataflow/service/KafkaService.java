package haneum.troller.dataflow.service;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final KafkaScheduler kafkaScheduler;
    private final UserInfoService userInfoService;


    /**
     * gamerecord produce (userinfo, mostchamp, linerank, fullsearch)
     * 머신러닝도 포함(현재는 트롤확률만 포함)
     */
    public void produceGameRecordAndML(String lolName, GameRecord gameRecord) throws IOException, InterruptedException, JSONException {
        kafkaScheduler.produceUserInfo(lolName);
        Thread.sleep(10000);
        kafkaScheduler.produceMostChampionRank(lolName);
        Thread.sleep(10000);
        kafkaScheduler.produceLineInfoRank(lolName);
        Thread.sleep(10000);
        kafkaScheduler.produceFullSearch(lolName);

        //머신러닝
        userInfoService.updateTrollPossibility(gameRecord);
    }
}
