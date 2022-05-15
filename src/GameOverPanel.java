import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel{
    JButton restart;
    JButton home;

    public GameOverPanel(){
        this.setLayout(null);
        restart=new JButton("RESTART");
        home=new JButton("HOME");
        restart.setBounds(150,310,200,80);
        home.setBounds(150,410,200,80);
        this.add(restart);
        this.add(home);
        restart.setVisible(true);
        home.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.gameOver, 0, 0, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        g.drawString("SCORES:" + Tetris.getTotalScore(), 200, 250);
        g.drawString("LINES:" + Tetris.getTotalLine(), 200, 295);
        super.paintChildren(g);
    }
}
