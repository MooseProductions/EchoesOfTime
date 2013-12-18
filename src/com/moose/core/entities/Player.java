package com.moose.core.entities;

import java.awt.Graphics;

import com.moose.core.graphics.SpriteSheet;

import com.moose.core.Input;

public class Player extends Mob{
	
	private Input input;

	public Player(Input input){
		this.input = input;
		sprite = SpriteSheet.sprites.getImage(20, 20, 20, 20);
	}
	
	public void update(){
		int xa = 0, ya = 0;
		if(input.up) ya--;
		if(input.down) ya++;
		if(input.left) xa--;
		if(input.right) xa++;
		if(xa != 0 || ya != 0)
		move(xa,ya);
	}

	public void render(Graphics g) {
		g.drawImage(sprite,400-10,300-10,20,20,null);
	}
	
}
