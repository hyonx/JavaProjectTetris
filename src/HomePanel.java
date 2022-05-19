
import javax.swing.*;
import java.awt.*;
public class HomePanel extends JPanel {
    JButton start;
    JButton load;
    JButton rule;
    JLabel background;
    public static ImageIcon backgroundImage;

    static {
        try {
            backgroundImage = new ImageIcon("src/科技背景.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HomePanel() {
        this.setLayout(null);
        start = new JButton("start");
        load = new JButton("load");
        rule = new JButton("Rule");
        background = new JLabel();
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(535, 595, Image.SCALE_DEFAULT));
        background.setIcon(backgroundImage);
        start.setBounds(150, 200, 200, 80);
        load.setBounds(150, 300, 200, 80);
        rule.setBounds(150, 400, 200, 80);
        background.setBounds(0, 0,535,595);

        this.add(start);
        this.add(load);
        this.add(rule);
        this.add(background);
        background.setVisible(true);
        start.setVisible(true);
        load.setVisible(true);
        rule.setVisible(true);
    }
}
