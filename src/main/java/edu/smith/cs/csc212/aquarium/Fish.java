package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Fish {
	// Basic fish attributes
	Color color;
	double x;
	double y;
	boolean isLittle;
	boolean facingLeft;

	// destination and speed
	double destX;
	double destY;
	double speed;

	public Fish(Color c, double startX, double startY, boolean isLittle, boolean facingLeft) {
		this.color = c;
		this.x = startX;
		this.y = startY;
		this.isLittle = isLittle;
		this.facingLeft = facingLeft;

		// set destination
		destination();
		
		// speed - big ones move more slowly
		if (isLittle) {
			this.speed = 3;
		} else {
			this.speed = 1.5;	
		}

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
		// find distances from fish to destination
		double xdist = this.destX - this.x;
		double ydist = this.destY - this.y;
		double dist = Math.sqrt(Math.pow(xdist, 2) + Math.pow(ydist, 2));
		
		// If destination is ~reached
		if (dist < 2) {
			// new  destination
			destination();
			
			// recalculate distances
			xdist = this.destX - this.x;
			ydist = this.destY - this.y;
			dist = Math.sqrt(Math.pow(xdist, 2) + Math.pow(ydist, 2));
		}
		
		// Calculate dx and dy to swim to destination at speed
		double dx = this.speed * xdist / dist;
		double dy = this.speed * ydist / dist;
		
		// Face destination
		if (dx < 0) {
			this.facingLeft = true;
		} else {
			this.facingLeft = false;
		}
		
		// Move towards destination
		this.x += dx;
		this.y += dy;
	}
	
	public void destination() {
		// set destination
		Random rand = new Random();
		this.destX = rand.nextInt(501); // int to double?
		this.destY = rand.nextInt(501);
	}
}