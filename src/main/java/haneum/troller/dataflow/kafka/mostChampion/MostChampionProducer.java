package haneum.troller.dataflow.kafka.mostChampion;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MostChampionProducer {
    private static final String TOPIC = "most_champion";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MostChampionProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public GameRecordRepository gameRecordRepository;


    public void sendMostChampion(String key, String message) {
        this.kafkaTemplate.send(TOPIC, key, message);
    }
}
