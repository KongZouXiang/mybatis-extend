package org.k.mybatis.extend;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();
        try {
            BlogMapper mapper = session.getMapper(BlogMapper.class);
            Blog blog1 = new Blog();
            blog1.setId(1);
            blog1.setName("张");
            List<Blog> blog = mapper.selectList(blog1);
            System.out.println(blog);
        } finally {
            session.close();
        }
    }
}
