package cn.ikangjia.pomelo.api.query;

import lombok.Data;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/5 10:06
 */
@Data
public class PageQuery {
    private Integer pageNum;
    private Integer pageSize;
    private String keyword;
}
