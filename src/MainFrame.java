
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainFrame {
    public static void main(String[] args) throws FileNotFoundException {
        JFrame frame = new JFrame("玩玩俄罗斯方块");

        Tetris gamePanel = new Tetris();
        HomePanel homePanel = new HomePanel();
        GameOverPanel gameOverPanel = new GameOverPanel();
        PausePanel pausePanel = new PausePanel();
        DifficultyPanel difficultyPanel=new DifficultyPanel();

        //设置一个窗口的总面板
        JPanel mainPanel = new JPanel();

       //创建一个卡片布局器的对象
        CardLayout cardLayout = new CardLayout();


        //将主面板的布局器设置为卡片布局器
        mainPanel.setLayout(cardLayout);

        //该卡片布局器中只有home面板和游戏面板
        mainPanel.add(homePanel);
        mainPanel.add(difficultyPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(pausePanel);
        mainPanel.add(gameOverPanel);



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
             //   Tetris.exit=true;
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
            }
        });
        homePanel.load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.isExit = true;
                Tetris.initiatLoad = false;
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
                new Thread(gamePanel).start();
            }
        });
        difficultyPanel.easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   Tetris.exit=true;
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(800);
                new Thread(gamePanel).start();
                //   a.start();
            }
        });

        difficultyPanel.middle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   Tetris.exit=true;
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(400);
                new Thread(gamePanel).start();
                //   a.start();

            }
        });

        difficultyPanel.hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //   Tetris.exit=true;
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(250);
                new Thread(gamePanel).start();
                //   a.start();
            }
        });



        


        gameOverPanel.restart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.isExit=true;
                Tetris.initiatLoad=true;
                gamePanel.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);

                //Tetris.exit= true;
               //不能用
                // Tetris.startInitiation();
              //  b.start();
              //  new Thread(gamePanel).start();

            }
        });

        gameOverPanel.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(Tetris.HOME);
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
                gamePanel.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
              // new Thread(gamePanel).start();
               // a.start();
            }
        });

        //在pause界面重新开始一个新的游戏。
        pausePanel.startAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.exit= false;
                Tetris.isExit=true;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);
                // Tetris.startInitiation();
                //a.start();
               // new Thread(gamePanel).start();
            }
        });

        File record=new File("records.txt");
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
                //注意scanner读入时从读入数字到读入字符串之间要加一个input.nextLine()否则会出错。
                printWriter.println(Tetris.getSpeed()+" "+Tetris.getTotalLine()+" "+Tetris.getTotalScore());
                //先将wall中的每个cell的imagelocation保存到文件中。空白的地方返回值是“null”.
                for(Cell[] c:gamePanel.wall){
                    for(Cell d:c){
                        if(d.getCol()==-1&&d.getRow()==-1){//d的rowcol如果为-1则表示该处没有俄罗斯方块。
                            printWriter.println("null");
                        }
                        if(d.getCol()!=-1&&d.getRow()!=-1){
                            printWriter.println(d.getImageLocation());
                        }
                    }
                }
                //再保存currentOne的方块类型和行列号和图片地址，以及nextOne的方块类型和行列号和图片地址。
                //每个cell的行列号保存完之后紧接着是图片地址。先列后行。
                printWriter.println(gamePanel.currentOne.getTetrominoType());
                for(Cell c:gamePanel.currentOne.cells){
                    printWriter.println(c.getCol());
                    printWriter.println(c.getRow());
                    printWriter.println(c.getImageLocation());
                }
                printWriter.println(gamePanel.nextOne.getTetrominoType());
                for(Cell c:gamePanel.nextOne.cells){
                    printWriter.println(c.getCol());
                    printWriter.println(c.getRow());
                    printWriter.println(c.getImageLocation());
                }
                printWriter.close();
            }
        });

        pausePanel.Continue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                Tetris.isExit=false;
                gamePanel.setGameState(Tetris.PLAYING);
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
                gamePanel.setGameState(Tetris.PAUSE);
                cardLayout.next(mainPanel);
               // a.start();
              //  new Thread(gamePanel).start();
            }
        });


        for (double i = 0; i < 1000000000000000.0; i++) {
            while (true) {
                System.out.print("");
                System.out.printf("\r");
                if (gamePanel.showGameState() == Tetris.GAMEOVER) {
                    break;
                }
            }
            Tetris.exit= false;
            cardLayout.last(mainPanel);
            //不能删
            gamePanel.setGameState(Tetris.PLAYING);

        }
    }
    }
