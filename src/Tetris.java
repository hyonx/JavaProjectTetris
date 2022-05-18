import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tetris extends JPanel implements Runnable {
    //This is the main class, which contains the main method, and other major methods.

    @Override
    public void run() {
        this.wall = new Cell[20][10];
        //让wall中没有块的地方赋一个不为null的初值，以便之后读入load时方便调用对象赋图片。
        for(int i=0;i<20;i++){
            for(int j=0;j<10;j++){
                wall[i][j] = new Cell(-1,-1);
            }
        }
        if (isExit) {
            if (initiatLoad) {
            j = 1;
            this.setGameState(PLAYING);

            this.currentOne = Tetromino.randomOne();
            this.nextOne = Tetromino.randomOne();
                totalScore = 0;
                totalLine = 0;
            }
            else{
                this.setGameState(PLAYING);
                try {

                    File file = new File("records.txt");
                    Scanner input = new Scanner(file);
                    setSpeed(input.nextInt());
                    setTotalLine(input.nextInt());
                    setTotalScore(input.nextInt());
                    input.nextLine();
                    //读入records里储存的每个cell里面的图片，如果是null表示该处没有方块。
                    for(int i=0;i<20;i++){
                        for(int j=0;j<10;j++){
                            String s = input.nextLine();
                            if (!s.equals("null")) {
                                wall[i][j].setImageByLocation(s);
                                wall[i][j].setColRow(j,i);
                            }
                        }
                    }
                    //读入currentOne和nextOne的行号列号和图片地址。
                    String typeOfCurrentOne = input.nextLine();
                    currentOne = Tetromino.generateByName(typeOfCurrentOne);
                    for (Cell c : this.currentOne.cells) {
                        int col = input.nextInt();
                        int row = input.nextInt();
                        c.setColRow(col, row);
                        input.nextLine();
                        String s = input.nextLine();
                        c.setImageByLocation(s);
                    }
                    String typeOfNextOne = input.nextLine();
                    nextOne = Tetromino.generateByName(typeOfNextOne);
                    for (Cell c : this.nextOne.cells) {
                        int col = input.nextInt();
                        int row = input.nextInt();
                        input.nextLine();
                        c.setColRow(col, row);
                        String s = input.nextLine();
                        c.setImageByLocation(s);
                    }
                    //input.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }
        if (exit) {//如果exit为真，就重新创建一个gamePanel上的键盘监听器。在同一局游戏中，
                   //最开始将exit赋为真，点击pause按钮后就将exit保持为假。
                   //一局游戏中除非在homepanel或者pausepanel重新开始否则只需要调用该方法一次。
                   //如果没有退出游戏而只是在homepanelLoad的话，则不需要调用该方法。
            this.startInitiation();
        }
        this.requestFocus();
        if(exit==false){
            Tetris.setGame_state(PLAYING);
        }
        try {
            this.Start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Tetromino currentOne = Tetromino.randomOne();
    public Tetromino nextOne = Tetromino.randomOne();
    Cell[][] wall;
    // Wall is used to memorize every cell except those who belongs to currentOne and nextOne.
    final int CELL_SIZE = 26;

    /////////////////////////////////////////////////
    //
    static boolean exit = true;
    static boolean  isExit = true;
    //如果在home界面重新开始，则initiatload=true，如果读取存档，则initiatload=false。
    static boolean initiatLoad = true;

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
        g.translate(-15, -15);
        super.paintChildren(g);
    }

    public void paintWall(Graphics a) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                if (wall[i][j].getCol()==-1&&wall[i][j].getRow()==-1) {
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

    public static int getTotalScore() {
        return totalScore;
    }

    public static void setTotalLine(int line) {
        totalLine = line;
    }

    public static void setTotalScore(int score) {
        totalScore = score;
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
            if (wall[row][col].getCol()!= -1&&wall[row][col].getRow()!=-1)
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
            } else if (wall[row + 1][col].getCol()!=-1&&wall[row + 1][col].getRow()!=-1) {//判断下方是否有方块
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



    public boolean isGameOver() {
        Cell[] cells = nextOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row + 1][col].getCol()!=-1&&wall[row + 1][col].getRow()!=-1) {
                return true;
            }
        }
        return false;
    }

    public boolean isFullLine(int row) {
        //取出当前行的所有列
        Cell[] line = wall[row];
        for (Cell r : line) {
            if (r.getCol()==-1&&r.getRow()==-1) {
                return false;
            }
        }
        return true;
    }

    public void destroyLine() {
        //统计销毁行的行数用于计分
        int lines = 0;
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            //取出每个元素所在的行号
            int row = c.getRow();
            while (row < 20) {
                //从当前所在行向下循环，依次判断每一行是否填满。
                if (isFullLine(row)) {
                    lines++;
                    //如果被填满，则将当前行重新赋初值。
                    wall[row] = new Cell[10];
                    for(int j=0;j<10;j++){
                        wall[row][j] = new Cell(-1,-1);
                    }
                    //并且将该行上面所有的行拷贝到对应行的下一行。
                    for (int i = row; i > 0; i--) {
                        System.arraycopy(wall[i - 1], 0, wall[i], 0, 10);
                    }
                    //然后补上缺失的第零行。
                    wall[0] = new Cell[10];
                    for(int j=0;j<10;j++){
                        wall[0][j] = new Cell(-1,-1);
                    }
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

    static JButton pause;

    public Tetris() throws FileNotFoundException {
        this.setLayout(null);
        pause = new JButton("PAUSE");
        this.add(pause);
        pause.setBounds(280,313,250,70);
        this.setVisible(true);
    }

    //速度池。
    private static int Speed = 400;
    private static int j = 1;
    int[] difficulty = {200, 400, 800};

    public static void setSpeed(int s){
        Speed=s;
    }
    public static int getSpeed(){
        return Speed;
    }

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
                        else {
                            j = 0;
                        }
                    }
                    break;

                    case KeyEvent.VK_E: {
                        j++;
                        if (j < 3)
                            Speed = difficulty[j];

                        else {
                            j = 2;
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
    public void Start() throws FileNotFoundException {
        while (true) {
            if (game_state == PAUSE)
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
            repaint();
        }
    }

    public boolean outOfLBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (col < 0)
                return true;
        }
        return false;
    }

    public boolean outOfRBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (col > 9)
                return true;
        }
        return false;
    }

    public boolean outOfUPBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (row <0)
                return true;
        }
        return false;
    }

    public boolean outOfBtmBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            int row = c.getRow();
            if (row > 19)
                return true;
        }
        return false;
    }

                public int showGameState () {
                    return game_state;
                }

                public void setGameState ( int a){
                    game_state = a;
                }


                public void RotateClockwise() {
                    currentOne.rotateClockwise();
                    currentOne.rotateNumber++;
                    if (outOfUPBounds()|| outOfBtmBounds()) {
                        currentOne.rotateCounter();
                        currentOne.rotateNumber--;
                        return;
                    }
                    int leftStep=0;
                    int rightStep=0;

                    if (outOfLBounds() ) {
                        currentOne.moveRight();
                        rightStep++;
                    }
                    if (outOfLBounds() ) {
                        currentOne.moveRight();
                        rightStep++;
                    }

                    if (outOfRBounds() )
                    {
                        currentOne.moveLeft();
                        leftStep++;
                    }
                    if (outOfRBounds() )
                    {
                        currentOne.moveLeft();
                        leftStep++;
                    }
                    if (coincide() ) {
                        int i;

                        for(i=0;i<leftStep;i++) {
                            currentOne.moveRight();
                        }
                        for(i=0;i<rightStep;i++) {
                            currentOne.moveLeft();
                        }

                        currentOne.rotateCounter();
                        currentOne.rotateNumber--;

                    }

                }


                public void RotateCounter() {
                    currentOne.rotateCounter();
                    currentOne.rotateNumber--;
                    if (outOfUPBounds()|| outOfBtmBounds()) {
                        currentOne.rotateClockwise();
                        currentOne.rotateNumber++;
                        return;
                    }
                    int leftStep=0;
                    int rightStep=0;

                    if (outOfLBounds() ) {
                        currentOne.moveRight();
                        rightStep++;
                    }
                    if (outOfLBounds() ) {
                        currentOne.moveRight();
                        rightStep++;
                    }

                    if (outOfRBounds() )
                    {
                        currentOne.moveLeft();
                        leftStep++;
                    }
                    if (outOfRBounds() )
                    {
                        currentOne.moveLeft();
                        leftStep++;
                    }
                    if (coincide() ) {
                        int i;

                        for(i=0;i<leftStep;i++) {
                            currentOne.moveRight();
                        }
                        for(i=0;i<rightStep;i++) {
                            currentOne.moveLeft();
                        }

                        currentOne.rotateClockwise();
                        currentOne.rotateNumber++;

                    }
                }
            }
