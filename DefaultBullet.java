

import java.awt.Graphics2D;

public class DefaultBullet extends Bullet {

	public DefaultBullet(Entity owner, Position position) {
		super(owner, "bullet_default", position);
		setGravity(1.25);
	}

	@Override
	public void onCollision(Entity other) {
		getOwner().setFiredBullet(false);
	}

	@Override
	public void update(Graphics2D g) {
		setVelocity(getVelocity() + getGravity());
		if (getOwner() instanceof Player) {
			setPosition(new Position(getPosition().getX() + (int) getVelocity(), getPosition().getY()));
		}
		
		if (getOwner() instanceof NPC) {
			setPosition(new Position(getPosition().getX() - (int) getVelocity(), getPosition().getY()));
		}
	}

}
