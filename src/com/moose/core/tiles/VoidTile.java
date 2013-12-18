package com.moose.core.tiles;

import java.awt.Graphics;

import com.moose.core.graphics.SpriteSheet;

public class VoidTile extends Tile {
	
	public VoidTile(){
		super(SpriteSheet.sprites.getImage(0, 0, 20,20));
	}

	public void render(Graphics g, int x, int y) {
		g.drawImage(sprite,x,y,20,20,null);
	}

}
