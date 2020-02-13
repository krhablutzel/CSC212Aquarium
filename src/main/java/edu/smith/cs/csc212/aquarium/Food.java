package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class Food {
	int x;
	int y;
	Boolean falling;
	
	public Food(int x) {
		this.x = x;
		this.y = 0;
		this.falling = true;
	}
	
	public void draw(Graphics2D g) {
		// fall
		this.fall();
		
		// draw
		this.drawFood(g);
	}
	
	public void drawFood(Graphics2D g) {
		// colors picked using:
		// https://www.google.com/search?q=rgb+color+picker&oq=rbg+color+&aqs=chrome.1.69i57j0l7.2305j0j7&sourceid=chrome&ie=UTF-8
		
		// create food
		Shape food = new Ellipse2D.Double(this.x - 5, this.y - 5, 10, 10);
				
		// food color
		Color brown = new Color(59, 35, 18);
		g.setColor(brown);
		
		// fill food and draw
		g.fill(food);
		g.draw(food);
	}
	
	public void fall() {
		if (this.y < Aquarium.SKYHEIGHT) {
			this.y += 2;
		}
	}
}
