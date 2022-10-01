package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GamePerRecord;
import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
    List<UserInfo> findByTrollPossibility(String trollPossibility);

}
