package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;

public class Fish {
	// Every fish has an x which is an int.
	
	Color color;
	int x;
	int y;
	boolean isLittle;
	boolean facingLeft;
	
	// destination
	int destX;
	int destY;
	
	public Fish(Color c, int startX, int startY, boolean isLittle, boolean facingLeft) {
		this.color = c;
		this.x = startX;
		this.y = startY;
		this.isLittle = isLittle;
		this.facingLeft = facingLeft;
		
		// destination
		this.destX = 450;
		this.destY = 450;
	}
	
	public void draw(Graphics2D g) {
		// remember to move every time
		this.swim();
		
		// determine size and direction
		if (this.isLittle) {
			if (this.facingLeft) {
				// little left
				DrawFish.smallFacingLeft(g, this.color, this.x, this.y);
			} else {
				// little right
				DrawFish.smallFacingRight(g, this.color, this.x, this.y);
			}
		} else {
			if (this.facingLeft) {
				// big left
				DrawFish.facingLeft(g, this.color, this.x, this.y);
			} else {
				// big right
				DrawFish.facingRight(g, this.color, this.x, this.y);
			}
		}
	}
	
	public void swim() {
		if (this.y < this.destY) {
			this.y += 1;
		}
		this.x += 1;
	}

}



/**
 * can change window size in Aquarium.java
 * (0,0) in top left
 * g is win
 * smallFacingLeft and smallFacingRight both work
 * screaming colors CYAN and cyan are same thing
 * newlines can go anywhere and the code will still work
 */