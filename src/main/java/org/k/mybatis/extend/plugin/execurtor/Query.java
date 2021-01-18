package org.k.mybatis.extend.plugin.execurtor;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.SimpleExecutor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */

@Intercepts({@Signature(type = Executor.class,
        method = "query",
//        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,CacheKey.class,BoundSql.class}
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)}
)
public class Query implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        Object[] args = invocation.getArgs();
        MappedStatement arg = (MappedStatement) args[0];
        Object arg1 = args[1];
        RowBounds arg2 = (RowBounds) args[2];
        ResultHandler arg3 = (ResultHandler) args[3];
        Object proceed = invocation.proceed();
        invocation.getTarget();

        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        //0.sql参数获取
        Object parameter = null;
        if (invocation.getArgs().length > 1) {
            parameter = invocation.getArgs()[1];
        }

        //1.获取sqlId
        String sqlId = mappedStatement.getId();
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);

        Configuration configuration = mappedStatement.getConfiguration();

        //获取真实的sql语句
        return proceed;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
