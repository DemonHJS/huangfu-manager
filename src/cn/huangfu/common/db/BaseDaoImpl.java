package cn.huangfu.common.db;

import cn.huangfu.common.annotation.Id;
import cn.huangfu.common.annotation.Table;
import cn.huangfu.common.bean.Page;
import cn.huangfu.common.bean.PageBean;
import cn.huangfu.common.db.mapper.CustomColumnMapRowMapper;
import cn.huangfu.common.util.StringsUtils;
import cn.huangfu.system.entity.SysUser;
import com.alibaba.druid.support.logging.Log;
import com.alibaba.druid.support.logging.LogFactory;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

;


/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:46
 * @Description:
 */
public class BaseDaoImpl extends AbstractBaseDao{

    private final static Log logger =  LogFactory.getLog(BaseDaoImpl.class);

    @Override
    public <E> E insert(E entity) {
        //获取表名
        String tableName = getTableName(entity.getClass());
        if(tableName == null){
            logger.info("对象表名获取不到...");
            return null;
        }

        Map<String, Object> objectMap = getObjectMap(entity);
        //删除主键名称
        objectMap.remove("tablePrimaryKey");
        //创建字符集合
        StringBuilder sb = new StringBuilder();
        //创建参数集合
        List<Object> list = new ArrayList<>();
        // insert into tablename(id) values(?,?);
        sb.append("insert into " + tableName + " ");
        StringBuilder sbName = new StringBuilder();
        sbName.append(" (");
        StringBuilder sbValues = new StringBuilder();
        sbValues.append(" values(");

        objectMap.keySet().forEach(key -> {
            sbName.append(key + ",");
            sbValues.append("?,");
            list.add(objectMap.get(key));
        });

        //Sql字符最总拼接
        sb.append(sbName.substring(0,sbName.length()-1)+") ")
                .append(sbValues.substring(0,sbValues.length()-1)+") ");

        int ret = template.update(sb.toString(), list.toArray(new Object[list.size()]));
        if(ret>0){
            return entity;
        }

        return null;
    }

    @Override
    public <E> E update(E entity) {
        //获取表名
        String tableName = getTableName(entity.getClass());
        if(tableName == null){
            logger.info("对象表名获取不到...");
            return null;
        }

        Map<String, Object> objectMap = getObjectMap(entity);
        String idName = (String) objectMap.get("tablePrimaryKey");
        if(idName == null){
            logger.info("对象主键获取不到...");
            return null;
        }

        Object idValue = objectMap.get(idName);
        //删除主键名，主键数据
        objectMap.remove("tablePrimaryKey");
        objectMap.remove(idName);

        StringBuilder sb = new StringBuilder();
        List<Object> list = new ArrayList<>();
        sb.append("update " + tableName + " set ");
        objectMap.keySet().forEach(key -> {
            sb.append(StringsUtils.upperUpperCase(key) + " = ? ,");
            list.add(objectMap.get(key));
        });
        list.add(idValue);
        int ret = template.update(sb.substring(0,sb.length()-1) +" where " + idName + " =? ", list.toArray(new Object[list.size()]));
        if(ret>0){
            return entity;
        }

        return null;
    }

