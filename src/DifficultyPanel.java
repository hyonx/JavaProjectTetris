import javax.swing.*;

public class DifficultyPanel extends JPanel {
    JButton easy;
    JButton middle;
    JButton hard;
    JLabel background;
    public static ImageIcon backgroundImage;
    static {
        try {
            backgroundImage = new ImageIcon("src/科技背景.png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public DifficultyPanel(){
        this.setLayout(null);
        easy=new JButton("EASY");
        middle=new JButton("MIDDLE");
        hard=new JButton("HARD");
        easy.setBounds(150,200,200,80);
        middle.setBounds(150,300,200,80);
        hard.setBounds(150,400,200,80);

        background = new JLabel();
        backgroundImage.setImage(backgroundImage.getImage().getScaledInstance(535, 595, 1));
        background.setIcon(backgroundImage);
        background.setBounds(0, 0,535,595);

        this.add(easy);
        this.add(middle);
        this.add(hard);
        this.add(background);

        background.setVisible(true);
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }
}
