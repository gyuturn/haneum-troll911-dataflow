package haneum.troller.dataflow.kafka.userInfo;

import haneum.troller.dataflow.repository.GameRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class UserInfoProducer {
    private static final String TOPIC = "user_info";
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public UserInfoProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Autowired
    public GameRecordRepository gameRecordRepository;


    public void sendUserInfo(String key, String message) {
        this.kafkaTemplate.send(TOPIC, key, message);
    }

}