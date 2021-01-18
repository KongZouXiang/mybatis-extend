package org.k.mybatis.extend.parameter;

import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import org.k.mybatis.extend.annotations.Param;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public interface ParamNode {

     Map<String, ParamNode> nodeHandlerMap = new HashMap<>();

     void builder(Param paramValue, String name, Object value);


     SQLSelectQueryBlock apply(SQLSelectQueryBlock sqlSelectQuery);
}
