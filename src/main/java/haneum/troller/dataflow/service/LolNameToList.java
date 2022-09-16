package haneum.troller.dataflow.service;

import haneum.troller.dataflow.common.env.ApiEnv;
import haneum.troller.dataflow.common.s3.AwsS3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LolNameToList {


    //경로 추후 클라우드에 올릴 시 문제?
    static File lolNameFile = new File("src/main/resources/lolname-test.txt");
//    static String path = System.getProperty("user.dir");


    public static List<String> readLolNameTxt() throws IOException {

        //s3사용

        //ResTemplate 생성
        RestTemplate restTemplate = new RestTemplate();

        //헤더 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", "*/*");
        headers.add("Content-Type", "application/json;charset=UTF-8");

        //url 생성
        URI url = URI.create("https://troller-bucket.s3.ap-northeast-2.amazonaws.com/lolname-test.txt");
        RequestEntity<String> req = new RequestEntity<>(headers, HttpMethod.GET, url);
        String body = restTemplate.exchange(req, String.class).getBody();
        URLEncoder.encode(body, "utf-8");

        final ByteArrayInputStream is = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        String lolName = "";
        List<String> lolNames = new ArrayList<>();
        while ((lolName = bufferedReader.readLine()) != null) {
            lolNames.add(lolName);
        }
        bufferedReader.close();

        return lolNames;
    }


}
