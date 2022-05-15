
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
        start = new JButton("START");
        load = new JButton("LOAD");
        rule = new JButton("RULE");
        
        this.add(start);
        this.add(load);
        this.add(rule);
        start.setVisible(true);
        load.setVisible(true);
        rule.setVisible(true);
    }
}

