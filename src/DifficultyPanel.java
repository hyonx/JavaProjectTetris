import javax.swing.*;
import java.awt.*;
//难度选择面板
public class DifficultyPanel extends JPanel {
    //创建三个label
    JLabel easy;
    JLabel middle;
    JLabel hard;
    //创建六个ImageIcon
    public static ImageIcon easyImage;
    public static ImageIcon easyImageEntered;
    public static ImageIcon middleImage;
    public static ImageIcon middleImageEntered;
    public static ImageIcon hardImage;
    public static ImageIcon hardImageEntered;

    //读入ImageIcon值
    static{
        try{
            easyImage = new ImageIcon(DifficultyPanel.class.getResource("easyImage.png"));
            easyImageEntered = new ImageIcon(DifficultyPanel.class.getResource("easyImageEntered.png"));
            middleImage = new ImageIcon(DifficultyPanel.class.getResource("middleImage.png"));
            middleImageEntered = new ImageIcon(DifficultyPanel.class.getResource("middleImageEntered.png"));
            hardImage = new ImageIcon(DifficultyPanel.class.getResource("hardImage.png"));
            hardImageEntered = new ImageIcon(DifficultyPanel.class.getResource("hardImageEntered.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //DifficultyPanel 构造器
    public DifficultyPanel(){
        this.setLayout(null);

        //初始化三个label
        easy = new JLabel();
        middle = new JLabel();
        hard = new JLabel();

        //设定三个label大小 位置
        easy.setBounds(163*2,228*2,200*2,70*2);
        middle.setBounds(163*2,330*2,200*2,70*2);
        hard.setBounds(163*2,432*2,200*2,70*2);

        //调整六个ImageIcon大小
        easyImage.setImage(easyImage.getImage().getScaledInstance(380,140,1));
        middleImage.setImage(middleImage.getImage().getScaledInstance(380,140,1));
        hardImage.setImage(hardImage.getImage().getScaledInstance(380,140,1));

        //将ImageIcon加入label中
        easy.setIcon(easyImage);
        middle.setIcon(middleImage);
        hard.setIcon(hardImage);

        //面板中添加label
        this.add(easy);
        this.add(middle);
        this.add(hard);
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }

    //绘制背景
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

