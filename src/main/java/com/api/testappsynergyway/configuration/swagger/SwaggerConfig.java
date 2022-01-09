package com.api.testappsynergyway.configuration.swagger;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.not;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(with())
                .paths(without())
                .build();
    }

    private Predicate<String> with() {
        return or(regex("/.*"), regex("/.*"));
    }

    private Predicate<String> without() {
        return not(regex("/error.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("test api")
                .version("1.0")
                .build();
    }

}