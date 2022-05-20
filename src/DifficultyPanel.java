import javax.swing.*;
import java.awt.*;

public class DifficultyPanel extends JPanel {
    JButton easy;
    JButton middle;
    JButton hard;
    

    public DifficultyPanel(){
        this.setLayout(null);
        easy=new JButton("Easy");
        middle=new JButton("Middle");
        hard=new JButton("Hard");

        easy.setBounds(163*2,200*2,200*2,70*2);
        middle.setBounds(163*2,300*2,200*2,70*2);
        hard.setBounds(163*2,400*2,200*2,70*2);

        easy.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));
        middle.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));
        hard.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 30*2));
        

        this.add(easy);
        this.add(middle);
        this.add(hard);
        

        
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.Difficulty, 0, 0, null);
        super.paintChildren(g);
    }
}

