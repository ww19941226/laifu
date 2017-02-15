package com.laifu.module.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.pagination.Page;
import com.laifu.common.pagination.PageUtil;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.ComplainsDao;
import com.laifu.module.dao.FinancialDao;
import com.laifu.module.dao.HouseDao;
import com.laifu.module.dao.NotifyDao;
import com.laifu.module.dao.NotifyvisitDao;
import com.laifu.module.dao.PaymentDao;
import com.laifu.module.dao.PaymenttypeDao;
import com.laifu.module.dao.RepairDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.dao.UsertypeDao;
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
import com.laifu.module.service.PropertyService;
import com.laifu.module.vo.ComplainsVo;
import com.laifu.module.vo.HouseVo;
import com.laifu.module.vo.PaymentVo;
import com.laifu.module.vo.PaymentsVo;
import com.laifu.module.vo.RepairVo;

@Service("PropertyService")
public class PropertyServiceImpl extends BaseServiceImpl implements
		PropertyService {

	@Autowired
	@Qualifier("PaymentDao")
	private PaymentDao paymentDao;

	@Autowired
	@Qualifier("NotifyvisitDao")
	private NotifyvisitDao notifyvisitDao;

	@Autowired
	@Qualifier("PaymenttypeDao")
	private PaymenttypeDao paymenttypeDao;

	@Autowired
	@Qualifier("NotifyDao")
	private NotifyDao notifyDao;

	@Autowired
	@Qualifier("RepairDao")
	private RepairDao repairDao;

	@Autowired
	@Qualifier("FinancialDao")
	private FinancialDao financialDao;

	@Autowired
	@Qualifier("UserDao")
	private UserDao userDao;

	@Autowired
	@Qualifier("HouseDao")
	private HouseDao houseDao;

	@Autowired
	@Qualifier("ComplainsDao")
	private ComplainsDao complainsDao;

	@Autowired
	@Qualifier("UsertypeDao")
	private UsertypeDao usertypeDao;

	@Autowired
	@Qualifier("CommunityDao")
	private CommunityDao communityDao;

	@Override
	public void setBaseDaoImpl(IBaseDao baseDao) {

	}

	/**************************************************************************************************************/

	@Override
	public List<Notifyvisit> getNotifyvisitList(String hql) {
		return notifyvisitDao.listAll(hql, -1, -1);
	}

	/**
	 * 获取缴费类型
	 * 
	 * @param paymenttype_id
	 * @return
	 */
	@Override
	public void updatePaymenttype(Paymenttype paymenttype) {
		paymenttypeDao.update(paymenttype);
	}

	/**
	 * 获取缴费类型
	 * 
	 * @param paymenttype_id
	 * @return
	 */
	@Override
	public Paymenttype getPaymenttype(int paymenttype_id) {
		return paymenttypeDao.get(paymenttype_id);
	}

	/**
	 * 添加缴费类型
	 * 
	 * @param paymenttype
	 */
	@Override
	public void addPaymenttype(Paymenttype paymenttype) {
		paymenttypeDao.save(paymenttype);
	}

	/**
	 * 通过hql获取报表
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getPaymentCount(String hql) throws Exception {
		return paymentDao.countAll(hql);
	}

	/**
	 * 获取总和
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	@Override
	public Double getPaymentSum(String hql) throws Exception {
		return paymentDao.getSum(hql);
	}

	/**
	 * 添加业主s
	 * 
	 * @param user
	 * @throws Exception
	 */
	@Override
	public void addUser(User user) throws Exception {
		userDao.save(user);
	}

	/**
	 * 更新业主
	 * 
	 * @param hql
	 * @throws Exception
	 */
	@Override
	public void updateUser(String hql) throws Exception {
		userDao.update(hql);
	}

	/**
	 * 更新小区信息
	 * 
	 * @param community
	 * @throws Exception
	 */
	@Override
	public void updateCommunity(Community community) throws Exception {
		try {
			String hql = "update Community set community_name='"
					+ community.getCommunity_name() + "', community_location='"
					+ community.getCommunity_location()
					+ "', community_indirect='"
					+ community.getCommunity_indirect()
					+ "' where community_id=" + community.getCommunity_id();
			communityDao.update(hql);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 通过id删除用户
	 * 
	 * @param user_id
	 * @throws Exception
	 */
	public void deleteUser(int user_id) throws Exception {
		userDao.delete(user_id);
	}

	/**
	 * 通过id获取小区
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@Override
	public Community getCommunity(int id) throws Exception {
		try {
			return communityDao.get(id);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	/**
	 * 添加投诉
	 * 
	 * @param request
	 * @param usertype
	 * @param complain
	 */
	@Override
	public void addComplain(User user, int usertype, Complains complain)
			throws Exception {
		User u = userDao.get(user.getUser_id());
		Complains newComplain = new Complains();
		newComplain.setComplains_title(complain.getComplains_title());
		newComplain.setComplains_content(complain.getComplains_content());
		newComplain.setComplains_datetime(new Date());
		newComplain.setComplains_phone(u.getUser_account());
		newComplain.setComplains_usertype(usertype);
		newComplain.setComplains_userid(user.getUser_id());
		newComplain.setComplains_state(0); /* 0-未处理状态 */
		complainsDao.save(newComplain);
	}

	/**
	 * 获取投诉建议的列表
	 * 
	 * @param user_id
	 * @param pn
	 * @param size
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Complains> getComplainsList(Integer user_id, int pn, int size)
			throws Exception {
		return complainsDao
				.listAll(
						"from Complains where complains_userid=? order by complains_id desc",
						pn, size, user_id);
	}

	/**
	 * 获取所有的用户类型
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<Usertype> getUserTypeList() throws Exception {
		return usertypeDao.listAll("from Usertype ut where usertype_id != 4",
				-1, -1);
	}

	/**
	 * 通过id获取单个投诉内容
	 * 
	 * @param complainId
	 * @return
	 * @throws Exception
	 */
	@Override
	public Complains getComplain(int complainId) throws Exception {
		return complainsDao.get(complainId);
	}

	/**
	 * 通过楼号和房号获取该楼房信息
	 * 
	 * @param houseFloorNum
	 * @param houseRoomNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public House getRoom(String houseFloorNum, String houseRoomNum)
			throws Exception {
		return houseDao.getRoom(houseFloorNum, houseRoomNum);
	}

	@Override
	public boolean exitHouse(String houseFloorNum) throws Exception {
		return houseDao.exitHouse(houseFloorNum);
	}

	/**
	 * 进行报修操作
	 * 
	 * @param repair
	 * @throws Exception
	 */
	@Override
	public void addRepair(Repair repair) throws Exception {
		repairDao.add(repair);
	}

	/**
	 * 获取缴费历史纪录
	 * 
	 * @param hql
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<PaymentsVo> getPaymentList(String hql) throws Exception {
		List<Object[]> list = paymentDao.listAll(hql, -1, -1);
		List<PaymentsVo> voList = new ArrayList<PaymentsVo>();
		int month = -1;
		for (int i = 0; i < list.size(); i++) {
			PaymentsVo vo = new PaymentsVo((Payment) list.get(i)[0],
					(Paymenttype) list.get(i)[1]);
			if (month == -1)
				month = vo.getPayment().getPayment_endtime().getMonth();
			if (vo.getPayment().getPayment_endtime().getMonth() != month) {
				voList.add(null);
				month = vo.getPayment().getPayment_endtime().getMonth();
			}
			voList.add(vo);
		}
		return voList;
	}

	/**
	 * 获取最新的通知公告
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public Notify getNewestNotify() {
		List<Notify> list = notifyDao.listAll(
				"from Notify order by notify_id desc", 0, 1);
		if (list.size() > 0)
			return list.get(0);
		else
			return new Notify();
	}

	/**
	 * 获取最新的number条数据
	 * 
	 * @see com.laifu.module.service.PropertyService#getTopNotifyList(int)
	 */
	public List<Notify> getTopNotifyList(int number) {

		return notifyDao.getTopNotify(number);
	}

	/**
	 * 获取费用类型的前number条数据
	 */
	public List<Paymenttype> getPaymenttypeList(int number) {
		return paymenttypeDao.getTopPaymenttype(number);
	}

	/**
	 * 删除费用类型
	 */
	public void deletePaymenttype(int paymenttypeId) {
		paymenttypeDao.delete(paymenttypeId);
	}

	/************************************************************************************/

	@Override
	public List<Payment> getPaymentList() {
		return paymentDao.getList();
	}

	@Override
	public void deletePayment(Payment payment) {
		paymentDao.delete(payment);
	}

	@Override
	public void addPayment(Payment payment) {
		paymentDao.add(payment);
	}

	@Override
	public List<Notify> getNotifyList() {
		return notifyDao.listAll();
	}

	@Override
	public void addNotify(Notify notify) {
		notifyDao.save(notify);
	}

	@Override
	public void updateNotify(Notify notify) {
		notifyDao.update(notify);
	}

	@Override
	public void deleteNotify(Notify notify) {
		notifyDao.deleteObject(notify);
	}

	@Override
	public void updateRepair(Repair repair) {
		repairDao.update(repair);
	}

	@Override
	public void deleteRepair(Repair repair) {
		repairDao.delete(repair);
	}

	@Override
	public List<Repair> getRepairList() {
		return repairDao.getList();
	}

	@Override
	public void updateFinancial(Financial financial) {
		financialDao.update(financial);
	}

	@Override
	public void deleteFinancial(Financial financial) {
		financialDao.delete(financial);
	}

	@Override
	public List<Financial> getFinancialList() {
		return financialDao.getList();
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	/**
	 * 添加财务统计
	 */
	public void addFinancial(Financial financial, String datetime, int userId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date date = sdf.parse(datetime);
			financial.setFinancial_datetime(date);
			financial.setFinancial_userid(userId);
			financialDao.add(financial);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到用户列表
	 * 
	 * @return 用户列表
	 */
	public List<User> getUserList() {
		return userDao.getList();
	}

	/**
	 * 得到维修详情
	 * 
	 * @return 维修详情
	 */
	public RepairVo getRepairDetail(int repairId) {
		/**
		 * 1、通过维修id查找到维修信息表的内容,进而查到到用户表,房屋表 2、将这些数据封装到vo对象里,然后在前端进行显示
		 */
		Repair repair = repairDao.get(repairId);
		User user = userDao.get(repair.getRepair_userid());
		House house = houseDao.get(user.getUser_house());
		RepairVo repairVo = new RepairVo(repair, user, house);
		return repairVo;
	}

	/**
	 * 更新维修信息
	 * 
	 * @param 维修id
	 **/
	public void updateRepair(int repairId, int state) {
		System.out.println(state);
		Repair repair = repairDao.get(repairId);
		Date date = new Date();
		repair.setRepair_state(state);
		repair.setRepair_completetime(date);
		repairDao.update(repair);
	}

	/**
	 * 得到通知信息
	 * 
	 * @param 通知id
	 */
	public Notify getNotify(int notifyId) {
		return notifyDao.get(notifyId);
	}

	/**
	 * 得到财务信息
	 */
	@Override
	public Financial getFinancial(int financialId) {
		// TODO Auto-generated method stub
		return financialDao.get(financialId);
	}

	/**
	 * 得到费用详情
	 */
	public Payment getPayment(int paymentId) {
		return paymentDao.get(paymentId);
	}

	/**
	 * 得到费用类型
	 */
	public Paymenttype getPaymentType(int paymenttypeId) {

		return paymenttypeDao.get(paymenttypeId);
	}

	/**
	 * 得到费用类型列表
	 */
	public List<Paymenttype> getPaymenttypeList() {
		return paymenttypeDao.getPaymenttypeList();
	}

	/**
	 * 添加费用
	 * 
	 * @throws Exception
	 */
	public void addPayment(HttpServletRequest request, PaymentVo paymentvo)
			throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date starttimeDate = formatter.parse(paymentvo
					.getPayment_starttime());
			Date endtimeDate = formatter.parse(paymentvo.getPayment_endtime());
			Date deadtimeDate = formatter
					.parse(paymentvo.getpayment_deadtime());
			int payment_type = paymentvo.getPaymenttype().getPaymenttype_id();
			User user = userDao.getByAccount(paymentvo.getPhone_number());
			Payment payment = new Payment();
			payment.setPayment_id(getPayment_id());
			payment.setPayment_starttime(starttimeDate);
			payment.setPayment_endtime(endtimeDate);
			payment.setPayment_deadtime(deadtimeDate);
			payment.setPayment_type(payment_type);
			payment.setPayment_userid(user.getUser_id());
			payment.setPayment_units(paymentvo.getPayment_units());
			payment.setPayment_id(getPayment_id());
			paymentDao.save(payment);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取payment_id
	 * 
	 * @return
	 * @throws Exception
	 */
	@Override
	public int getPayment_id() throws Exception {
		int count = paymentDao.countAll() + 1;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		return Integer.parseInt(format.format(new Date())) + count;
	}

	/**
	 * 得到用户
	 */
	public User getUser(int userid) {
		return userDao.get(userid);
	}

	/**
	 * 修改费用
	 * 
	 * @throws Exception
	 */
	public void updatePayment(HttpServletRequest request, PaymentVo paymentvo,
			int paymenttypeId) throws Exception {
		Payment payment = paymentDao.get(paymentvo.getPayment_id());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date starttimeDate = formatter.parse(paymentvo
					.getPayment_starttime());
			Date endtimeDate = formatter.parse(paymentvo.getPayment_endtime());
			Date deadtimeDate = formatter
					.parse(paymentvo.getpayment_deadtime());
			User user = userDao.getByAccount(paymentvo.getPhone_number());

			payment.setPayment_starttime(starttimeDate);
			payment.setPayment_endtime(endtimeDate);
			payment.setPayment_deadtime(deadtimeDate);
			payment.setPayment_type(paymenttypeId);
			payment.setPayment_userid(user.getUser_id());
			payment.setPayment_units(paymentvo.getPayment_units());

			paymentDao.update(payment);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 更新缴费状态信息
	 */
	public void updatePaymentState(Payment payment) {
		String str = new SimpleDateFormat("yyyy-MM-dd").format(payment
				.getPayment_complettime());
		String hql = "update Payment set payment_paystate=1, payment_complettime='"
				+ str + "' where payment_id=" + payment.getPayment_id();
		paymentDao.execute(hql);
	}

	/****************************************************************************************/

	public Page<Community> community_listAll(String hql, int pn, int pageSize) {
		Integer count = communityDao.countAll("select count(*) " + hql);
		List<Community> items = communityDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<ComplainsVo> complainsVo_listAll(String hql, int pn,
			int pageSize) {
		Integer count = complainsDao.countAll("select count(*) " + hql);
		List<Object[]> list = complainsDao.listAll(hql, pn, pageSize);
		List<ComplainsVo> items = new ArrayList<ComplainsVo>();
		for (int i = 0; i < list.size(); i++) {
			items.add(new ComplainsVo((Complains) list.get(i)[0],
					(Usertype) list.get(i)[1]));
		}
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<Complains> complains_listAll(String hql, int pn, int pageSize) {
		Integer count = complainsDao.countAll("select count(*) " + hql);
		List<Complains> items = complainsDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<Financial> financial_listAll(String hql, int pn, int pageSize) {
		Integer count = financialDao.countAll("select count(*) " + hql);
		List<Financial> items = financialDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<Notify> notify_listAll(String hql, int pn, int pageSize) {
		Integer count = notifyDao.countAll("select count(*) " + hql);
		List<Notify> items = notifyDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<PaymentsVo> paymentVo_listAll(String hql, int pn, int pageSize) {
		Integer count = paymentDao.countAll("select count(*) " + hql);
		List<Object[]> list = paymentDao.listAll(hql, pn, pageSize);
		List<PaymentsVo> items = new ArrayList<PaymentsVo>();
		for (int i = 0; i < list.size(); i++) {
			PaymentsVo vo = new PaymentsVo((Payment) list.get(i)[0],
					(Paymenttype) list.get(i)[1]);
			if (list.get(i).length > 2)
				vo.setUser((User) list.get(i)[2]);
			items.add(vo);
		}
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<Payment> payment_listAll(String hql, int pn, int pageSize) {
		Integer count = paymentDao.countAll("select count(*) " + hql);
		List<Payment> items = paymentDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	public Page<Paymenttype> paymenttype_listAll(String hql, int pn,
			int pageSize) {
		Integer count = paymentDao.countAll("select count(*) " + hql);
		List<Paymenttype> items = paymenttypeDao.listAll(hql, pn, pageSize);
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<Repair> repair_listAll(String hql, int pn, int pageSize) {
		// TODO Auto-generated method stub
		Integer count = repairDao.countAll("select count(*)" + hql);
		List<Repair> items = repairDao.listAll(hql, pn, pageSize);

		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<RepairVo> repairVo_listAll(String hql, int pn, int pageSize) {
		// TODO Auto-generated method stub
		Integer count = repairDao.countAll("select count(*)" + hql);
		List<Object[]> list = repairDao.listAll(hql, pn, pageSize);
		List<RepairVo> items = new ArrayList<RepairVo>();
		for (int i = 0; i < list.size(); i++) {
			RepairVo vo = new RepairVo((Repair) list.get(i)[0],
					(User) list.get(i)[1], (House) list.get(i)[2]);
			System.out.println(vo);
			items.add(vo);
		}
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	@Override
	public Page<HouseVo> HouseVo_listAll(String hql, int pn, int pageSize) {
		// TODO Auto-generated method stub
		Integer count = repairDao.countAll("select count(*)" + hql);
		List<House> list = houseDao.listAll(hql, pn, pageSize);
		List<HouseVo> items = new ArrayList<HouseVo>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		String sql = "(";
		for (int i = 0; i < list.size(); i++) {
			HouseVo vo = new HouseVo();
			vo.setHouse(list.get(i));
			items.add(vo);
			if (i != 0)
				sql += ",";
			sql += vo.getHouse().getUser_id();
			map.put(vo.getHouse().getHouse_id(), i);
		}
		sql += ")";

		/**
		 * 通过房屋获取对应的房主
		 */
		if (list.size() != 0) {
			hql = "from User where user_id in " + sql;
			List<User> users = userDao.listAll(hql, pn, pageSize);
			for (int i = 0; i < users.size(); i++) {
				int id = map.get(users.get(i).getUser_house());
				items.get(id).setUser(users.get(i));
			}
		}
		return PageUtil.getPage(count, pn, items, pageSize);
	}

	/**
	 * 导入excel表
	 */
	@Override
	public void addSample(MultipartFile file, Model model) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		String msg = null;
		// 获取excel文档
		HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
		// 获取excel文档的sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		// 获取excel文档最后一行，这里获取250多行，不准确的，在39行进行了是否有数据的判断
		int rowNum = sheet.getLastRowNum();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 遍历每行的数据
		// System.out.println(rowNum);
		for (int i = 1; i <= rowNum; i++) {
			try {
				Payment payment = new Payment();
				System.out.println(sheet.getRow(i).getCell(4)
						.getStringCellValue().toString());
				User user = userDao.getByAccount(sheet.getRow(i).getCell(4)
						.getStringCellValue().toString());
				/*
				 * System.out.println(sheet.getRow(i).getCell(4)
				 * .getStringCellValue().toString());
				 * System.out.println(sheet.getRow(i).getCell(5)
				 * .getStringCellValue().toString());
				 */
				String date_start = sheet.getRow(i).getCell(5)
						.getStringCellValue().toString();
				String date_end = sheet.getRow(i).getCell(6)
						.getStringCellValue().toString();
				String date_comple = sheet.getRow(i).getCell(8)
						.getStringCellValue().toString();
				String date_dead = sheet.getRow(i).getCell(7)
						.getStringCellValue().toString();

				if (date_start.equals(""))
					payment.setPayment_starttime(null);
				else
					payment.setPayment_starttime(format.parse(date_start));
				if (date_end.equals(""))
					payment.setPayment_endtime(null);
				else
					payment.setPayment_endtime(format.parse(date_end));
				if (date_comple.equals(""))
					payment.setPayment_complettime(null);
				else
					payment.setPayment_complettime(format.parse(date_comple));
				if (date_dead.equals(""))
					payment.setPayment_deadtime(null);
				else
					payment.setPayment_deadtime(format.parse(date_dead));

				payment.setPayment_id(getPayment_id());
				payment.setPayment_type((int) sheet.getRow(i).getCell(1)
						.getNumericCellValue());

				payment.setPayment_userid(user.getUser_id());
				payment.setPayment_units(sheet.getRow(i).getCell(9)
						.getNumericCellValue());
				if (sheet.getRow(i).getCell(10).getStringCellValue().toString()
						.equals("未缴费")) {
					payment.setPayment_paystate(0);
				} else
					payment.setPayment_type(1);
				paymentDao.save(payment);

			} catch (Exception e) {
				e.printStackTrace();
				if (msg == null) {
					msg = "";
				}
				msg += i + ",";
			}
			// System.out.println(33);
		}
		model.addAttribute("msg", msg);

	}

	@Override
	public void exportExcel() throws Exception {
		// TODO Auto-generated method stub
		// 生成一个工作簿
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("费用表");
		// 给单子名称一个长度
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = wb.createCellStyle();

		SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		HSSFDataFormat format = wb.createDataFormat();
		// 创建第一行（也可以称为表头）
		HSSFRow row = sheet.createRow(0);
		// 样式字体居中
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 给表头第一行一次创建单元格
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("费用编号");
		cell.setCellStyle(style);
		cell = row.createCell((short) 1);
		cell.setCellValue("缴费类型");
		cell.setCellStyle(style);
		cell = row.createCell((short) 2);
		cell.setCellValue("缴费项目");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("用户姓名");
		cell.setCellStyle(style);
		cell = row.createCell((short) 4);
		cell.setCellValue("用户账号");
		cell.setCellStyle(style);

		cell = row.createCell((short) 5);
		cell.setCellValue("费用起始时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 6);
		cell.setCellValue("费用结束时间");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("缴费截止时间");

		cell.setCellStyle(style);
		cell = row.createCell((short) 8);
		cell.setCellValue("缴费完成时间");

		cell.setCellStyle(style);
		cell = row.createCell((short) 9);
		cell.setCellValue("金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 10);
		cell.setCellValue("缴费状态");
		cell.setCellStyle(style);

		List<Payment> list = paymentDao.getList();

		for (short i = 0; i < list.size(); i++) {
			User user = userDao.get(list.get(i).getPayment_userid());
			Paymenttype paymenttype = paymenttypeDao.get(list.get(i)
					.getPayment_type());
			Date data_start = list.get(i).getPayment_starttime();
			Date data_comple = list.get(i).getPayment_complettime();
			Date data_end = list.get(i).getPayment_endtime();
			Date data_dead = list.get(i).getPayment_deadtime();
			row = sheet.createRow(i + 1);
			if (data_start == null)
				row.createCell(5).setCellValue("");
			else
				row.createCell(5).setCellValue(format2.format(data_start));
			if (data_end == null)
				row.createCell(6).setCellValue("");
			else
				row.createCell(6).setCellValue(format2.format(data_end));
			if (data_dead == null)
				row.createCell(7).setCellValue("");
			else
				row.createCell(7).setCellValue(format2.format(data_dead));
			if (data_comple == null)
				row.createCell(8).setCellValue("");
			else
				row.createCell(8).setCellValue(format2.format(data_comple));

			row.createCell(0).setCellValue(list.get(i).getPayment_id());
			row.createCell(1).setCellValue(list.get(i).getPayment_type());
			row.createCell(2).setCellValue(paymenttype.getPaymenttype_name());
			row.createCell(3).setCellValue(user.getUser_realname());
			row.createCell(4).setCellValue(user.getUser_account());
			row.createCell(9).setCellValue(list.get(i).getPayment_units());
			if (list.get(i).getPayment_paystate() == 0) {
				row.createCell(10).setCellValue("未缴费");
			} else
				row.createCell(10).setCellValue("已缴费");
			try {
				// 默认导出到D盘下
				FileOutputStream out = new FileOutputStream("D://费用表.xls");
				wb.write(out);
				out.close();

			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {

				e.printStackTrace();
			}

		}
	}

	/**
	 * 进行查看管理
	 * 
	 * @param vis
	 */
	@Override
	public void addNotifyvisit(Notifyvisit vis) {
		int count = notifyvisitDao
				.countAll("select count(*) from Notifyvisit where notifyvisit_userid="
						+ vis.getNotifyvisit_userid()
						+ " and notifyvisit_notifyid="
						+ vis.getNotifyvisit_notifyid());
		if (count == 0)
			notifyvisitDao.save(vis);
	}

	/**
	 * 进行查看管理
	 * 
	 * @param vis
	 */
	@Override
	public int getNotifyvisit_count(int user_id) {
		int count = notifyvisitDao
				.countAll("select count(*) from Notifyvisit as v, Notify as n where v.notifyvisit_userid="
						+ user_id + " and v.notifyvisit_notifyid=n.notify_id");
		int sum = notifyvisitDao.countAll("select count(*) from Notify");
		return sum - count;
	}
}
