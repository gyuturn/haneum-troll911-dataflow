//package haneum.troller.dataflow.domain;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@Entity
//@Table(name = "game_record_json")
//public class GameRecordJson {
//    @Id
//    @GeneratedValue
//    @Column(name = "game_record_json_id")
//    private Long id;
//
//
//    @Column(name = "json", columnDefinition = "longtext")
//    @Lob
//    private String twentyGameRecord;
//
//    @Builder
//    public GameRecordJson(Long id, String twentyGameRecord) {
//        this.id = id;
//        this.twentyGameRecord = twentyGameRecord;
//    }
//
//
//
//}
