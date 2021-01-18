package org.k.mybatis.extend.parameter;

import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import org.k.mybatis.extend.annotations.Param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class ParamNodeList implements ParamNode {

    private List<ParamNode> counterList = new ArrayList();

    private final static Map<String, ParamNode> nodeHandlerMap = new HashMap<>();

    static {
        nodeHandlerMap.put("LIKE", new LikeParamNode());
        nodeHandlerMap.put("EQ", new EqualParamNode());
        nodeHandlerMap.put("ORDER_BY", new OrderByParamNode());
    }

    public static ParamNode builderParamNode(String paramName){
        return nodeHandlerMap.get(paramName);
    }
    public void add(ParamNode counter) {
        counterList.add(counter);
    }

    public List<ParamNode> getChild() {
        return counterList;
    }

    @Override
    public void builder(Param paramValue, String name, Object value) {

    }

    @Override
    public SQLSelectQueryBlock apply(SQLSelectQueryBlock sqlSelectQuery) {

        for (ParamNode counter : counterList) {
            counter.apply(sqlSelectQuery);
        }
        return sqlSelectQuery;
    }
}
