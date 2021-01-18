package org.k.mybatis.extend.parameter;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import org.k.mybatis.extend.annotations.Param;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class EqualParamNode extends BaseParamNode {

    private String name = "";
    private Object value = "";
    private Param paramValue;


    @Override
    public void builder(Param paramValue, String name, Object value) {
        this.paramValue = paramValue;
        this.name = name;
        this.value = value;
    }

    @Override
    public SQLSelectQueryBlock apply(SQLSelectQueryBlock sqlSelectQuery) {

        if (paramValue.equals(Param.EQ)) {
            SQLExpr sqlExpr = SQLUtils.toSQLExpr(name + "  = '" + value+"'");
            sqlSelectQuery.addWhere(sqlExpr);
        }
        return sqlSelectQuery;
    }
}
