


public abstract class NPC extends Entity implements Collidable {

	private long moveDelay;
	private long attackDelay;
	
	public NPC(String fileName, int hitpoints, Position position) {
		super(fileName, position);
		this.setHitpoints(hitpoints);
	}

	public long getMoveDelay() {
		return moveDelay;
	}

	public void setMoveDelay(long moveDelay) {
		this.moveDelay = moveDelay;
	}

	public long getAttackDelay() {
		return attackDelay;
	}

	public void setAttackDelay(long attackDelay) {
		this.attackDelay = attackDelay;
	}

}
