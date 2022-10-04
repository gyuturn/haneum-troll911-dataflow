package haneum.troller.dataflow.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.dto.KMeansDto;
import haneum.troller.dataflow.dto.RegressionDto;
import haneum.troller.dataflow.repository.UserInfoRepository;
import haneum.troller.dataflow.service.ml.KMeansService;
import haneum.troller.dataflow.service.ml.RegressionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Time;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;
    private final RegressionService regressionService;
    private final KMeansService kMeansService;

    public void updateTrollPossibility(String lolName) throws JSONException, JsonProcessingException, InterruptedException {
        List<RegressionDto> regressionDtoList = regressionService.makeRegressionDtoList(lolName);
        Double sumTrollPossibility = Double.valueOf(0);
        for (RegressionDto regressionDto : regressionDtoList) {
            Double trollPossibility = Double.valueOf(regressionService.callFlaskApi(regressionDto));
//            Thread.sleep(3000);
            log.info("한판당 트롤확률:{}", trollPossibility);
            sumTrollPossibility += trollPossibility;
        }
        sumTrollPossibility = sumTrollPossibility / regressionDtoList.size();

        log.info("트롤확률업데이트:{}", lolName);
        log.info("트롤확률:{}", sumTrollPossibility);

        UserInfo userInfo = userInfoRepository.findById(lolName).get();

        UserInfo userInfoUpdatedTrollPossibility = userInfo.updateTrollPossibility(String.valueOf(sumTrollPossibility));
        userInfoRepository.save(userInfoUpdatedTrollPossibility);
    }

    public void updateCluster(String lolName) throws JSONException, JsonProcessingException, InterruptedException {
        UserInfo userInfo = userInfoRepository.findById(lolName).get();

        KMeansDto kMeansDto = kMeansService.makeKMeansDto(lolName);
        Integer cluster = kMeansService.callFlaskApi(kMeansDto);

        log.info("cluster 업데이트:{}", lolName);
        log.info("cluster:{}", cluster);

        UserInfo updateCluster = userInfo.updateCluster(String.valueOf(cluster));
        userInfoRepository.save(updateCluster);
    }
}
