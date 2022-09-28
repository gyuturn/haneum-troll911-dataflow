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
    private String hit_damage;
    private String kda;
    private String play_time;
    private Boolean win;
}
