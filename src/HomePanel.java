import javax.swing.*;
import java.awt.*;
public class HomePanel extends JPanel {
    JButton start;
    JButton load;
    JButton rule;
    public HomePanel() {
        this.setLayout(null);
        start = new JButton("Start");
        load = new JButton("Load");
        rule = new JButton("Rule");
        start.setBounds(163*2,228*2,200*2,80*2);
        load.setBounds(163*2,330*2,200*2,80*2);
        rule.setBounds(163*2,432*2,200*2,80*2);

        start.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));
        load.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));
        rule.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));

        this.add(start);
        this.add(load);
        this.add(rule);
        start.setVisible(true);
        load.setVisible(true);
        rule.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.tetris, -55*2, -10*2, null);
        super.paintChildren(g);
    }
}
