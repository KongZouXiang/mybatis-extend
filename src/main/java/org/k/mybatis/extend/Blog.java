package org.k.mybatis.extend;

import org.k.mybatis.extend.annotations.Param;
import org.k.mybatis.extend.annotations.FieldParam;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class Blog {

    @FieldParam(Param.ORDER_BY)
    private Integer id;

    @FieldParam(Param.EQ)
    private String name;

//    private
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
