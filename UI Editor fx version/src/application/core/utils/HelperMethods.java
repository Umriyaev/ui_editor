package application.core.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;

import application.core.BorderType;
import application.core.ButtonType;
import application.core.ComponentType;

public class HelperMethods {

	// get byte array from javafx image
	public static byte[] ConvertImageToByteArray(Image image)
			throws IOException {
		BufferedImage bimage = SwingFXUtils.fromFXImage(image, null);
		ByteArrayOutputStream s = new ByteArrayOutputStream();
		ImageIO.write(bimage, "png", s);
		byte[] res = s.toByteArray();
		return res;
	}

	// get javafx image from byte array
	public static Image ConvertByteArrayToImage(byte[] rawPixels)
			throws IOException {
		ByteArrayInputStream bais = new ByteArrayInputStream(rawPixels);

		BufferedImage image = ImageIO.read(bais);
		Image resultImage = SwingFXUtils.toFXImage(image, null);
		return resultImage;

	}

	public static String ConvertByteArrayToString(byte[] bytes) {
		return new String(bytes);
	}

	public static byte[] ConvertStringToByteArray(String string) {
		return string.getBytes();
	}

	public static String ConvertComponentTypeToString(
			ComponentType componentType) {
		String result = "";
		switch (componentType) {
		case PANEL:
			result = "panel";
			break;
		case TEXTFIELD:
			result = "text";
			break;
		case BUTTON:
			result = "button";
			break;
		case TABLE:
			result = "table";
			break;
		case IMAGE:
			result = "image";
			break;
		}

		return result;
	}

	public static String ConvertButtonTypeToString(ButtonType buttonType) {
		String result = "";
		switch (buttonType) {
		case TOGGLE:
			result = "toggle";
			break;
		case HOLDING:
			result = "holding";
			break;
		case NORMAL:
			result = "normal";
			break;
		}
		return result;
	}

	public static String ConvertBorderTypeToString(BorderType borderType) {
		String result = "";
		switch (borderType) {
		case SOLID:
			result = "solid";
			break;
		case DOTTED:
			result = "dotted";
			break;
		case DASHED:
			result = "dashed";
			break;
		}
		return result;
	}
}
