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
        easy.setBounds(150*2,200*2,200*2,80*2);
        middle.setBounds(150*2,300*2,200*2,80*2);
        hard.setBounds(150*2,400*2,200*2,80*2);

        

        this.add(easy);
        this.add(middle);
        this.add(hard);
        

        
        easy.setVisible(true);
        middle.setVisible(true);
        hard.setVisible(true);
    }
}
