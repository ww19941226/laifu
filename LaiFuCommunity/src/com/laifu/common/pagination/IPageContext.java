package com.laifu.common.pagination;

import com.laifu.common.Constants;


/**
 * 分页上下文环境。用于计算Page。
 * @author Raindrops
 * @version 2016/9/1
 */
public interface IPageContext<E> {
    
    /**
     * 默认设定每页记录为Constants中的默认值
     */
    public static final int DEFAULT_PAGE_SIZE = Constants.DEFAULT_PAGE_SIZE;
    
    /**
     * 计算总页数.
     * 
     * @return
     */
    public int getPageCount();
    

    /**
     * 返回 Page 对象.
     * 
     * @param index 索引
     * @return
     */
    public Page<E> getPage(int index);
    
    /**
     * 每页显示的记录数量
     * 
     * @return
     */
    public int getPageSize();
    
    /**
     * 计算总记录数
     * 
     * @return
     */
    public int getTotal();
    
}
