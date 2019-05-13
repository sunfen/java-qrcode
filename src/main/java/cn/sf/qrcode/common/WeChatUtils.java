package cn.sf.qrcode.common;


import java.security.MessageDigest;


public class WeChatUtils {
	
	
	public static String getSignature(String timestamp, String nonceStr, String url, String jsapiTicket) throws Exception {
		try {
			String str = String.format("jsapi_ticket=%s&noncestr=%s&timestamp=%s&url=%s", jsapiTicket,nonceStr, timestamp, url);
			// SHA1签名生成
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(str.getBytes());
			byte[] digest = md.digest();

			StringBuffer hexstr = new StringBuffer();
			String shaHex = "";
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			return hexstr.toString();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
	   }
	
	

    
 
}
