package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/11/1 13:59
 */
@Data
public class CharacterSetEntity {
    private String characterSet;
    private String defaultCollation;
    private String description;
    private String maxLen;
}
