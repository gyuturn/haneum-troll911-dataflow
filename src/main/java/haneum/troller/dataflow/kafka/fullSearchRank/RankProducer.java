package haneum.troller.dataflow.kafka.fullSearchRank;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RankProducer {
    private static final String TOPIC = "rank_record";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public RankProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public GameRecordRepository gameRecordRepository;


    public void sendFullRecordJson(String key,String message) {
        this.kafkaTemplate.send(TOPIC,key,message);
    }
}