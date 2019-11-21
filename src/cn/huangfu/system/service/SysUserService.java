package cn.huangfu.system.service;

import cn.huangfu.system.entity.SysUser;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 21:10
 * @Description:
 */
public interface SysUserService {

    /**
     * 用户登录
     * @param user
     * @return
     */
    SysUser login(SysUser user);
}
