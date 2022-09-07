package haneum.troller.dataflow.kafka.fullSearch;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FullRecordConsumer {
    @KafkaListener(topics = "full_record", groupId = "full_record_con_grp")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Consumed message : %s", message));
    }
}
