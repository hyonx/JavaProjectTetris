import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class HomePanel extends JPanel {
    JButton start;
    JButton settings;
    JButton quit;
    public HomePanel(){
        start = new JButton("start");
        settings = new JButton("settings");
        quit = new JButton("quit");
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
            }
        });
    }
}
