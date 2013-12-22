package com.moose.core.entities;

import java.awt.Graphics;

import com.moose.core.graphics.Animation;
import com.moose.core.graphics.SpriteSheet;
import com.moose.core.util.Log;

import com.moose.core.Input;

public class Player extends Mob{
	
	private Input input;
	private int frame;
	private Animation anim;

	public Player(Input input){
		this.input = input;
		anim = new Animation();
		sprite = SpriteSheet.sprites.getImage(32, 32, 32, 32);
		anim.addFrame(sprite, 30);
		anim.addFrame(SpriteSheet.sprites.getImage(0, 32, 32, 32), 30);
		anim.addFrame(SpriteSheet.sprites.getImage(0, 0, 32, 32), 30);
	}
	
	public void update(int delta){
		anim.update(delta);
		int xa = 0, ya = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		if(xa != 0 || ya != 0)
		move(xa,ya);
	}

	public void render(Graphics g) {
		//g.drawImage(sprite,400-16,300-16,32,32,null);
		anim.draw(g, 400-16, 300-16);
	}
	
}
