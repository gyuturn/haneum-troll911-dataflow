package haneum.troller.dataflow.callApi;

import haneum.troller.dataflow.common.Apimethod.CallApi;
import org.springframework.http.ResponseEntity;

public class GameRecordApi {

    public static String fullRecordGameRecord(String lolName){
        String addUrl = "/dataflow/user/gameRecordMachineLearning"+"?lolName="+lolName;
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String fullRecordGameRecordRank(String lolName){
        String addUrl = "/dataflow/user/gameRecordMachineLearning"+"?lolName="+lolName+"&count=20&type=true";
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String lineInfo(String lolName){
        String addUrl = "/dataflow/user/lineMachineLearning"+"?lolName="+lolName;
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String lineInfoRank(String lolName){
        String addUrl = "/dataflow/user/lineMachineLearning"+"?lolName="+lolName+"&count=20&type=true";
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String mostChampion(String lolName){
        String addUrl = "/dataflow/user/mostMachineLearning"+"?lolName="+lolName;
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String mostChampionRank(String lolName){
        String addUrl = "/dataflow/user/mostMachineLearning"+"?lolName="+lolName+"&count=20&type=true";
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }

    public static String userInfo(String lolName){
        String addUrl = "/dataflow/user/infoMachineLearning"+"?lolName="+lolName;
        ResponseEntity responseEntity = CallApi.GetIncludeParameter(addUrl);
        return responseEntity.getBody().toString();
    }



}
