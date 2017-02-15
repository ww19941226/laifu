package com.laifu.common.service;

import java.io.Serializable;
import java.util.List;

import com.laifu.common.pagination.Page;
/**
 * 通用Service层
 * @author Raindrops
 *
 * @param <M> 传入的entity
 * @param <PK> 约束主键
 */
public interface IBaseService<M extends Serializable, PK extends Serializable> {

	/**
	 * 保存
	 * @param model M 传入entity
	 * @return M entity对象
	 */
	public M save(M model);
	/**
	 * 保存或者更新(有则更新没则保存)
	 * @param model M 传入entity
	 */
	public void saveOrUpdate(M model);
	/**
	 * 更新
	 * @param model M 传入要更新的entity
	 */
	public void update(M model);
	/**
	 * 同样是更新 只是过程不同而已
	 * @param model M 传入要更新的entity
	 */
	public void merge(M model);
	/**
	 * 根据主键删除数据条
	 * @param id 传入的主键
	 */
	public void delete(PK id);
	
	/**
	 * 根据对象删除数据
	 * @param model M 传入的entity
	 */
	public void deleteObject(M model);
	/**
	 * 根据id得到一跳数据 
	 * @param id PK 主键
	 * @return M entity
	 */
	public M get(PK id);
	/**
	 * 得到总条数
	 * @return int 数据总数
	 */
	public int countAll();
	/**
	 * 得到所有的数据（小数据时才使用）
	 * @return List<M> list对象
	 */
	public List<M> listAll();
	/**
	 * 表示分页中的一页
	 * @param pn int 表示传入哪一页
	 * @return Page<M> 返回一页数据
	 */
	public Page<M> listAll(int pn);
	/**
     * 分页获取所有模型对象
     * @param pn 页码 从1开始
     * @param pageSize 每页记录数
     * @return Page<M> 返回所有数据
     */
	public Page<M> listAll(int pn, int pageSize);
	/**
     * 获取上一页 （自定义页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录
     */
	public Page<M> pre(PK pk, int pn, int pageSize);
	/**
     * 获取下一页 （自定义页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录 
     */
	public Page<M> next(PK pk, int pn, int pageSize);
	/**
     * 获取上一页 （根据默认的页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     */
	public Page<M> pre(PK pk, int pn);
	/**
     * 获取下一页 （根据默认的页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     */
	public Page<M> next(PK pk, int pn);
	
	/**************************************************************************************************/
	
	
	public Page<M> listAll(String hql, int pn, int pageSize);
	
	public Page<M> pre(String hql, PK pk, int pn, int pageSize);
	
	public Page<M> next(String hql, PK pk, int pn, int pageSize);

}
