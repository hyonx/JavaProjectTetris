import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("玩玩俄罗斯方块");

        Tetris gamePanel = new Tetris();
        HomePanel homePanel = new HomePanel();
        GameOverPanel gameOverPanel = new GameOverPanel();
        PausePanel pausePanel=new PausePanel();

        //设置一个窗口的总面板
        JPanel mainPanel = new JPanel();

        //创建一个卡片布局器的对象
        CardLayout cardLayout = new CardLayout();


        //将主面板的布局器设置为卡片布局器
        mainPanel.setLayout(cardLayout);

        //该卡片布局器中只有home面板和游戏面板
        mainPanel.add(homePanel);
      //  mainPanel.add(gameOverPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(pausePanel);
        mainPanel.add(gameOverPanel);
        //mainPanel.add(pausePanel);

        frame.setContentPane(mainPanel);

        frame.setSize(535, 595);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //when game_state is "HOME", we need to display the homePanel;
        //when game_state is "PLAYING", we need to display the gamePanel and start the game;


        homePanel.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);
                new Thread(gamePanel).start();
            }
        });

        gameOverPanel.restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(0);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);
                new Thread(gamePanel).start();
            }
        });

        gameOverPanel.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(3);
                cardLayout.first(mainPanel);
                new Thread(gamePanel).start();
            }
        });

        pausePanel.homeAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(3);
                cardLayout.first(mainPanel);
                new Thread(gamePanel).start();
            }
        });

        pausePanel.startAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(0);
                cardLayout.previous(mainPanel);
                new Thread(gamePanel).start();
            }
        });

        Tetris.pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(1);
                cardLayout.next(mainPanel);
            }
        });


        for (double i = 0; i < 1000000000000000.0; i++) {
            while (true) {
                System.out.print("");
                System.out.printf("\r");
                if (gamePanel.showGameState() == 2) {
                    break;
                }
            }
            cardLayout.last(mainPanel);
            gamePanel.setGameState(0);
        }
    }
}