package com.laifu.common.service.impl;

import java.io.Serializable;
import java.util.List;

import com.laifu.common.Constants;
import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.IBaseService;
import com.laifu.module.entity.Topictype;
/**
 * 通用Service 实现层
 * @author Raindrops
 *
 * @param <M> 传入的entity
 * @param <PK> 约束
 */
public abstract class BaseServiceImpl<M extends Serializable, PK extends Serializable>
		implements IBaseService<M, PK> {

	protected IBaseDao<M, PK> baseDao;

	public abstract void setBaseDaoImpl(IBaseDao<M, PK> baseDao);

	
	/**
	 * 保存
	 * @param model M 传入entity
	 * @return M entity对象
	 */
	@Override
	public M save(M model) {
		baseDao.save(model);
		return model;
	}
	/**
	 * 保存或者更新(有则更新没则保存)
	 * @param model M 传入entity
	 */
	@Override
	public void merge(M model) {
		baseDao.merge(model);
	}
	/**
	 * 更新
	 * @param model M 传入要更新的entity
	 */
	@Override
	public void saveOrUpdate(M model) {
		baseDao.saveOrUpdate(model);
	}
	/**
	 * 同样是更新 只是过程不同而已
	 * @param model M 传入要更新的entity
	 */
	@Override
	public void update(M model) {
		baseDao.update(model);
	}
	/**
	 * 根据主键删除数据条
	 * @param id 传入的主键
	 */
	@Override
	public void delete(PK id) {
		baseDao.delete(id);
	}
	/**
	 * 根据对象删除数据
	 * @param model M 传入的entity
	 */
	@Override
	public void deleteObject(M model) {
		baseDao.deleteObject(model);
	}
	/**
	 * 根据id得到一跳数据 
	 * @param id PK 主键
	 * @return M entity
	 */
	@Override
	public M get(PK id) {
		return baseDao.get(id);
	}
	/**
	 * 得到总条数
	 * @return int 数据总数
	 */
	@Override
	public int countAll() {
		return baseDao.countAll();
	}
	/**
	 * 得到所有的数据（小数据时才使用）
	 * @return List<M> list对象
	 */
	@Override
	public List<M> listAll() {
		return baseDao.listAll();
	}
	/**
	 * 表示分页中的一页
	 * @param pn int 表示传入哪一页
	 * @return Page<M> 返回一页数据
	 */
	@Override
	public Page<M> listAll(int pn) {

		return this.listAll(pn, Constants.DEFAULT_PAGE_SIZE);
	}
	
	public Page<M> listAllWithOptimize(int pn) {
		return this.listAllWithOptimize(pn, Constants.DEFAULT_PAGE_SIZE);
	}
	/**
     * 分页获取所有模型对象
     * @param pn 页码 从1开始
     * @param pageSize 每页记录数
     * @return Page<M> 返回所有数据
     */
	@Override
	public Page<M> listAll(int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<M> listAllWithOptimize(int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.listAll(pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	/**
     * 获取上一页 （自定义页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录
     */
	@Override
	public Page<M> pre(PK pk, int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.pre(pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	/**
     * 获取下一页 （自定义页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录 
     */
	@Override
	public Page<M> next(PK pk, int pn, int pageSize) {
		Integer count = countAll();
		List<M> items = baseDao.next(pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	/**
     * 获取上一页 （根据默认的页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     */
	@Override
	public Page<M> pre(PK pk, int pn) {
		return pre(pk, pn, Constants.DEFAULT_PAGE_SIZE);
	}
	/**
     * 获取下一页 （根据默认的页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     */
	@Override
	public Page<M> next(PK pk, int pn) {
		return next(pk, pn, Constants.DEFAULT_PAGE_SIZE);
	}


	/*********************************************************************************************************/
	
	
	@Override
	public Page<M> listAll(String hql, int pn, int pageSize) {
		Integer count = baseDao.countAll("select count(*) " + hql);
		List<M> items = baseDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<M> pre(String hql, PK pk, int pn, int pageSize) {
		Integer count = baseDao.countAll("select count(*) " + hql);
		List<M> items = baseDao.pre(hql, pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
	/**
     * 获取下一页 （自定义页码大小）
     * @param pk PK 主键
     * @param pn int 页码
     * @param pageSize 获取多少条记录 
     */
	@Override
	public Page<M> next(String hql, PK pk, int pn, int pageSize) {
		Integer count = baseDao.countAll("select count(*) " + hql);
		List<M> items = baseDao.next(hql, pk, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}
}
