package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GamePerRecord;
import haneum.troller.dataflow.domain.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GamePerRecordRepository extends JpaRepository<GamePerRecord, String> {
    List<GamePerRecord> findByName(String Name);

    List<GamePerRecord> findByNameAndGameMode(String name, String gameMode);

    List<GamePerRecord> findByNameAndGameModeOrderByGameStartTimeStampDesc(String name, String gameMode);
}

