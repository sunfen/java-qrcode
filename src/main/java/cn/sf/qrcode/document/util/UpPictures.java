package cn.sf.qrcode.document.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.imageio.ImageIO;


public class UpPictures {
	
	
    /**
     * 判断是否为图片
     * @param path 文件路径
     * @return 真假
     */
    public static Boolean isImage(final String fileName) {
        final String fileType = fileName.substring(fileName.lastIndexOf('.') + 1);
        if (fileType.equalsIgnoreCase("JPEG")) {
            return true;
        }
        if (fileType.equalsIgnoreCase("JPG")) {
            return true;
        }
        if (fileType.equalsIgnoreCase("GIF")) {
            return true;
        }
        if (fileType.equalsIgnoreCase("BMP")) {
            return true;
        }
        if (fileType.equalsIgnoreCase("PNG")) {
            return true;
        }
        return false;
    }

	/**
	 * 
	 * @title: smallPicture
	 * @description: 生成略缩图
	 * @param: @param
	 *             issue
	 * @param: @param
	 *             response
	 * @param: @param
	 *             i
	 * @param: @throws
	 *             IOException
	 * @return:
	 * @throws:
	 * @author: ztw
	 * @time: 2019 年1月2日
	 */
	public static void smallPicture(byte[] data, OutputStream outputStream,  String  formatImg) throws IOException {
		minPictrue(getInputStream(data), formatImg);
	}

	
	public static ByteArrayOutputStream minPictrue( InputStream in,  String  formatImg) throws IOException {
		AffineTransform transform = new AffineTransform();
		// 读取图片
		BufferedImage bis = ImageIO.read(in);
		// 获得图片原来的高宽
		int w = bis.getWidth();
		int h = bis.getHeight();
		// double scale = (double) w / h;
		// 等比例缩放
		int nowWidth = 120;
		int nowHeight = (nowWidth * h) / w;
		if (nowHeight > 120) {
			nowHeight = 120;
			nowWidth = (nowHeight * w) / h;
		}
		double sx = (double) nowWidth / w;
		double sy = (double) nowHeight / h;
		transform.setToScale(sx, sy);
		
		AffineTransformOp ato = new AffineTransformOp(transform, null);
		
		BufferedImage bid = new BufferedImage(nowWidth, nowHeight, BufferedImage.TYPE_3BYTE_BGR);
		ato.filter(bis, bid);
		
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ImageIO.write(bid, formatImg, out);
		return out;
	}
	
	
	/**
	 * 下载图片
	 * 
	 * @param issue
	 * @param response
	 * @param i
	 * @throws IOException
	 */
	public static void downPIcture(byte[] data, OutputStream outputStream) throws IOException {

		InputStream in = getInputStream(data);
		int len = 0;
		byte[] buf = new byte[1024];
		while ((len = in.read(buf, 0, 1024)) != -1) {
			outputStream.write(buf, 0, len);
		}
		outputStream.close();
	}

	/**
	 * 获得输出流
	 * 
	 * @param issue
	 * @param response
	 * @param i
	 * @return
	 * @throws IOException
	 */
	private static InputStream getInputStream(byte[] data) throws IOException {
		return new ByteArrayInputStream(data);
	}

	/**
	 * 
	 * @title: upPicture
	 * @description: 保存图片
	 * @param: @param
	 *             file
	 * @param: @param
	 *             req
	 * @param: @return
	 * @param: @throws
	 *             Exception
	 * @return:
	 * @throws:
	 * @author: sunfen
	 * @time: 2019年1月2日
	 */
	public static byte[] upPicture(InputStream fis, byte[] buffer) throws Exception {
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	/**
	 * 
	 * @title: sizeAndFormat
	 * @description: 判断文件大小和图片格式
	 * @param: @param
	 *             fl
	 * @param: @param
	 *             flFileName
	 * @param: @return
	 * @return:
	 * @throws:
	 * @author: ztw
	 * @time: 2019年1月2日
	 */
	public static Boolean sizeAndFormat(Long fileSize, String flFileName) {
		// 判断传入的是否为 图片
		Set<String> set = UpPictures.imgSet();
		Boolean b = false;
		for (String s : set) {
			if ((flFileName.substring(flFileName.lastIndexOf(".")).equalsIgnoreCase(s))) {
				b = true;
				break;
			}
		}
		if (!b) {
			return false;
		}
		// 判断文件大小
		//大于1M
		if (fileSize >= 1048576) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @title: imgSet
	 * @description: 把常见的图片格式放入 set中
	 * @param: @return
	 * @return:
	 * @throws:
	 * @author: ztw
	 * @time: 2019年1月2日
	 */
	private static Set<String> imgSet() {
		Set<String> set = new HashSet<>();
		set.add(".bmp");
		set.add(".jpg");
		set.add(".jpeg");
		set.add(".png");
		return set;
	}

}
