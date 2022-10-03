package haneum.troller.dataflow.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.dto.RegressionDto;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import haneum.troller.dataflow.kafka.fullSearchRank.FullRecordRankProducer;
import haneum.troller.dataflow.kafka.linInfoRank.LineInfoRankProducer;
import haneum.troller.dataflow.kafka.lineInfo.LineInfoProducer;
import haneum.troller.dataflow.kafka.mostChampion.MostChampionProducer;
import haneum.troller.dataflow.kafka.mostChampionRank.MostChampionRankProducer;
import haneum.troller.dataflow.kafka.userInfo.UserInfoProducer;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.repository.UserInfoRepository;
import haneum.troller.dataflow.service.KafkaService;
import haneum.troller.dataflow.service.LolNameToList;
import haneum.troller.dataflow.service.UserInfoService;
import haneum.troller.dataflow.service.ml.RegressionService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class KafkaScheduler {

    @Autowired
    public KafkaService kafkaService;

    @Autowired
    public GameRecordRepository gameRecordRepository;







    /**
     * 초기 머신러닝 적용(regression)
     */
//    @Scheduled(fixedDelay=1000) //작업끝나고 1초뒤에 작업
//    public void initialUpdateTrollPossibility() throws IOException, JSONException {
//
//        List<UserInfo> userInfoList = userInfoRepository.findByTrollPossibility("0");
//
//        UserInfo userInfo = userInfoList.get(lolNameCount++);
//        String lolName = userInfo.getLolName();
//
//        List<RegressionDto> regressionDtoList = regressionService.makeRegressionDtoList(lolName);
//
//        Double sumTrollPossibility = Double.valueOf(0);
//        for (RegressionDto regressionDto : regressionDtoList) {
//            Double trollPossibility = Double.valueOf(regressionService.callFlaskApi(regressionDto));
//            sumTrollPossibility += trollPossibility;
//        }
//        sumTrollPossibility = sumTrollPossibility / regressionDtoList.size();
//
//        log.info("트롤확률업데이트:{}", lolName);
//        log.info("트롤확률:{}", sumTrollPossibility);
//
//        UserInfo userInfoUpdatedTrollPossibility = userInfo.updateTrollPossibility(String.valueOf(sumTrollPossibility));
//        userInfoRepository.save(userInfoUpdatedTrollPossibility);
//    }


    /**
     * 처음 초기 세팅시에만 사용
     * 처음 db구성시 해당 함수를 통해 create, timestamp를 함께넣어 db에 저장(nifi??)
     */
    public static int lolNameCount=0;
    @Scheduled(fixedDelay=180000) //함수가 끝난후 3분뒤에 한번씩 호출
    public void createFullSearchInitial() throws IOException, InterruptedException, JSONException {
        List<String> lolNamesList = LolNameToList.readLolNameTxt();
        GameRecord gameRecordEntity = GameRecord.builder().lolName(lolNamesList.get(lolNameCount++)).build();
        GameRecord gameRecord = gameRecordRepository.save(gameRecordEntity);
        String lolName = gameRecord.getLolName();

        kafkaService.produceGameRecordAndML(lolName,gameRecord);
    }




}
