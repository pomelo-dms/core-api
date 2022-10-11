package cn.ikangjia.pomelo.core.entity;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/9 14:35
 */
@Data
public class DbEntity {
    private String dbName;
    private String collate;
    private String character;
}
