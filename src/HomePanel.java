
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomePanel extends JPanel {
    JButton start;
    JButton load;
    JButton rule;
    public HomePanel(){
        this.setLayout(null);
        start = new JButton("start");
        load = new JButton("load");
        rule = new JButton("Rule");

        start.setBounds(150,200,200,80);
        load.setBounds(150,300,200,80);
        rule.setBounds(150,400,200,80);
        this.add(start);
        this.add(load);
        this.add(rule);
        start.setVisible(true);
        load.setVisible(true);
        rule.setVisible(true);
    }
}

