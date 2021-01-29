package states;

import java.awt.Graphics;
import java.awt.MouseInfo;

import graphics.Assets;
import math.Vector2D;
import movingObject.Seeker;

public class ShowState {
	
	private Seeker seeker;
	
	public ShowState()
	{
		seeker = new Seeker(new Vector2D(200, 200), new Vector2D(), 7.0, Assets.seeker, new Vector2D(MouseInfo.getPointerInfo().getLocation().getX(), MouseInfo.getPointerInfo().getLocation().getY()), this);
	}
	
	public void update(float dt)
	{
		seeker.update(dt);
	}
	
	public void draw(Graphics g)
	{
		seeker.draw(g);
	}
	
	public Seeker getSeeker() {
		return seeker;
	}
}
