

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class World {
	
	private final Assign9 main;
	private final Player player;
	private static final List<Entity> entities = new ArrayList<Entity>();
	public static int enemyCount = 0;
	
	private boolean newWave = false;
	private int wave = 0;
	
	public World(Assign9 main) {
		this.main = main;
		this.player = new Player(main);
		this.newWave = true;
	}
	
	public void tick(final Graphics2D g) {
		
		if (main.isGameOver()) {
			return;
		}
		
		if (newWave)  {
			enemyCount = 0;
			entities.clear();
			if (wave < 5) {
				wave ++;
			}
			createInitSpawn(g, true);
			newWave = false;
		}
		player.draw(g);
		player.update(g);
		
		for (Entity e : entities) {
			if (e != null) {
				
				if (e instanceof DefaultNPC) {
					final DefaultNPC npc = (DefaultNPC) e;
					npc.draw(g);
					npc.update(g);
				}
				
				if (e instanceof BossNPC) {
					final BossNPC npc = (BossNPC) e;
					npc.draw(g);
					npc.update(g);
				}
				
				if (player.collidesWithSprite(e)) {
					player.onCollision(e);
					if (e instanceof Collidable) {
						((Collidable) e).onCollision(player);
					}
				}
			}
		}
		
		if (enemyCount <= 0) {
			newWave = true;
		}
	}
	
	private void createInitSpawn(Graphics2D g, boolean update) {
		if (wave == 1) {
			for (int y = 64; y < 385; y += 64) {
				createNPC(720, y, g, update);
				enemyCount ++;
			}
		} else if (wave == 2) {
			for (int y = 64; y < 385; y += 64) {
				createNPC(620, y, g, update);
				enemyCount ++;
			}
			for (int y = 64; y < 385; y += 64) {
				createNPC(720, y, g, update);
				enemyCount ++;
			}
		} else if (wave == 3) {
			for (int y = 64; y < 385; y += 64) {
				createNPC(520, y, g, update);
				enemyCount ++;
			}
			for (int y = 64; y < 385; y += 64) {
				createNPC(620, y, g, update);
				enemyCount ++;
			}
			for (int y = 64; y < 385; y += 64) {
				createNPC(720, y, g, update);
				enemyCount ++;
			}
		} else if (wave == 4) {
			for (int y = 64; y < 385; y += 64) {
				createNPC(520, y, g, update);
				enemyCount ++;
			}
			for (int y = 64; y < 385; y += 64) {
				createNPC(620, y, g, update);
				enemyCount ++;
			}
			for (int y = 64; y < 385; y += 64) {
				createNPC(720, y, g, update);
				enemyCount ++;
			}
			createBoss(740, 100, g, update);
			enemyCount ++;
			createBoss(740, 200, g, update);
			enemyCount ++;
			createBoss(740, 300, g, update);
			enemyCount ++;
		}
	}
	
	public void createNPC(int x, int y, Graphics2D g, boolean update) {
		DefaultNPC npc = new DefaultNPC(main, new Position(x, y));
		npc.draw(g);
		if (update) {
			entities.add(npc);
		}
	}
	
	public void createBoss(int x, int y, Graphics2D g, boolean update) {
		BossNPC npc = new BossNPC(main, new Position(x, y));
		npc.draw(g);
		if (update) {
			entities.add(npc);
		}
	}

	public static List<Entity> getEntities() {
		return entities;
	}

	public Player getPlayer() {
		return player;
	}

	public int getWave() {
		return wave;
	}

	public void setWave(int wave) {
		this.wave = wave;
	}
	
}
