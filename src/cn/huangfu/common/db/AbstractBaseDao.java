package cn.huangfu.common.db;

import cn.huangfu.common.db.datasource.DruidDataConfig;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:11
 * @Description:
 *
 * DAO基类
 *
 */
public abstract class AbstractBaseDao implements BaseDao{

    /**
     * JdbcTemplate
     */
    protected static JdbcTemplate template;

    static{
        //初始化JdbcTemplate
        template = new JdbcTemplate(DruidDataConfig.getDataSource());
    }

    /**
     * 返回JDBCTemplate
     * @return
     */
    @Override
    public JdbcTemplate getTemplate(){
        return template;
    }

}
