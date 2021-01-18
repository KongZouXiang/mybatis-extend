package org.k.mybatis.extend.execurtor;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.SQLSelect;
import com.alibaba.druid.sql.ast.statement.SQLSelectQueryBlock;
import com.alibaba.druid.sql.ast.statement.SQLSelectStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlDeleteStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlUpdateStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import org.k.mybatis.extend.parameter.SelectParamHandel;

import java.io.StringWriter;

/**
 * @author 孔邹祥
 * @date ${DATE} ${TIME}
 */
public class Execurtor {

    public String handler(String sql, Object parameter) {
        String s = "";
        // 解析sql
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement stmt = parser.parseStatement();
        if (stmt instanceof SQLSelectStatement) {
            SQLSelect sqlSelect = ((SQLSelectStatement) stmt).getSelect();

            SQLSelectQueryBlock sqlSelectQuery = (SQLSelectQueryBlock) sqlSelect.getQuery();
            return new SelectParamHandel(sql, parameter, sqlSelectQuery).handler();

        } else if (stmt instanceof MySqlUpdateStatement) {
            MySqlUpdateStatement update = (MySqlUpdateStatement) stmt;
        } else if (stmt instanceof MySqlDeleteStatement) {
            MySqlDeleteStatement delete = (MySqlDeleteStatement) stmt;
        }

        return sql;

    }
}
