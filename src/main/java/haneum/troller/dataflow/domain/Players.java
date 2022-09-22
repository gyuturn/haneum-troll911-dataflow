package haneum.troller.dataflow.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "players")
public class Players {
    @Id
    private String uuid;
    private String matchId;
    private String championImg;
    private String lolName;
    private String championName;
    private String Position;
    private String team;


    @Builder
    public Players(String uuid, String matchId, String championImg, String lolName, String championName, String position, String team) {
        this.uuid = uuid;
        this.matchId = matchId;
        this.championImg = championImg;
        this.lolName = lolName;
        this.championName = championName;
        Position = position;
        this.team = team;
    }
}
