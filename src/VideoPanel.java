import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javax.swing.*;
import java.io.File;
//失败动画面板
public class VideoPanel extends JFXPanel {
    //创建mediaPlayer和label
    public MediaPlayer player;
    static JLabel OK;

    //创建两个ImageIcon
    public  static ImageIcon OKImageEntered;

    //读入ImageIcon值
    static{
        try{
            OKImageEntered = new ImageIcon(VideoPanel.class.getResource("OKImageEntered.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //VideoPanel构造器
    public VideoPanel() {
        this.setLayout(null);

        //初始化label
        OK = new JLabel();

        //设定label大小 位置
        OK.setBounds(163*2, 370*2, 200*2, 70*2);

        //调整ImageIcon大小
        OKImageEntered.setImage(OKImageEntered.getImage().getScaledInstance(380,140,1));

        //将ImageIcon加入label中
        OK.setIcon(OKImageEntered);

        //面板中添加label
        this.add(OK);
        OK.setVisible(true);
    }
    //构造setImage方法 以便在mainFrame调用
    public void setImage(JLabel label,ImageIcon icon){
        icon.setImage(icon.getImage().getScaledInstance(380,140,1));
        label.setIcon(icon);
    }
    //创建showVideo方法 以便在mainFrame里面调用 从而展示结束视频
    public void showVideo () {
        //File videoSource = new File("src/overvideo.mp4");
        Media m = new Media(VideoPanel.class.getResource("overvideo.mp4").toString());
        player = new MediaPlayer(m);
        MediaView viewer = new MediaView(player);
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        root.getChildren().add(viewer);
        this.setScene(scene);
    }
    
}
