package haneum.troller.dataflow.domain;

import haneum.troller.dataflow.common.timestamp.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "record_ml")
public class RecordML extends BaseTimeEntity {

    @Id
    private String matchId;

    private String lolName;

    private Double hitDamage;

    private  Integer win;

    private String kda;

    private String csPerMinute;

    private Integer playTime;

    private String gameMode;

    @CreatedDate
    private LocalDateTime createDate;



    @Builder
    public RecordML(String lolName, Double hitDamage, Integer win, String kda, String csPerMinute, Integer playTime, String matchId,String gameMode) {
        this.lolName = lolName;
        this.hitDamage = hitDamage;
        this.win = win;
        this.kda = kda;
        this.csPerMinute = csPerMinute;
        this.playTime = playTime;
        this.matchId = matchId;
        this.gameMode=gameMode;
    }
}
