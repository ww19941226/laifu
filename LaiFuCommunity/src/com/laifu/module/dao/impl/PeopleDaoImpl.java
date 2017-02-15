package com.laifu.module.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;


import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.PeopleDao;
import com.laifu.module.entity.People;
/**
 * 测试类 Dao层
 * @author Raindrops
 * @version 2016/9/1
 */
@Repository("PeopleDao")
public class PeopleDaoImpl extends BaseDaoImpl<People, Integer> implements PeopleDao{

	/**
	 * 表来自哪个实体类
	 */
    private static final String HQL_LIST = "from UserModel ";
    /**
     * 定义User表的数量
     */
    private static final String HQL_COUNT = "select count(*) from UserModel ";
    /**
     * 添加约束条件
     */
    private static final String HQL_LIST_QUERY_CONDITION = " where username like ?";
    /**
     * from UserModer where username like ? order by id desc;
     * 约束表和约束条件并且按降序排序
     */
    private static final String HQL_LIST_QUERY_ALL = HQL_LIST + HQL_LIST_QUERY_CONDITION + "order by id desc";
    /**
     * select count(*) from UserModel where username like ?
     */
    private static final String HQL_COUNT_QUERY_ALL = HQL_COUNT + HQL_LIST_QUERY_CONDITION;

    
    
    
    
  
    /**
     * <p></p>
     * 
     * 
     */
    @Override
    public List<People> query(int pn, int pageSize, People command) {
        return list(HQL_LIST_QUERY_ALL, pn, pageSize, getQueryParam(command));
    }

    @Override
    public int countQuery(People command) {
        return this.<Number>aggregate(HQL_COUNT_QUERY_ALL, getQueryParam(command)).intValue();
    }
    
    
    private Object[] getQueryParam(People command) {
        //TODO 改成全文索引
        String usernameLikeStr = "%" + command.getUsername() + "%";
        return new Object[]{
            usernameLikeStr
        };
    }

}
