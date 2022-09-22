package haneum.troller.dataflow.kafka.mostChampionRank;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MostChampionRankProducer {
    private static final String TOPIC = "most_champion_rank";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public MostChampionRankProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public GameRecordRepository gameRecordRepository;


    public void sendMostChampion(String key, String message) {
        this.kafkaTemplate.send(TOPIC, key, message);
    }
}