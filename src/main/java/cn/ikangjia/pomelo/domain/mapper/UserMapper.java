package cn.ikangjia.pomelo.domain.mapper;

import cn.ikangjia.pomelo.domain.entity.UserDO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/11 15:03
 */
@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
}
