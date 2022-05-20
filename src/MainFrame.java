import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class MainFrame {
    public static void main(String[] args) throws FileNotFoundException {
        //创建游戏的主窗口
        JFrame frame = new JFrame("玩玩俄罗斯方块");
         //创建主页面panel
        HomePanel homePanel = new HomePanel();

        //创建规则panel


        //难度选择时的panel
        DifficultyPanel difficultyPanel=new DifficultyPanel();

        //创建游戏panel
        Tetris gamePanel = new Tetris(); 

        //创建暂停时的panel
        PausePanel pausePanel = new PausePanel();

        //创建gameover时的panel
        GameOverPanel gameOverPanel = new GameOverPanel();
        //创建一个用于播放结束视频的panel
        VideoPanel videoPanel=new VideoPanel();
       
        

        //创建用于存放所有panel的mainpanel，方便用卡片布局器进行界面切换
        JPanel mainPanel = new JPanel();

       //创建一个卡片布局器的对象
        CardLayout cardLayout = new CardLayout();

        //将主面板的布局器设置为卡片布局
        mainPanel.setLayout(cardLayout);

        //该卡片布局器中存放了游戏中所有的panel
        mainPanel.add(homePanel);
        mainPanel.add(difficultyPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(pausePanel);
        mainPanel.add(gameOverPanel);
        mainPanel.add(videoPanel);

        //将窗口的主容器设置为mainpanel,以及设置大小及状态。
        frame.setContentPane(mainPanel);
        frame.setSize(535*2, 585*2);
       // frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //while中的true可换成参数来控制音乐的停止播放
        new Thread(()->{while(true) {Tetris.playMusic();}
        }).start();

        homePanel.start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                Tetris.flag=false;
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
                new Thread(gamePanel).start();
            }
        });
        difficultyPanel.easy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(800);
                new Thread(gamePanel).start();
            }
        });

        difficultyPanel.middle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(400);
                new Thread(gamePanel).start();

            }
        });

        difficultyPanel.hard.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                gamePanel.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(250);
                new Thread(gamePanel).start();
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
            }
        });

        gameOverPanel.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
            }
        });

        pausePanel.homeAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                gamePanel.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
            }
        });

        //在pause界面重新开始一个新的游戏。
        pausePanel.startAgain.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.initiatLoad=true;
                Tetris.exit= false;
                Tetris.isExit=true;
                Tetris.flag=true;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);
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
                Tetris.flag=false;
                gamePanel.setGameState(Tetris.PLAYING);
                cardLayout.previous(mainPanel);
                new Thread(gamePanel).start();
            }
        });


        Tetris.pause.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tetris.exit= false;
                Tetris.flag=true;
                gamePanel.setGameState(Tetris.PAUSE);
                cardLayout.next(mainPanel);
              
            }
        });

        VideoPanel.OK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(mainPanel);

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


            }
        });

        gameOverPanel.home.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
              
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
            Tetris.flag=true;

            cardLayout.last(mainPanel);
            videoPanel.showVideo();
            videoPanel.player.setAutoPlay(true);
            gamePanel.setGameState(Tetris.PLAYING);

        }
    }
    }
