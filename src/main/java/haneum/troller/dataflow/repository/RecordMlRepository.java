package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.RecordML;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordMlRepository extends JpaRepository<RecordML,String> {
}
