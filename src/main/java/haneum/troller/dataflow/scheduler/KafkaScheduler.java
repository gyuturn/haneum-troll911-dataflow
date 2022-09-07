package haneum.troller.dataflow.scheduler;

import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KafkaScheduler {
    @Autowired
    public FullRecordProducer fullRecordProducer;

    @Scheduled(fixedRate=1000)
    public void produceFullSearchScheduler(){
        String fullGameRecord = GameRecordApi.fullRecordGameRecord("hideonbush");
        fullRecordProducer.sendFullRecordJson(fullGameRecord);
    }

}
