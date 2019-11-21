package cn.huangfu.system.dao.impl;

import cn.huangfu.common.db.BaseDao;
import cn.huangfu.common.db.BaseDaoImpl;
import cn.huangfu.system.dao.SysUserDao;
import cn.huangfu.system.entity.SysUser;

import java.util.List;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:41
 * @Description:
 *
 */
public class SysUserDaoImpl implements SysUserDao {
    /**
     * 创建基础Dao对象
     */
    private BaseDao baseDao = new BaseDaoImpl();

    @Override
    public List<SysUser> findAll(){
        return baseDao.getTemplate().queryForList("select * from sys_user", SysUser.class);
    }

    @Override
    public SysUser getSysUserByNamePass(String userName, String password) {
        return baseDao.getBySql(SysUser.class
                ,"select * from sys_user t where t.user_name=? and password=? "
                ,userName,password);
    }

}
