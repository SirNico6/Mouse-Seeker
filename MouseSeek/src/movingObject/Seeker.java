package movingObject;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import graphics.Assets;
import math.Vector2D;
import states.ShowState;

public class Seeker extends MovingObject {

	private Vector2D path;
	private static Vector2D heading = new Vector2D(0, 1);	
	
	public Seeker(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, Vector2D path, ShowState window) {
		super(position, velocity, maxVel, texture, window);
		this.path = path;
	}

	private Vector2D pathFollowing() {
		return seekForce(path);

	}

	private Vector2D seekForce(Vector2D target) {
		Vector2D desiredVelocity = target.subtract(getCenter());
		desiredVelocity = desiredVelocity.normalize().scale(maxVel);
		return desiredVelocity.subtract(velocity);
	}

	@Override
	public void update(float dt) {
		
		path = new Vector2D(MouseInfo.getPointerInfo().getLocation().getX(),MouseInfo.getPointerInfo().getLocation().getY());
		
		Vector2D pathFollowing = new Vector2D();

		pathFollowing = pathFollowing();
		
		pathFollowing = pathFollowing.scale(1 / 20);

		velocity = velocity.add(pathFollowing);

		velocity = velocity.limit(maxVel);

		position = position.add(velocity);

		if(position.getX() > 800)
			position.setX(0);
		if(position.getY() > 600)
			position.setY(0);
		
		if(position.getX() < -width)
			position.setX(800);
		if(position.getY() < -height)
			position.setY(600);
		
		Vector2D toPlayer = window.getSeeker().getCenter().subtract(getCenter());

		double currentAngle = toPlayer.getAngle();
		
		if (toPlayer.getX() < 0)
			currentAngle = -currentAngle + Math.PI;
		
		angle = currentAngle;

	}

	@Override
	public void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;

		at = AffineTransform.getTranslateInstance(position.getX(), position.getY());
		at.rotate(angle, Assets.seeker.getWidth() /2, Assets.seeker.getHeight() /2);
		
		g2d.drawImage(texture, at, null);

	}

	public static Vector2D getHeading() {
		return heading;
	}

}