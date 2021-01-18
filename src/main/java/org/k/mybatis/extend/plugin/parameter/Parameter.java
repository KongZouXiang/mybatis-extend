package org.k.mybatis.extend.plugin.parameter;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.PreparedStatement;
import java.util.Properties;

@Intercepts({@Signature(type = ParameterHandler.class,
        method = "setParameters",
        args = {PreparedStatement.class}
)}
)
public class Parameter implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation.proceed();
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
