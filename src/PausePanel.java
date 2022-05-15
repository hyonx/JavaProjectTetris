import javax.swing.*;

public class PausePanel extends JPanel{
    JButton startAgain;
    JButton homeAgain;
    JButton save;
    JButton Continue;

    public PausePanel(){
        this.setLayout(null);
        startAgain=new JButton("RESTART");
        homeAgain=new JButton("HOME");
        save=new JButton("SAVE");
        Continue=new JButton("CONTINUE");
        this.add(startAgain);
        this.add(Continue);
        this.add(save);
        this.add(homeAgain);

        save.setVisible(true);
        Continue.setVisible(true);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }
}

