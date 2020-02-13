package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class TankDraw {
	public static void drawTreasure(Graphics2D g) {
		/**
		 * for drawing the treasure chest
		 */
		// shapes
		Shape bottom = new Rectangle2D.Double(290, 410, 120, 90);
		Shape top = new Rectangle2D.Double(270, 380, 160, 30);
		Shape latch = new Rectangle2D.Double(340, 400, 20, 20);
		
		// draw brown bottom and top
		g.setColor(new Color(92, 70, 55));
		g.fill(bottom);
		g.fill(top);
		
		// add dark brown outlines
		g.setColor(new Color(38, 29, 23));
		g.draw(bottom);
		g.draw(top);
		
		// draw gold latch
		g.setColor(new Color(227, 195, 14));
		g.fill(latch);
		
		// add dark brown outline for latch too
		g.setColor(new Color(38, 29, 23));
		g.draw(latch);
	}

	public static void drawEndMessage(Graphics2D g) {
		/**
		 * for the end
		 */
		Font f = g.getFont();
		g.setFont(f.deriveFont(50.0f));
		g.setColor(Color.white);
		g.drawString("THE END", (int) Aquarium.WIDTH/2 - 100, (int) Aquarium.HEIGHT/2);
	}
}
