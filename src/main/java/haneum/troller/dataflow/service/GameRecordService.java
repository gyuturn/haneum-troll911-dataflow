package haneum.troller.dataflow.service;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.repository.GameRecordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GameRecordService {

    private final GameRecordRepository gameRecordRepository;


    @Transactional(readOnly = false)
    public GameRecord updateLineInfo(GameRecord gameRecord,String lineInfo) {
        gameRecord.updateLineInfo(lineInfo);
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }

    @Transactional(readOnly = false)
    public GameRecord updateLineInfoRank(GameRecord gameRecord,String lineInfoRank) {
        gameRecord.updateLineInfoRank(lineInfoRank);
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }

    @Transactional(readOnly = false)
    public GameRecord updateFullRecord(GameRecord gameRecord,String fullRecord) {
        gameRecord.updateFullRecord(fullRecord);
        gameRecordRepository.save(gameRecord);
        return  gameRecord;
    }


    @Transactional(readOnly = false)
    public GameRecord updateFullRecordRank(GameRecord gameRecord,String fullRecordRank) {
        gameRecord.updateFullRecordRank(fullRecordRank);
        gameRecordRepository.save(gameRecord);
        return  gameRecord;
    }

    @Transactional(readOnly = false)
    public GameRecord updateMostChampion(GameRecord gameRecord,String mostChampion) {
        gameRecord.updateMostChampion(mostChampion);
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }

    @Transactional(readOnly = false)
    public GameRecord updateMostChampionRank(GameRecord gameRecord,String updateMostChampionRank) {
        gameRecord.updateMostChampionRank(updateMostChampionRank);
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }

    @Transactional(readOnly = false)
    public GameRecord updateUserInfo(GameRecord gameRecord,String userInfo) {
        gameRecord.updateUserInfo(userInfo);
        gameRecordRepository.save(gameRecord);
        return gameRecord;
    }
}
