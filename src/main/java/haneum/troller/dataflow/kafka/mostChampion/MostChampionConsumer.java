package haneum.troller.dataflow.kafka.mostChampion;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.repository.GameRecordRepository;
import haneum.troller.dataflow.service.GameRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MostChampionConsumer {
    @Autowired
    public GameRecordRepository gameRecordRepository;
    @Autowired
    public GameRecordService gameRecordService;



    @KafkaListener(topics = "most_champion", groupId = "most_champion_con_grp")
    public void consume( @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                         @Payload String data) throws IOException {
        GameRecord gameRecord = gameRecordRepository.findById(key).get();
        gameRecordService.updateMostChampion(gameRecord,data);
    }
}