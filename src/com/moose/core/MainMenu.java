package com.moose.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.moose.core.Input;

public class MainMenu {

	private String[] items = { "Start Game", "Story", "Options", "Credits", "Quit" };
	private Font font = new Font("DialogInput", 1, 35);
	private Font heading = new Font("DialogInput", 1, 50);
	private Input input;
	private int selected = 0;
	public static int timer = 10;
	public static int timerReset = 10;

	public MainMenu(Input input) {
		this.input = input;
	}
	
	public void update(){
		if (timer > 0)
			timer--;
		if (input.down && timer == 0){
			selected++;
			timer = timerReset;
		}
		if (input.up && timer == 0){
			selected--;
			timer = timerReset;
		}
		if (selected < 0)
			selected = 0;
		if (selected > 4)
			selected = 4;
		if (selected == 0) {
			items[0] = "> Start Game <";
			if(input.use && timer == 0){
				ClassCore.gs = ClassCore.GameState.GAME;
			}
		} else {
			items[0] = "Start Game";
		}
		if (selected == 1) {
			items[1] = "> Story <";
			if(input.use && timer == 0){
				//StoryMenu.timer = 50;
				input.use = false;
				//ClassCore.gs = ClassCore.GameState.STORYMENU;
			}
		} else {
			items[1] = "Story";
		}
		if (selected == 2) {
			items[2] = "> Options <";
			if(input.use && timer == 0){
				//OptionsMenu.timer = 50;
				input.use = false;
				//ClassCore.gs = ClassCore.GameState.OPTIONS;
			}
		} else {
			items[2] = "Options";
		}
		if (selected == 3) {
			items[3] = "> Credits <";
			if(input.use && timer == 0){
				//Credits.timer = 50;
				input.use = false;
				//ClassCore.gs = ClassCore.GameState.CREDITS;
			}
		} else {
			items[3] = "Credits";
		}
		if (selected == 4) {
			items[4] = "> Quit <";
			if(input.use){
				System.exit(0);
			}
		} else {
			items[4] = "Quit";
		}
	}

	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.setFont(heading);
		g.drawString("Echoes of Time", 400 - "Echoes of Time".length() * 14, 200);
		g.setFont(font);
		for (int i = 0; i < items.length; i++) {
			g.drawString(items[i], 400 - items[i].length() * 10, 300 + i * 40);
		}
	}
}
