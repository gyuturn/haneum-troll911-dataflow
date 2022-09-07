package haneum.troller.dataflow;

import haneum.troller.dataflow.callApi.GameRecordApi;
import haneum.troller.dataflow.kafka.fullSearch.FullRecordProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DataflowApplication {

	public static void main(String[] args) {

		SpringApplication.run(DataflowApplication.class, args);
	}

}
