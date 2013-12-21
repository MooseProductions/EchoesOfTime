package com.moose.core.entities;

import java.awt.Graphics;

import com.moose.core.Level;
import com.moose.core.tiles.Tile;

import java.awt.Image;

public abstract class Entity {
	
	protected int x, y;
	protected Image sprite;
	protected boolean removed = false;
	protected Level level;
	
	public boolean removed(){
		return removed;
	}
	
	public abstract void render(Graphics g); 
	
	public abstract void update(int delta);
	
	public void init(Level level){
		this.level = level;
	}
	
	public void setToTile(int xt, int yt){
		Tile t = level.getTile(xt, yt);
		if(t == null) return;
		if(t.solid()) return;
		x = xt * 32 + 16;
		y = yt * 32 + 16;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public Image getSprite() {
		return sprite;
	}

}
