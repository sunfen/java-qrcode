 package cn.sf.qrcode.common;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @author SunFen mail:1121788582@qq.com
 * @date 2019/04/10
 */
public class QrCodeUtil {

    private static final String CHARSET = "utf-8";
    private static final String FORMAT = "PNG";
    // 二维码尺寸  
    private static final int BG_WIDTH = 320;
    private static final int BG_HEIGHT = 570;
    private static final int QRCODE_SIZE = 200;

    private static BufferedImage createBg(String content) throws Exception {
        
        BufferedImage image = new BufferedImage(BG_WIDTH, BG_HEIGHT, BufferedImage.TYPE_INT_RGB);   
        Graphics2D g2 = (Graphics2D)image.getGraphics();   
        g2.setBackground(new Color(92,147,240));
        g2.clearRect(0, 0, BG_WIDTH, BG_HEIGHT);   
        g2.setPaint(Color.WHITE);

        String title = "长按二维码识别付款";
        Font font1 = new Font("微软雅黑", Font.BOLD, 25);
        g2.setFont(font1);
        double x1 = (BG_WIDTH - QRCODE_SIZE) / 2;   
        double y1 = (BG_HEIGHT - QRCODE_SIZE) / 4;  
        g2.drawString(title, (int)x1, (int)y1);
        
        Font font = new Font("微软雅黑", Font.PLAIN, 10);
        if(content == null || content.isEmpty()) {
            content = "***";
        } 
        content = "向 " + content + " 付款";
        double x = (BG_WIDTH - QRCODE_SIZE);   
        double y = BG_HEIGHT - (BG_HEIGHT - QRCODE_SIZE) / 3 - 15;
        Graphics2D g3 = (Graphics2D)image.getGraphics();   
        g3.drawString(content, (int)x, (int)y);   
        g3.setFont(font);
        return image;
    }
    
    private static BufferedImage createQrcode(String url) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 2);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(url, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
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

    /**
     * 插入二维码
     * @param bgImge 背景图片
     * @param qrcode 二维码
     * @return
     * @throws Exception
     */
    private static BufferedImage insertImage(BufferedImage bgImge, Image qrcode) throws Exception {

        int width = qrcode.getWidth(null);
        int height = qrcode.getHeight(null);
        // 插入二维码
        Graphics2D graph = bgImge.createGraphics();
        int x = (BG_WIDTH - width) / 2;
        int y = (BG_HEIGHT - height) / 2;
        graph.drawImage(qrcode, x, y, width, height, null);
        graph.dispose();
        
        return bgImge;
    }


    /**
     * 生成二维码(外嵌北京) 
     * @param content
     * @param logoPath
     * @param output
     * @throws Exception
     */
    public static void encode(String content, String name, OutputStream output) throws Exception {
 
        BufferedImage bgImage = QrCodeUtil.createBg(name);
        BufferedImage qrcode = QrCodeUtil.createQrcode(content);
        
        BufferedImage image = QrCodeUtil.insertImage(bgImage, qrcode);
        
        ImageIO.write(image, FORMAT, output);
    }

}
