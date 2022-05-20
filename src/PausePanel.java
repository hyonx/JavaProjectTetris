import javax.swing.*;
public class PausePanel extends JPanel{
    JButton startAgain;
    JButton homeAgain;
    JButton save;
    JButton Continue;
    JLabel background;
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

        background = new JLabel();
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(535, 595, 1));
        background.setIcon(backgroundImage);


        background.setBounds(0, 0,535,595);
        startAgain.setBounds(150,100,200,80);
        Continue.setBounds(150,200,200,80);
        save.setBounds(150,300,200,80);
        homeAgain.setBounds(150,400,200,80);
        
        this.add(startAgain);
        this.add(Continue);
        this.add(save);
        this.add(homeAgain);
        this.add(background);

        background.setVisible(true);
        save.setVisible(true);
        Continue.setVisible(true);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }
}
