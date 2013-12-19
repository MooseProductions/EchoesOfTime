package com.moose.core.entities;

import com.moose.core.tiles.Tile;

public abstract class Mob extends Entity {

	public void move(int xa, int ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
		}
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}

	private boolean collision(int xa, int ya) {
		for(int i = 0; i<4;i++){
			int xt = ((x+xa)+i%2*31-16) >> 5; //int xt = ((x+xa)+i%2*32-16) >> 5;
			int yt = ((y+ya)+i/2*31-16) >> 5; //int yt = ((y+ya)+i/2*32-16) >> 5;
			Tile tile = level.getTile(xt, yt);
			if(tile == null) continue;
			if(tile.solid()) return true;
		}
		return false;
	}

}
