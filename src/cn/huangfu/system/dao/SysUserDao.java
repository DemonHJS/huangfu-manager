package cn.huangfu.system.dao;

import cn.huangfu.system.entity.SysUser;

import java.util.List;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 20:42
 * @Description:
 */
public interface SysUserDao {
    /**
     * 查询所有用户集合
     * @return
     */
    List<SysUser> findAll();

    /**
     * 根据用户名密码获取用户对象
     * @param userName
     * @param password
     */
    SysUser getSysUserByNamePass(String userName, String password);
}
