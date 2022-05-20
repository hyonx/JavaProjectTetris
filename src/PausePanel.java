import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

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
        startAgain=new JButton("Restart");
        homeAgain=new JButton("Home");
        save=new JButton("Save");
        Continue=new JButton("Continue");

        startAgain.setBounds(163*2,163*2,200*2,65*2);
        Continue.setBounds(163*2,252*2,200*2,65*2);
        save.setBounds(163*2,341*2,200*2,65*2);
        homeAgain.setBounds(163*2,430*2,200*2,65*2);

        startAgain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));
        Continue.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));
        save.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));
        homeAgain.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));

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
        g.drawImage(Tetris.Pause, 0, -80, null);
        super.paintChildren(g);
    }
}
