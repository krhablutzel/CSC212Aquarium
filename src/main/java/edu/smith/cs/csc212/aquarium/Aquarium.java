package edu.smith.cs.csc212.aquarium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import me.jjfoley.gfx.GFX;

/**
 * Aquarium is a graphical "application" that uses some code I built and have
 * shared with you that takes care of opening a window and communicating with
 * the user in a simple way.
 * 
 * The method draw is called 50 times per second, so we make an animation by
 * drawing our fish in one place, and moving that place slightly. The next time
 * draw gets called, our fish looks like it moved!
 * 
 * @author jfoley
 *
 */
public class Aquarium extends GFX {
	/**
	 * This is a static variable that tells us how wide the aquarium is.
	 */
	public static int WIDTH = 500;
	/**
	 * This is a static variable that tells us how tall the aquarium is.
	 */
	public static int HEIGHT = 500;
	/**
	 * This is a static variable for how tall the sky is.
	 */
	public static int SKYHEIGHT = 20;
	/**
	 * For whether the game has ended
	 */
	public static boolean theEnd = false;
	/**
	 * Put a snail on the top of the tank.
	 */
	Snail algorithm = new Snail(177, Snail.HEIGHT + 1, "top");
	/**
	 * Bubble array
	 */
	Bubble[] bubbles;
	/**
	 * Foods list is static so HungryFish can hunt
	 */
	static List<Food> foods;
	/**
	 * List of fish - again static so Killer Whale can hunt
	 */
	static List<HungryFish> hFish;
	/**
	 * Draw killer whale near the bottom right.
	 */
	KillerWhale orca = new KillerWhale(400, 400, true);
	/**
	 * Track how green the tank is getting.
	 */
	double green;
	/**
	 * This is a constructor, code that runs when we make a new Aquarium.
	 */
	public Aquarium() {
		// Here we ask GFX to make our window of size WIDTH and HEIGHT.
		// Don't change this here, edit the variables instead.
		super(WIDTH, HEIGHT);
		Random rand = new Random();
		
		// green of tank
		this.green = 0;
		
		// create bubbles
		bubbles = new Bubble[10];
		for (int i = 0; i < bubbles.length; i++) {
			bubbles[i] = new Bubble();
		}
		
		// create food
		foods = new ArrayList<>();
		int numFoods = 10;
		for (int i = 0; i < numFoods; i++) {
			int x = rand.nextInt(500);
			foods.add(new Food(x));
		}
		
		// create hungry fish
		hFish = new ArrayList<>();
		int numFish = 20;
		for (int i = 0; i < numFish; i++) {
			// random position
			int x = rand.nextInt(WIDTH + 1);
			int y = rand.nextInt(HEIGHT - SKYHEIGHT + 1) + SKYHEIGHT;
			
			// 80% chance little
			boolean isLittle; 
			if (rand.nextInt(100) < 80)  {
				isLittle = true;
			} else {
				isLittle = false;
			}
			
			// add magenta fish
			hFish.add(new HungryFish(Color.magenta, x, y, isLittle, true));
		}
	}
 
	@Override
	public void draw(Graphics2D g) {
		// Draw the "ocean" background.
		g.setColor(new Color(40, (int) this.green, 150));
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// Draw the "sky"
		g.setColor(Color.cyan);
		g.fillRect(0, 0, getWidth(), SKYHEIGHT);
		
		// Bubbles draw and move
		for (Bubble bubb : this.bubbles) {
			bubb.draw(g);
		}
		
		// Source of bubbles - treasure chest
		TankDraw.drawTreasure(g);
		
		// 2% chance of adding more food (if not the end)
		if (!theEnd) {
			Random rand = new Random();
			int chance = 2;
			if (rand.nextInt(100) + 1 < chance) {
				foods.add(new Food(rand.nextInt(500)));
			}
		}

		// Draw food
		for (int i = 0; i < foods.size(); i++) {
			foods.get(i).draw(g);
		}
		
		// Draw hungry fish
		for (int i = 0; i < hFish.size(); i++) {
			hFish.get(i).draw(g);
		}
		
		// Draw orca
		orca.draw(g);
		
		// Draw our snail!
		algorithm.draw(g);
		
		// Bounds on when the snail is cleaning
		if (this.green > 230) {
			// Definitely clean if > 230
			Snail.asleep = false;
		} else if (this.green < 25) {
			// Definitely don't clean if < 25
			Snail.asleep = true;
		}
		
		if (this.green < 255 && Snail.asleep) {
			// Ocean gets greener if snail asleep
			this.green += 0.7;	
		} else {
			// Ocean gets cleaned if snail awake
			this.green -= 0.3;
		}
		
		// End message (if the end)
		if (theEnd) {
			TankDraw.drawEndMessage(g);
		}
	}

	public static void main(String[] args) {
		// Note that we can store an Aquarium in a variable of type GFX because Aquarium
		// is a very specific GFX, much like 7 can be stored in a variable of type int!
		GFX app = new Aquarium();
		app.start();
	}

}