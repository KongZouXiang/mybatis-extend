package org.k.mybatis.extend.annotations;


import java.lang.annotation.*;

/**
 * @author Administrator
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FieldParam {


    Param value();

    /**
     * 表名获取表别名
     */
    String tableName() default "";

    /**
     * 其否启动
     */
    boolean enable() default true;
}

