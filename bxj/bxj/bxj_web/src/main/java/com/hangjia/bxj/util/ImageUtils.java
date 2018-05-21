package com.hangjia.bxj.util;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Transparency;
import java.awt.color.ColorSpace;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <strong>图像处理</strong> 工具类。
 * <p>
 * 此类提供静态方法封装对图像的处理操作。不依赖任何第三方软件包。
 * 可支持 JPG/JPEG，CMYK 色差模式的图片，对索引色的读取可能会有色差。
 * </p>
 * @author K9999
 * @since 0.1
 */
public abstract class ImageUtils {
	
	private static final Log log = LogFactory.getLog(ImageUtils.class);
	
	private static final boolean debugEnabled = log.isDebugEnabled();

	/**
	 * 返回图片格式名（jpg、png、gif）等。如果无法识别图像，返回 {@code null}。
	 * @param input 输入流、文件等。
	 * @return 图片格式，或 {@code null}（无法识别）。
	 */
	public static String getImageFormatName(Object input) {
		ImageInputStream in = null;
		try {
			in = ImageIO.createImageInputStream(input);
			Iterator<ImageReader> it = ImageIO.getImageReaders(in);
			if (!it.hasNext()) {
				return null;
			}
			return it.next().getFormatName();
		} catch (Exception e) {
			if (debugEnabled) {
				log.debug("读取图片异常，已返回 null。", e);
			}
			return null;
		} finally {
			close(in);
		}
	}

	/**
	 * 判定对象是否为图像。
	 * @param input 输入流、文件等。
	 * @return 如果是图像，返回 {@code true}，其他情况返回 {@code false}。
	 */
	public static boolean isImage(Object input) {
		return getImageFormatName(input) != null;
	}
	
	/**
	 * 保留图片原本
	 * @param imageFile
	 * @param dstFile
	 * @throws Exception
	 */
	public static void write(File imageFile, File dstFile) throws Exception {
		BufferedImage source = readImage(imageFile);
		writeImage(source, dstFile);
	}

	public static void zoom(File imageFile, File dstFile, int targetW, int targetH) throws Exception {
		BufferedImage source = readImage(imageFile);
		writeImage(source, targetW, targetH, dstFile);
	}
	
	public static void zoomReferWidth(File imageFile, File dstFile, int targetW) throws Exception {
		BufferedImage source = readImage(imageFile);
		writeImageReferWidth(source, targetW, dstFile);
	}
	
	public static void zoomReferHeight(File imageFile, File dstFile, int targetH) throws Exception {
		BufferedImage source = readImage(imageFile);
		writeImageReferHeight(source, targetH, dstFile);
	}
	
	public static void zoomAutoFix(File imageFile, File dstFile, int targetW, int targetH) throws Exception {
		BufferedImage source = readImage(imageFile);
		writeImageAutoFix(source, targetW, targetH, dstFile);
	}

	public static BufferedImage readImage(Object file) throws Exception {
		ImageInputStream stream = null;
		try {
			stream = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReaders(stream);
			while (iter.hasNext()) {
				ImageReader reader = iter.next();
				reader.setInput(stream);

				BufferedImage image;
				try {
					image = reader.read(0);
				} catch (IIOException e) {
					WritableRaster raster = (WritableRaster) reader.readRaster(0, null);
					image = createJPEG4(raster);
				}
				return image;
			}
			throw new IOException("ImageReaders is null");
		} finally {
			close(stream);
		}
	}

	private static BufferedImage createJPEG4(Raster raster) {
		int w = raster.getWidth();
		int h = raster.getHeight();
		byte[] rgb = new byte[w * h * 3];

		float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
		float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
		float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
		float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);

		for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
			float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

			double val = y + 1.402 * (cr - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);

			val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base + 1] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);

			val = y + 1.772 * (cb - 128) - k;
			val = (val - 128) * .65f + 128;
			rgb[base + 2] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
					: (byte) (val + 0.5);
		}

		raster = Raster.createInterleavedRaster(new DataBufferByte(rgb,
				rgb.length), w, h, w * 3, 3, new int[] { 0, 1, 2 }, null);

		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, true,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, (WritableRaster) raster, true, null);
	}

	public static void writeImage(BufferedImage source, int targetW, int targetH, File dstFile) throws IOException {
		ImageIO.write(rize(source, targetW, targetH), "jpg", dstFile);
	}
	
	public static void writeImage(BufferedImage source, File dstFile) throws IOException {
		ImageIO.write(source, "jpg", dstFile);
	}
	
	private static void writeImageReferWidth(BufferedImage source, int targetW, File dstFile) throws IOException {
		ImageIO.write(rizeReferWidth(source, targetW), "jpg", dstFile);
	}
	
	private static void writeImageReferHeight(BufferedImage source, int targetH, File dstFile) throws IOException {
		ImageIO.write(rizeReferHeight(source, targetH), "jpg", dstFile);
	}
	
	private static void writeImageAutoFix(BufferedImage source, int targetW, int targetH, File dstFile) throws IOException {
		ImageIO.write(rizeAutoFix(source, targetW, targetH), "jpg", dstFile);
	}
	
	private static BufferedImage rizeReferHeight(BufferedImage srcBufImage, int height) {
		double sy = (double) height / srcBufImage.getHeight();
		int width = (int) (sy * srcBufImage.getWidth());
		return scale(srcBufImage, sy, width, sy, height);
	}
	
	private static BufferedImage rizeReferWidth(BufferedImage srcBufImage, int width) {
		double sx = (double) width / srcBufImage.getWidth();
		int height = (int) (sx * srcBufImage.getHeight());
		return scale(srcBufImage, sx, width, sx, height);
	}
	
	private static BufferedImage rize(BufferedImage srcBufImage, int width, int height) {
		double sx = (double) width / srcBufImage.getWidth();
		double sy = (double) height / srcBufImage.getHeight();
		return scale(srcBufImage, sx, width, sy, height);
	}
	
	private static BufferedImage rizeAutoFix(BufferedImage srcBufImage, int width, int height) {
		int srcWidth = srcBufImage.getWidth();
		int srcHeight = srcBufImage.getHeight();
		if (srcWidth > srcHeight) {
			return rizeReferWidth(srcBufImage, width);
		} else if (srcWidth < srcHeight) {
			return rizeReferHeight(srcBufImage, height);
		} else {
			return rize(srcBufImage, width, height);
		}
		
	}
	
	private static BufferedImage scale(BufferedImage srcBufImage, double sx, int width, double sy, int height) {
		BufferedImage bufTarget = createBufferedImage(srcBufImage, width, height);
		Graphics2D g = bufTarget.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(srcBufImage, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return bufTarget;
	}
	
	private static BufferedImage createBufferedImage(BufferedImage srcBufImage, int width, int height) {
		BufferedImage bufTarget = null;
		int type = srcBufImage.getType();
		if (type == BufferedImage.TYPE_CUSTOM) {
			ColorModel cm = srcBufImage.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(width,
					height);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			bufTarget = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else {
			bufTarget = new BufferedImage(width, height, type);
		}
		return bufTarget;
	}
	
	private static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (IOException e) {
				log.error("关闭流资源失败：", e);
			}
		}
	}
	
}