    @Override
    public <E> E insertOrUpdate(E entity) {
        String idName = getIdName(entity.getClass());
        try {
            String id = BeanUtils.getProperty(entity, idName);
            if(StringsUtils.isEmpty(id)){
                return insert(entity);
            }else{
                return update(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public <E> void delete(E entity) {
        String idName = getIdName(entity.getClass());
        String tableName = getTableName(entity.getClass());
        Object idValue = null;
        try {
            idValue = BeanUtils.getProperty(entity, StringsUtils.upperUnderline(idName));
            /*idValue = BeanUtils.findMethod(entity.getClass(), getMethodStr("get", idName))
                    .invoke(entity);*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        template.update("delete from " + tableName + " where " + idName + " =? ", idValue);
    }

    @Override
    public <T> T getBySql(Class<T> t,String sql, Object... os) {
        try{
            return template.queryForObject(sql,new BeanPropertyRowMapper<T>(t),os);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public <T> List<T> listBySql(Class<T> t, String sql, Object... os) {
        return template.queryForList(sql,t,os);
    }

    @Override
    public List<Map<String, Object>> listMapBySql(String sql, Object... os) {
        return listQuery(sql,os);
    }

    @Override
    public Map<String, Object> getMapBySql(String sql, Object... os) {
        List<Map<String, Object>> list = listMapBySql(sql,os);
        if(list!=null && list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 获取注解的主键名称
     * @param classz
     * @return
     */
    private String getIdName(Class classz){
        Class superclass = classz.getSuperclass();
        //获取父类对象的成员属性
        Field[] fields = superclass.getDeclaredFields();
        for (Field field : fields) {
            //校验是否注释
            if (field.isAnnotationPresent(Id.class)) {
                return field.getName();
            }
        }
        //获取对象的成员属性
        fields = classz.getDeclaredFields();
        for (Field field : fields) {
            //校验是否注释
            if(field.isAnnotationPresent(Id.class)){
                return field.getName();
            }
        }
        return null;
    }

    /**
     * 返回对象键值对数据
     * @param entity
     * @param <T>
     * @return
     */
    private <T> Map<String,Object> getObjectMap(T entity){
        HashMap<String, Object> rmap = new HashMap<>(16);
        //获取父对象数据
        setMapValue(entity,rmap,true);
        //获取子数据
        setMapValue(entity,rmap,false);
        return rmap;
    }

    /**
     * 对象和MAP数据设置
     * @param entity
     * @param map
     * @param status
     * @param <T>
     * @return
     */
    private <T> Map<String,Object> setMapValue(T entity,Map<String,Object> map,boolean status){
        //获取当前字节码对象
        Class classz = entity.getClass();
        if(status) {
            //获取继承类字节码对象
            classz = classz.getSuperclass();
        }
        //获取成员属性
        Field[] fields = classz.getDeclaredFields();
        if(fields!=null) {
            for (int i = 0; i < fields.length; i++) {
                Field field = fields[i];
                String fieldName = field.getName();
                if("serialVersionUID".equals(fieldName)){
                    continue;
                }
                try {
                    Method method = entity.getClass().getMethod(getMethodStr("get", field.getName()));
                    Object invoke = method.invoke(entity);
                    //检验主键注解
                    if (field.isAnnotationPresent(Id.class)) {
                        map.put("tablePrimaryKey",fieldName);
                    }
                    //设置值
                    map.put(StringsUtils.upperUpperCase(fieldName), invoke);
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error("遍历取值失败...");
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public  Page getPage(PageBean pb, Object... os) {
        String sql = pb.getSql();
        //获取总数
        Long total = template.queryForObject(pb.getCountSql(), Long.class, os);
        pb.setTotal(total);
        List<?> list = listQuery(pb.getSql(), os);
        pb.setData(list);
        return pb.toPage();
    }


    /**
     * 获取注解的表名
     * @param classz
     * @return
     */
    private String getTableName(Class classz){
        Table table = (Table) classz.getAnnotation(Table.class);
        if(table == null){
            return null;
        }
        return table.value();
    }

    /**
     * 获取方法名
     * @param prefix
     * @param name
     * @return
     */
    private String getMethodStr(String prefix,String name){
        return prefix + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
    }



    /**
     * 使用自定义列转换映射
     * @param sql
     * @param os
     * @return
     */
    private List<Map<String,Object>> listQuery(String sql, Object... os){
        return template.query(sql, new CustomColumnMapRowMapper(), os);
    }

    public static void main(String[] args) {

        /*System.out.println(getSmallHump("xxxx_ss_e"));*/
        SysUser sysUser = new SysUser();
        sysUser.setId(5);
        sysUser.setDeptId(100);
        sysUser.setNickname("黄家胜22");
        sysUser.setUserName("黄家胜22");
        sysUser.setLoginDate(new Date());
        sysUser.setCreateTime(new Date());
        BaseDaoImpl baseDao = new BaseDaoImpl();
        /*new BaseDaoImpl().delete(sysUser);*/
        //baseDao.insert(sysUser);
        //baseDao.update(sysUser);
        //System.out.println(baseDao.getObjectMap(sysUser));
        PageBean pageBean = new PageBean();
        pageBean.setPage(0);
        pageBean.setSize(10);
        pageBean.setColumns("*");
        pageBean.setFromWheres(" from sys_user");

        System.out.println(baseDao.getPage(pageBean).getData());

    }

}
