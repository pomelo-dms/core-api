package cn.ikangjia.pomelo.api.vo;

import lombok.Data;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/18 21:24
 */
@Data
public class TreeVO {
    private String nodeKey;
    private String labelName;
    private Integer level;
    private boolean leaf;
    private List<TreeVO> children;
}
