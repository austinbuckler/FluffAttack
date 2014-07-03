

import java.awt.Graphics2D;

public class DefaultNPC extends NPC {

	private Assign9 main;
	
	public DefaultNPC(Assign9 main, Position position) {
		super("default_npc", 50, position);
		this.main = main;
		setMoveDelay(800);
		setAttackDelay(800);
	}

	@Override
	public void onCollision(Entity other) {
		if (other instanceof Bullet) {
			final Bullet bullet = (Bullet) other;
			if (!(bullet.getOwner() instanceof NPC)) {
				bullet.setDead(true);
				setDead(true);
				if (bullet.getOwner() instanceof Player) {
					final Player player = (Player) bullet.getOwner();
					player.setScore(player.getScore() + 50);
				}
			}
		} else if (other instanceof Player) {
			final Player player = (Player) other;
			player.setDead(true);
		}
	}

	@Override
	public void fire(Bullet bullet) {
	}

	@Override
	public void update(Graphics2D g) {

		if (!isDead()) {
			if (System.currentTimeMillis() - getMoveDelay() > 40) {
				setPosition(new Position(getPosition().getX() - 3, getPosition()
						.getY()));
				setMoveDelay(System.currentTimeMillis());
			}
			
			if (getPosition().getX() <= 0) {
				main.setDidPassPlayer(true);
			}
		}

	}

}
