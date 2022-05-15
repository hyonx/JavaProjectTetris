import javax.swing.*;

public class PausePanel extends JPanel{
    JButton startAgain;
    JButton homeAgain;

    public PausePanel(){
        startAgain=new JButton("RESTART");
        homeAgain=new JButton("HOME");
        this.add(startAgain);
        this.add(homeAgain);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }
}
