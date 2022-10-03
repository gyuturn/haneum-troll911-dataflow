package haneum.troller.dataflow.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema
@Builder
public class LolNameDto {
    public String lolName;
}
