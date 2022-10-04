package haneum.troller.dataflow.service.ml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haneum.troller.dataflow.common.Apimethod.CallApi;
import haneum.troller.dataflow.common.env.ApiEnv;
import haneum.troller.dataflow.common.env.Tier;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.dto.KMeansDto;
import haneum.troller.dataflow.dto.RegressionDto;
import haneum.troller.dataflow.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class KMeansService {
    private final UserInfoRepository userInfoRepository;

    public KMeansDto makeKMeansDto(String lolName) {

        UserInfo userInfo = userInfoRepository.findById(lolName).get();
        log.info("kmeans 적용:{}",lolName);


        Tier tier = null;
        switch(userInfo.getTier()) {
            case "IRON":
                tier= Tier.IRON;
                break;
            case "BRONZE":
                tier= Tier.BRONZE;
                break;
            case "SILVER":
                tier= Tier.SILVER;
                break;
            case "GOLD":
                tier= Tier.GOLD;
                break;
            case "PLATINUM":
                tier= Tier.PLATINUM;
                break;
            case "DIAMOND":
                tier= Tier.DIAMOND;
                break;
            case "MASTER":
                tier= Tier.MASTER;
                break;
            case "GRANDMASTER":
                tier= Tier.GRANDMASTER;
                break;
            case "CHALLENGER":
                tier= Tier.CHALLENGER;
                break;
        }

        return  KMeansDto.builder()
                .tier(tier.ordinal())
                .troll_possibility(userInfo.getTrollPossibility().substring(0,3))
                .build();
    }

    public Integer callFlaskApi(KMeansDto kMeansDto) throws JSONException, JsonProcessingException {

        //예외처리
        if (kMeansDto.getTroll_possibility().equals("NaN")) {
            return -1;
        }

        ObjectMapper objectMapper = new ObjectMapper();


        ResponseEntity regression = CallApi.PostIncludeObject(ApiEnv.FLASKURL, "kmeans", objectMapper.writeValueAsString(kMeansDto));

        String jsonString = regression.getBody().toString();

        JSONObject jsonObject = new JSONObject(jsonString);
        int cluster = jsonObject.getInt("cluster");


        return cluster;
    }

}
