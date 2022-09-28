package haneum.troller.dataflow.domain;

import haneum.troller.dataflow.common.jpa.StringListConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "game_per_record")
public class GamePerRecord {

    @Id
    private String uuid;
    private String name;
    private String death;
    private Integer playtimeMinutes;
    private String csPerMinutes;
    private String playtime;
    private String semiRuneImg;
    private String semiRuneNumber;
    private String primaryRune;
    private String spell2img;
    private String championUI;
    private Boolean win;
    private String spell1;
    private Integer spellNumber1;
    private String spell1img;
    private String spell2;
    private Integer spellNumber2;
    private String matchId;
    private Integer hitDamageToChampion;
    private String averageTier;
    private String semiRune;
    private String visionWard;

//
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Players> players;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "json")
    private List<Players> players;

    @Convert(converter = StringListConverter.class)
    @Column(columnDefinition = "json")
    private List<Item> itemArray;
//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Item> itemArray;


    private String kda;
    private Integer primaryRuneNumber;
    private String killed;
    private String assist;
    private String killRate;
    private String championLevel;
    private Integer gameStartTimeStamp;
    private String cs;
    private String championName;
    private String lastPlayTime;
    private String primaryRuneImg;
    private String gameMode;

    @Builder
    public GamePerRecord(String uuid, String name, String death, Integer playtimeMinutes, String csPerMinutes, String playtime, String semiRuneImg, String semiRuneNumber, String primaryRune, String spell2img, String championUI, Boolean win, String spell1, Integer spellNumber1, String spell1img, String spell2, Integer spellNumber2, String matchId, Integer hitDamageToChampion, String averageTier, String semiRune, String visionWard, List<Players> players, List<Item> itemArray, String kda, Integer primaryRuneNumber, String killed, String assist, String killRate, String championLevel, Integer gameStartTimeStamp, String cs, String championName, String lastPlayTime, String primaryRuneImg, String gameMode) {
        this.uuid = uuid;
        this.name = name;
        this.death = death;
        this.playtimeMinutes = playtimeMinutes;
        this.csPerMinutes = csPerMinutes;
        this.playtime = playtime;
        this.semiRuneImg = semiRuneImg;
        this.semiRuneNumber = semiRuneNumber;
        this.primaryRune = primaryRune;
        this.spell2img = spell2img;
        this.championUI = championUI;
        this.win = win;
        this.spell1 = spell1;
        this.spellNumber1 = spellNumber1;
        this.spell1img = spell1img;
        this.spell2 = spell2;
        this.spellNumber2 = spellNumber2;
        this.matchId = matchId;
        this.hitDamageToChampion = hitDamageToChampion;
        this.averageTier = averageTier;
        this.semiRune = semiRune;
        this.visionWard = visionWard;
        this.players = players;
        this.itemArray = itemArray;
        this.kda = kda;
        this.primaryRuneNumber = primaryRuneNumber;
        this.killed = killed;
        this.assist = assist;
        this.killRate = killRate;
        this.championLevel = championLevel;
        this.gameStartTimeStamp = gameStartTimeStamp;
        this.cs = cs;
        this.championName = championName;
        this.lastPlayTime = lastPlayTime;
        this.primaryRuneImg = primaryRuneImg;
        this.gameMode = gameMode;
    }










}
