package com.crud.restapi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
public class SwaggerConfig implements WebMvcConfigurer {

        @Bean
        public Docket api() {
                return new Docket(DocumentationType.SWAGGER_2).select()
                                .apis(RequestHandlerSelectors.basePackage("com.crud.restapi"))
                                .paths(PathSelectors.regex("/.*"))
                                .build().apiInfo(apiInfoMetaData());
        }

        private ApiInfo apiInfoMetaData() {

                return new ApiInfoBuilder().title("CRUD Users Managment")
                                .description("API Endpoint Decoration")
                                .contact(new Contact("Serge KOKOUA",
                                                "https://www.linkedin.com/in/serge-th%C3%A9ophile-kokoua/",
                                                "kokouaserge3@gmail.com"))
                                .license("Apache 2.0")
                                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                                .version("1.0.0")
                                .build();
        }

        @Override
        public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/docApi/v2/api-docs", "/v2/api-docs");
                registry.addRedirectViewController("/docApi/swagger-resources/configuration/ui",
                                "/swagger-resources/configuration/ui");
                registry.addRedirectViewController("/docApi/swagger-resources/configuration/security",
                                "/swagger-resources/configuration/security");
                registry.addRedirectViewController("/docApi/swagger-resources", "/swagger-resources");
        }

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/docApi/swagger-ui.html**")
                                .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
                registry.addResourceHandler("/docApi/webjars/**")
                                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }

}
