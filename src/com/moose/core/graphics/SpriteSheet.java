package com.moose.core.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	public static SpriteSheet sprites = new SpriteSheet("/sprites/spritesheet.png");
	private BufferedImage img;
	private int width, height;
	
	public SpriteSheet(String path){
		try {
			img = ImageIO.read(getClass().getResourceAsStream(path));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Image getImage(int x, int y, int width, int height){
		Image image = null;
		image = img.getSubimage(x,y,width,height);
		return image;
	}
	
	public int getWidth(){
		return width;
	}
	
	public int getHeight(){
		return height;
	}

}
