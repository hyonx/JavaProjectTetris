import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tetris extends JPanel implements Runnable {
    //This is the main class, which contains the main method, and other major methods.

    @Override
    public void run() {
        if(isExit){
            Speed = 400;
            j = 1;
            this.setGameState(0);
            this.wall = new Cell[20][10];
            this.currentOne = Tetromino.randomOne();
            this.nextOne = Tetromino.randomOne();
            totalScore = 0;
            totalLine = 0;
        }
        //this.startInitiation();
        if(exit){
            this.startInitiation();
        }
        this.requestFocus();
        this.Start();
    }

    Tetromino currentOne = Tetromino.randomOne();
    Tetromino nextOne = Tetromino.randomOne();
    Cell[][] wall = new Cell[20][10];
    // Wall is used to memorize every cell except those who belongs to currentOne and nextOne.
    final int CELL_SIZE = 26;

 /////////////////////////////////////////////////
    static boolean exit=true;
    static boolean isExit=true;


    // The length of every cell in wall and blocks.
    public static BufferedImage blue;
    public static BufferedImage orange;
    public static BufferedImage green;
    public static BufferedImage purple;
    public static BufferedImage red;
    public static BufferedImage skyblue;
    public static BufferedImage yellow;
    public static BufferedImage background;
    public static BufferedImage gameOver;

    static {
        try {
            blue = ImageIO.read(Tetris.class.getResource("blue.png"));
            green = ImageIO.read(Tetris.class.getResource("green.png"));
            orange = ImageIO.read(Tetris.class.getResource("orange.png"));
            purple = ImageIO.read(Tetris.class.getResource("purple.png"));
            red = ImageIO.read(Tetris.class.getResource("red.png"));
            skyblue = ImageIO.read(Tetris.class.getResource("skyblue.png"));
            yellow = ImageIO.read(Tetris.class.getResource("yellow.png"));
            background = ImageIO.read(Tetris.class.getResource("background.png"));
            gameOver = ImageIO.read(Tetris.class.getResource("a.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, null);
        g.translate(15, 15);
        paintWall(g);
        paintCurrentOne(g);
        paintNextOne(g);
        paintScore(g);
        paintState(g);
    }

    public void paintWall(Graphics a) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                if (wall[i][j] == null) {
                    a.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    a.drawImage(wall[i][j].getImage(), x, y, null);
                }
            }
        }
    }

    public void paintCurrentOne(Graphics a) {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int x = c.getCol() * CELL_SIZE;
            int y = c.getRow() * CELL_SIZE;
            a.drawImage(c.getImage(), x, y, null);
        }
    }

    public void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell c : cells) {
            int x = c.getCol() * CELL_SIZE + 260;
            int y = c.getRow() * CELL_SIZE + 26;
            g.drawImage(c.getImage(), x, y, null);
        }
    }

    int[] scores_pool = {0, 1, 2, 5, 10};
    private static int totalScore = 0;
    private static int totalLine = 0;

    public void paintScore(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
        g.drawString("SCORES:" + totalScore, 285, 160);
        g.drawString("LINES:" + totalLine, 285, 215);
    }

    public static int getTotalLine() {
        return totalLine;
    }
    public static int getTotalScore(){
        return totalScore;
    }

    //////////////////////////////////////////////////////////////////

    /*定义三个常量：充当游戏的状态*/
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    public static final int RESTART = 5;
    public static final int HOME = 3;
    public static final int QUIT = 4;
    /*定义一个属性，存储游戏的当前状态*/
    private static int game_state = HOME;
    private int whether_restart = PLAYING;


    public static void setGame_state(int x) {
        game_state = x;
    }

    public static int getGame_state() {

        return game_state;
    }

    String[] show_state = {"P[pause]", "C[continue]", "S[replay]"};


    public void paintState(Graphics g) {
      /*  if (game_state == GAMEOVER) {
            g.drawImage(gameOver, -15, -15, null);

            //  addButton();
        }  */
        if (game_state == PLAYING) {


            g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));
            g.drawString("PLAYING", 285, 275);
        } else if (game_state == PAUSE) {
            g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30));

            g.drawString("PAUSE", 285, 275);

        }
    }


    private boolean coincide() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row][col] != null)
                return true;
        }
        return false;
    }

    public boolean outOfBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (col < 0 || col > 9 || row > 19 || row < 0)
                return true;
        }
        return false;
    }

    public void moveRightAction() {
        currentOne.moveRight();
        if (outOfBounds() || coincide())
            currentOne.moveLeft();
    }

    /*
     * 使用Left键控制四格方块左移
     */
    public void moveLeftAction() {
        currentOne.moveLeft();
        if (outOfBounds() || coincide()) {
            currentOne.moveRight();
        }
    }

    public boolean canDrop() {
        Cell[] cells = currentOne.cells;
        /*
         *
         */
        for (Cell c : cells) {
            /* 获取每个元素的行号,列号
             * 判断：
             * 只要有一个元素的下一行上有方块
             * 或者只要有一个元素到达最后一行
             * 就不能再下落了
             */
            int row = c.getRow();
            int col = c.getCol();
            if (row == 19) {//判断是否到达底部
                return false;
            } else if (wall[row + 1][col] != null) {//判断下方是否有方块
                return false;
            }

        }
        return true;
    }



    //使用Down键控制四格方块的下落


    public void softDropAction() {
        if (canDrop()) {
            currentOne.softDrop();
        } else {
            landToWall();
            destroyLine();
            if (!isGameOver()) {
                currentOne = nextOne;
                nextOne = Tetromino.randomOne();
            } else {
                game_state = GAMEOVER;
            }
        }
    }
    // manually control the drop action of currentOne.
    public void handDropAction() {
        while (true) {
            if (canDrop()) {
                currentOne.softDrop();
            } else break;
        }                     // This while loop aims at quickly letting the currentOne drop down.
        landToWall();
        destroyLine();
        if (!isGameOver()) {
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
        } else {
            game_state = GAMEOVER;
        }
    }


    // manually control the drop action of currentOne.
    public void handDropAction(){
        while(true){
            if(canDrop()){
            currentOne.softDrop();
            }
            else break;
        }                     // This while loop aims at quickly letting the currentOne drop down.
        landToWall();
        destroyLine();
        if (!isGameOver()) {
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
        } else {
            game_state = GAMEOVER;
        }
    }

    public boolean isGameOver() {
        Cell[] cells = nextOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row][col] != null) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullLine(int row) {
        //取出当前行的所有列
        Cell[] line = wall[row];
        for (Cell r : line) {
            if (r == null) {
                return false;
            }
        }
        return true;
    }

    public void destroyLine() {
        //统计销毁行的行数
        int lines = 0;
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            //取出每个元素所在的行号
            int row = c.getRow();
            while (row < 20) {
                if (isFullLine(row)) {
                    lines++;
                    wall[row] = new Cell[10];
                    for (int i = row; i > 0; i--) {
                        System.arraycopy(wall[i - 1], 0, wall[i], 0, 10);
                    }
                    wall[0] = new Cell[10];
                }
                row++;
            }
        }
        //从分数池中取出分数，加入总分数
        totalScore += scores_pool[lines];
        //统计总行数
        totalLine += lines;
    }

    /*
     * 当不能再下落时，需要将四格方块嵌入到墙中
     * 也就是存储到二维数组中相应的位置上
     */
    public void landToWall() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            //获取最终的行号和列号
            int row = c.getRow();
            int col = c.getCol();
            wall[row][col] = c;
        }
    }


    /* public void addButton(){
          super.setLayout(null);
          JButton restart=new JButton("RESTART");
          JButton home=new JButton("HOME");
          restart.setVisible(true);
          home.setVisible(true);
          restart.setBounds(208,260,150,80);
          home.setBounds(208,380,150,80);
          this.add(restart);
          this.add(home);
          restart.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                  Restart(restart, home);
              }
          });
      }
     */
    static JButton pause;

    public Tetris() throws FileNotFoundException {

        pause=new JButton("PAUSE");
        this.add(pause);
        this.setVisible(true);
    }

    public void Restart(JButton b1, JButton b2) {
        game_state = PLAYING;
        whether_restart = RESTART;
        this.remove(b1);
        this.remove(b2);

        this.revalidate();
        repaint();
        Start();
    }


    //速度池。
    private static int Speed = 400;
    private static int j = 1;
    int[] difficulty = {150, 400, 800};


    // the startInitiation method contains the initiation of keyListener and focus.
    public void startInitiation() {
        KeyListener listener1 = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_DOWN:
                        softDropAction();
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeftAction();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightAction();
                        break;
                    case KeyEvent.VK_A:
                        RotateCounter();
                        break;
                    case KeyEvent.VK_D:
                        RotateClockwise();
                        break;
                    case KeyEvent.VK_W:
                        handDropAction();
                        break;
                    case KeyEvent.VK_P:

                        game_state = PAUSE;
                        break;
                    case KeyEvent.VK_S:
                        game_state = PLAYING;
                        break;
                    case KeyEvent.VK_Q: {
                        j--;
                        if (j >= 0)
                            Speed = difficulty[j];
                        else{
                            j=0;
                        }
                    }
                    break;

                    case KeyEvent.VK_E: {
                        j++;
                        if (j < 3)
                            Speed = difficulty[j];

                        else{
                            j=2;
                        }
                    }
                    break;

                    default:
                        break;
                }
                repaint();
            }
        };
        this.addKeyListener(listener1);
        this.requestFocus();
    }

    public void listener(){ KeyListener listener1 = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int code = e.getKeyCode();
            switch (code) {
                case KeyEvent.VK_DOWN:
                    softDropAction();
                    break;
                case KeyEvent.VK_LEFT:
                    moveLeftAction();
                    break;
                case KeyEvent.VK_RIGHT:
                    moveRightAction();
                    break;
                case KeyEvent.VK_A:
                    RotateCounter();
                    break;
                case KeyEvent.VK_D:
                    RotateClockwise();
                    break;
                case KeyEvent.VK_W:
                    handDropAction();
                    break;
                case KeyEvent.VK_P:
                    game_state = PAUSE;
                    break;
                case KeyEvent.VK_S:
                    game_state = PLAYING;
                    break;
                case KeyEvent.VK_Q: {
                    j--;
                    if (j >= 0)
                        Speed = difficulty[j];
                    else{
                        j=0;
                    }
                }
                break;
                case KeyEvent.VK_E: {
                    j++;
                    if (j < 3)
                        Speed = difficulty[j];
                    else{
                        j=2;
                    }
                }
                break;
                default:
                    break;
            }
            repaint();
        }
    };
        this.addKeyListener(listener1);
        this.requestFocus();
    }
        //The start method includes the  main logic of this game.
        public void Start(){
            while (true) {
                if (game_state == PLAYING) {
                    /*
                     * 当程序运行到此，会进入睡眠状态，
                     * 睡眠时间为300毫秒,单位为毫秒
                     * 400毫秒后，会自动执行后续代码
                     */
                    try {
                        Thread.sleep(Speed);

    }
 //   PrintWriter printWriter=new PrintWriter("records.txt");


    //The start method includes the  main logic of this game.
    public void Start() {
        //if (exit) {

       // }
        /*
        else{
            KeyListener listener2 = new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    super.keyPressed(e);

                }

            };
            this.addKeyListener(listener2);
        }

        this.requestFocus();


         */
        while (true) {
            if(game_state==PAUSE)
                break;

            if (game_state == PLAYING) {
                /*
                 * 当程序运行到此，会进入睡眠状态，
                 * 睡眠时间为300毫秒,单位为毫秒
                 * 400毫秒后，会自动执行后续代码
                 */
                try {
                    Thread.sleep(Speed);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (canDrop()) {
                    currentOne.softDrop();
                } else {
                    landToWall();
                    destroyLine();
                    //将下一个下落的四格方块赋值给正在下落的变量
                    if (!isGameOver()) {
                        currentOne = nextOne;
                        nextOne = Tetromino.randomOne();
                    } else {
                        game_state = GAMEOVER;
                        break;
                    }
                }
            }
        }
        //  if(game_state==GAMEOVER)
        //this.setVisible(false);



        public void RotateClockwise () {
            currentOne.rotateClockwise();
            if (outOfBounds() || coincide()) {
                currentOne.rotateCounter();
            }
            if (!outOfBounds() && !coincide()) {
                currentOne.rotateNumber += 1;
            }
        }
        public void RotateCounter () {
            currentOne.rotateCounter();
            if (outOfBounds() || coincide()) {
                currentOne.rotateClockwise();
            }
            if (!outOfBounds() && !coincide()) {
                currentOne.rotateNumber -= 1;
            }
        }
        public static void main (String[]args){
            JFrame frame = new JFrame("玩玩俄罗斯方块");
            Tetris gamePanel = new Tetris();
            HomePanel homePanel = new HomePanel();
            //设置一个窗口的总面板
            JPanel mainPanel = new JPanel();
            //创建一个卡片布局器的对象
            CardLayout cardLayout = new CardLayout();
            //将主面板的布局器设置为卡片布局器
            mainPanel.setLayout(cardLayout);
            //该卡片布局器中只有home面板和游戏面板
            mainPanel.add(homePanel);
            mainPanel.add(gamePanel);
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
                    game_state=PLAYING;
                    cardLayout.last(mainPanel);
                    new Thread(gamePanel).start();
                }
            });
        }
    }

    public int showGameState() {
        return game_state;
    }

    public void setGameState(int a) {
        game_state = a;
    }


    public void RotateClockwise() {
        currentOne.rotateClockwise();
        if (outOfBounds() || coincide()) {
            currentOne.rotateCounter();
        }
        if (!outOfBounds() && !coincide()) {
            currentOne.rotateNumber += 1;
        }
    }

    public void RotateCounter() {
        currentOne.rotateCounter();
        if (outOfBounds() || coincide()) {
            currentOne.rotateClockwise();
        }
        if (!outOfBounds() && !coincide()) {
            currentOne.rotateNumber -= 1;
        }
    }


    //有问题！！！！！！！！！！！！！
    public void Initiation() {
        System.out.println("qqqqqqq");
        this.wall = new Cell[20][10];
        this.currentOne = Tetromino.randomOne();
        this.nextOne = Tetromino.randomOne();
        this.totalScore = 0;
        this.totalLine = 0;
    }


}


