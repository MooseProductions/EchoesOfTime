package com.moose.core;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Input implements KeyListener, FocusListener, Serializable{
	
	private static final long serialVersionUID = -1497616920357335642L;
	private boolean keys[] = new boolean[65536];
	private boolean focus = false;
	public boolean up, down, left, right;
	private List<Integer> typed = new ArrayList<Integer>();
	public boolean use;

	public void focusGained(FocusEvent e) {
		focus = true;
	}
	
	public boolean getFocus(){
		return focus;
	}

	public void focusLost(FocusEvent e) {
		focus = false;
		for(int i = 0; i<keys.length;i++){
			keys[i] = false;
		}
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		keys[code] = true;
	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		for(int i = 0; i<typed.size();i++){
			if(typed.get(i).equals(code)) typed.remove(new Integer(code));
		}
		keys[code] = false;
	}
	
	public boolean keyTyped(int key){
		if(keys[key]){
			if(!typed.contains(key)){
				typed.add(new Integer(key));
				return true;
			}else{
				return false;
			}
		}
		return false;
	}

	public void keyTyped(KeyEvent e) {
		
	}
	
	public void update(){
		up = keys[KeyEvent.VK_W] | keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_S] | keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_A] | keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_D] | keys[KeyEvent.VK_RIGHT];
		use = keys[KeyEvent.VK_ENTER];
	}

}
