package cn.sf.qrcode.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Hashtable;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/10
 */
public class QrCodeUtil2 {

    private static final String CHARSET = "utf-8";
    // 二维码尺寸  
    private static final int BG_WIDTH = 320;
    private static final int BG_HEIGHT = 600;
    private static final int QRCODE_SIZE = 200;

    public static BufferedImage createBg(String name, Boolean personShow, Boolean alipayShow) throws Exception {
        
        BufferedImage image = new BufferedImage(BG_WIDTH, BG_HEIGHT, BufferedImage.TYPE_INT_RGB);   
        Graphics2D g2 = (Graphics2D)image.getGraphics();   
        g2.setBackground(new Color(92,147,240));
        g2.clearRect(0, 0, BG_WIDTH, BG_HEIGHT);   
        g2.setPaint(Color.WHITE);

        byte[] title = new String("长按识别二维码").getBytes("utf-8");
        Font font1 = new Font("SimHei", Font.BOLD, 25);
        g2.setFont(font1);
        double x1 = (BG_WIDTH - QRCODE_SIZE) / 2;   
        double y1 = (BG_HEIGHT - QRCODE_SIZE) / 7;  
        g2.drawString(new String(title), (int)x1, (int)y1);
        
        Font font = new Font("SimHei", Font.PLAIN, 15);

        if(alipayShow) {
        	
        	if(name == null || name.isEmpty()) {
        		name = "***";
        	} 
        	name = "向 " + name + " 付款";
        	byte[] names = new String(name).getBytes("utf-8");
        	double x = (BG_WIDTH - QRCODE_SIZE);   
        	double y =  (BG_HEIGHT - QRCODE_SIZE) /5;
        	Graphics2D g3 = (Graphics2D)image.getGraphics();   
        	g3.drawString(new String(names), (int)x, (int)y);   
        	g3.setFont(font);
        }
        
        
        if(personShow) {
            name = "加我为好友";
            byte[] names = new String(name).getBytes("utf-8");
            double x = (BG_WIDTH - QRCODE_SIZE);   
            double y =  BG_HEIGHT /5*2.8;
            Graphics2D g3 = (Graphics2D)image.getGraphics();   
            g3.drawString(new String(names), (int)x, (int)y);   
            g3.setFont(font);
        } 
        return image;
    }
    
    
   

    /**
     * 插入二维码
     * @param bgImge 背景图片
     * @param qrcode 二维码
     * @return
     * @throws Exception
     */
    public static BufferedImage insertImage(BufferedImage bgImge, Image qrcode, Image alipayQrcode) throws Exception {

    	// 插入二维码
    	if(qrcode != null) {
    		
    		int width = qrcode.getWidth(null);
    		int height = qrcode.getHeight(null);
    		Graphics2D graph = bgImge.createGraphics();
    		int x = (BG_WIDTH - width) / 2;
    		int y = (BG_HEIGHT - height) / 4;
    		graph.drawImage(qrcode, x, y, width, height, null);
    		graph.dispose();
    	}
        
    	if(alipayQrcode != null) {
	        // 插入二维码
	        int width2 = alipayQrcode.getWidth(null);
	        int height2 = alipayQrcode.getHeight(null);
	        Graphics2D graph2 = bgImge.createGraphics();
	        int x2 = (BG_WIDTH - width2)/2 ;
	        int y2 = BG_HEIGHT /5*3;
	        graph2.drawImage(alipayQrcode, x2, y2, width2, height2, null);
	        graph2.dispose();
    	}
        return bgImge;
    }
    
    
    public static BufferedImage createQrcode(String url) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        return image;
    }

  

}
