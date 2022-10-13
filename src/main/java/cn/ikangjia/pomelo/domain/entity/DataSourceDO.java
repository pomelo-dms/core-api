package cn.ikangjia.pomelo.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/10 12:37
 */
@Data
@TableName("t_datasource")
public class DataSourceDO {
    private long id;
    private String name;
    private String type;

    private String host;
    private String port;

    private String username;
    private String password;

    /*
     * 数据源描述
     */
    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /*
     * 是否启用，1-启用，2-禁用
     */
    private boolean enabled;

    /*
     * 是否逻辑删除，1-删除，0-未删除
     */
    @TableField("is_deleted")
    private boolean deleted;
}
