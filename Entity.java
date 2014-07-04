

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * An abstract representation of a game entity.
 * @author 216517
 *
 */
public abstract class Entity {
	
	private Image image;
	private final String fileName;
	
	private Position position;
	
	private boolean dead = false;
	private boolean firedBullet;
	private double velocity = 0.0;
	private double gravity = 0.25;
	private int hitpoints;
	private Bullet currentBullet = null;
	private final Rectangle hitbox;
	
	public Entity(final String fileName, final Position position) {
		this.fileName = fileName;
		try {
			this.image = ImageIO.read(
                    this.getClass().getClassLoader().getResource("assets/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.position = position;
		this.hitbox = new Rectangle();
		this.dead = false;
	}
	
	public abstract void fire(Bullet bullet);
	public abstract void update(Graphics2D g);
	
	public void draw(Graphics2D g) {
		if (dead) {
			return;
		}
		
		//g.setColor(Color.GREEN);
		//g.drawRect(position.getX(), position.getY(), image.getWidth(null), image.getHeight(null));
		g.drawImage(image, position.getX(), position.getY(), null);
		
	}
	
	public boolean collidesWithSprite(Entity entity) {
		hitbox.setBounds(position.getX(), position.getY(), image.getWidth(null), image.getHeight(null));
		entity.getHitbox().setBounds(entity.getPosition().getX(), entity.getPosition().getY(),
				entity.getImage().getWidth(null), entity.getImage().getHeight(null));
		return hitbox.intersects(entity.getHitbox()) && !entity.isDead();
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getFileName() {
		return fileName;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public Image getImage() {
		return image;
	}

	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getGravity() {
		return gravity;
	}

	public void setGravity(double gravity) {
		this.gravity = gravity;
	}

	public int getHitpoints() {
		return hitpoints;
	}

	public void setHitpoints(int hitpoints) {
		this.hitpoints = hitpoints;
	}

	public Bullet getCurrentBullet() {
		return currentBullet;
	}

	public void setCurrentBullet(Bullet currentBullet) {
		this.currentBullet = currentBullet;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public boolean isFiredBullet() {
		return firedBullet;
	}

	public void setFiredBullet(boolean firedBullet) {
		this.firedBullet = firedBullet;
	}
	
}
