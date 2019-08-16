package com.video.manager.common;

public class Configure {
	private static String key = "TgyJpbqnUn45aLizzgdAJsaMyyRiHqfH";

	//小程序ID	
	private static String appID = "12wx4ff80123a9d7c7f8ff12";
	//商户号
	private static String mch_id = "1215198012031112";
	//
	private static String secret = "12d48f800125f7ab92421b8ee9b8604dbc7512";

	private static String spbill_create_ip = "47.105.90.172";

	private static String mch_appid = "12ww4ca1272a98aa7c8bfd12";//商户账号appid

	public static String getMch_appid() {
		return mch_appid;
	}

	public static void setMch_appid(String mch_appid) {
		Configure.mch_appid = mch_appid;
	}

	public static String getSecret() {
		return secret;
	}

	public static void setSecret(String secret) {
		Configure.secret = secret;
	}

	public static String getKey() {
		return key;
	}

	public static void setKey(String key) {
		Configure.key = key;
	}

	public static String getAppID() {
		return appID;
	}

	public static void setAppID(String appID) {
		Configure.appID = appID;
	}

	public static String getMch_id() {
		return mch_id;
	}

	public static void setMch_id(String mch_id) {
		Configure.mch_id = mch_id;
	}

	public static String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public static void setSpbill_create_ip(String spbill_create_ip) {
		Configure.spbill_create_ip = spbill_create_ip;
	}
}
