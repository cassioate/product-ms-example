package br.com.tessaro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket swagger() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.tessaro"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
    	return new ApiInfoBuilder()
    			.title("API Rest - Products-ms")
    			.description("Um microservice que adiciona, pesquisa, atualiza e deleta um porduto")
    			.version("1.0.0")
    			.contact(new Contact("Cássio Aragão Tessaro", "https://www.linkedin.com/in/ctessaro/", "cassioate@gmail.com"))
    			.build();
    }
}
