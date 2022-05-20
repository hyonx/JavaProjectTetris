import javax.swing.*;
public class PausePanel extends JPanel{
    JButton startAgain;
    JButton homeAgain;
    JButton save;
    JButton Continue;
    
    public static ImageIcon backgroundImage;

    static {
        try {
            backgroundImage = new ImageIcon("src/科技背景.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PausePanel(){
        this.setLayout(null);
        startAgain=new JButton("RESTART");
        homeAgain=new JButton("HOME");
        save=new JButton("SAVE");
        Continue=new JButton("CONTINUE");

       


        
        startAgain.setBounds(150*2,100*2,200*2,80*2);
        Continue.setBounds(150*2,200*2,200*2,80*2);
        save.setBounds(150*2,300*2,200*2,80*2);
        homeAgain.setBounds(150*2,400*2,200*2,80*2);
        
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
