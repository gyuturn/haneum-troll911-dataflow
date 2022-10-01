package haneum.troller.dataflow.callApi;

import com.google.gson.Gson;
import haneum.troller.dataflow.common.Apimethod.CallApi;
import haneum.troller.dataflow.common.env.ApiEnv;
import haneum.troller.dataflow.dto.DateDto;
import org.json.simple.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MemberApi {
    String addUrl = "/dataflow/compare/member/date";

    public List<Long> CallMemberListDateBefore() {
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

        ResponseEntity responseEntity = CallApi.PostIncludeObject(ApiEnv.URL,addUrl, jsonToString);

        List<Long> membersId = new ArrayList<Long>();
        System.out.println(responseEntity.getBody());
        List<String> myList = new ArrayList<String>(Arrays.asList(responseEntity.getBody().toString().split(",")));

        for (String s : myList) {
            membersId.add(Long.valueOf(s));
        }

        System.out.println("membersId = " + membersId);
        return membersId;
    }

}

