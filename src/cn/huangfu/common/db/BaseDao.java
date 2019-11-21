package cn.huangfu.common.db;

import cn.huangfu.common.bean.Page;
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
     * 数据更新
     * @param entity
     * @param <E>
     * @return
     */
    <E> E update(E entity);

    /**
     * 插入或者更新，判断条件主键是否为空
     * @param entity
     * @param <E>
     * @return
     */
    <E> E insertOrUpdate(E entity);

    /**
     * 数据删除
     * @param entity
     * @param <E>
     */
    <E> void delete(E entity);

    /**
     * 返回单个对象
     * @param t
     * @param sql
     * @param os
     * @param <T>
     * @return
     */
    <T> T getBySql(Class<T> t, String sql, Object... os);

    /**
     * 返回对象list集合
    * @param t
     * @param sql
     * @param os
     * @param <T>
     * @return
     */
    <T> List<T> listBySql(Class<T> t, String sql, Object... os);

    /**
     * 返回对象list<Map<String,Object>>集合
     * @param sql
     * @param os
     * @return
     */
    List<Map<String,Object>> listMapBySql(String sql, Object... os);

    /**
     * 返回单个Map<String,Object>
     * @param sql
     * @param os
     * @return
     */
    Map<String,Object> getMapBySql(String sql, Object... os);

    /**
     * 返回分页对象
     * @param pb
     * @param os
     * @return
     */
    Page getPage(PageBean pb, Object... os);

}
