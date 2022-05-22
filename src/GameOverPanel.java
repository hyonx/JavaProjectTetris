import javax.swing.*;
import java.awt.*;
//失败界面
public class GameOverPanel extends JPanel{
    //创建两个label
    JLabel restart;
    JLabel home;

    //创建四个ImageIcon
    public static ImageIcon restartImage;
    public static ImageIcon restartImageEntered;
    public static ImageIcon homeImage;
    public static ImageIcon homeImageEntered;

    //读入ImageIcon
    static{
        try{
            restartImage = new ImageIcon(GameOverPanel.class.getResource("startAgainImage.png"));
            restartImageEntered = new ImageIcon(GameOverPanel.class.getResource("startAgainImageEntered.png"));
            homeImage = new ImageIcon(GameOverPanel.class.getResource("homeAgainImage.png"));
            homeImageEntered = new ImageIcon(GameOverPanel.class.getResource("homeAgainImageEntered.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //GameOverPanel 构造器
    public GameOverPanel(){
        this.setLayout(null);

        //初始化label
        restart=new JLabel();
        home=new JLabel();

        //设定label大小 位置
        restart.setBounds(163*2,310*2,200*2,70*2);
        home.setBounds(163*2,410*2,200*2,70*2);

        //调整ImageIcon大小
        restartImage.setImage(restartImage.getImage().getScaledInstance(380,140,1));
        homeImage.setImage(homeImage.getImage().getScaledInstance(380,140,1));

        //将ImageIcon加入label中
        restart.setIcon(restartImage);
        home.setIcon(homeImage);

        //面板中添加label
        this.add(restart);
        this.add(home);
        restart.setVisible(true);
        home.setVisible(true);
    }

    //绘制背景及得分数和消行数
    public void paint(Graphics g){
        g.drawImage(Tetris.gameOver, 0, -100, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40*2));
        g.setColor(new Color(255,185,15));
        g.drawString("SCORES:" + Tetris.getTotalScore(), 148*2, 195*2);
        g.drawString("LINES:" + Tetris.getTotalLine(), 150*2, 265*2);
        super.paintChildren(g);
    }

    //构造setImage方法 以便在mainFrame调用
    public void setImage(JLabel b,ImageIcon i){
        i.setImage(i.getImage().getScaledInstance(380,140,1));
        b.setIcon(i);
    }
}

