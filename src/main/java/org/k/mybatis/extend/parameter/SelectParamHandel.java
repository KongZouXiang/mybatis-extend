package org.k.mybatis.extend.parameter;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import org.apache.ibatis.binding.MapperMethod;
import org.k.mybatis.extend.annotations.Param;
import org.k.mybatis.extend.annotations.FieldParam;

import java.lang.reflect.Field;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class SelectParamHandel {

    private String sql;
    private Object parameter;
    private SQLSelectQueryBlock sqlSelectQuery;





    public SelectParamHandel(String sql, Object parameter, SQLSelectQueryBlock sqlSelectQuery) {
        this.sql = sql;
        this.parameter = parameter;
        this.sqlSelectQuery = sqlSelectQuery;

    }


    public String handler() {

        ParamNodeList paramNodeList = new ParamNodeList();
        Class<?> aClass = this.parameter.getClass();
        MapperMethod.ParamMap parameter = (MapperMethod.ParamMap) this.parameter;
        parameter.forEach((k, v) -> {
            Class<?> aClass1 = v.getClass();

            Field[] declaredFields = aClass1.getDeclaredFields();
            for (Field declaredField : declaredFields) {

                FieldParam annotation = declaredField.getAnnotation(FieldParam.class);
                if (annotation != null) {
                    String name = declaredField.getName();
                    Object o = null;

                    declaredField.setAccessible(true);
                    try {
                        Param paramValue = annotation.value();
                        o = declaredField.get(v);
                        ParamNode counter = ParamNodeList.builderParamNode(paramValue.name());
                        counter.builder(paramValue, name, o);
                        paramNodeList.add(counter);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }


                }

            }

        });
        paramNodeList.apply(sqlSelectQuery);
        return SQLUtils.toSQLString(sqlSelectQuery);

    }
}
