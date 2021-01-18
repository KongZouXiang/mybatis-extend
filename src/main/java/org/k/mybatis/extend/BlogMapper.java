package org.k.mybatis.extend;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public interface BlogMapper {

/*    @Select("select * from blog where   <if test=\"name != null\">\n" +
            "    AND title like #{name}\n" +
            "  </if> ")*/
    List<Blog> selectList(@Param("blog") Blog blog);
}
