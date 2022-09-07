package haneum.troller.dataflow.callApi;

import haneum.troller.dataflow.common.Apimethod.CallApi;
import org.springframework.http.ResponseEntity;

public class GameRecordApi {

    public static String fullRecordGameRecord(String lolName){
        String addUrl = "/search/user/gameRecord"+"?lolName="+lolName;
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

}
