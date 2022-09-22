package haneum.troller.dataflow.domain;

import haneum.troller.dataflow.common.timestamp.BaseTimeEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user_info")
public class UserInfo  {
    @Id
    private String name;
    private String encryptedLolName;
    private String tier;
    private String ranking;
    private String point;
    private String winRate;
    private String win;
    private String lose;
    private String icon;
    private String level;
    private String trollPossibility;

//    @CreatedDate
//    private LocalDateTime createDate;
//
//    @LastModifiedDate
//    private LocalDateTime modifiedDate;

    @Builder
    public UserInfo(String name, String encryptedLolName, String tier, String ranking, String point, String winRate, String win, String lose, String icon, String level, String trollPossibility, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.name = name;
        this.encryptedLolName = encryptedLolName;
        this.tier = tier;
        this.ranking = ranking;
        this.point = point;
        this.winRate = winRate;
        this.win = win;
        this.lose = lose;
        this.icon = icon;
        this.level = level;
        this.trollPossibility = trollPossibility;
    }






}
