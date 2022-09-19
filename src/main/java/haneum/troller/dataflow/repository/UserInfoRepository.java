package haneum.troller.dataflow.repository;

import haneum.troller.dataflow.domain.GameRecord;
import haneum.troller.dataflow.domain.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo,String> {
}
