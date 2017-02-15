package com.laifu.common.dao.impl;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.Id;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.utils.Assert;


/**
 * 通用Dao实现层
 * 实现了通用增删查改
 * 通用分页
 * @author Raindrops 
 *
 * @param <M> 传入的entity
 * @param <PK> 约束主键
 */
public class BaseDaoImpl <M extends Serializable,PK extends Serializable> implements IBaseDao<M, PK>{
	 protected static final Logger LOGGER = LoggerFactory.getLogger(BaseDaoImpl.class);


	    private final Class<M> entityClass;//获取entity类
	    private final String HQL_LIST_ALL;//定义查询语句from entity orderby pk desc
	    private final String HQL_COUNT_ALL;//统计entity 总条数
	    private final String HQL_OPTIMIZE_PRE_LIST_ALL;//用主键的比较来确定上一页 升序查询 比pkName大的所有记录
	    private final String HQL_OPTIMIZE_NEXT_LIST_ALL;//用主键的比较来确定下一页 比pkName小的所有记录
	    private String pkName = null;

	    @SuppressWarnings("unchecked")
	    public BaseDaoImpl() {
	    	//得到entity的实体类
	        this.entityClass = (Class<M>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	        Field[] fields = this.entityClass.getDeclaredFields();//得到entity的所有成员属性
	        for(Field f : fields) {
	        	//只要得到我们需要的主键约束就行
	            if(f.isAnnotationPresent(Id.class)) {
	                this.pkName = f.getName();
	            }
	        }
	        
	        Assert.notNull(pkName);//断定pkName 不为空
	        //TODO @Entity name not null
	        HQL_LIST_ALL = "from " + this.entityClass.getSimpleName() + " order by " + pkName + " desc";
	        HQL_OPTIMIZE_PRE_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " > ? order by " + pkName + " asc";
	        HQL_OPTIMIZE_NEXT_LIST_ALL = "from " + this.entityClass.getSimpleName() + " where " + pkName + " < ? order by " + pkName + " desc";
	        HQL_COUNT_ALL = " select count(*) from " + this.entityClass.getSimpleName();
	    }
	        
	    @Autowired
	    @Qualifier("sessionFactory")
	    private SessionFactory sessionFactory;

	    public Session getSession() {
	        //事务必须是开启的(Required)，否则获取不到
	        return sessionFactory.getCurrentSession();
	    } 

	   
	    /**
	     * 保存并且返回主键
	     * @param model M 传入的entity类型
	     * @return PK 传出的主键类型
	     */
	    @SuppressWarnings("unchecked")
	    @Override
	    public PK save(M model) {
	        return (PK) getSession().save(model);
	    }
	    /**
	     * 保存或者更新
	     * （如果已经存在就更新不存在则保存）
	     * @param model M 传入的entity类型
	     */
	    @Override
	    public void saveOrUpdate(M model) {
	        getSession().saveOrUpdate(model);
	    }
	    /**
	     * 单纯的更新
	     * @param model M 传入的entity类型
	     */
	    @Override
	    public void update(M model) {
	        getSession().update(model);

	    }
	    /**
	     * 和update 类似的功能
	     * 只是保存的过程并不一样
	     * 具体自己百度吧
	     */
	    @Override
	    public void merge(M model) {
	        getSession().merge(model);
	    }
	    
	    /**
	     * 根据主键删除数据
	     * @param id PK 主键
	     */
	    @Override
	    public void delete(PK id) {
	        getSession().delete(this.get(id));

	    }
	    /**
	     * 根据entity删除数据
	     * @param model M 传入的entity类型
	     */
	    @Override
	    public void deleteObject(M model) {
	        getSession().delete(model);

	    }

	    /**
	     * 根据id判断是否存在数据
	     * @param id PK 传入的主键
	     * @return boolean true为存在 false为不存在
	     */
	    @Override
	    public boolean exists(PK id) {
	        return get(id) != null;
	    }

	    /**
	     * 根据id得到一个实体类
	     * @param id PK 传入的id
	     * @return M 得到相应的实体
	     */
	    @Override
	    public M get(PK id) {
	        return (M) getSession().get(this.entityClass, id);
	    }

	    /**
	     * 统计记录数量
	     * @return int 得到记录条数
	     */
	    @Override
	    public int countAll() {
	        Long total = aggregate(HQL_COUNT_ALL);
	        return total.intValue();
	    }

	    /**
	     *得到所有数据(慎用，只用来查询权限角色这些数据量少的)
	     *
	     *@return List<M> list 得到所有数据集合
	     */
	    @Override
	    public List<M> listAll() {
	        return list(HQL_LIST_ALL);
	    }
	    
	    /**
	     * 根据页码和要显示的页面数据量查询数据
	     * 
	     * @param pn int 页码
	     * @param pageSize int 页面数据量
	     * @return listAll List<M> 返回记录数
	     */
	    @Override
	    public List<M> listAll(int pn, int pageSize) {
	        return list(HQL_LIST_ALL, pn, pageSize);
	    }
	    
	    /**
	     * 获取上一页
	     * @param pk PK 主键
	     * @param pn int 页码
	     * @param pageSize 获取多少条记录
	     */
	    @Override
	    public List<M> pre(PK pk, int pn, int pageSize) {
	        if(pk == null) {
	            return list(HQL_LIST_ALL, pn, pageSize);
	        }
	        //倒序，重排
	        List<M> result = list(HQL_OPTIMIZE_PRE_LIST_ALL, 1, pageSize, pk);
	        //使用Reverse方法可以根据元素的自然顺序 对指定列表按降序进行排序。
	        Collections.reverse(result);
	        return result;
	    }
	    /**
	     * 获取下一页
	     * @param pk PK 主键
	     * @param pn int 页码
	     * @param pageSize 获取多少条记录 
	     */
	    @Override
	    public List<M> next(PK pk, int pn, int pageSize) {
	        if(pk == null) {
	            return list(HQL_LIST_ALL, pn, pageSize);
	        }
	        return list(HQL_OPTIMIZE_NEXT_LIST_ALL, 1, pageSize, pk);
	    }
	    /**
	     * 清理缓存，执行SQL
	     */
	    @Override
	    public void flush() {
	        getSession().flush();
	    }

	    
	    
	    /**
	     * 无论是Load 还是 Get 都会首先查找缓存（一级缓存） 
	     * 如果没有，才会去数据库查找，
	     * 调用Clear() 方法，可以强制清除Session缓存
	     */
	    @Override
	    public void clear() {
	        getSession().clear();
	    }
	    
	    /**
	     * 根据查询条件获得第一条记录id
	     * @param hql
	     * @param paramlist
	     * @return
	     */
	    protected long getIdResult(String hql, Object... paramlist) {
	        long result = -1;
	        List<?> list = list(hql, paramlist);
	        if (list != null && list.size() > 0) {
	            return ((Number) list.get(0)).longValue();
	        }
	        return result;
	    }

	    protected List<M> listSelf(final String hql, final int pn, final int pageSize, final Object... paramlist) {
	        return this.<M> list(hql, pn, pageSize, paramlist);
	    }


	    /**
	     * for in
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> List<T> listWithIn(final String hql,final int start, final int length, final Map<String, Collection<?>> map, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        for (Entry<String, Collection<?>> e : map.entrySet()) {
	            query.setParameterList(e.getKey(), e.getValue());
	        }
	        if (start > -1 && length > -1) {
	            query.setMaxResults(length);
	            if (start != 0) {
	                query.setFirstResult(start);
	            }
	        }
	        List<T> results = query.list();
	        return results;
	    }

	    
	    
	    /**
	     * 通用列表查询,当pn<=-1 且 pageSize<=-1表示查询所有记录
	     * @param <T> 模型类型
	     * @param hql Hibernate查询语句
	     * @param pn  页码 从1开始，
	     * @param pageSize 每页记录数
	     * @param paramlist 可变参数列表
	     * @return 模型对象列表
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> List<T> list(final String hql, final int pn, final int pageSize, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        if (pn > -1 && pageSize > -1) {
	            query.setMaxResults(pageSize);//获取pageSize范围的数据 和limit 一样的效果
	            int start = PageUtil.getPageStart(pn, pageSize);//获取游标位置
	            if (start != 0) {
	            	//获取 limit start,pageSize
	                query.setFirstResult(start);
	            }
	        }
	        if (pn < 0) {
	        	//说明是第一页 直接从第一个数据开始获取
	            query.setFirstResult(0);
	        }
	        //pn<=-1 pageSize<=-1 直接查询全部
	        List<T> results = query.list();
	        
	        return results;
	    }

	    /**
	     * 根据查询条件返回唯一一条记录
	     * @param <T> 返回类型
	     * @param hql Hibernate查询语句
	     * @param paramlist 参数列表
	     * @return
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> T unique(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        return (T) query.setMaxResults(1).uniqueResult();
	    }

	       /**
	        * 
	        * for in
	        */
	    @SuppressWarnings("unchecked")
	    protected <T> T aggregate(final String hql, final Map<String, Collection<?>> map, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        if (paramlist != null) {
	            setParameters(query, paramlist);
	            for (Entry<String, Collection<?>> e : map.entrySet()) {
	                query.setParameterList(e.getKey(), e.getValue());
	            }
	        }

	        return (T) query.uniqueResult();
	    }
	    
	    
	    /**
	     * 根据hql语句查询一条结果
	     * @param hql
	     * @param paramlist
	     * @return T 返回一条记录
	     */
	    @SuppressWarnings("unchecked")
	    protected <T> T aggregate(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        //当确定返回的实例只有一个或者null时 用uniqueResult()方法
	        return (T) query.uniqueResult();

	    }


	    /**
	     * 执行批处理语句.如 之间insert, update, delete 等.
	     */
	    protected int execteBulk(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        Object result = query.executeUpdate();
	        return result == null ? 0 : ((Integer) result).intValue();
	    }
	    
	    protected int execteNativeBulk(final String natvieSQL, final Object... paramlist) {
	        Query query = getSession().createSQLQuery(natvieSQL);
	        setParameters(query, paramlist);
	        Object result = query.executeUpdate();
	        return result == null ? 0 : ((Integer) result).intValue();
	    }
	    
	    /**
	     * 当pn<=-1 且 pageSize<=-1表示查询所有记录
	     * @param sql	约束sql语句
	     * @param paramlist 约束条件
	     * @return list <T>List<T>  得到约束后的所有记录
	     */
	    protected <T> List<T> list(final String sql, final Object... paramlist) {
	        return list(sql, -1, -1, paramlist);
	    }
	    
	    /**
	     * 将所有paramlist的对象设置给query
	     * @param query 查询用的 query
	     * @param paramlist 约束对象
	     */
	    protected void setParameters(Query query, Object[] paramlist) {
	        if (paramlist != null) {
	            for (int i = 0; i < paramlist.length; i++) {
	                if(paramlist[i] instanceof Date) {
	                    //TODO 难道这是bug 使用setParameter不行？？
	                    query.setTimestamp(i, (Date)paramlist[i]);
	                } else {
	                    query.setParameter(i, paramlist[i]);
	                }
	            }
	        }
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    /*******************************************************************************************************/
	    
	    
	    public Object copyObject(Object preObj, Object newObj) throws Exception {
	    	Class<?> newClazz = newObj.getClass();
	    	Class<?> preClasz = preObj.getClass();
	    	Field[] field = newClazz.getDeclaredFields();
	    	for(Field f : field) {
	    		String name = String.valueOf((char)(f.getName().charAt(0)-'a'+'A')) + f.getName().substring(1);
	    		try { if(preClasz. getMethod("get"+name) == null) continue; } catch(Exception e) { continue; }
	    		newClazz.getMethod("set"+name, f.getType()).invoke(newObj, preClasz.getMethod("get"+name).invoke(preObj));
	    	}
	    	return newObj;
	    }
	    
	    
	    
	    @Override
	    public List listAll(String hql, int pn, int pageSize, Object... params) {
	        return list(hql, pn, pageSize, params);
	    }
	    
	    /**
	     * 获取上一页
	     * @param pk PK 主键
	     * @param pn int 页码
	     * @param pageSize 获取多少条记录
	     */
	    @Override
	    public List pre(String hql, PK pk, int pn, int pageSize, Object... params) {
	        if(pk == null) {
	            return list(hql, pn, pageSize, params);
	        }
	        //倒序，重排
	        List<M> result = list(hql, 1, pageSize, pk);
	        //使用Reverse方法可以根据元素的自然顺序 对指定列表按降序进行排序。
	        Collections.reverse(result);
	        return result;
	    }
	    
	    
	    /**
		 * 获取总和
		 * 
		 * @param hql
		 * @param paramlist
		 * @return
		 */
	    @Override
	    public Double getSum(final String hql, final Object... paramlist) {
	        Query query = getSession().createQuery(hql);
	        setParameters(query, paramlist);
	        Double d = (Double) query.setMaxResults(1).uniqueResult();
	        if(d == null) d = new Double(0);
	        return d;
	    }
	    
	    /**
	     * 获取下一页
	     * @param pk PK 主键
	     * @param pn int 页码
	     * @param pageSize 获取多少条记录 
	     */
	    @Override
	    public List next(String hql, PK pk, int pn, int pageSize, Object... params) {
	        if(pk == null) {
	            return list(hql, pn, pageSize, params);
	        }
	        return list(hql, 1, pageSize, pk);
	    }
	    
	    @Override
	    public int countAll(String hql, Object... params) {
	        Long total = aggregate(hql, params);
	        return total.intValue();
	    }
	    
	    public void execute(String hql) {
	    	getSession().createQuery(hql).executeUpdate();
	    }
}
