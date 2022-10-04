package haneum.troller.dataflow.service;

import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import haneum.troller.dataflow.kafka.lineInfo.LineInfoProducer;
import haneum.troller.dataflow.kafka.mostChampion.MostChampionProducer;
import haneum.troller.dataflow.kafka.userInfo.UserInfoProducer;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

@Service
@RequiredArgsConstructor
public class KafkaService {
    private final UserInfoService userInfoService;
    private final FullRecordProducer fullRecordProducer;
    private final UserInfoProducer userInfoProducer;
    private final MostChampionProducer mostChampionProducer;
    private final LineInfoProducer lineInfoProducer;


    /**
     * gamerecord produce (userinfo, mostchamp, linerank, fullsearch)
     * 머신러닝도 포함(현재는 트롤확률만 포함)
     */
    public void produceGameRecordAndML(String lolName, GameRecord gameRecord) throws IOException, InterruptedException, JSONException {
        produceUserInfo(lolName);
        Thread.sleep(10000);
        produceMostChampion(lolName);
        Thread.sleep(10000);
        produceLineInfo(lolName);
        Thread.sleep(10000);
        produceFullSearch(lolName);
        Thread.sleep(1000);

        //머신러닝
        userInfoService.updateTrollPossibility(lolName);
        Thread.sleep(3000);
        userInfoService.updateCluster(lolName);
    }


    public void produceFullSearch(String lolName) throws IOException, InterruptedException {
        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
        String fullGameRecord = GameRecordApi.fullRecordGameRecord(encodedLolName);
        fullRecordProducer.sendFullRecordJson(lolName,fullGameRecord);
    }

//    public void produceFullSearchRank(String lolName) throws IOException, InterruptedException {
//        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
//        String fullGameRecord = GameRecordApi.fullRecordGameRecordRank(encodedLolName);
//        fullRecordRankProducer.sendFullRecordJson(lolName,fullGameRecord);
//    }

    public void produceLineInfo(String lolName) throws IOException{
        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
        String lineInfo = GameRecordApi.lineInfo(encodedLolName);
        lineInfoProducer.sendLineInfo(lolName,lineInfo);
    }

//    public void produceLineInfoRank(String lolName) throws IOException{
//        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
//        String lineInfo = GameRecordApi.lineInfoRank(encodedLolName);
//        lineInfoRankProducer.sendLineInfo(lolName,lineInfo);
//    }

    public void produceUserInfo(String lolName) throws IOException{
        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
        String userInfo = GameRecordApi.userInfo(encodedLolName);
        userInfoProducer.sendUserInfo(lolName,userInfo);
    }

    public void produceMostChampion(String lolName) throws IOException{
        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
        String mostChampion = GameRecordApi.mostChampion(encodedLolName);
        mostChampionProducer.sendMostChampion(lolName,mostChampion);
    }

//    public void produceMostChampionRank(String lolName) throws IOException{
//        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
//        String mostChampion = GameRecordApi.mostChampionRank(encodedLolName);
//        mostChampionRankProducer.sendMostChampion(lolName,mostChampion);
//    }

}
