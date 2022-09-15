package haneum.troller.dataflow.scheduler;

import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import haneum.troller.dataflow.kafka.lineInfo.LineInfoProducer;
import haneum.troller.dataflow.kafka.mostChampion.MostChampionProducer;
import haneum.troller.dataflow.kafka.userInfo.UserInfoProducer;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.service.LolNameToList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Component
public class KafkaScheduler {

    @Autowired
    public FullRecordProducer fullRecordProducer;
    @Autowired
    public LineInfoProducer lineInfoProducer;
    @Autowired
    public UserInfoProducer userInfoProducer;
    @Autowired
    public MostChampionProducer mostChampionProducer;

    @Autowired
    public GameRecordRepository gameRecordRepository;
    public static int lolNameCount=0;


    public void produceFullSearch(String lolName) throws IOException, InterruptedException {
            String encodedLolName = URLEncoder.encode(lolName, "utf-8");
            String fullGameRecord = GameRecordApi.fullRecordGameRecord(encodedLolName);
            fullRecordProducer.sendFullRecordJson(lolName,fullGameRecord);
    }

    public void produceLineInfo(String lolName) throws IOException{
        String encodedLolName = URLEncoder.encode(lolName, "utf-8");
        String lineInfo = GameRecordApi.lineInfo(encodedLolName);
        lineInfoProducer.sendLineInfo(lolName,lineInfo);
    }

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


    /**
     * 처음 초기 세팅시에만 사용
     * 처음 db구성시 해당 함수를 통해 create, timestamp를 함께넣어 db에 저장(nifi??)
     */
    @Scheduled(fixedDelay=120000) //2분에 한번 호출
    public void createFullSearchInitial() throws IOException, InterruptedException {
        List<String> lolNamesList = LolNameToList.readLolNameTxt();
        GameRecord gameRecordEntity = GameRecord.builder().lolName(lolNamesList.get(lolNameCount++)).build();
        GameRecord gameRecord = gameRecordRepository.save(gameRecordEntity);

        produceFullSearch(gameRecord.getLolName());
        produceLineInfo(gameRecord.getLolName());
        produceMostChampion(gameRecord.getLolName());
        produceUserInfo(gameRecord.getLolName());

    }



}
