package cn.ikangjia.pomelo.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/11 15:20
 */
@Configuration
@EnableOpenApi
public class Swagger3Config {
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(true)
                .groupName("Pomelo-dms")
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ikangjia.pomelo.api.controller"))
//                .paths(PathSelectors.ant("/controller/**"))
                .build();
    }


//    @SuppressWarnings("all")
    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("我是 Title")
                .license("MIT")
                .licenseUrl("")
                .version("v0.1")
                .description("我是描述")
                .termsOfServiceUrl("ikangjia.cn@outlook.com")
                .contact(new Contact("xx", "http://xxx/xxx", "xxx@outlook.com"))
                .build();
    }
}
