
/**
 * Represents an applet alarm.
 * @author 216517
 *
 */
public class GameAlarm {

	private long currentTime;
	private long stopTime;
	private long timeInMillis;
	
	public GameAlarm(int timeInSeconds) {
		this.timeInMillis = (long) (timeInSeconds * 1000);
	}
	
	public void createAlarm() {
		this.currentTime = System.currentTimeMillis();
		this.stopTime = currentTime + timeInMillis;
	}
	
	public int getTimeLeft() {
		this.currentTime = System.currentTimeMillis();
		return (int) (stopTime - currentTime) / 1000;
	}
	
	public boolean hasExpired() {
		this.currentTime = System.currentTimeMillis();
		return (currentTime > stopTime);
	}
	
}