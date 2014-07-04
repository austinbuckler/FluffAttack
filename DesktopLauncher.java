import javax.swing.*;

/**
 * Created by Austin on 2014-07-03.
 */
public class DesktopLauncher {

    public static void main(String[] args) {
        Assign9 assign9 = new Assign9();
        assign9.init();
        assign9.start();
        JFrame jFrame = new JFrame("Fluff Attack");
        jFrame.setContentPane(assign9);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(Constants.APPLET_WIDTH + 100, Constants.APPLET_HEIGHT + 100);
        jFrame.setVisible(true);
    }

}
