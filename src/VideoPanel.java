import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class VideoPanel extends JFXPanel {

    public MediaPlayer player;
    JButton restart;
    JButton home;

    public VideoPanel() {
        this.setLayout(null);
        this.setBounds(100*2,100*2,100*2,100*2);
        restart = new JButton("RESTART");
        restart.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30*2));
        home = new JButton("HOME");
        home.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30*2));
        restart.setBounds(160*2, 300*2, 200*2, 80*2);
        home.setBounds(160*2, 400*2, 200*2, 80*2);
        this.add(restart);
        this.add(home);

        restart.setVisible(true);
        home.setVisible(true);
    }
        public void showVideo () {
            File video_source = new File("src/ok.mp4");

            Media m = new Media(video_source.toURI().toString());

            player = new MediaPlayer(m);


            MediaView viewer = new MediaView(player);

            StackPane root = new StackPane();

            Scene scene = new Scene(root);

// center video position



// resize video based on screen size


// add video to stack pane

            root.getChildren().add(viewer);

            //player.setAutoPlay(true);
            this.setScene(scene);

        }



}