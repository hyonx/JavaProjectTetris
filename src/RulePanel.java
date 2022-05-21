import javax.swing.*;
import java.awt.*;

//规则界面
public class RulePanel extends JPanel {
    //创建ok label 和 okImage ImageIcon
    static JLabel ok;
    public  static ImageIcon okImage;
    //读入ImageIcon值
    static{
        try{
            okImage = new ImageIcon("src/OKImageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //RulePanel构造器
    public RulePanel() {
        this.setLayout(null);

        //对ok label进行调整
        ok = new JLabel();
        ok.setBounds(200*2,480*2,120*2,50*2);
        okImage.setImage(okImage.getImage().getScaledInstance(240,100,1));
        ok.setIcon(okImage);
        ok.setVisible(true);

        //在面板中添加ok label
        this.add(ok);
    }

    public void paint(Graphics g){
        //画面板背景
        g.drawImage(Tetris.Rule, -15, -150, null);

        //画规则
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20*2));
        g.drawString("操作方块，使之排列成完整的一行消除得分",100,280);
        g.drawString("左键 左移方块                         右键 右移方块",50,380);
        g.drawString("下键 加速方块下落                  W键 方块直接落地",50,480);
        g.drawString("A键 逆时针旋转方块                D键 顺时针旋转方块",50,580);
        g.drawString("一次消1，2，3，4行  分别得1，2，5，10分",100,690);
        g.drawString("随着得分的增多  方块下落速度加快",170,780);
        g.drawString("方块堆积到顶端  游戏结束",250,880);

        //重绘组件
        super.paintChildren(g);
    }
}
