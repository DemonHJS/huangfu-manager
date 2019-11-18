package cn.huangfu.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/15 15:39
 * @Description:
 * 标记实体对象表名
 */
//定义在类上
@Target(ElementType.TYPE)
//运行阶段有效
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
