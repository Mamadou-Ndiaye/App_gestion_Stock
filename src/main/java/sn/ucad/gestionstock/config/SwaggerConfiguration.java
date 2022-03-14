package sn.ucad.gestionstock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static sn.ucad.gestionstock.utils.Constatnts.APP_ROOT;

@Configuration
//@EnableSwagger2
public class SwaggerConfiguration {

    public static final String AUTHORIZATION_HEADER = "Authorization";

   /* @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("sn.ucad.gestionstock"))  // .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant(APP_ROOT + "/**"))  // .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        return new ApiInfo("Gsetion Stock Rest APIs",
                "APIs for Gestion Stock.",
                "1.0",
                "Terms of service",
                new Contact("Mamadou Ndiaye", "www.org.com", "ndiamamadou@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }*/

    /*private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API",
                "Spring Boot REST API for Online Store",
                "1.0",
                "Terms of service",
                " new Contact('John Thompson', 'https://springframework.guru/about/', 'john@springfrmework.guru')",
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }*/
    private ApiInfo apiInfo() {
        return new ApiInfo("Gestion de Stock Rest APIs",
                "APIs for Gestion de Stock.",
                "1.0",
                "Terms of service",
                new Contact("Mamadou Ndiaye", "www.mamadou.ndiayeherokuapp.com", "ndiamamadou@gmail.com"),
                "License of API",
                "API license URL",
                Collections.<VendorExtension>emptyList());
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .groupName("REST API v1")
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.<SecurityScheme>singletonList(apiKey()))
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("sn.ucad.gestionstock"))
                .paths(PathSelectors.ant(APP_ROOT + "/**"))
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey(AUTHORIZATION_HEADER, "JWT", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference(AUTHORIZATION_HEADER, authorizationScopes));
    }
}