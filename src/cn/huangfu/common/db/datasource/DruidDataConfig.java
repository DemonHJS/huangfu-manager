package cn.huangfu.common.db.datasource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.util.Properties;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/10/26 10:43
 * @Description:
 */
public class DruidDataConfig {
    private static DataSource dataSource = null;

    static{
        //测试使用
        loadDataSource(DruidDataConfig.class.getClassLoader().getResource("druid.properties").getPath());
    }


    /**
     * 加载数据源
     * @param path 配置文件绝对路径
     */
    public static void loadDataSource(String path) {
        try {
            //读取配置文件
            Properties prop = new Properties();
//            prop.load(DruidDataConfig.class.getClassLoader().getResourceAsStream("druid.properties"));
            prop.load(new FileReader(path));
            //加载数据源 DataSource
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    private DruidDataConfig() {
    }

    /**
     * 获取数据源
     * @return
     */
    public static DataSource getDataSource(){
        return dataSource;
    }

}
