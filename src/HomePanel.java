import javax.swing.*;

import javafx.scene.image.Image;

import java.awt.*;
import java.io.FileNotFoundException;
public class HomePanel extends JPanel {
    JLabel  startLabel;
    JLabel  loadLabel;
    JLabel  ruleLabel;
    public static ImageIcon startimage;
    public static ImageIcon startimageEntered;
    public static ImageIcon loadLabelimage;
    public static ImageIcon loadLabelimageEntered;
    public static ImageIcon ruleimage;
    public static ImageIcon ruleimageEntered;
    //读入homepanel上所有的图片。
    static{
        try{
            startimage = new ImageIcon("src/startimage.png");
            startimageEntered = new ImageIcon("src/startimageEntered.png");
            loadLabelimage = new ImageIcon("src/startimage.png");
            loadLabelimageEntered = new ImageIcon("src/startimageEntered.png");
            ruleimage = new ImageIcon("src/startimage.png");
            ruleimageEntered = new ImageIcon("src/startimageEntered.png");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    //HomePanel的构造器。
    public HomePanel() {
        this.setLayout(null);

        startLabel = new JLabel();
        loadLabel = new JLabel();
        ruleLabel = new JLabel();
       
        startLabel.setBounds(163*2,228*2,200*2,80*2);
        loadLabel.setBounds(163*2,330*2,200*2,80*2);
        ruleLabel.setBounds(163*2,432*2,200*2,80*2);

        
       
        startimage.setImage(startimage.getImage().getScaledInstance(380,140,1));
        startLabel.setIcon(startimage);
        loadLabelimage.setImage( loadLabelimage.getImage().getScaledInstance(380,140,1));
        loadLabel.setIcon( loadLabelimage);
        ruleimage.setImage(ruleimage.getImage().getScaledInstance(380,140,1));
        ruleLabel.setIcon(ruleimage);
        
        this.add(startLabel);
        this.add(loadLabel);
        this.add(ruleLabel);
      

        startLabel.setVisible(true);
        loadLabel.setVisible(true);
        ruleLabel.setVisible(true);
        
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
