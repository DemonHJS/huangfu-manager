package test;

import cn.huangfu.system.dao.SysUserDao;
import cn.huangfu.system.dao.impl.SysUserDaoImpl;
import org.junit.Test;

/**
 * @Author: HuangFu
 * @Date: 2019/11/21 15:52
 * @Description:
 */
public class SysUserDaoImplTest {
    @Test
    public void test01(){
        SysUserDao sysUserDao = new SysUserDaoImpl();

        System.out.println(sysUserDao.getSysUserByNamePass("hjs","123456"));
        System.out.println("------");
    }
}
