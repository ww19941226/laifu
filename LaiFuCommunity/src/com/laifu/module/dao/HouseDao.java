package com.laifu.module.dao;

import com.laifu.common.dao.IBaseDao;
import com.laifu.module.entity.House;

/**
 * 
 * @author Raindrops
 * 
 */
public interface HouseDao extends IBaseDao<House, Integer> {

	/**
	 * 通过楼号和房号获取该楼房信息
	 * 
	 * @param houseFloorNum
	 * @param houseRoomNum
	 * @return
	 * @throws Exception
	 */
	public House getRoom(String houseFloorNum, String houseRoomNum) throws Exception;
	
	/**
	 * 判断该房屋是否存在
	 * 
	 * @param houseFloorNum
	 * @return
	 * @throws Exception
	 */
	public boolean exitHouse(String houseFloorNum) throws Exception;
	
	/******************************************************************************************************/
	
	/**
	 * 根据楼号，房号查询房屋id
	 */
	public int getHouseid(String houseFloorNum, String houseRoomNum,
			Integer user_id);

}
