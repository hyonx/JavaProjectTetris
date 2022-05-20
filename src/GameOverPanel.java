import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel{
    JButton restart;
    JButton home;

    public GameOverPanel(){
        this.setLayout(null);
        restart=new JButton("RESTART");
        home=new JButton("HOME");
        restart.setBounds(150*2,310*2,200*2,80*2);
        home.setBounds(150*2,410*2,200*2,80*2);
        this.add(restart);
        this.add(home);
        restart.setVisible(true);
        home.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.gameOver, 0, 0, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30*2));
        g.drawString("SCORES:" + Tetris.getTotalScore(), 200*2, 250*2);
        g.drawString("LINES:" + Tetris.getTotalLine(), 200*2, 295*2);
        super.paintChildren(g);
    }
}
