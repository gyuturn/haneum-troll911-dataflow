package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GameRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRecordRepository extends JpaRepository<GameRecord,String> {
}