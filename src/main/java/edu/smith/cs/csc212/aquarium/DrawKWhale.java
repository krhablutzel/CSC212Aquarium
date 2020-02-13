package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * This class is used statically to draw orcas in various positions
 *
 */

public class DrawKWhale {
	// an adaptation of drawFish
	
	public static void facingLeft(Graphics2D g, double x, double y) {
		// parts of body
		Shape body = new Ellipse2D.Double(x - 75, y - 20, 150, 40);
		Shape tail = new Ellipse2D.Double(x + 60, y - 25, 40, 50);
		Shape topFin = new Ellipse2D.Double(x, y - 40, 20, 60);
		Shape bottomFin = new Ellipse2D.Double(x - 35, y - 18, 20, 60);
		Shape eye =  new Ellipse2D.Double(x - 50, y - 10, 30, 15);
		
		// Draw black parts
		g.setColor(Color.black);
		g.fill(body);
		g.fill(tail);
		g.fill(topFin);
		g.fill(bottomFin);
		
		// Draw white parts
		g.setColor(Color.white);
		g.fill(eye);
	}
	
	/**
	 * this code is almost completely borrowed from DrawFish
	 */
	public static void facingRight(Graphics2D g, double x, double y) {
		Graphics2D flipped = (Graphics2D) g.create();
		flipped.translate(x, y);
		flipped.scale(-1, 1);
		facingLeft(flipped, 0, 0);
		flipped.dispose();
	}
}
