package com.moose.core;

import java.awt.Graphics;
import java.util.ArrayList;

import com.moose.core.entities.Entity;
import com.moose.core.graphics.Image;
import com.moose.core.tiles.Tile;
import com.moose.core.tiles.VoidTile;
import com.moose.core.tiles.WallTile;

public class Level {
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private Tile[] tiles;
	private Image image;
	private int width, height;
	
	public Level(String path){
		image = new Image(path);
		width = image.getWidth();
		height = image.getHeight();
		tiles = new Tile[width*height];
		init();
	}
	
	private void init(){
		for(int i=0; i<image.getPixels().length;i++){
			int col = image.getPixels()[i];
			if(col == 0xff000000) tiles[i] = new VoidTile();
			if(col == 0xffFF0000) tiles[i] = new WallTile();
		}
	}
	
	public void render(Graphics g, int xOffset, int yOffset){
		for(int y = 0; y < height ; y++){
			for(int x = 0; x < width ; x++){
				Tile t = tiles[x+y*width];
				int xx = x*32 - xOffset;
				int yy = y*32 - yOffset;
				if(t != null) t.render(g, xx, yy);
			}
		}
		for(Entity e : entities){
			e.render(g);
		}
	}
	
	public void update(){
		for(Entity e : entities){
			e.update();
		}
	}
	
	public void add(Entity e){
		e.init(this);
		entities.add(e);
	}

	public Tile getTile(int xt, int yt) {
		if(xt < 0 || yt < 0 || xt >= width || yt >= height) return null;
		return tiles[xt+yt*width];
	}

}
