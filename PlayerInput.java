

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Handles game input for the player.
 * @author 216517
 *
 */
public class PlayerInput extends MouseAdapter {

	private final Player player;
	
	public PlayerInput(final Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		int button = evt.getButton();
	
		if (button == MouseEvent.BUTTON1) {
			player.jump();
		} else if (button == MouseEvent.BUTTON2) {
			// dunno
		} else if (button == MouseEvent.BUTTON3) {
			player.fire(new DefaultBullet(player, player.getPosition()));
		}
		
	}

}
