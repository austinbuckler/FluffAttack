

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

public class Assign9 extends Applet {
	
	private World world = null;
	private boolean gameOver = false;
	private boolean didPassPlayer = false;
	private int secondsLeft;
	private GameAlarm alarm = null;
	private Font font = null;
	private AudioClip soundBossDeath = null;
	private AudioClip soundHit = null;
	private AudioClip soundJump = null;
	private AudioClip soundLose = null;
	private AudioClip soundWin = null;
	private AudioClip soundShoot = null;
	
	// REQUIRED FOR FLICKER/FLASH FIX
	private Image image = null;
	private Graphics gfx = null;

	@Override
	public void init() {
		super.init();
		setSize(Constants.APPLET_WIDTH, Constants.APPLET_HEIGHT);
		setBackground(Color.BLACK);
		
		soundBossDeath = getAudioClip(getDocumentBase(), "assets/sound_boss_death.wav");
		soundHit = getAudioClip(getDocumentBase(), "assets/sound_hit.wav");
		soundJump = getAudioClip(getDocumentBase(), "assets/sound_jump.wav");
		soundLose = getAudioClip(getDocumentBase(), "assets/sound_lose.wav");
		soundShoot = getAudioClip(getDocumentBase(), "assets/sound_shoot.wav");
		soundWin = getAudioClip(getDocumentBase(), "assets/sound_win.wav");
		
		world = new World(this);
		alarm = new GameAlarm(180);
		alarm.createAlarm();
		
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/font.ttf")).deriveFont(21f);
			setFont(font);
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		addMouseListener(world.getPlayer().getInput());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		final Graphics2D g2d = (Graphics2D) g;
		
		secondsLeft = alarm.getTimeLeft();
		g2d.setColor(Color.WHITE);
		g2d.drawString("TIME: " + secondsLeft, world.getPlayer().getPosition().getX() + 60, world.getPlayer().getPosition().getY());
		g2d.drawString("SCORE: " + world.getPlayer().getScore(), world.getPlayer().getPosition().getX() + 60, world.getPlayer().getPosition().getY() + 20);
		g2d.drawString("HEALTH: " + world.getPlayer().getHitpoints(), world.getPlayer().getPosition().getX() + 60, world.getPlayer().getPosition().getY() + 40);
		g2d.drawString(world.getWave() == 4 ? "FINAL WAVE" : "WAVE: " + world.getWave(), world.getPlayer().getPosition().getX() + 60, world.getPlayer().getPosition().getY() + 60);
		
		if (secondsLeft > 170) {
			g2d.setColor(Color.WHITE);
			g2d.drawString("Right click to shoot", 420, 520);
			g2d.drawString("Left click to stay in the air", 420, 540);
			g2d.setColor(Color.YELLOW);
			g2d.drawString("You are the green person", 420, 560);
			g2d.setColor(Color.RED);
			g2d.drawString("Survive 3 waves to reach the boss", 420, 580);
		}
		
		if (world.getWave() == 5) {
			setGameOver(true);
			g2d.setColor(Color.GREEN);
			g2d.drawString("YOU WIN!", 300, 200);
			soundWin.play();
			return;
		} else {
			if (world.getPlayer().isDead() || secondsLeft <= 0
					|| isDidPassPlayer()) {
				setGameOver(true);
				g2d.setColor(Color.RED);
				g2d.drawString("YOU LOSE!", 300, 200);
				soundLose.play();
				return;
			}
		}
		
		world.tick(g2d);
		
		repaint();
		delay(30);
	}
	
	@Override
	public void update(final Graphics g) {
		if (image == null) {
			image = createImage(getWidth(), getHeight());
			gfx = image.getGraphics();
		}

		gfx.setColor(getBackground());
		gfx.fillRect(0, 0, getWidth(), getHeight());

		gfx.setColor(getForeground());
		paint(gfx);

		g.drawImage(image, 0, 0, null);
	}
	
	public void delay(long NumMilliSecs) {
		long curtime = System.currentTimeMillis();
		long stoptime = curtime + NumMilliSecs;
		while (System.currentTimeMillis() < stoptime);
	}
	
	public static int random(int maxRange) {
		return (int) Math.round((Math.random() * maxRange));
	}

	/**
	 * Prints a message to the console.
	 * @param message The message being printed.
	 */
	public static void sysOut(String message) {
		System.out.println(message);
	}
	
	public World getWorld() {
		return world;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public int getSecondsLeft() {
		return secondsLeft;
	}


	public AudioClip getSoundBossDeath() {
		return soundBossDeath;
	}

	public AudioClip getSoundHit() {
		return soundHit;
	}

	public AudioClip getSoundJump() {
		return soundJump;
	}

	public AudioClip getSoundLose() {
		return soundLose;
	}

	public AudioClip getSoundWin() {
		return soundWin;
	}

	public AudioClip getSoundShoot() {
		return soundShoot;
	}


	public boolean isDidPassPlayer() {
		return didPassPlayer;
	}

	public void setDidPassPlayer(boolean didPassPlayer) {
		this.didPassPlayer = didPassPlayer;
	}


	/**
	 * Generated serial.
	 */
	private static final long serialVersionUID = 1L;

}
