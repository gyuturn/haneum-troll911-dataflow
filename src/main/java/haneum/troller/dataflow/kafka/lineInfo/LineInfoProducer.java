package haneum.troller.dataflow.kafka.lineInfo;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LineInfoProducer {
    private static final String TOPIC = "line_info";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public LineInfoProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendLineInfo(String key,String message) {
        this.kafkaTemplate.send(TOPIC,key,message);
    }
}
