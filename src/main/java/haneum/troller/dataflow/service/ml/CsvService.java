package haneum.troller.dataflow.service.ml;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import haneum.troller.dataflow.domain.GamePerRecord;
import haneum.troller.dataflow.dto.RegressionDto;
import haneum.troller.dataflow.repository.GamePerRecordRepository;
import haneum.troller.dataflow.repository.RecordMlRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CsvService {

    private final GamePerRecordRepository gamePerRecordRepository;

    public void makeCsvForRegression(String lolName) {
        List<GamePerRecord> gamePerRecords = gamePerRecordRepository.findByLolName(lolName);

//        RegressionDto regressionDto = new RegressionDto();

//        Long sumCsPerMinute = null;
//        Float sumKda = null;
//        Long sumPlayTime = null;
//        for (GamePerRecord gamePerRecord : gamePerRecords) {
//            sumCsPerMinute += Long.valueOf(gamePerRecord.getCsPerMinutes());
//            sumKda += Float.valueOf(gamePerRecord.getKda());
//            sumPlayTime +=Long.valueOf(gamePerRecord.getPlaytime());
//
//        }


        // 파일의 row생성
        List<RegressionDto> list = new ArrayList<>();

        for (GamePerRecord gamePerRecord : gamePerRecords) {
            //playtime
            String playTimeMinutes = gamePerRecord.getPlaytime().substring(0, gamePerRecord.getPlaytime().indexOf("분"));

            RegressionDto regressionDto = RegressionDto.builder()
                    .kda(gamePerRecord.getKda())
                    .cs_per_minute(Float.valueOf(gamePerRecord.getCsPerMinutes()))
                    .hit_damage(String.valueOf(gamePerRecord.getHitDamageToChampion() / 1000))
                    .play_time(playTimeMinutes)
                    .win(gamePerRecord.getWin())
                    .build();
            list.add(regressionDto);
        }

        // 저장할 CSV파일 객체
        File csvFile = new File("test.csv");
        makeCsvFile(RegressionDto.class, csvFile, list);
    }

    public void makeCsvFile(Class<?> clazz, File csvFile, List<?> dataList) {
        try {
            CsvMapper csvMapper = new CsvMapper();
            CsvSchema csvSchema =
                    csvMapper.enable(CsvGenerator.Feature.ALWAYS_QUOTE_STRINGS)
                            .schemaFor(clazz)   // CSV 파일로 생성할 자바 객체의 클래스 정보
                            .withHeader()       // CSV 헤더 사용 여부
                            .withColumnSeparator(',')   // 컬럼 간 구분자
                            .withLineSeparator("\n");   // 개행

            ObjectWriter writer = csvMapper.writer(csvSchema);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(csvFile), "UTF-8");
            writer.writeValues(outputStreamWriter).writeAll(dataList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
