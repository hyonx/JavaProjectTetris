import javax.swing.*;

public class DifficultyPanel extends JPanel {
    JButton easy;
    JButton middle;
    JButton hard;

    public DifficultyPanel(){
        this.setLayout(null);
        easy=new JButton("EASY");
        middle=new JButton("MIDDLE");
        hard=new JButton("HARD");
        easy.setBounds(150,200,200,80);
        middle.setBounds(150,300,200,80);
        hard.setBounds(150,400,200,80);
        this.add(easy);
        this.add(middle);
        this.add(hard);
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }
}
