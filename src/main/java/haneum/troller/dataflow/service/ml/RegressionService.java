package haneum.troller.dataflow.service.ml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haneum.troller.dataflow.common.Apimethod.CallApi;
import haneum.troller.dataflow.common.env.ApiEnv;
import haneum.troller.dataflow.domain.GamePerRecord;
import haneum.troller.dataflow.dto.RegressionDto;
import haneum.troller.dataflow.repository.GamePerRecordRepository;
import jep.Jep;
import jep.JepException;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegressionService {
    private final GamePerRecordRepository gamePerRecordRepository;




    public  List<RegressionDto> makeRegressionDtoList(String lolName) {
        /**
         * gamemode가 Classic인것만 선택
         * gamerecords 사이즈 10개로 고정
         */
        List<GamePerRecord> gamePerRecords = gamePerRecordRepository.findByNameAndGameModeOrderByGameStartTimeStampDesc(lolName,"CLASSIC");

        int listSize = gamePerRecords.size();
        if (listSize >= 10) {
            listSize = 10;
        }

        List<RegressionDto> regressionDtoList = new ArrayList<>();

        for (int i=0; i<listSize;i++) {
            //playtime 수정
            String playTimeMinutes = gamePerRecords.get(i).getPlaytime().substring(0, gamePerRecords.get(i).getPlaytime().indexOf("분"));

            String kda = gamePerRecords.get(i).getKda();
            if(kda.equals("perfect")) kda = "5.0";


            RegressionDto regressionDto = RegressionDto.builder()
                    .kda(Float.valueOf(kda))
                    .cs_per_minute(Float.valueOf(gamePerRecords.get(i).getCsPerMinutes()))
                    .hit_damage(Float.valueOf((gamePerRecords.get(i).getHitDamageToChampion() / 1000)))
                    .play_time(Integer.valueOf(playTimeMinutes))
                    .win(gamePerRecords.get(i).getWin())
                    .build();
            regressionDtoList.add(regressionDto);
        }

        return regressionDtoList;
    }

    public String callFlaskApi(RegressionDto regressionDto) throws JSONException, JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        ResponseEntity regression = CallApi.PostIncludeObject(ApiEnv.FLASKURL, "regression", objectMapper.writeValueAsString(regressionDto));

        String jsonString = regression.getBody().toString();

        JSONObject jsonObject = new JSONObject(jsonString);
        String troll_possibility = jsonObject.getString("troll_possibility");

        return troll_possibility;
    }



}
