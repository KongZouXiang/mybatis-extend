package org.k.mybatis.extend.plugin.execurtor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.util.Properties;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class UpdateExecutorPlugin implements ExecutorPlugin {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return invocation;
    }

    @Override
    public Object plugin(Object target) {
        return target;
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
