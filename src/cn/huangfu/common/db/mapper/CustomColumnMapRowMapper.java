package cn.huangfu.common.db.mapper;

import cn.huangfu.common.util.StringsUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.LinkedCaseInsensitiveMap;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Map;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/20 14:52
 * @Description:
 * 自定义列映射对象
 */
public class CustomColumnMapRowMapper implements RowMapper<Map<String, Object>> {

    @Override
    public Map<String, Object> mapRow(ResultSet rs, int rowNum) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, Object> mapOfColValues = this.createColumnMap(columnCount);

        for(int i = 1; i <= columnCount; ++i) {
            //字符转换
            String key = this.getColumnKey(StringsUtils.upperUnderline(JdbcUtils.lookupColumnName(rsmd, i)));
            Object obj = this.getColumnValue(rs, i);
            mapOfColValues.put(key, obj);
        }

        return mapOfColValues;
    }

    protected Map<String, Object> createColumnMap(int columnCount) {
        return new LinkedCaseInsensitiveMap(columnCount);
    }

    protected String getColumnKey(String columnName) {
        return columnName;
    }

    @Nullable
    protected Object getColumnValue(ResultSet rs, int index) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index);
    }

}
