import javax.swing.*;
import java.awt.*;
//停止面板
public class PausePanel extends JPanel{
    //创建四个label
    JLabel startAgain;
    JLabel homeAgain;
    JLabel save;
    JLabel Continue;

    //创建八个ImageIcon
    public static ImageIcon startAgainImage;
    public static ImageIcon startAgainImageEntered;
    public static ImageIcon homeAgainImage;
    public static ImageIcon homeAgainImageEntered;
    public static ImageIcon saveImage;
    public static ImageIcon saveImageEntered;
    public static ImageIcon ContinueImage;
    public static ImageIcon ContinueImageEntered;

    //读入ImageIcon值
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

    //PausePanel 构造器
    public PausePanel(){
        this.setLayout(null);

        //初始化label
        startAgain=new JLabel();
        homeAgain=new JLabel();
        save=new JLabel();
        Continue=new JLabel();

        //设定label大小 位置
        Continue.setBounds(163*2,163*2,200*2,75*2);
        startAgain.setBounds(163*2,252*2,200*2,75*2);
        save.setBounds(163*2,341*2,200*2,75*2);
        homeAgain.setBounds(163*2,430*2,200*2,75*2);

        //调整ImageIcon大小
        startAgainImage.setImage(startAgainImage.getImage().getScaledInstance(380,140,1));
        homeAgainImage.setImage(homeAgainImage.getImage().getScaledInstance(380,140,1));
        saveImage.setImage(saveImage.getImage().getScaledInstance(380,140,1));
        ContinueImage.setImage(ContinueImage.getImage().getScaledInstance(380,140,1));

        //将ImageIcon加入label中
        startAgain.setIcon(startAgainImage);
        homeAgain.setIcon(homeAgainImage);
        save.setIcon(saveImage);
        Continue.setIcon(ContinueImage);

        //面板中添加label
        this.add(Continue);
        this.add(startAgain);
        this.add(save);
        this.add(homeAgain);
        save.setVisible(true);
        Continue.setVisible(true);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }

    //绘制背景
    public void paint(Graphics g){
        g.drawImage(Tetris.Pause, 0, 0, null);
        super.paintChildren(g);
    }

    //构造setImage方法 以便在mainFrame调用
    public void setImage(JLabel label,ImageIcon icon){
        icon.setImage(icon.getImage().getScaledInstance(380,140,1));
        label.setIcon(icon);
    }
}
