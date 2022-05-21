import javax.swing.*;
import java.awt.*;

public class RulePanel extends JPanel {
    static JLabel ok;
    public  static ImageIcon okImage;


    static{
        try{
            okImage = new ImageIcon("src/OKImageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public RulePanel() {
        this.setLayout(null);
        ok = new JLabel();
        ok.setBounds(163*2,420*2,200*2,70*2);
        okImage.setImage(okImage.getImage().getScaledInstance(380,140,1));
        ok.setIcon(okImage);

        this.add(ok);
        ok.setVisible(true);
    }
 public void paint(Graphics g){
     g.drawImage(Tetris.Rule, -20, -150, null);
     g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20*2));
     g.drawString("通过左右键 左移右移方块",25,280);
     g.drawString("通过左右键 左移右移方块",25,380);
     g.drawString("通过左右键 左移右移方块",25,480);
     g.drawString("通过左右键 左移右移方块",25,580);
     g.drawString("通过左右键 左移右移方块",25,680);

     super.paintChildren(g);
 }

}
