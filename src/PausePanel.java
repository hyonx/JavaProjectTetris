import javax.swing.*;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
public class PausePanel extends JPanel implements Runnable {
    JButton startAgain;
    JButton homeAgain;
    JButton save;
    JButton Continue;
    public static File record;
    public void run(){
        record = new File("records.txt");
                try{
                    PrintWriter printWriter = new PrintWriter(record);
                }catch(Exception e){
                    e.printStackTrace();
                }
                //先将wall中的每个cell的imagelocation保存到文件中。空白的地方返回值是“null”.
                // for(Cell[] c:gamePanel.wall){
                //     for(Cell d:c){
                //         printWriter.println(d.getImageLocation());
                //     }
                // }
                // //再保存currentOne的行列号和图片地址，以及nextOne的行列号和图片地址。
                // //每个cell的行列号保存完之后紧接着是图片地址。先列后行。
                // for(Cell c:gamePanel.currentOne.cells){
                //     printWriter.println(c.getCol());
                //     printWriter.println(c.getRow());
                //     printWriter.println(c.getImageLocation());
                // }
                // for(Cell c:gamePanel.nextOne.cells){
                //     printWriter.println(c.getCol());
                //     printWriter.println(c.getRow());
                //     printWriter.println(c.getImageLocation());
                // }
    }
    public PausePanel(){
        this.setLayout(null);
        startAgain=new JButton("RESTART");
        homeAgain=new JButton("HOME");
        save=new JButton("SAVE");
        Continue=new JButton("CONTINUE");

        startAgain.setBounds(150,100,200,80);
        Continue.setBounds(150,200,200,80);
        save.setBounds(150,300,200,80);
        homeAgain.setBounds(150,400,200,80);

        this.add(startAgain);
        this.add(Continue);
        this.add(save);
        this.add(homeAgain);

        save.setVisible(true);
        Continue.setVisible(true);
        startAgain.setVisible(true);
        homeAgain.setVisible(true);
    }
}
