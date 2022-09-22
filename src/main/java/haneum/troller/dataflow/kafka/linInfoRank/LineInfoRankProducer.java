package haneum.troller.dataflow.kafka.linInfoRank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LineInfoRankProducer {
    private static final String TOPIC = "line_info_rank";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public LineInfoRankProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendLineInfo(String key,String message) {
        this.kafkaTemplate.send(TOPIC,key,message);
    }
}
