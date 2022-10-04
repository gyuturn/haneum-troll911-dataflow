package haneum.troller.dataflow.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KMeansDto {
    private Integer tier;
    private String troll_possibility;
}
