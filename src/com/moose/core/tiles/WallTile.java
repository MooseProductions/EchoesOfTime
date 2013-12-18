package com.moose.core.tiles;

import java.awt.Graphics;

import com.moose.core.graphics.SpriteSheet;

public class WallTile extends Tile {

	public WallTile(){
		super(SpriteSheet.sprites.getImage(20, 20, 20,20));
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(sprite,x,y,20,20,null);
	}
	
	public boolean solid(){
		return true;
	}

}
