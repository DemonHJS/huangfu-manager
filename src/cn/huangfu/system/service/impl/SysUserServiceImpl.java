package cn.huangfu.system.service.impl;

import cn.huangfu.system.dao.SysUserDao;
import cn.huangfu.system.dao.impl.SysUserDaoImpl;
import cn.huangfu.system.entity.SysUser;
import cn.huangfu.system.service.SysUserService;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/14 21:10
 * @Description:
 */
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao = new SysUserDaoImpl();

    @Override
    public SysUser login(SysUser user) {
        return sysUserDao.getSysUserByNamePass(user.getUserName(), user.getPassword());
    }
}
