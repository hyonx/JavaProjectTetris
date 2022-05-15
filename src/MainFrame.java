import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainFrame {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("玩玩俄罗斯方块");

        Tetris gamePanel = new Tetris();
        HomePanel homePanel = new HomePanel();
        GameOverPanel gameOverPanel = new GameOverPanel();
        PausePanel pausePanel = new PausePanel();

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

        Thread a=new Thread(gamePanel);
        Thread b=new Thread(gamePanel);
        Thread c=new Thread(gamePanel);
        Thread d=new Thread(gamePanel);
        Thread e=new Thread(gamePanel);
        Thread f=new Thread(gamePanel);


        homePanel.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   Tetris.exit=true;
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);

                new Thread(gamePanel).start();
             //   a.start();

            }
        });
        File record=new File("records.txt");

        homePanel.load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Tetris.isExit = true;

                java.util.Scanner input = null;
                try {
                    input = new java.util.Scanner(record);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                Tetris.initiatLoad=false;
                gamePanel.setGameState(0);

                //    String read=input.next();
                //   String[] Read=read.split("");
                //  int line=Integer.parseInt(Read[0]);
                //  int score=Integer.parseInt(Read[1]);
                Tetris.setTotalLine(input.nextInt());
                 Tetris.setTotalScore(input.nextInt());

                cardLayout.next(mainPanel);

                new Thread(gamePanel).start();
                //   a.start();

            }
        });


        gameOverPanel.restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.isExit=true;
                Tetris.initiatLoad=true;
                gamePanel.setGameState(0);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);

                //Tetris.exit= true;
               //不能用
                // Tetris.startInitiation();
              //  b.start();
                new Thread(gamePanel).start();

            }
        });

        gameOverPanel.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(3);
                cardLayout.first(mainPanel);
                //也许不需要
              //  new Thread(gamePanel).start();
              //  a.start();
            }
        });

        pausePanel.homeAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                gamePanel.setGameState(3);
                cardLayout.first(mainPanel);
              // new Thread(gamePanel).start();
               // a.start();
            }
        });

        pausePanel.startAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.exit= false;
                Tetris.isExit=true;
                gamePanel.setGameState(0);
                cardLayout.previous(mainPanel);
               // Tetris.startInitiation();
                //a.start();
                new Thread(gamePanel).start();
            }
        });


        pausePanel.save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PrintWriter printWriter= null;

                try {
                    printWriter = new PrintWriter(record);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }

                //  System.out.print(gamePanel.getTotalScore());
                //   String line= String.valueOf(Tetris.getTotalLine());
                // String score=String.valueOf(Tetris.getTotalScore());

                //    printWriter.println(0+" "+line+" "+score);
                printWriter.println(Tetris.getTotalLine()+" "+Tetris.getTotalScore());
                //    Tetris.record();
                printWriter.close();
            }
        });

        pausePanel.Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                Tetris.isExit=false;
                gamePanel.setGameState(0);
                cardLayout.previous(mainPanel);
                // Tetris.startInitiation();
                //a.start();
                new Thread(gamePanel).start();
            }
        });


        Tetris.pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                gamePanel.setGameState(1);
                cardLayout.next(mainPanel);
               // a.start();
              //  new Thread(gamePanel).start();
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
          //  System.out.print("qqqqq");
            Tetris.exit= false;

            cardLayout.last(mainPanel);
            //不能删
            gamePanel.setGameState(0);

        }
    }
    }
