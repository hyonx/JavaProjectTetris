import javax.swing.*;
import java.awt.*;
//开始面板
public class HomePanel extends JPanel {
    //创建三个label和六个ImageIcon
    JLabel  startLabel;
    JLabel  loadLabel;
    JLabel  ruleLabel;
    public static ImageIcon startImage;
    public static ImageIcon startImageEntered;
    public static ImageIcon loadLabelImage;
    public static ImageIcon loadLabelImageEntered;
    public static ImageIcon ruleImage;
    public static ImageIcon ruleImageEntered;

    //读入ImageIcon值
    static{
        try{
            startImage = new ImageIcon("src/startimage.png");
            startImageEntered = new ImageIcon("src/startimageEntered.png");
            loadLabelImage = new ImageIcon("src/loadLabelimage.png");
            loadLabelImageEntered = new ImageIcon("src/loadLabelimageEntered.png");
            ruleImage = new ImageIcon("src/ruleimage.png");
            ruleImageEntered = new ImageIcon("src/ruleimageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //HomePanel构造器
    public HomePanel() {
        this.setLayout(null);

        //初始化三个label
        startLabel = new JLabel();
        loadLabel = new JLabel();
        ruleLabel = new JLabel();

        //设定三个label大小 位置
        startLabel.setBounds(163*2,228*2,200*2,80*2);
        loadLabel.setBounds(163*2,330*2,200*2,80*2);
        ruleLabel.setBounds(163*2,432*2,200*2,80*2);

        //调整三个ImageIcon大小
        startImage.setImage(startImage.getImage().getScaledInstance(380,140,1));
        loadLabelImage.setImage( loadLabelImage.getImage().getScaledInstance(380,140,1));
        ruleImage.setImage(ruleImage.getImage().getScaledInstance(380,140,1));

        //将ImageIcon加入label中
        startLabel.setIcon(startImage);
        loadLabel.setIcon( loadLabelImage);
        ruleLabel.setIcon(ruleImage);

        //面板中添加label
        this.add(startLabel);
        this.add(loadLabel);
        this.add(ruleLabel);
        startLabel.setVisible(true);
        loadLabel.setVisible(true);
        ruleLabel.setVisible(true);
    }

    //绘制面板背景
    public void paint(Graphics g){
        g.drawImage(Tetris.tetris, -55*2, -10*2, null);
        super.paintChildren(g);
    }

    //构造setImage方法 以便在mainFrame调用
    public void setImage(JLabel label,ImageIcon icon){
        icon.setImage(icon.getImage().getScaledInstance(380,140,1));
        label.setIcon(icon);
    }
}
