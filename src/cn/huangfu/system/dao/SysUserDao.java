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

}
