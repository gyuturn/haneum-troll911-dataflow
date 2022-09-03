package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GameRecordJson;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRecordJsonRepository extends JpaRepository<GameRecordJson,Long> {
}
