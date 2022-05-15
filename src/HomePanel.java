
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HomePanel extends JPanel {
    JButton start;
    JButton load;
    JButton quit;
    public HomePanel(){
        Container a=new Container();
        start = new JButton("start");
        load = new JButton("load");
        quit = new JButton("quit");
        a.add(start);

      //  start.setBounds(200,200,40,40);
        this.add(start);
        this.add(load);
        this.add(quit);
        start.setVisible(true);
        load.setVisible(true);
        quit.setVisible(true);
    }
}

