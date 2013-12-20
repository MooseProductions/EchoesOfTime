package com.moose.core.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private int frame;
	private ArrayList<Frame> images = new ArrayList<Frame>();
	private int length;
	private boolean stopped = false;
	private boolean loop = false;
	private float speed = 1.0f;
	private int currentFrame = -1;
	private long nextChange = 0;
	private int direction = 1;
	
	public Animation(){
		//this.images = imgs;
	}
	
	public void addFrame(Image img, int duration){
		images.add(new Frame(img, duration));
	}
	
	public void update(long delta){
		nextFrame(delta);
	}
	
	private void nextFrame(long delta) {
		if (stopped) {
			return;
		}
		if (images.size() == 0) {
			return;
		}
		
		nextChange -= delta;
		
		while (nextChange < 0 && (!stopped)) {
			if ((currentFrame == images.size() - 1) && (!loop)) {
	            stopped = true; 
				break;
			}
			
			currentFrame = (currentFrame + direction) % images.size();
			
			if (currentFrame >= images.size()-1) {
				currentFrame = images.size()-1;
				direction = -1;
			}
			int realDuration = (int) (((Frame) images.get(currentFrame)).duration / speed);
			nextChange = nextChange + realDuration;
		}
	}

	public void draw(Graphics g, int x, int y){
		g.drawImage(images.get(frame).img,x,y,20,20,null);
	}
	
	public void setLength(int length){
		this.length = length;
	}
	
	public void resetAnimation(){
		frame = 0;
	}
	
	private class Frame{
		
		private Image img;
		private int duration;

		public Frame(Image img, int duration){
			this.img = img;
			this.duration = duration;
		}
		
	}

}
