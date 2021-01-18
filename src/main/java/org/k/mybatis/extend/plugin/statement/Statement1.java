package org.k.mybatis.extend.plugin.statement;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.ParameterMode;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.k.mybatis.extend.execurtor.Execurtor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {java.sql.Connection.class,
        Integer.class})})
public class Statement1 implements Interceptor {
    Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        System.out.println("当前拦截到的对象：" + target);
        MetaObject metaObject = SystemMetaObject.forObject(target);
        String[] properties = metaObject.getGetterNames();
        // Object value = metaObject.getValue("boundSql.parameterMappings");
        // 对sql语句进行重新赋值
        Configuration configuration = (Configuration) metaObject.getValue("delegate.configuration");
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
//        Object oundSql = (Object) metaObject.getValue("delegate.getBoundSql");
        String sql = (String) metaObject.getValue("delegate.boundSql.sql");

//        metaObject.setValue("boundSql.sql", "select * from menu t where 1=1 and t.authority=?");
        // 对参数名称重新赋值
//        List list = new MyFirstPlugin().getParameterMappings(configuration);
//        Object value = metaObject.getValue("boundSql.parameterObject");
        // 获取参数值
        Map parameterObject = (Map) metaObject.getValue("parameterHandler.parameterObject");
        // 对参数值重新赋值
        String handler = new Execurtor().handler(sql, parameterObject);
        System.out.println("sql = " + handler);

        metaObject.setValue("delegate.boundSql.sql", handler );

//        parameterObject.clear();
//        Blog blog = new Blog();
//        blog.setName("1");
//        parameterObject.put("blog", blog);
//        metaObject.setValue("parameterHandler.parameterObject", parameterObject);
        // 执行目标方法
        Object proceed = invocation.proceed();
        // 返回执行后的返回值
        return proceed;
    }

    private List<ParameterMapping> getParameterMappings(Configuration configuration) {
        List<ParameterMapping> list = new ArrayList<ParameterMapping>();
        ParameterMapping parameterMapper = new ParameterMapping.Builder(configuration, "param3", Object.class)
                .mode(ParameterMode.IN).build();
        list.add(parameterMapper);
        return list;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }
}
