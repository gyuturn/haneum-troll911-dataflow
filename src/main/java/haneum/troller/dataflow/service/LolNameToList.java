package haneum.troller.dataflow.service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LolNameToList {

    //경로 추후 클라우드에 올릴 시 문제?
    static File lolNameFile = new File("/src/main/resources/lolname-test.txt");
    static String path = System.getProperty("user.dir");


    public static List<String> readLolNameTxt() throws IOException {
        FileReader fileReader = new FileReader(path+lolNameFile);

        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String lolName = "";
        List<String> lolNames = new ArrayList<>();
        while ((lolName = bufferedReader.readLine()) != null) {
            lolNames.add(lolName);
        }
        bufferedReader.close();

        return lolNames;
    }


}
