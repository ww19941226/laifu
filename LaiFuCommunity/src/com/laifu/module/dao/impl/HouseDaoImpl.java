package com.laifu.module.dao.impl;

import org.springframework.stereotype.Repository;

import com.laifu.common.dao.impl.BaseDaoImpl;
import com.laifu.module.dao.HouseDao;
import com.laifu.module.entity.House;

/**
 * 
 * @author Raindrops
 * 
 */
@Repository("HouseDao")
public class HouseDaoImpl extends BaseDaoImpl<House, Integer> implements
		HouseDao {

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
		String hql = "from House where house_roomnumber='" + houseRoomNum
				+ "' and house_floornumber='" + houseFloorNum + "'";
		return aggregate(hql);
	}
	
	/**
	 * 判断该房屋是否存在
	 * 
	 * @param houseFloorNum
	 * @return
	 * @throws Exception
	 */
	@Override
	public boolean  exitHouse(String houseFloorNum) throws Exception {
		String hql = "from House where house_floornumber='" + houseFloorNum + "'";
		return this.listAll(hql, -1, -1).size() != 0;
	}

	/****************************************************************************************************************/

	@Override
	public int getHouseid(String houseFloorNum, String houseRoomNum,
			Integer user_id) {
		// TODO Auto-generated method stub
		String hqlString = "from House where house_floornumber="
				+ houseFloorNum + " and house_roomnumber=" + houseRoomNum
				+ "and user_id=" + user_id;
		House house = (House) getSession().createQuery(hqlString)
				.uniqueResult();
		int id = house.getHouse_id();
		return id;
	}

}
