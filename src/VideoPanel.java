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
    static JButton OK;



    public VideoPanel() {
        this.setLayout(null);

        OK = new JButton("OK");

        OK.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22*2));

        OK.setBounds(198*2, 380*2, 120*2, 50*2);

        OK.setVisible(true);

        this.add(OK);
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


}
