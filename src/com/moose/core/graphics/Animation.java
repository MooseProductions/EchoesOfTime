package com.moose.core.graphics;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import com.moose.core.util.Log;

public class Animation {
	
	//private int frame;
	private ArrayList<Frame> images = new ArrayList<Frame>();
	//private int length;
	private boolean stopped = false;
	private boolean loop = false;
	private float speed = 1.0f;
	private int currentFrame = -1;
	private long nextChange = 0;
	private int direction = 1;
	private boolean didit = false;
	
	public Animation(ArrayList<Frame> imgs){
		this.images = imgs;
	}
	
	public Animation(){
		
	}
	
	public void addFrame(Image img, int duration){
		if (duration == 0) {
			Log.error("Invalid duration: "+duration);
			throw new RuntimeException("Invalid duration: "+duration);
		}

	    if (images.isEmpty()) {
			nextChange = (int) (duration / speed);
		} 
		images.add(new Frame(img, duration));
		currentFrame = 0;
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
		
		//nextChange -= delta;
		
		//while (nextChange < 0 && (!stopped)) {
			if ((currentFrame == images.size() - 1) && (!loop)) {
	            stopped = true; 
				return;
			}
			
			//currentFrame = (currentFrame + direction) % images.size();
			nextChange--;
			
			if (currentFrame >= images.size()-1) {
				currentFrame = images.size()-1;
				direction = -1;
			}
			
			if(!didit){
				didit = true;
				currentFrame = 0;
				nextChange = images.get(currentFrame).duration;
			}
			
			if(nextChange <= 0){
				currentFrame++;
				nextChange = images.get(currentFrame).duration;
				didit = false;
			}
			//System.out.println(nextChange);
			//nextChange = images.get(currentFrame).duration;
			//currentFrame = images.get(currentFrame).duration;
			//int realDuration = (int) (((Frame) images.get(currentFrame)).duration / speed);
			//nextChange = nextChange + realDuration;
		//}
	}

	public void draw(Graphics g, int x, int y){
		if (images.size() == 0) {
			return;
		}
		g.drawImage(images.get(currentFrame).img,x,y,32,32,null);
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
