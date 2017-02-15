package com.laifu.common.dao;

import java.util.List;

/**
 * 通用Dao接口
 * 实现了通用增删查改
 * 实现了通用分页
 * @author Raindrops 
 * @version 2016/9/1
 * @param <M> 传入的entity
 * @param <PK> 约束主键
 */
public interface IBaseDao<M extends java.io.Serializable, PK extends java.io.Serializable> {
	
	
	/**
	 * 获取总和
	 * 
	 * @param hql
	 * @param paramlist
	 * @return
	 */
	public Double getSum(final String hql, final Object... paramlist);
	
	/**
     * 保存并且返回主键
     * @param model M 传入的entity类型
     * @return PK 传出的主键类型
     */
    public PK save(M model);

    /**
     * 保存或者更新
     * （如果已经存在就更新不存在则保存）
     * @param model M 传入的entity类型
     */
    public void saveOrUpdate(M model);
    /**
     * 单纯的更新
     * @param model M 传入的entity类型
     */
    public void update(M model);
    /**
     * 和update 类似的功能
     * 只是保存的过程并不一样
     * 具体自己百度吧
     */
    public void merge(M model);
    /**
     * 根据主键删除数据
     * @param id PK 主键
     */
    public void delete(PK id);
    /**
     * 根据entity删除数据
     * @param model M 传入的entity类型
     */
    public void deleteObject(M model);
    /**
     * 根据id得到一个实体类
     * @param id PK 传入的id
     * @return M 得到相应的实体
     */
    public M get(PK id);
    /**
     * 统计模型对象对应数据库表中的记录数
     * @return 返回数据库表的记录数
     */
    public int countAll();
    /**
     * 
     * @return 返回所有模型对象
     */
    public List<M> listAll();
    /**
     * 分页获取所有模型对象
     * @param pn 页码 从1开始
     * @param pageSize 每页记录数
     * @return
     */
    public List<M> listAll(int pn, int pageSize);
    /**
     * 获取上一页
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录
     */
    public List<M> pre(PK pk, int pn, int pageSize);
    /**
     * 获取下一页
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录 
     */
    public List<M> next(PK pk, int pn, int pageSize);
    /**
     * 根据id判断是否存在数据
     * @param id PK 传入的主键
     * @return boolean true为存在 false为不存在
     */
    boolean exists(PK id);
    /**
     * 清理缓存，执行SQL
     */
    public void flush();
    /**
     * 无论是Load 还是 Get 都会首先查找缓存（一级缓存） 
     * 如果没有，才会去数据库查找，
     * 调用Clear() 方法，可以强制清除Session缓存
     */
    public void clear();
    
    //********************************************************************************************************/
    
    public Object copyObject(Object preObj, Object newObj) throws Exception;
    
    public List listAll(String hql, int pn, int pageSize, Object... params);
    
    public List pre(String hql, PK pk, int pn, int pageSize, Object... params);
    
    public List next(String hql, PK pk, int pn, int pageSize, Object... params);
    
    public int countAll(String hql, Object... params);
    
    public void execute(String hql);
}
