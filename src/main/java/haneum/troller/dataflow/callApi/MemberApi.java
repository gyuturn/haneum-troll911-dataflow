package haneum.troller.dataflow.callApi;

import com.google.gson.Gson;
import haneum.troller.dataflow.common.Apimethod.CallApi;
import haneum.troller.dataflow.dto.DateDto;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;


public class MemberApi {
    String addUrl = "/dataflow/compare/member/date";

    public void CallMemberListDateBefore() {
        LocalDateTime now = LocalDateTime.now();


        Gson gson = new Gson();
        String jsonToString = gson.toJson(DateDto.builder()
                .year(now.getYear())
                .month(now.getMonthValue())
                .day(now.getDayOfMonth())
                .hour(now.getHour())
                .minute(now.getMinute())
                .second(now.getSecond())
                .build());

        ResponseEntity responseEntity = CallApi.PostIncludeObject(addUrl, jsonToString);
        System.out.println(responseEntity.getBody());
    }

}

