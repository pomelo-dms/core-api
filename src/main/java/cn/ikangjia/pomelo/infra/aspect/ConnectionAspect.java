package cn.ikangjia.pomelo.infra.aspect;

import cn.ikangjia.pomelo.core.mysql.JdbcThreadLocal;
import cn.ikangjia.pomelo.core.mysql.MySQLDataSourceUtil;
import cn.ikangjia.pomelo.domain.entity.DataSourceDO;
import cn.ikangjia.pomelo.domain.mapper.DataSourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author kangJia
 * @email ikangjia.cn@outlook.com
 * @since 2022/10/13 15:09
 */
@Slf4j
@Aspect
@Component
public class ConnectionAspect {

    private final JdbcThreadLocal jdbcThreadLocal;

    private final DataSourceMapper dataSourceMapper;

    public ConnectionAspect(JdbcThreadLocal jdbcThreadLocal, DataSourceMapper dataSourceMapper) {
        this.jdbcThreadLocal = jdbcThreadLocal;
        this.dataSourceMapper = dataSourceMapper;
    }

    @Pointcut("execution(public * cn.ikangjia.pomelo.manager.impl..*.*(..))")
    public void pointcut(){}

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("obtain connection...");
        System.out.println(joinPoint);
        DataSourceDO dataSourceDO = dataSourceMapper.selectById(Long.valueOf(String.valueOf(joinPoint.getArgs()[0])));
        System.out.println(dataSourceDO);
        Connection connection = null;
        try {
            connection = MySQLDataSourceUtil.getConnection(dataSourceDO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        this.jdbcThreadLocal.setContext(connection);
    }

    @After("pointcut()")
    public void doAfter() {
        log.info("close and remove connection...");
        this.jdbcThreadLocal.remove();
    }
}
