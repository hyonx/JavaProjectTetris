
import javax.swing.*;
import java.awt.*;
public class HomePanel extends JPanel {
    JButton start;
    JButton load;
    JButton rule;
    public HomePanel() {
        this.setLayout(null);
        start = new JButton("start");
        load = new JButton("load");
        rule = new JButton("Rule");
        start.setBounds(160*2,220*2,200*2,80*2);
        load.setBounds(160*2,320*2,200*2,80*2);
        rule.setBounds(160*2,420*2,200*2,80*2);

        this.add(start);
        this.add(load);
        this.add(rule);
        start.setVisible(true);
        load.setVisible(true);
        rule.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.tetris, -63*2, -10*2, null);
        super.paintChildren(g);
    }
}
