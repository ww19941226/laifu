package com.laifu.module.service.impl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.laifu.common.dao.IBaseDao;
import com.laifu.common.service.impl.BaseServiceImpl;
import com.laifu.module.dao.CommentDao;
import com.laifu.module.dao.CommunityDao;
import com.laifu.module.dao.ComplainsDao;
import com.laifu.module.dao.FinancialDao;
import com.laifu.module.dao.HouseDao;
import com.laifu.module.dao.NotifyDao;
import com.laifu.module.dao.NotifyvisitDao;
import com.laifu.module.dao.PaymentDao;
import com.laifu.module.dao.PaymenttypeDao;
import com.laifu.module.dao.PeopleDao;
import com.laifu.module.dao.PraiseDao;
import com.laifu.module.dao.RepairDao;
import com.laifu.module.dao.TopicDao;
import com.laifu.module.dao.TopictypeDao;
import com.laifu.module.dao.UserDao;
import com.laifu.module.dao.UsertypeDao;
import com.laifu.module.entity.Complains;
import com.laifu.module.entity.People;
import com.laifu.module.entity.Usertype;
import com.laifu.module.service.PeopleService;

@Service("PeopleService")
public class PeopleServiceImpl extends BaseServiceImpl<People, Integer>
		implements PeopleService {

	// 打印日志
	private static final Logger LOGGER = LoggerFactory
			.getLogger(PeopleServiceImpl.class);
	@Autowired
	@Qualifier("NotifyvisitDao")
	private NotifyvisitDao notifyvisitDao;
	@Autowired
	@Qualifier("UserDao")
	private UserDao userDao;
	@Autowired
	@Qualifier("CommentDao")
	private CommentDao commentDao;
	@Autowired
	@Qualifier("CommunityDao")
	private CommunityDao communityDao;

	@Autowired
	@Qualifier("ComplainsDao")
	private ComplainsDao complainsDao;

	@Autowired
	@Qualifier("PraiseDao")
	private PraiseDao praiseDao;
	@Autowired
	@Qualifier("FinancialDao")
	private FinancialDao financialDao;

	@Autowired
	@Qualifier("HouseDao")
	private HouseDao houseDao;

	@Autowired
	@Qualifier("NotifyDao")
	private NotifyDao notifyDao;

	@Autowired
	@Qualifier("PaymentDao")
	private PaymentDao paymentDao;

	@Autowired
	@Qualifier("PaymenttypeDao")
	private PaymenttypeDao paymenttypeDao;

	@Autowired
	@Qualifier("RepairDao")
	private RepairDao repairDao;

	@Autowired
	@Qualifier("TopicDao")
	private TopicDao topicDao;

	@Autowired
	@Qualifier("TopictypeDao")
	private TopictypeDao topictypeDao;

	@Autowired
	@Qualifier("UsertypeDao")
	private UsertypeDao usertypeDao;

	private PeopleDao peopleDao;

	@Autowired
	@Qualifier("PeopleDao")
	@Override
	public void setBaseDaoImpl(IBaseDao<People, Integer> baseDao) {
		this.baseDao = baseDao;
		this.peopleDao = (PeopleDao) baseDao;
	}

	@Override
	public void test(Usertype usertype) {
		usertypeDao.save(usertype);
	}

	public void saveAll() {/*
							 * int account = 12345678; for(int i=0; i<1000; i++)
							 * { User user = new User();
							 * user.setUser_account("188" + account);
							 * user.setUser_password("123456");
							 * user.setUser_type(1);
							 * user.setUser_registertime(new java.util.Date());
							 * 
							 * if(i % 100 == 0)
							 * System.out.println(user.getUser_account());
							 * 
							 * userDao.save(user);
							 * 
							 * account ++;
							 * 
							 * 
							 * }
							 */
		/*
		 * try { userDao.update("delete from User where user_age=0"); } catch
		 * (UserException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		// for(int i=2; i<1002; i++) complainsDao.delete(i);
		/*
		  for(int i=0; i<1000; i++) { Complains c = new Complains();
		  c.setComplains_userid(1); c.setComplains_title("投诉" + i);
		  c.setComplains_content("投诉内容" + i); c.setComplains_datetime(new
		  Date()); c.setComplains_phone("18816713191");
		  c.setComplains_usertype(i%3+1); complainsDao.save(c); }
		 */

		/*
		 * for(int i=0; i<1000; i++) { Financial f = new Financial();
		 * f.setFinancial_datetime(new Date()); f.setFinancial_funds("交保护费" +
		 * i); f.setFinancial_money(233); f.setFinancial_userid(3);
		 * financialDao.save(f); }
		 */

		/*
		 * for(int k=1; k<23; k++) for(int i=1; i<23; i++) for(int j=0; j<60;
		 * j++) { House h = new House(); h.setCommunity_id(1);
		 * h.setHouse_prePrice(123); h.setHouse_area(233);
		 * h.setHouse_price(h.getHouse_area() * h.getHouse_prePrice());
		 * h.setHouse_floornumber(String.valueOf(k));
		 * h.setHouse_roomnumber(String.valueOf(i*100+j)); houseDao.save(h); }
		 */

		/*
		 * for(int i=0; i<1000; i++) {
		 * 
		 * Notify n = new Notify(); n.setNotify_datetime(new Date());
		 * n.setNotify_titile("日常通知" + i); n.setNotify_content("通知内容" + i);
		 * n.setNotify_userid(2); notifyDao.save(n); }
		 */

		try {/*
			 * for(int i=0; i<1000; i++) { SimpleDateFormat format = new
			 * SimpleDateFormat("yyyy-MM-dd"); Payment p = new Payment();
			 * p.setPayment_type((int)(Math.random()*3+1));
			 * p.setPayment_starttime(format.parse("2013-01-02"));
			 * p.setPayment_endtime(format.parse("2013-01-25"));
			 * p.setPayment_userid((int)(Math.random()*5+1));
			 * p.setPayment_complettime(format.parse("2013-01-30"));
			 * p.setPayment_deadtime(format.parse("2013-01-30"));
			 * p.setPayment_units(1023.25); p.setPayment_paystate(0);
			 * paymentDao.add(p); }
			 * 
			 * paymentDao.flush(); paymentDao.clear();
			 */
			/*
			 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			 * for(int i=0; i<1000; i++) { Repair r = new Repair();
			 * r.setRepair_userid((int)(Math.random()*6+1));
			 * r.setRepair_project("电脑 爆炸");
			 * r.setRepair_decldatatime(format.parse("2013-01-30"));
			 * r.setRepair_completetime(format.parse("2013-01-30"));
			 * r.setRepair_starttime(format.parse("2013-01-02"));
			 * r.setRepair_endtime(format.parse("2013-01-25"));
			 * repairDao.add(r); }
			 *//*
				 * repairDao.flush(); repairDao.clear();
				 */
			/*
			 * for(int i=0; i<(int)3*(1e5); i++) { Repair repair = new Repair();
			 * repair.setRepair_userid(1); repair.setRepair_completetime(new
			 * Date()); repair.setRepair_decldatatime(new Date());
			 * repair.setRepair_endtime(new Date());
			 * repair.setRepair_starttime(new Date());
			 * repair.setRepair_project("linjinquan"+i);
			 * repair.setRepair_state(0); repair.setRepair_reply(null);
			 * 
			 * repairDao.save(repair); if(i % 100 == 0) { repairDao.flush();
			 * repairDao.clear(); } if(i % 10000 == 0) {
			 * System.out.println(i/10000); } }
			 */

			userDao.execute("delete from User");

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	// for(int i=2; i<1002; i++) complainsDao.delete(i);

	/*
	 * }
	 * 
	 * //for(int i=2; i<1002; i++) complainsDao.delete(i); /*for(int i=0;
	 * i<1000; i++) { Complains c = new Complains(); c.setComplains_userid(1);
	 * c.setComplains_title("投诉" + i); c.setComplains_content("投诉内容" + i);
	 * c.setComplains_datetime(new Date()); c.setComplains_phone("18816713191");
	 * c.setComplains_usertype(i%3+1); complainsDao.save(c); }
	 */

	/*
	 * for(int i=0; i<1000; i++) { Financial f = new Financial();
	 * f.setFinancial_datetime(new Date()); f.setFinancial_funds("交保护费" + i);
	 * f.setFinancial_money(233); f.setFinancial_userid(3);
	 * financialDao.save(f); }
	 */

	/*
	 * for(int k=1; k<23; k++) for(int i=1; i<23; i++) for(int j=0; j<60; j++) {
	 * House h = new House(); h.setCommunity_id(1); h.setHouse_prePrice(123);
	 * h.setHouse_area(233); h.setHouse_price(h.getHouse_area() *
	 * h.getHouse_prePrice()); h.setHouse_floornumber(String.valueOf(k));
	 * h.setHouse_roomnumber(String.valueOf(i*100+j)); houseDao.save(h); }
	 */

	/*
	 * for(int i=0; i<1000; i++) {
	 * 
	 * Notify n = new Notify(); n.setNotify_datetime(new Date());
	 * n.setNotify_titile("日常通知" + i); n.setNotify_content("通知内容" + i);
	 * n.setNotify_userid(2); notifyDao.save(n); }
	 */

	/*
	 * try { for(int i=0; i<1000; i++) { SimpleDateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd"); Payment p = new Payment();
	 * p.setPayment_type((int)(Math.random()*3+1));
	 * p.setPayment_starttime(format.parse("2013-01-02"));
	 * p.setPayment_endtime(format.parse("2013-01-25"));
	 * p.setPayment_userid((int)(Math.random()*5+1));
	 * p.setPayment_complettime(format.parse("2013-01-30"));
	 * p.setPayment_deadtime(format.parse("2013-01-30"));
	 * p.setPayment_units(1023.25); p.setPayment_paystate(0); paymentDao.add(p);
	 * }
	 * 
	 * paymentDao.flush(); paymentDao.clear();
	 */
	/*
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); for(int
	 * i=0; i<1000; i++) { Repair r = new Repair();
	 * r.setRepair_userid((int)(Math.random()*6+1));
	 * r.setRepair_project("电脑 爆炸");
	 * r.setRepair_decldatatime(format.parse("2013-01-30"));
	 * r.setRepair_completetime(format.parse("2013-01-30"));
	 * r.setRepair_starttime(format.parse("2013-01-02"));
	 * r.setRepair_endtime(format.parse("2013-01-25")); repairDao.add(r); }
	 *//*
		 * repairDao.flush(); repairDao.clear();
		 */
	/*
	 * for(int i=0; i<(int)3*(1e5); i++) { Repair repair = new Repair();
	 * repair.setRepair_userid(1); repair.setRepair_completetime(new Date());
	 * repair.setRepair_decldatatime(new Date()); repair.setRepair_endtime(new
	 * Date()); repair.setRepair_starttime(new Date());
	 * repair.setRepair_project("linjinquan"+i); repair.setRepair_state(0);
	 * repair.setRepair_reply(null);
	 * 
	 * repairDao.save(repair); if(i % 100 == 0) { repairDao.flush();
	 * repairDao.clear(); } if(i % 10000 == 0) { System.out.println(i/10000); }
	 * }
	 */

	// userDao.execute("delete from User");
	/*
	 * String name = "hello,bye,yello"; int start = 0; while(name.length() > 0)
	 * { int end = name.indexOf(','); int len = name.length(); if(end == -1) end
	 * = len;
	 * 
	 * System.out.println(name.substring(start, end)); if(end < len) name =
	 * name.substring(end+1); }
	 * 
	 * 
	 * } catch(Exception e) { e.printStackTrace(); } }
	 */

	/*
	 * for(int i=0; i<1000; i++) { Complains c = new Complains();
	 * c.setComplains_userid(1); c.setComplains_title("投诉" + i);
	 * c.setComplains_content("投诉内容" + i); c.setComplains_datetime(new Date());
	 * c.setComplains_phone("18816713191"); c.setComplains_usertype(i%3+1);
	 * complainsDao.save(c); }
	 */

	/*
	 * for(int i=0; i<1000; i++) { Financial f = new Financial();
	 * f.setFinancial_datetime(new Date()); f.setFinancial_funds("交保护费" + i);
	 * f.setFinancial_money(233); f.setFinancial_userid(3);
	 * financialDao.save(f); }
	 */

	/*
	 * for(int k=1; k<23; k++) for(int i=1; i<23; i++) for(int j=0; j<60; j++) {
	 * House h = new House(); h.setCommunity_id(1); h.setHouse_prePrice(123);
	 * h.setHouse_area(233); h.setHouse_price(h.getHouse_area() *
	 * h.getHouse_prePrice()); h.setHouse_floornumber(String.valueOf(k));
	 * h.setHouse_roomnumber(String.valueOf(i*100+j)); houseDao.save(h); }
	 */

	/*
	 * for(int i=0; i<1000; i++) {
	 * 
	 * Notify n = new Notify(); n.setNotify_datetime(new Date());
	 * n.setNotify_titile("日常通知" + i); n.setNotify_content("通知内容" + i);
	 * n.setNotify_userid(2); notifyDao.save(n); }
	 */

	/*
	 * try { for(int i=0; i<1000; i++) { SimpleDateFormat format = new
	 * SimpleDateFormat("yyyy-MM-dd"); Payment p = new Payment();
	 * p.setPayment_type((int)(Math.random()*3+1));
	 * p.setPayment_starttime(format.parse("2013-01-02"));
	 * p.setPayment_endtime(format.parse("2013-01-25"));
	 * p.setPayment_userid((int)(Math.random()*5+1));
	 * p.setPayment_complettime(format.parse("2013-01-30"));
	 * p.setPayment_deadtime(format.parse("2013-01-30"));
	 * p.setPayment_units(1023.25); p.setPayment_paystate(0); paymentDao.add(p);
	 * }
	 * 
	 * paymentDao.flush(); paymentDao.clear();
	 */
	/*
	 * SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd"); for(int
	 * i=0; i<1000; i++) { Repair r = new Repair();
	 * r.setRepair_userid((int)(Math.random()*6+1));
	 * r.setRepair_project("电脑 爆炸");
	 * r.setRepair_decldatatime(format.parse("2013-01-30"));
	 * r.setRepair_completetime(format.parse("2013-01-30"));
	 * r.setRepair_starttime(format.parse("2013-01-02"));
	 * r.setRepair_endtime(format.parse("2013-01-25")); repairDao.add(r); }
	 *//*
		 * repairDao.flush(); repairDao.clear();
		 */
	/*
	 * for(int i=0; i<(int)3*(1e5); i++) { Repair repair = new Repair();
	 * repair.setRepair_userid(1); repair.setRepair_completetime(new Date());
	 * repair.setRepair_decldatatime(new Date()); repair.setRepair_endtime(new
	 * Date()); repair.setRepair_starttime(new Date());
	 * repair.setRepair_project("linjinquan"+i); repair.setRepair_state(0);
	 * repair.setRepair_reply(null);
	 * 
	 * repairDao.save(repair); if(i % 100 == 0) { repairDao.flush();
	 * repairDao.clear(); } if(i % 10000 == 0) { System.out.println(i/10000); }
	 * }
	 */

	// userDao.execute("delete from User");

	/*
	 * } catch(Exception e) { e.printStackTrace(); } }
	 */

	@Override
	public void addSample(MultipartFile file, Model model) throws IOException {
		// TODO Auto-generated method stub
		String msg = null;
		// 获取excel文档
		HSSFWorkbook wb = new HSSFWorkbook(file.getInputStream());
		// 获取excel文档的sheet
		HSSFSheet sheet = wb.getSheetAt(0);
		// 获取excel文档最后一行，这里获取250多行，不准确的，在39行进行了是否有数据的判断
		int rowNum = sheet.getLastRowNum();
		// 遍历每行的数据
		System.out.println(rowNum);
		for (int i = 1; i <= rowNum; i++) {
			try {

				System.out.println(22);
				People people = new People();
				people.setUsername(sheet.getRow(i).getCell(0)
						.getStringCellValue().toString());

				System.out.println(sheet.getRow(i).getCell(0)
						.getStringCellValue().toString());

				people.setPassword(sheet.getRow(i).getCell(1)
						.getStringCellValue());
				System.out.println(sheet.getRow(i).getCell(1)
						.getStringCellValue());

				Date date = sheet.getRow(i).getCell(2).getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy-MM-dd HH:mm:ss");
				people.setRegisterDate(date);
				System.out.println(sheet.getRow(i).getCell(2)
						.getDateCellValue());
				people.setEmail(sheet.getRow(i).getCell(3).getStringCellValue()
						.toString());
				System.out.println(sheet.getRow(i).getCell(3)
						.getStringCellValue().toString());
				System.out.println(people);
				peopleDao.save(people);

			} catch (Exception e) {
				e.printStackTrace();
				if (msg == null) {
					msg = "";
				}
				msg += i + ",";
			}
			System.out.println(33);
		}
		model.addAttribute("msg", msg);

	}
	/*
	 * @Override public Page<People> query(int pn, int pageSize, People command)
	 * { return PageUtil.getPage(peopleDao.countQuery(command) ,pn,
	 * peopleDao.query(pn, pageSize, command), pageSize); }
	 */

}
