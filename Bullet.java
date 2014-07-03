


public abstract class Bullet extends Entity implements Collidable {

	private final Entity owner;
	
	public Bullet(Entity owner, String fileName, Position position) {
		super(fileName, position);
		this.owner = owner;
	}
	
	@Override
	public void fire(Bullet bullet) {}

	public void reset(Entity entity) {
		entity.setCurrentBullet(new DefaultBullet(entity, entity.getPosition()));
		entity.setFiredBullet(false);
	}
	
	public Entity getOwner() {
		return owner;
	}

}
