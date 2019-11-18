package cn.huangfu.common.db;//package cn.huangfu.common.db;
//
//import cn.huangfu.common.annotation.Id;
//import cn.huangfu.common.annotation.Table;
//import cn.huangfu.common.bean.PageBean;
//import cn.huangfu.common.util.StringsUtils;
//import cn.huangfu.system.entity.SysUser;
//import com.alibaba.druid.support.logging.Log;
//import com.alibaba.druid.support.logging.LogFactory;
//import org.apache.commons.beanutils.BeanUtils;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//import java.util.*;
//
//;
//
//
///**
// * @Author: HuangJiaSheng
// * @Date: 2019/11/14 20:46
// * @Description:
// */
//public class BaseDaoImplDemo01 extends AbstractBaseDao{
//
//    private final static Log logger =  LogFactory.getLog(BaseDaoImplDemo01.class);
//
//    @Override
//    public <E> E insert(E entity) {
//        //获取主键ID名
//        String idName = getIdName(entity.getClass());
//        //获取表名
//        String tableName = getTableName(entity.getClass());
//        //创建字符集合
//        StringBuilder sb = new StringBuilder();
//        //创建参数集合
//        List<Object> list = new ArrayList<>();
//        // insert into tablename(id) values(?,?);
//        sb.append("insert into " + tableName + "  ");
//        StringBuilder sbName = new StringBuilder();
//        sbName.append(" (");
//        StringBuilder sbValues = new StringBuilder();
//        sbValues.append(" values(");
//        Field[] fields = entity.getClass().getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            String fieldName = field.getName();
//            try {
//                Method method = entity.getClass().getMethod(getMethodStr("get",field.getName()));
//                Object invoke = method.invoke(entity);
//                if(i == fields.length-1){
//                    sbName.append(StringsUtils.upperUpperCase(fieldName));
//                    sbValues.append("?");
//                }else{
//                    sbName.append(StringsUtils.upperUpperCase(fieldName) + ",");
//                    sbValues.append("?, ");
//                }
//                list.add(invoke);
//            } catch (Exception e) {
//                e.printStackTrace();
//                logger.error("遍历取值失败...");
//                return null;
//            }
//        }
//        sbName.append(") ");
//        sbValues.append(") ");
//        //Sq字符最总拼接
//        sb.append(sbName.toString()).append(sbValues.toString());
//
//        int ret = template.update(sb.toString(), list.toArray(new Object[list.size()]));
//        if(ret>0){
//            return entity;
//        }
//
//        return null;
//    }
//
//    @Override
//    public <E> E insertOrUpdate(E entity) {
//        String idName = getIdName(entity.getClass());
//        try {
//            String id = BeanUtils.getProperty(entity, idName);
//            if(StringsUtils.isEmpty(id)){
//                return insert(entity);
//            }else{
//                return update(entity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    public <E> E update(E entity) {
//        StringBuilder sb = new StringBuilder();
//        String idName = getIdName(entity.getClass());
//        String tableName = getTableName(entity.getClass());
//
//        HashMap<String, Object> map = new HashMap<>(16);
//
//        List<Object> list = new ArrayList<>();
//        sb.append("update " + tableName + " set ");
//        Field[] fields = entity.getClass().getDeclaredFields();
//        for (int i = 0; i < fields.length; i++) {
//            Field field = fields[i];
//            String fieldName = field.getName();
//            try {
//                Method method = entity.getClass().getMethod(getMethodStr("get",field.getName()));
//                Object invoke = method.invoke(entity);
//                if(fieldName.equals(idName)){
//                    map.put("primaryKey",invoke);
//                }else{
//                    list.add(invoke);
//                    if(i == fields.length-1){
//                        sb.append(StringsUtils.upperUpperCase(fieldName) + " = ?");
//                    }else {
//                        sb.append(StringsUtils.upperUpperCase(fieldName) + " = ? ,");
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//
//                return null;
//            }
//        }
//
//        sb.append(" where  " + idName + " =? ");
//        list.add(map.get("primaryKey"));
//        int ret = template.update(sb.toString(), list.toArray(new Object[list.size()]));
//        if(ret>0){
//            return entity;
//        }
//
//        return null;
//    }
//
//    @Override
//    public <E> void delete(E entity) {
//        String idName = getIdName(entity.getClass());
//        String tableName = getTableName(entity.getClass());
//        Object idValue = null;
//        try {
//            idValue = BeanUtils.getProperty(entity, StringsUtils.upperUnderline(idName));
//            /*idValue = BeanUtils.findMethod(entity.getClass(), getMethodStr("get", idName))
//                    .invoke(entity);*/
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        template.update("delete from " + tableName + " where " + idName + " =? ", idValue);
//    }
//
//    @Override
//    public <T> T getBySql(Class<T> t,String sql, Object... os) {
//        return template.queryForObject(sql,t,os);
//    }
//
//
//    @Override
//    public <T> List<T> listBySql(Class<T> t, String sql, Object... os) {
//        return template.queryForList(sql,t,os);
//    }
//
//    @Override
//    public List<Map<String, Object>> listMapBySql(String sql, Object... os) {
//        return template.queryForList(sql,os);
//    }
//
//    @Override
//    public Map<String, Object> getMapBySql(String sql, Object... os) {
//        List<Map<String, Object>> list = template.queryForList(sql, os);
//        if(list!=null && list.size()>0){
//            return list.get(0);
//        }
//        return null;
//    }
//
//    /**
//     * 获取注解的主键名称
//     * @param classz
//     * @return
//     */
//    private String getIdName(Class classz){
//        Class superclass = classz.getSuperclass();
//        //获取父类对象的成员属性
//        Field[] fields = superclass.getDeclaredFields();
//        for (Field field : fields) {
//            //校验是否注释
//            if (field.isAnnotationPresent(Id.class)) {
//                return field.getName();
//            }
//        }
//        //获取对象的成员属性
//        fields = classz.getDeclaredFields();
//        for (Field field : fields) {
//            //校验是否注释
//            if(field.isAnnotationPresent(Id.class)){
//                /*try {
//                    //问题原因：注解是在成员属性上，类对象上获取不到
////                    Id id = (Id) classz.getAnnotation(Id.class);
//                    Id id = (Id) field.getAnnotation(Id.class);
//                    //如果默认值为空字符返回成员方法名，否则返回属性值
//                    if ("".equals(id.value())) {
//                        return field.getName();
//                    }
//                    return id.value();
//
//                }catch (Exception e){
//                    e.printStackTrace();
//                }*/
//                return field.getName();
//            }
//        }
//        return null;
//    }
//
//    /**
//     * 获取注解的表名
//     * @param classz
//     * @return
//     */
//    private String getTableName(Class classz){
//        Table table = (Table) classz.getAnnotation(Table.class);
//        if(table == null){
//            return null;
//        }
//        return table.value();
//    }
//
//    /**
//     * 获取方法名
//     * @param prefix
//     * @param name
//     * @return
//     */
//    private String getMethodStr(String prefix,String name){
//        return prefix + String.valueOf(name.charAt(0)).toUpperCase() + name.substring(1);
//    }
//
//    @Override
//    public <T> PageBean<T> getPageBean(PageBean<T> pb, String sql, Object... os) {
//
//        return null;
//    }
//    /*
//    private String getSmallHump(String str){
//        String[] s = str.split("_");
//        if(s.length ==0){
//           return str;
//        }
//        StringBuilder sb = new StringBuilder();
//        sb.append(s[0]);
//        for (int i = 1; i < s.length; i++) {
//            //获取第一位
//            String first_s = s[i].charAt(0)+"";
//            //小写转大写
//            first_s = first_s.toUpperCase();
//            sb.append(first_s + s[i].substring(1));
//        }
//        return sb.toString();
//    }*/
//
//
//    public static void main(String[] args) {
//
//        /*System.out.println(getSmallHump("xxxx_ss_e"));*/
//        SysUser sysUser = new SysUser();
//        sysUser.setId(5);
//        sysUser.setDeptId(100);
//        sysUser.setLoginName("黄家胜");
//        sysUser.setUserName("黄家胜");
//        sysUser.setLoginDate(new Date());
//        BaseDaoImplDemo01 baseDao = new BaseDaoImplDemo01();
//        /*new BaseDaoImpl().delete(sysUser);*/
//        baseDao.insert(sysUser);
//        /*baseDao.update(sysUser);*/
//    }
//
//}