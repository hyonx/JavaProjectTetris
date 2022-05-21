import javax.swing.*;
import java.awt.*;

public class DifficultyPanel extends JPanel {
    // JButton easy;
    // JButton middle;
    // JButton hard;
    JLabel easy;
    JLabel middle;
    JLabel hard;
    public static ImageIcon easyImage;
    public static ImageIcon easyImageEntered;
    public static ImageIcon middleImage;
    public static ImageIcon middleImageEntered;
    public static ImageIcon hardImage;
    public static ImageIcon hardImageEntered;

    static{
        try{
            easyImage = new ImageIcon("src/easyImage.png");
            easyImageEntered = new ImageIcon("src/easyImageEntered.png");
            middleImage = new ImageIcon("src/middleImage.png");
            middleImageEntered = new ImageIcon("src/middleImageEntered.png");
            hardImage = new ImageIcon("src/hardImage.png");
            hardImageEntered = new ImageIcon("src/hardImageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public DifficultyPanel(){
        this.setLayout(null);
        // easy=new JButton("Easy");
        // middle=new JButton("Middle");
        // hard=new JButton("Hard");
        easy = new JLabel();
        middle = new JLabel();
        hard = new JLabel();


        easy.setBounds(163*2,200*2,200*2,70*2);
        middle.setBounds(163*2,300*2,200*2,70*2);
        hard.setBounds(163*2,400*2,200*2,70*2);

        easyImage.setImage(easyImage.getImage().getScaledInstance(380,140,1));
        easy.setIcon(easyImage);
        middleImage.setImage(middleImage.getImage().getScaledInstance(380,140,1));
        middle.setIcon(middleImage);
        hardImage.setImage(hardImage.getImage().getScaledInstance(380,140,1));
        hard.setIcon(hardImage);
        

        this.add(easy);
        this.add(middle);
        this.add(hard);
        

        
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }
    public void paint(Graphics g){
        g.drawImage(Tetris.tetris, -55*2, -10*2, null);
        super.paintChildren(g);
    }
    public void setImage(JLabel b,ImageIcon i){
        i.setImage(i.getImage().getScaledInstance(380,140,1));
        b.setIcon(i);
    }
}

