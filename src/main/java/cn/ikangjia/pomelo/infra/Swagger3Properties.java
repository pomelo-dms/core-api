package cn.ikangjia.pomelo.infra;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/12 16:25
 */
@Data
@ConfigurationProperties(prefix = "swagger3")
public class Swagger3Properties {
    private Boolean enable;
    private String groupName;
    private String title;
    private String license;
    private String licenseUrl;
    private String version;
    private String description;
    private Map<String, String> contact;
}
