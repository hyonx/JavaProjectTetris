import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel{
    JButton restart;
    JButton home;

    public GameOverPanel(){
        this.setLayout(null);
        restart=new JButton("Restart");
        home=new JButton("Home");
        restart.setBounds(163*2,310*2,200*2,70*2);
        home.setBounds(163*2,410*2,200*2,70*2);
        restart.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));
        home.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));
        this.add(restart);
        this.add(home);
        restart.setVisible(true);
        home.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.gameOver, 0, -150, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 30*2));
        g.drawString("SCORES:" + Tetris.getTotalScore(), 50*2, 250*2);
        g.drawString("LINES:" + Tetris.getTotalLine(), 340*2, 250*2);
        super.paintChildren(g);
    }
}

