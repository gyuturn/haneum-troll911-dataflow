//package haneum.troller.dataflow.kafka.fullSearch;
//
//
//import haneum.troller.dataflow.domain.GameRecord;
//import haneum.troller.dataflow.domain.RecordML;
//import haneum.troller.dataflow.repository.*;
//import haneum.troller.dataflow.service.GameRecordService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.common.record.Record;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.KafkaHeaders;
//import org.springframework.messaging.handler.annotation.Header;
//import org.springframework.messaging.handler.annotation.Payload;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//@Service
//@Slf4j
//public class FullRecordConsumer {
//    @Autowired
//    public GameRecordRepository gameRecordRepository;
//    @Autowired
//    public GameRecordService gameRecordService;
//    @Autowired
//    public RecordMlRepository recordMlRepository;
//
//
//
//    @KafkaListener(topics = "full_record", groupId = "full_record_con_grp")
//    public void consume( @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//                         @Payload String data) throws IOException {
//        GameRecord gameRecord = gameRecordRepository.findById(key).get();
//        gameRecordService.updateFullRecord(gameRecord,data);
//    }
//
//    @KafkaListener(topics = "full_record", groupId = "full_record_con_grp_entity")
//    public void consumeML( @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
//                         @Payload String data) throws IOException, ParseException {
//        log.info("record-ml 저장");
//        log.info("유저 닉네임:{}", key);
//        JSONParser parser = new JSONParser();
//        Object fullSearchObj = parser.parse(data);
//        JSONObject jsonObject = (JSONObject) fullSearchObj;
//
//        JSONArray gameRecord = (JSONArray) jsonObject.get("gameRecord");
//
//        for (int i = 0; i < gameRecord.size(); i++) {
//            JSONObject perGameRecord = (JSONObject) gameRecord.get(i);
//
//            //playtime
//            String playtime = perGameRecord.get("playtime").toString();
//            String playTimeMinutes = playtime.substring(0, playtime.indexOf("분"));
//
//            //win
//            Integer win=1;
//            if (perGameRecord.get("win").toString() == "false") win=0;
//
//
//            //hitdamage
//            Long hitDamageToChampion = (Long) perGameRecord.get("hitDamageToChampion");
//            double hitDamage = (double) hitDamageToChampion / 1000;
//
//            //kda
//            String kda = (String) perGameRecord.get("kda");
//            if (kda == "perfect") kda = "10.0";
//
//
//
//            RecordML recordML = RecordML.builder()
//                    .lolName(key)
//                    .csPerMinute(Double.valueOf((String)perGameRecord.get("csPerMinutes")))
//                    .kda(Double.valueOf(kda))
//                    .win(win)
//                    .playTime(Integer.valueOf(playTimeMinutes))
//                    .matchId((String) perGameRecord.get("matchId"))
//                    .gameMode((String) perGameRecord.get("gameMode"))
//                    .hitDamage(Double.valueOf(hitDamage))
//                    .build();
//
//            recordMlRepository.save(recordML);
//
//        }
//
//    }
//}
