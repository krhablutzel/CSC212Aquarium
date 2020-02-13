package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class HungryFish {
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
	
	// hunger
	double hunger;
	boolean hungry;

	public HungryFish(Color c, double startX, double startY, boolean isLittle, boolean facingLeft) {
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

		// hunger starts at integer somewhere between 10 and 100
		Random rand = new Random();
		this.hunger = rand.nextInt(91) + 10;
		this.hungry = false;
	}

	public void draw(Graphics2D g) {
		// remember to move every time
		this.swim();
		
		// Fish color - red if hungry; random otherwise
		Color drawColor;
		if (this.hungry) {
			drawColor = Color.red;
		} else {
			drawColor = this.color;
		}
		
		// determine size and direction
		if (this.isLittle) {
			if (this.facingLeft) {
				// little left
				DrawFish.smallFacingLeft(g, drawColor, this.x, this.y);
			} else {
				// little right
				DrawFish.smallFacingRight(g, drawColor, this.x, this.y);
			}
		} else {
			if (this.facingLeft) {
				// big left
				DrawFish.facingLeft(g, drawColor, this.x, this.y);
			} else {
				// big right
				DrawFish.facingRight(g, drawColor, this.x, this.y);
			}
		}
	}

	public void swim() {		
		// Get more hungry
		// With trivial "don't get hungry in the area where the food is"
		// Aka from the water surface to 20 down
		if (this.y > Aquarium.SKYHEIGHT + 20) {
			this.hunger -= 0.5;
		}

		// check if hungry now
		if (this.hunger < 10) {
			this.hungry = true;
		}
		
		// Hunt food if too hungry and food available
		if (this.hungry && Aquarium.foods.size() > 0) {
			hunt();
		} 
		
		// Get new destination if destination ~reached
		if (this.close()) {
			// update destination
			destination();
		}
		
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
	
	public void destination() {
		Random rand = new Random();
		
		// set random destination within bounds
		this.destX = rand.nextInt(501); // int to double?
		
		if (this.hungry) {
			// swim near surface if hungry
			this.destY = rand.nextInt(20 + 1) + Aquarium.SKYHEIGHT;	
		} else {
			// swim away from surface if not hungry
			this.destY = rand.nextInt(Aquarium.HEIGHT - Aquarium.SKYHEIGHT - 20 + 1) + Aquarium.SKYHEIGHT + 20;	
		}


	}
	
	public void hunt() {
		// target a food if hungry
		Food target = Aquarium.foods.get(0);
		this.destX = target.x;
		this.destY = target.y;	
		
		// eat food if close enough, then redo destination
		if (this.close()) {
			Aquarium.foods.remove(0);
			this.hunger = 100;
			this.hungry = false;
			destination();
		}
		
		// don't go above water
		if (this.destY < Aquarium.SKYHEIGHT) {
			this.destY = Aquarium.SKYHEIGHT;
		}
	}
	
	public double dist() {
		// calculate distance from fish to destination
		return Math.sqrt(Math.pow(this.xdist(), 2) + Math.pow(this.ydist(), 2));
	}
	
	public double xdist() {
		// calculate x distance from fish to destination
		return this.destX - this.x;
	}
	
	public double ydist() {
		// calculate y distance from fish to destination
		return this.destY - this.y;
	}
	
	public boolean close() {
		// return whether fish is close (enough) to target
		return this.dist() < 2;
		
		/*
		 * close() is a method because it gets checked in many places
		 * while hungry is an instance variable because it's more of a
		 * state the fish stay in for a while
		 */
		
	}
	
}
