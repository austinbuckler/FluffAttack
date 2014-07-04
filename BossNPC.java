

import java.awt.Color;
import java.awt.Graphics2D;

public class BossNPC extends NPC {
	
	private Assign9 main;

	public BossNPC(Assign9 main, Position position) {
		super("boss_npc", 100, position);
		this.main = main;
		setMoveDelay(800);
		setDead(false);
	}

	@Override
	public void onCollision(Entity other) {
		if (other instanceof Bullet) {
			final Bullet bullet = (Bullet) other;
			if (!(bullet.getOwner() instanceof NPC)) {
				bullet.setDead(true);
				setHitpoints(getHitpoints() - 25);
				if (getHitpoints() <= 0) {
					setDead(true);
					World.enemyCount --;
					SoundEffects.BOSS_DEATH.play();
					if (bullet.getOwner() instanceof Player) {
						final Player player = (Player) bullet.getOwner();
						player.setScore(player.getScore() + 1000);
					}
				}
			}
		} else if (other instanceof Player) {
			final Player player = (Player) other;
			player.setDead(true);
		}
	}

	@Override
	public void fire(Bullet bullet) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Graphics2D g) {
		if (!isDead()) {
			
			//g.setColor(Color.RED);
			//g.drawString("BOSS", getPosition().getX(), getPosition().getY() - 20);
			
			if (System.currentTimeMillis() - getMoveDelay() > 40) {
				setPosition(new Position(getPosition().getX() - 2, getPosition()
						.getY()));
				setMoveDelay(System.currentTimeMillis());
			}
			
			if (getPosition().getX() <= 0) {
				main.setDidPassPlayer(true);
			}
		}
	}

}
