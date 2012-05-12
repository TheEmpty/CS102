package com.reliablerabbit.nyancat;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * Display a NyanCat in your GUI application!
 * @author Mohammad El-Abid
 * @category Fun
 */
public class NyanCat implements Runnable {
	/**
	 * Resource location for NyanCat image
	 */
	private final String IMAGE_LOCATION = "/spriteSheet.png";
	/**
	 * Pixel count of each individual frames width
	 */
	public final static int FRAME_WIDTH = 400;
	/**
	 * Pixel count of each individual frames height
	 */
	public final static int FRAME_HEIGHT = 400;
	/**
	 * Number of frames in the image
	 */
	private final int FRAME_COUNT = 12;
	/**
	 * X-position of where the NyanCat should be displayed
	 */
	private int imageX = 0;
	/**
	 * Y-position of where the NyanCat should be displayed
	 */
	private int imageY = 0;
	/**
	 * The sprite image
	 */
	private BufferedImage image = null;
	/**
	 * Current frame
	 */
	private int frame = 0;
	/**
	 * Position of the frames
	 */
	private int[][] clips = new int[FRAME_COUNT][2];
	/**
	 * The graphic device to draw on
	 */
	private Graphics g;
	
	public NyanCat() {
		loadImage();
	}
	
	/**
	 * Load the image and calculate the frame indexes
	 */
	private void loadImage() {
		try {
			URL file = NyanCat.class.getResource(IMAGE_LOCATION);
			if(file == null) throw new java.io.FileNotFoundException("Unable to load NyanCat from: " + IMAGE_LOCATION);
			image = ImageIO.read(file);
			
			for(int i = 0; clips.length > i; i++) {
				clips[i][0] = (FRAME_WIDTH * i) % image.getWidth();
				clips[i][1] = (FRAME_HEIGHT * i) % image.getHeight();
			}
		} catch (IOException e) {
			System.err.println("Unable to load NyanCat image");
			e.printStackTrace();
		}
	}

	/**
	 * As long as the image is loaded and we can sleep (to control FPS), display the NyanCat!
	 */
	@Override
	public void run() {
		if(image == null) return;
		
		while(true) {
			BufferedImage clip = image.getSubimage(clips[frame][0], clips[frame][1], FRAME_WIDTH, FRAME_HEIGHT);
			g.drawImage(clip, imageX, imageY, FRAME_WIDTH/3, FRAME_HEIGHT/3, null);
			frame = (frame + 1) % FRAME_COUNT;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	/**
	 * @param x is the new X cord for where the NyanCat should be displayed
	 */
	public void setX(int x) {
		this.imageX = x;
	}
	
	/**
	 * @param y is the new Y cord for where the NyanCat should be displayed
	 */
	public void setY(int y) {
		this.imageY = y;
	}
	
	/**
	 * Used to set the graphics component to draw on. Either your JFrame or a separate component dedicated to NyanCat.
	 * @param g the graphics component to draw on.
	 */
	public void setGraphics(Graphics g) {
		this.g = g;
	}
}
