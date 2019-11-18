package cn.huangfu.common.bean;

import java.util.List;

/**
 * @Author: HuangJiaSheng
 * @Date: 2019/11/16 14:14
 * @Description:
 *  分页对象
 */
public class PageBean<T> {
    /**
     * 总记录数
     */
    private Long totalCount;
    /**
     * 总页码
     */
    private int totalPage ;
    /**
     * 每页的数据
     */
    private List<T> list ;
    /**
     * 当前页码
     */
    private int currentPage ;
    /**
     * 每页显示的记录数
     */
    private int rows;


    public PageBean() {
    }

    public PageBean(Long totalCount, int totalPage, List<T> list, int currentPage, int rows) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.list = list;
        this.currentPage = currentPage;
        this.rows = rows;
    }


}
