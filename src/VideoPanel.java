import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Screen;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VideoPanel extends JFXPanel {

    public MediaPlayer player;
    static JLabel OK;
    public  static ImageIcon OKImage;
    public  static ImageIcon OKImageEntered;
    static{
        try{
            OKImage = new ImageIcon("src/OKImage.png");
            OKImageEntered = new ImageIcon("src/OKImageEntered");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    


    public VideoPanel() {
        this.setLayout(null);

        OK = new JLabel();

        OKImage.setImage(OKImage.getImage().getScaledInstance(380,140,1));
        OK.setIcon(OKImage);


        OK.setBounds(198*2, 380*2, 120*2, 50*2);

        this.add(OK);

        OK.setVisible(true);
    }

    public void showVideo () {
        File video_source = new File("src/overvideo.mp4");
        Media m = new Media(video_source.toURI().toString());
        player = new MediaPlayer(m);
        MediaView viewer = new MediaView(player);
        StackPane root = new StackPane();
        Scene scene = new Scene(root);
        root.getChildren().add(viewer);
        this.setScene(scene);

    }

    public void setImage(JLabel b,ImageIcon i){
        i.setImage(i.getImage().getScaledInstance(380,140,1));
        b.setIcon(i);
    }
}
