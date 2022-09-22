package haneum.troller.dataflow.kafka.fullSearchRank;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FullRecordRankProducer {
    private static final String TOPIC = "full_record_rank";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public FullRecordRankProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public GameRecordRepository gameRecordRepository;


    public void sendFullRecordJson(String key,String message) {
        this.kafkaTemplate.send(TOPIC,key,message);
    }
}