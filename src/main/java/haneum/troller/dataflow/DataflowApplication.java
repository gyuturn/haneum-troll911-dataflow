package haneum.troller.dataflow;

import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import haneum.troller.dataflow.scheduler.KafkaScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@SpringBootApplication
@EnableScheduling
@EnableJpaAuditing
public class DataflowApplication {


	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(DataflowApplication.class, args);
	}

}
