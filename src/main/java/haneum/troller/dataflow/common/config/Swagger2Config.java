package haneum.troller.dataflow.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Swagger2Config {

    @Bean
    public GroupedOpenApi SignUpApi() {
        return GroupedOpenApi.builder()
                .group("userRecord-dataflow")
                .pathsToMatch("/api/dataflow/record/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("troller-DATAFLOW MSA API")
                        .description("troller -dataflow -API명세")
                        .version("v0.0.1"));
    }
}
