import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//游戏主框架
public class MainFrame {
    public static void main(String[] args) {
        //创建游戏主框架
        JFrame frame = new JFrame("TETRIS");

        //创建HomePanel对象
        HomePanel homePanel = new HomePanel();
        //创建RulePanel对象
        RulePanel rulePanel=new RulePanel();
        //创建DifficultyPanel对象
        DifficultyPanel difficultyPanel=new DifficultyPanel();
        //创建Tetris对象
        Tetris gamePanel = new Tetris();
        //创建PausePanel对象
        PausePanel pausePanel = new PausePanel();
        //创建GameOverPanel对象
        GameOverPanel gameOverPanel = new GameOverPanel();
        //创建VideoPanel对象
        VideoPanel videoPanel=new VideoPanel();


        //创建用于存放所有panel的mainPanel，方便用卡片布局器进行界面切换
        JPanel mainPanel = new JPanel();

        //创建一个卡片布局器的对象
        CardLayout cardLayout = new CardLayout();

        //将mainPanel的布局器设置为卡片布局
        mainPanel.setLayout(cardLayout);

        //该卡片布局器中存放了游戏中所有的panel
        mainPanel.add(homePanel);
        mainPanel.add(rulePanel);
        mainPanel.add(difficultyPanel);
        mainPanel.add(gamePanel);
        mainPanel.add(pausePanel);
        mainPanel.add(gameOverPanel);
        mainPanel.add(videoPanel);

        //将窗口的主容器设置为mainPanel,以及设置大小及状态。
        frame.setContentPane(mainPanel);
        frame.setSize(535*2, 580*2);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //调用playMusic 播放音乐
        new Thread(()->{while(true) {Tetris.playMusic();}
        }).start();

        //创建储存读档数据的txt文档
        File record=new File("records.txt");


        //开始面板 startLabel鼠标监听
        homePanel.startLabel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                homePanel.setImage(homePanel.startLabel, HomePanel.startImageEntered);
                homePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homePanel.setImage(homePanel.startLabel, HomePanel.startImage);
                homePanel.repaint();
            }

        });


        //开始面板 loadLabel鼠标监听
        homePanel.loadLabel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.isExit = true;
                Tetris.initiatLoad = false;
                Tetris.flag=false;
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
                new Thread(gamePanel).start();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                homePanel.setImage(homePanel.loadLabel, HomePanel.loadLabelImageEntered);
                homePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homePanel.setImage(homePanel.loadLabel, HomePanel.loadLabelImage);
                homePanel.repaint();
            }
        });

        //开始面板 ruleLabel鼠标监听
        homePanel.ruleLabel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                homePanel.setImage(homePanel.ruleLabel, HomePanel.ruleImageEntered);
                homePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                homePanel.setImage(homePanel.ruleLabel, HomePanel.ruleImage);
                homePanel.repaint();
            }

        });

        //规则面板 ok鼠标监听
        RulePanel.ok.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.previous(mainPanel);
            }
        });

        //难度选择面板 easy鼠标监听
        difficultyPanel.easy.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(800);
                new Thread(gamePanel).start();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.easy, DifficultyPanel.easyImageEntered);
                difficultyPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.easy, DifficultyPanel.easyImage);
                difficultyPanel.repaint();
            }

        });

        //难度选择面板 middle鼠标监听
        difficultyPanel.middle.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                Tetris.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(400);
                new Thread(gamePanel).start();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.middle, DifficultyPanel.middleImageEntered);
                difficultyPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.middle, DifficultyPanel.middleImage);
                difficultyPanel.repaint();
            }
        });

        //难度选择面板 hard鼠标监听
        difficultyPanel.hard.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.isExit=true;
                Tetris.flag=false;
                Tetris.setGameState(0);
                cardLayout.next(mainPanel);
                Tetris.setSpeed(250);
                new Thread(gamePanel).start();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.hard, DifficultyPanel.hardImageEntered);
                difficultyPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                difficultyPanel.setImage(difficultyPanel.hard, DifficultyPanel.hardImage);
                difficultyPanel.repaint();
            }
        });

        //游戏面板 pause鼠标监听
        gamePanel.pause.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.exit= false;
                Tetris.flag=false;
                Tetris.setGameState(Tetris.PAUSE);
                cardLayout.next(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                gamePanel.setDirector(true);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gamePanel.setDirector(false);
            }
        });

        //停止面板 Continue鼠标监听
        pausePanel.Continue.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.exit= false;
                Tetris.isExit=false;
                Tetris.flag=false;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.previous(mainPanel);
                new Thread(gamePanel).start();
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                pausePanel.setImage(pausePanel.Continue, PausePanel.ContinueImageEntered);
                pausePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pausePanel.setImage(pausePanel.Continue, PausePanel.ContinueImage);
                pausePanel.repaint();
            }
        });


        //停止面板 startAgain鼠标监听
        pausePanel.startAgain.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.initiatLoad=true;
                Tetris.exit= false;
                Tetris.isExit=true;
                Tetris.flag=true;
                Tetris.setGameState(Tetris.PLAYING);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                pausePanel.setImage(pausePanel.startAgain, PausePanel.startAgainImageEntered);
                pausePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pausePanel.setImage(pausePanel.startAgain, PausePanel.startAgainImage);
                pausePanel.repaint();
            }
        });


        //停止面板 save鼠标监听
        pausePanel.save.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
            @Override
            public void mouseEntered(MouseEvent e) {
                pausePanel.setImage(pausePanel.save, PausePanel.saveImageEntered);
                pausePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pausePanel.setImage(pausePanel.save, PausePanel.saveImage);
                pausePanel.repaint();
            }
        });

        //停止面板 homeAgain鼠标监听
        pausePanel.homeAgain.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.exit= false;
                Tetris.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                pausePanel.setImage(pausePanel.homeAgain, PausePanel.homeAgainImageEntered);
                pausePanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                pausePanel.setImage(pausePanel.homeAgain, PausePanel.homeAgainImage);
                pausePanel.repaint();
            }
        });

        //失败视频面板 OK鼠标监听
        VideoPanel.OK.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                cardLayout.previous(mainPanel);
            }
        });

        //失败面板 restart鼠标监听
        gameOverPanel.restart.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.isExit=true;
                Tetris.initiatLoad=true;
                Tetris.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
                cardLayout.next(mainPanel);
                cardLayout.next(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                gameOverPanel.setImage(gameOverPanel.restart, GameOverPanel.restartImageEntered);
                gameOverPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                difficultyPanel.setImage(gameOverPanel.restart, GameOverPanel.restartImage);
                gameOverPanel.repaint();
            }
        });

        //失败面板 home鼠标监听
        gameOverPanel.home.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.setGameState(Tetris.HOME);
                cardLayout.first(mainPanel);
            }
            @Override
            public void mouseEntered(MouseEvent e) {
            gameOverPanel.setImage(gameOverPanel.home, GameOverPanel.homeImageEntered);
            gameOverPanel.repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                gameOverPanel.setImage(gameOverPanel.home, GameOverPanel.homeImage);
                gameOverPanel.repaint();
            }
        });

        //判断游戏是否失败
        for (double i = 0; i < 1000000000000000.0; i++) {
            while (true) {
                System.out.print("");

                //若游戏状态显示GameOver 则退出循环
                if (gamePanel.showGameState() == Tetris.GAMEOVER) {
                    break;
                }
            }
            Tetris.exit= false;
            Tetris.flag=true;

            cardLayout.last(mainPanel);//切换至最后一个面板 即视频面板
            videoPanel.showVideo();//调用showVideo方法 播放视频
            videoPanel.player.setAutoPlay(true);//让视频自动播放
            Tetris.setGameState(Tetris.PLAYING);//设置游戏状态从gameOver变为Playing
        }
    }
}
