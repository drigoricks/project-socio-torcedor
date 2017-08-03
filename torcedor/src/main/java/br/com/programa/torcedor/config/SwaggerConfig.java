package br.com.programa.torcedor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.programa.torcedor.TorcedorApplication;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@ComponentScan(basePackageClasses = TorcedorApplication.class)
public class SwaggerConfig {
    
    private static final String PACKAGE_BASE = "br.com.programa.torcedor.controller";
    
    @Bean
    public Docket swaggerInit() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(PACKAGE_BASE))
                .paths(PathSelectors.any()).build().apiInfo(this.apiInfo()).useDefaultResponseMessages(false);
    }
    
    private ApiInfo apiInfo() {
        return new ApiInfo("torcedor", "Serviço Rest para Administracao de torcedor e campanha", null, null,
                new Contact("ADMINISTRADOR", null, "torcedor@projeto.com.br"), null, null);
    }

}
