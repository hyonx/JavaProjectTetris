import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class PausePanel extends JPanel{
    JLabel startAgain;
    JLabel homeAgain;
    JLabel save;
    JLabel Continue;
    public static ImageIcon startAgainImage;
    public static ImageIcon startAgainImageEntered;
    public static ImageIcon homeAgainImage;
    public static ImageIcon homeAgainImageEntered;
    public static ImageIcon saveImage;
    public static ImageIcon saveImageEntered;
    public static ImageIcon ContinueImage;
    public static ImageIcon ContinueImageEntered;
    static {
        try {
            startAgainImage = new ImageIcon("src/startAgainImage.png");
            startAgainImageEntered = new ImageIcon("src/startAgainImageEntered.png");
            homeAgainImage = new ImageIcon("src/homeAgainImage.png");
            homeAgainImageEntered = new ImageIcon("src/homeAgainImageEntered.png");
            saveImage = new ImageIcon("src/saveImage.png");
            saveImageEntered = new ImageIcon("src/saveImageEntered.png");
            ContinueImage = new ImageIcon("src/ContinueImage.png");
            ContinueImageEntered = new ImageIcon("src/ContinueImageEntered.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PausePanel(){
        this.setLayout(null);

        startAgain=new JLabel();
        homeAgain=new JLabel();
        save=new JLabel();
        Continue=new JLabel();

        startAgain.setBounds(163*2,163*2,200*2,75*2);
        Continue.setBounds(163*2,252*2,200*2,75*2);
        save.setBounds(163*2,341*2,200*2,75*2);
        homeAgain.setBounds(163*2,430*2,200*2,75*2);

        startAgainImage.setImage(startAgainImage.getImage().getScaledInstance(380,140,1));
        startAgain.setIcon(startAgainImage);

        homeAgainImage.setImage(homeAgainImage.getImage().getScaledInstance(380,140,1));
        homeAgain.setIcon(homeAgainImage);

        saveImage.setImage(saveImage.getImage().getScaledInstance(380,140,1));
        save.setIcon(saveImage);

        ContinueImage.setImage(ContinueImage.getImage().getScaledInstance(380,140,1));
        Continue.setIcon(ContinueImage);

        this.add(Continue);
        this.add(startAgain);
        this.add(save);
        this.add(homeAgain);
        
        save.setVisible(true);
        Continue.setVisible(true);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }

    public void paint(Graphics g){
        g.drawImage(Tetris.Pause, 0, 0, null);
        super.paintChildren(g);
    }
    public void setImage(JLabel b,ImageIcon i){
        i.setImage(i.getImage().getScaledInstance(380,140,1));
        b.setIcon(i);
    }
}
