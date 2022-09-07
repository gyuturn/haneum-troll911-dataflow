package haneum.troller.dataflow.kafka.fullSearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class FullRecordProducer {
    private static final String TOPIC = "full_record";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public FullRecordProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendFullRecordJson(String message) {
        System.out.println(String.format("Produce message : %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
