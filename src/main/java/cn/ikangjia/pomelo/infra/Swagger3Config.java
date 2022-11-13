package cn.ikangjia.pomelo.infra;

import io.swagger.models.auth.In;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/11 15:20
 */
@Configuration
public class Swagger3Config {
    private final Swagger3Properties properties;

    public Swagger3Config(Swagger3Properties properties) {
        this.properties = properties;
    }

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .enable(properties.getEnable() != null && properties.getEnable())
                .groupName(properties.getGroupName())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.ikangjia.pomelo.api.controller"))
                .paths(PathSelectors.any())
                .build()
                // 授权信息设置，必要的header token等认证信息
                .securitySchemes(securitySchemes())
                // 授权信息全局应用
                .securityContexts(securityContexts());
    }

    /**
     * 设置授权信息
     */
    private List<SecurityScheme> securitySchemes() {
        ApiKey apiKey = new ApiKey("Token", "token", In.HEADER.toValue());
        return Collections.singletonList(apiKey);
    }

    /**
     * 授权信息全局应用
     */
    private List<SecurityContext> securityContexts() {
        return Collections.singletonList(
                SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(
                                        new SecurityReference(
                                                "Token",
                                                new AuthorizationScope[]{new AuthorizationScope("global", "")}
                                        )
                                )
                        )
                        .build()
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .license(properties.getLicense())
                .licenseUrl(properties.getLicenseUrl())
                .version(properties.getVersion())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getContact().get("email"))
                .contact(new Contact(
                                properties.getContact().get("name"),
                                properties.getContact().get("url"),
                                properties.getContact().get("email")
                        )
                )
                .build();
    }
}
