import javax.swing.*;
import java.awt.*;

public class GameOverPanel extends JPanel{
    JLabel restart;
    JLabel home;
    public static ImageIcon restartImage;
    public static ImageIcon restartImageEntered;
    public static ImageIcon homeImage;
    public static ImageIcon homeImageEntered;
    static{
        try{
            restartImage = new ImageIcon("src/startAgainImage.png");
            restartImageEntered = new ImageIcon("src/startAgainImageEntered.png");
            homeImage = new ImageIcon("src/homeAgainImage.png");
            homeImageEntered = new ImageIcon("src/homeAgainImageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public GameOverPanel(){
        this.setLayout(null);

        restart=new JLabel();
        home=new JLabel();

        restart.setBounds(163*2,310*2,200*2,70*2);
        home.setBounds(163*2,410*2,200*2,70*2);

        restartImage.setImage(restartImage.getImage().getScaledInstance(380,140,1));
        restart.setIcon(restartImage);
        homeImage.setImage(homeImage.getImage().getScaledInstance(380,140,1));
        home.setIcon(homeImage);

        
        this.add(restart);
        this.add(home);

        restart.setVisible(true);
        home.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.gameOver, 0, -150, null);
        g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40*2));
        g.setColor(new Color(255,185,15));
        g.drawString("SCORES:" + Tetris.getTotalScore(), 148*2, 180*2);
        g.drawString("LINES:" + Tetris.getTotalLine(), 150*2, 250*2);
        super.paintChildren(g);
    }
    public void setImage(JLabel b,ImageIcon i){
        i.setImage(i.getImage().getScaledInstance(380,140,1));
        b.setIcon(i);
    }
}

