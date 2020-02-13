package edu.smith.cs.csc212.aquarium;

import java.awt.Graphics2D;

public class KillerWhale {
	// Basic whale attributes
	double x;
	double y;
	boolean facingLeft;

	// destination and speed
	double destX;
	double destY;
	double speed;

	public KillerWhale(double startX, double startY, boolean facingLeft) {
		this.x = startX;
		this.y = startY;
		this.facingLeft = facingLeft;

		// set original destination as starting place
		this.destX = startX;
		this.destY = startY;
		
		// speed - just faster than all fish
		this.speed = 3.5;
	}

	public void draw(Graphics2D g) {
		// remember to move every time
		this.swim();
		
		// determine direction
		if (this.facingLeft) {
			// left
			DrawKWhale.facingLeft(g, this.x, this.y);
		} else {
			// right
			DrawKWhale.facingRight(g, this.x, this.y);
		}
	}

	public void swim() {		
		// Hunt fish
		hunt();
		
		// Calculate dx and dy to swim to destination at speed
		double dx = this.speed * this.xdist() / this.dist();
		double dy = this.speed * this.ydist() / this.dist();
		
		// Face destination
		if (dx < 0) {
			this.facingLeft = true;
		} else {
			this.facingLeft = false;
		}
		
		// move
		this.x += dx;
		this.y += dy;
	}
	
	public void hunt() {
		// if no more fish, slowly swim offscreen and trigger "the end"
		if (Aquarium.hFish.size() < 1) {
			this.destX = 700;
			// don't change y
			this.speed = 0.6;
			Aquarium.theEnd = true;
			return;
		}
		
		// otherwise target a fish
		HungryFish target = Aquarium.hFish.get(0);
		this.destX = target.x;
		this.destY = target.y;	
		
		// eat fish if close enough, then redo destination
		if (this.close()) {
			Aquarium.hFish.remove(0);
			hunt();
		}
		
		// don't go above water
		if (this.destY < Aquarium.SKYHEIGHT) {
			this.destY = Aquarium.SKYHEIGHT;
		}
	}
	
	public double dist() {
		// calculate distance from orca to destination
		return Math.sqrt(Math.pow(this.xdist(), 2) + Math.pow(this.ydist(), 2));
	}
	
	public double xdist() {
		// calculate x distance from orca to destination
		return this.destX - this.x;
	}
	
	public double ydist() {
		// calculate y distance from orca to destination
		return this.destY - this.y;
	}
	
	public boolean close() {
		// return whether orca is close (enough) to fish
		return this.dist() < 2;
	}
}
