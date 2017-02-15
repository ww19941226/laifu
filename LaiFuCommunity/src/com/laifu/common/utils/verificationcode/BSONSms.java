package com.laifu.common.utils.verificationcode;

/**
 * 类作用:发送短信验证码
 * 
 * @author zepeng date: 2016-9-21
 */
public class BSONSms {

	// 使用RestAPI前必须先初始化，KEY可在Bmob应用信息里查询。
	private static void initBmob() {
		Bmob.initBmob("6fe7fb4c95aab82551625bca29b1ff81",
				"2a775abe4cd35067ea1984b375f8768a");
	}

	/**
	 * @param phoneNumber
	 *            手机号
	 * @param smsContent
	 *            短信内容,验证码自动生成并添加进这个参数里
	 * @example:"18318260159","您的验证码是:125214, 有效期是10分钟。"
	 */
	public static void requestSms(String phoneNumber, String smsContent) {
		initBmob();

		//String res = Bmob.requestSms(phoneNumber, smsContent);
		//System.out.println(res);

	}

}
