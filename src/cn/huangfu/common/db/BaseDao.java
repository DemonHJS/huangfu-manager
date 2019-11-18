package cn.huangfu.common.db;

import cn.huangfu.common.bean.PageBean;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:45
 * @Description:
 */
public interface BaseDao {
    /**
     * 返回JdbcTemplate
     * @return
     */
    JdbcTemplate getTemplate();

    /**
     * 数据插入
     * @param entity
     * @param <E>
     * @return
     */
    <E> E insert(E entity);

    /**
     * 插入或者更新，判断条件主键是否为空
     * @param entity
     * @param <E>
     * @return
     */
    <E> E insertOrUpdate(E entity);

    /**
     * 数据更新
     * @param entity
     * @param <E>
     * @return
     */
    <E> E update(E entity);

    /**
     * 数据删除
     * @param entity
     * @param <E>
     */
    <E> void delete(E entity);

    <T> T getBySql(Class<T> t, String sql, Object... os);

    <T> List<T> listBySql(Class<T> t, String sql, Object... os);

    List<Map<String,Object>> listMapBySql(String sql, Object... os);

    Map<String,Object> getMapBySql(String sql, Object... os);

    <T> PageBean<T> getPageBean(PageBean<T> pb, String sql, Object... os);

}
