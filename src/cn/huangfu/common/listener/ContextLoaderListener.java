package cn.huangfu.common.listener;

import cn.huangfu.common.db.datasource.DruidDataConfig;
import cn.huangfu.common.util.SOSScriptUtils;
import cn.huangfu.common.util.XmlUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.util.Map;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 18:37
 * @Description:
 *
 * ServletContext监听器加载，初始化系统资源
 *
 */
@WebListener
public class ContextLoaderListener implements ServletContextListener {
   public final static Log logger = LogFactory.getLog(XmlUtils.class);

    /**
     * 服务器启动创建ServletContext对象监听
     * @param servletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取ServletContext域对象
        ServletContext context = servletContextEvent.getServletContext();
        //获取数据库配置文件绝对路径
        String realPath = context.getRealPath("/WEB-INF/classes/druid.properties");
        //加载数据源
        DruidDataConfig.loadDataSource(realPath);
        logger.info("数据源初始化完成....");
        //获取系统配置文件
        String systemConfig = context.getRealPath("/WEB-INF/classes/systemConfig.js");
        try {
            Map<String, Object> sysConfig = SOSScriptUtils.parse(new FileInputStream(systemConfig),"getParams");
            context.setAttribute("systemConfig",sysConfig.get("systemConfig"));
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("配置文件加载失败,请检查...");
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
