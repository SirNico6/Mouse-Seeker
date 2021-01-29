package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import graphics.Assets;
import movingObject.Seeker;
import states.ShowState;

public class Window extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Canvas canvas;
	private Thread thread;
	private boolean running = false;

	private BufferStrategy bs;
	private Graphics g;

	private final int FPS = 60;
	private double TARGETTIME = 1000000000 / FPS;
	private double delta = 0;

	private Seeker seeker;
	private ShowState show;

	public Window() {
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);

		canvas = new Canvas();

		canvas.setPreferredSize(new Dimension(800, 600));
		canvas.setMaximumSize(new Dimension(800, 600));
		canvas.setMinimumSize(new Dimension(800, 600));
		canvas.setFocusable(true);

		add(canvas);
		setVisible(true);
	}

	public static void main(String[] args) {
		new Window().start();
	}

	private void update(float dt) {
		//seeker.update(dt);
		show.update(dt);
	}

	private void draw() {
		bs = canvas.getBufferStrategy();

		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics();

		// -----------------------
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());

		//g.drawImage(Assets.seeker, (int)MouseInfo.getPointerInfo().getLocation().getX(), (int)MouseInfo.getPointerInfo().getLocation().getY(), null);
		show.draw(g);
		// ---------------------

		g.dispose();
		bs.show();
	}

	private void init() {
		Assets.init();
		show = new ShowState();
		//seeker = new Seeker(new Vector2D(200, 200), new Vector2D(), 7.0, Assets.seeker, new Vector2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY()), this);
	}

	@Override
	public void run() {

		long now = 0;
		long lastTime = System.nanoTime();
		long time = 0;

		init();

		while (running) {
			now = System.nanoTime();
			delta += (now - lastTime) / TARGETTIME;
			time += (now - lastTime);
			lastTime = now;

			if (delta >= 1) {
				update((float) (delta * TARGETTIME * 0.000001f));
				draw();
				delta--;

			}
			if (time >= 1000000000) {
				time = 0;

			}

		}
		stop();
	}

	private void start() {

		thread = new Thread(this);
		thread.start();
		running = true;

	}

	private void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Seeker getSeeker() {
		return seeker;
	}
	
}