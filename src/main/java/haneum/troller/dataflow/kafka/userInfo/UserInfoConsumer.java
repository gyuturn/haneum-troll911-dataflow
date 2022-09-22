package haneum.troller.dataflow.kafka.userInfo;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.repository.UserInfoRepository;
import haneum.troller.dataflow.service.GameRecordService;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Slf4j
public class UserInfoConsumer {
    @Autowired
    public GameRecordRepository gameRecordRepository;
    @Autowired
    public GameRecordService gameRecordService;
    @Autowired
    public UserInfoRepository userInfoRepository;



    @KafkaListener(topics = "user_info", groupId = "user_info_con_grp")
    public void consumerToJson( @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                         @Payload String data) throws IOException {
        GameRecord gameRecord = gameRecordRepository.findById(key).get();
        gameRecordService.updateUserInfo(gameRecord,data);
    }

//    @KafkaListener(topics = "user_info", groupId = "user_info_con_grp_entity")
//    public void consumeToEntity(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//                                @Payload String data) throws JSONException {
//        JSONObject jsonObject = new JSONObject(data);
//
//
//        Optional<UserInfo> userInfoById = userInfoRepository.findById(key);
//        if (userInfoById.isEmpty()) {
//            log.info("userConsumer - userInfo 새로 저장 - db에 존재하지 않음");
//            log.info("저장 유저:{}", key);
//            UserInfo userInfo = UserInfo.builder()
//                    .encryptedLolName(jsonObject.getString("encryptedLolName"))
//                    .icon(jsonObject.getString("icon"))
//                    .level(jsonObject.getString("level"))
//                    .name(jsonObject.getString("name"))
//                    .lose(jsonObject.getString("lose"))
//                    .point(jsonObject.getString("point"))
//                    .ranking(jsonObject.getString("rank"))
//                    .tier(jsonObject.getString("tier"))
//                    .win(jsonObject.getString("win"))
//                    .winRate(jsonObject.getString("winRate"))
//                    .trollPossibility(jsonObject.getString("trollPossibility"))
//                    .build();
//
//            userInfoRepository.save(userInfo);
//        }
//        else{
//            log.info("userConsumer - userInfo Update");
//            log.info("Updated 유저:{}", key);
//            UserInfo userInfo = userInfoById.get().updateUserInfo(
//                    jsonObject.getString("name"),
//                    jsonObject.getString("encryptedLolName"),
//                    jsonObject.getString("tier"),
//                    jsonObject.getString("rank"),
//                    jsonObject.getString("point"),
//                    jsonObject.getString("winRate"),
//                    jsonObject.getString("win"),
//                    jsonObject.getString("lose"),
//                    jsonObject.getString("icon"),
//                    jsonObject.getString("level"),
//                    jsonObject.getString("trollPossibility")
//            );
//            userInfoRepository.save(userInfo);
//        }
//    }
}