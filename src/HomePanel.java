import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomePanel extends JPanel {
    JButton start;
    JButton settings;
    JButton quit;
    public HomePanel(){
        start = new JButton("start");
        settings = new JButton("settings");
        quit = new JButton("quit");
        this.add(start);
        this.add(settings);
        this.add(quit);
        start.setVisible(true);
        settings.setVisible(true);
        quit.setVisible(true);
    }
}
