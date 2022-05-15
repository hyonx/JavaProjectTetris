import javax.swing.*;

public class GameOverPanel extends JPanel{
    JButton restart;
    JButton home;

    public GameOverPanel(){
        restart=new JButton("RESTART");
        home=new JButton("HOME");
        this.add(restart);
        this.add(home);
        restart.setVisible(true);
        home.setVisible(true);
    }
}
