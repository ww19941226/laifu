package com.laifu.module.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.pagination.Page;
import com.laifu.module.entity.Community;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.Financial;
import com.laifu.module.entity.House;
import com.laifu.module.entity.Notify;
import com.laifu.module.entity.Notifyvisit;
import com.laifu.module.entity.Payment;
import com.laifu.module.entity.Paymenttype;
import com.laifu.module.entity.Repair;
import com.laifu.module.entity.User;
import com.laifu.module.entity.Usertype;
import com.laifu.module.vo.ComplainsVo;
import com.laifu.module.vo.HouseVo;
import com.laifu.module.vo.PaymentVo;
import com.laifu.module.vo.PaymentsVo;
import com.laifu.module.vo.RepairVo;

public interface PropertyService {
	
	
	public List<Notifyvisit> getNotifyvisitList(String hql);
	
	/**
	 * 获取缴费类型
	 * 
	 * @param paymenttype_id
	 * @return
	 */
	public void updatePaymenttype(Paymenttype paymenttype);
	
	
	/**
	 * 获取缴费类型
	 * 
	 * @param paymenttype_id
	 * @return
	 */
	public Paymenttype getPaymenttype(int paymenttype_id);
	
	/**
	 * 添加缴费类型
	 * 
	 * @param paymenttype
	 */
	public void addPaymenttype(Paymenttype paymenttype);
	
	/**
	 * 通过hql获取报表
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public int getPaymentCount(String hql) throws Exception;
	
	/**
	 * 获取总和
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public Double getPaymentSum(String hql) throws Exception;

	/**
	 * 获取payment_id
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getPayment_id() throws Exception;

	/**
	 * 添加业主s
	 * 
	 * @param user
	 * @throws Exception
	 */
	public void addUser(User user) throws Exception;

	/**
	 * 更新业主
	 * 
	 * @param hql
	 * @throws Exception
	 */
	public void updateUser(String hql) throws Exception;

	/**
	 * 更新小区信息
	 * 
	 * @param community
	 * @throws Exception
	 */
	public void updateCommunity(Community community) throws Exception;

	/**
	 * 通过id删除用户
	 * 
	 * @param user_id
	 * @throws Exception
	 */
	public void deleteUser(int user_id) throws Exception;

	/**
	 * 通过id获取小区
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Community getCommunity(int id) throws Exception;

	/**
	 * 添加投诉
	 * 
	 * @param request
	 * @param usertype
	 * @param complain
	 */
	public void addComplain(User user, int usertype, Complains complain)
			throws Exception;

	/**
	 * 获取投诉建议的列表
	 * 
	 * @param user_id
	 * @param pn
	 * @param size
	 * @return
	 * @throws Exception
	 */
	public List<Complains> getComplainsList(Integer user_id, int pn, int size)
			throws Exception;

	/**
	 * 获取所有的用户类型
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Usertype> getUserTypeList() throws Exception;

	/**
	 * 通过id获取单个投诉内容
	 * 
	 * @param complainId
	 * @return
	 * @throws Exception
	 */
	public Complains getComplain(int complainId) throws Exception;

	/**
	 * 通过楼号和房号获取该楼房信息
	 * 
	 * @param houseFloorNum
	 * @param houseRoomNum
	 * @return
	 * @throws Exception
	 */
	public House getRoom(String houseFloorNum, String houseRoomNum)
			throws Exception;

	/**
	 * 查询房屋是否存在
	 * 
	 * @param houseFloorNum
	 * @return
	 * @throws Exception
	 */
	public boolean exitHouse(String houseFloorNum) throws Exception;

	/**
	 * 进行报修操作
	 * 
	 * @param repair
	 * @throws Exception
	 */
	public void addRepair(Repair repair) throws Exception;

	/**
	 * 获取缴费历史纪录
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	public List<PaymentsVo> getPaymentList(String hql) throws Exception;

	/**
	 * 获取最新的通知公告
	 * 
	 * @return
	 * @throws Exception
	 */
	public Notify getNewestNotify() throws Exception;
	
	/**
	 * 删除费用类型
	 * @param paymenttypeId
	 */
	public void deletePaymenttype(int paymenttypeId);
	

	

	/*******************************************************************************/

	public List<Payment> getPaymentList();

	public void deletePayment(Payment payment);

	public void addPayment(Payment payment);

	public List<Notify> getNotifyList();

	public void addNotify(Notify notify);

	public void updateNotify(Notify notify);

	public void deleteNotify(Notify notify);

	public void updateRepair(Repair repair);

	public void deleteRepair(Repair repair);

	public List<Repair> getRepairList();

	public void updateFinancial(Financial financial);

	public void deleteFinancial(Financial financial);

	public List<Financial> getFinancialList();

	public void updateUser(User user);

	/* 添加财务统计 */
	public void addFinancial(Financial financial, String datetime, int userId);

	/* 得到用户列表 */
	public List<User> getUserList();

	/* 得到维修详情 */
	public RepairVo getRepairDetail(int repairId);

	/* 更新维修信息 */
	public void updateRepair(int repairId, int state);

	/* 得到通知详情 */
	public Notify getNotify(int notifyId);

	/* 得到财务详情 */

	public Financial getFinancial(int financialId);

	/* 得到建议详情 */

	/* 得到费用详情 */
	public Payment getPayment(int paymentId);

	/* 得到费用类型 */
	public Paymenttype getPaymentType(int paymenttypeId);

	/* 得到费用类型列表 */
	public List<Paymenttype> getPaymenttypeList();

	/* 添加费用 */
	public void addPayment(HttpServletRequest request, PaymentVo paymentvo)
			throws Exception;

	/* 得到用户 */
	public User getUser(int userid);

	/* 修改费用 */
	public void updatePayment(HttpServletRequest request, PaymentVo paymentvo,
			int paymenttypeId) throws Exception;

	/* 更新缴费状态信息 */
	public void updatePaymentState(Payment payment);

	/* 导入excel表方法 */
	public void addSample(MultipartFile file, Model model) throws IOException;

	/* 到处excel表方法 */
	public void exportExcel() throws Exception;
	
	/*获取最新的N条通知数据*/
	public List<Notify> getTopNotifyList(int number);
	
	/*获取费用类型的前三条数据*/
	public List<Paymenttype> getPaymenttypeList(int number);

	/****************************************************************************************/

	public Page<Community> community_listAll(String hql, int pn, int pageSize);

	public Page<Complains> complains_listAll(String hql, int pn, int pageSize);

	public Page<ComplainsVo> complainsVo_listAll(String hql, int pn,
			int pageSize);

	public Page<Financial> financial_listAll(String hql, int pn, int pageSize);

	public Page<Notify> notify_listAll(String hql, int pn, int pageSize);

	public Page<PaymentsVo> paymentVo_listAll(String hql, int pn, int pageSize);

	public Page<Payment> payment_listAll(String hql, int pn, int pageSize);

	public Page<Paymenttype> paymenttype_listAll(String hql, int pn,
			int pageSize);

	public Page<Repair> repair_listAll(String hql, int pn, int pageSize);

	public Page<RepairVo> repairVo_listAll(String hql, int pn, int pageSize);

	public Page<HouseVo> HouseVo_listAll(String hql, int pn, int pageSize);
	
	/**
	 * 进行查看管理
	 * 
	 * @param vis
	 */
	public void addNotifyvisit(Notifyvisit vis);
	
	public int getNotifyvisit_count(int user_id);

}
