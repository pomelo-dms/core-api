package cn.ikangjia.pomelo.service;

import java.util.List;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:19
 */
public interface DatabaseService {
    List<String> listDatabases(Long dataSourceId);
}
