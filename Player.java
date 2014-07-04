

import java.awt.Graphics2D;

public class Player extends Entity implements Collidable {

	private final Assign9 main;
	private final PlayerInput input;
	private int score = 0;
	
	public Player(Assign9 main) {
		super("player", new Position(0, 0));
		this.input = new PlayerInput(this);
		this.main = main;
		this.setHitpoints(100);
	}

	@Override
	public void onCollision(Entity other) {
		if (other instanceof DefaultNPC) {
			final DefaultNPC npc = (DefaultNPC) other;
			npc.setDead(true);
		} else if (other instanceof Bullet) {
			final Bullet bullet = (Bullet) other;
			if (bullet.getOwner() != this) {
				setHitpoints(getHitpoints() - 25);
				if (getHitpoints() <= 0) {
					setDead(true);
				}
				bullet.setDead(true);
			}
		}
	}
	
	@Override
	public void update(Graphics2D g) {
		setVelocity(getVelocity() + getGravity());
		setPosition(new Position(getPosition().getX(), getPosition().getY() + (int) getVelocity()));
		
		if (getCurrentBullet() != null) {
			if (getCurrentBullet().getPosition().getX() >= Constants.APPLET_WIDTH) {
				getCurrentBullet().setDead(true);
				setFiredBullet(false);
			}
			
			for (Entity e : World.getEntities()) {
				if (e != null) {
					if (getCurrentBullet().collidesWithSprite(e)) {
						getCurrentBullet().onCollision(e);
						if (e instanceof Collidable) {
							((Collidable) e).onCollision(getCurrentBullet());
							if (e instanceof DefaultNPC) {
								World.enemyCount --;
							}
						}
					}
				}
			}
		}
		
		if (isFiredBullet()) {
			getCurrentBullet().draw(g);
			getCurrentBullet().update(g);
		}
		
		if (getPosition().getY() > Constants.APPLET_HEIGHT) {
			setDead(true);
			return;
		}
	}
	
	@Override
	public void fire(Bullet bullet) {
		if (!isFiredBullet()) {
			this.setCurrentBullet(bullet);
		}
		setFiredBullet(true);
        SoundEffects.SHOOT.play();
	}
	
	public void jump() {
		if (getPosition().getY() < 0) {
			return;
		}
		setVelocity(-7.6);
        SoundEffects.JUMP.play();
	}

	/**
	 * Gets the input instance.
	 * @return The input.
	 */
	public PlayerInput getInput() {
		return input;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
}
