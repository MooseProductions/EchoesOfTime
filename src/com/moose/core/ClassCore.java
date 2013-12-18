package com.moose.core;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import com.moose.core.entities.Player;
import com.moose.core.graphics.GUI;


public class ClassCore extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	private int fps = 0;
	private Thread t;
	private JFrame jframe;
	private GUI gui;
	private Level level;
	private Input input;
	private Player player;
	
	public ClassCore(){
		Dimension size = new Dimension(800,600);
		setMaximumSize(size);
		setMinimumSize(size);
		setPreferredSize(size);
		setLocation(0,0);
		jframe = new JFrame("Echoes of Time");
		jframe.add(this);
		jframe.setResizable(false);
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui = new GUI();
		level = new Level("/levels/level1.png");
		input = new Input();
		addFocusListener(input);
		addKeyListener(input);
		player = new Player(input);
		level.add(player);
		//player.setToTile(3, 3);
		jframe.pack();
		jframe.setLocationRelativeTo(null);
		jframe.setVisible(true);
		t = new Thread(this, "MainThread");
		System.out.println((double) (7 >> 5));
		System.out.println((double) (7 / 32.0));
		t.start();
	}

	public static void main(String[] args) {
		ClassCore cc = new ClassCore();
		cc.toString();
	}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(3);
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		gui.render(g);
		level.render(g, player.getX() - 400, player.getY() - 300);
		
		g.setColor(Color.blue);
		g.drawString("FPS: "+ fps, 15, 20);
		
		g.dispose();
		bs.show();
	}
	
	public void update(){
		input.update();
		gui.update();
		level.update();
	}

	public void run() {
		long lastTime = System.nanoTime();
		double unprocessed = 0;
		double nsPerTick = 1000000000.0 / 60.0;
		int frames = 0;
		long lastTimer1 = System.currentTimeMillis();
		while(true){
			long now = System.nanoTime();
			unprocessed += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while (unprocessed >= 1) {
				update();
				unprocessed -= 1;
				shouldRender = true;
			}
			try {
				Thread.sleep(0);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTimer1 > 1000) {
				lastTimer1 += 1000;
				fps = frames;
				frames = 0;
			}
		}
	}

}
