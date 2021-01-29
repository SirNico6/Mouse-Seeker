package movingObject;

import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import math.Vector2D;
import states.ShowState;

public abstract class MovingObject extends GameObject{
	
	protected Vector2D velocity;
	protected AffineTransform at;
	protected double angle;
	protected double maxVel;
	protected int width;
	protected int height;
	protected ShowState window;
	
	public MovingObject(Vector2D position, Vector2D velocity, double maxVel, BufferedImage texture, ShowState window) {
		super(position, texture);
		this.velocity = velocity;
		this.maxVel = maxVel;
		this.window = window;
		width = texture.getWidth();
		height = texture.getHeight();
		angle = 0;
	}
	
	public Vector2D getCenter(){
		return new Vector2D(position.getX() + width/2, position.getY() + height/2);
	}
	
}