package com.moose.core.tiles;

import java.awt.Graphics;
import java.awt.Image;

public abstract class Tile {
	
	protected Image sprite;
	
	public Tile(Image img){
		sprite = img;
	}

	public boolean solid() {
		return false;
	}
	
	public abstract void render(Graphics g, int x, int y);

}
