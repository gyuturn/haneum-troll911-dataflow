package haneum.troller.dataflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegressionDto {
    private Float cs_per_minute;
    private Float hit_damage;
    private Float kda;
    private Integer play_time;
    private Boolean win;

    @Override
    public String toString() {
        return "{" +
                "cs_per_minute:" + cs_per_minute +
                ", hit_damage:" + hit_damage +
                ", kda:" + kda +
                ", play_time:" + play_time +
                ", win:" + win +
                '}';
    }
}
