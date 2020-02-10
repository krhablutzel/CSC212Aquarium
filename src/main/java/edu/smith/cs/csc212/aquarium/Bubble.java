package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.util.Random;


public class Bubble {
	// basic Bubble attributes
	double x;
	double y;
	int size;
	
	// movement bubble attributes
	
	public Bubble() {
		Random rand = new Random();
		
		// random initial position
		this.x = rand.nextInt(200) + 250; // only on right
		this.y = rand.nextInt(500);
		
		// random sizes 5 - 14
		this.size = rand.nextInt(10) + 5;
	}
	
	public void draw(Graphics2D g) {
		// move each time
		this.drift();
		
		// then draw
		this.drawBubble(g);
	}
	
	public void drawBubble(Graphics2D g) {
		// colors picked using:
		// https://www.google.com/search?q=rgb+color+picker&oq=rbg+color+&aqs=chrome.1.69i57j0l7.2305j0j7&sourceid=chrome&ie=UTF-8
		
		// bubble color
		Color gblue = new Color(174, 234, 245);
		g.setColor(gblue);
		
		// create bubble
		Shape bubble = new Ellipse2D.Double(this.x - (this.size / 2), this.y - (this.size / 2), this.size, this.size);
		
		// fill bubble
		g.fill(bubble);
		
		// outline new color
		Color dgblue = new Color(103, 145, 153);
		g.setColor(dgblue);
		g.draw(bubble);
		
	}
	
	public void drift() {
		// test
		this.y -= 1;
		
		if (this.y < -10) {
			this.y = 510;
		}
	}

}
