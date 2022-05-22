import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
//游戏面板
public class Tetris extends JPanel implements Runnable {
    //创建label
    JLabel pause;

    //Tetris构造器
    public Tetris(){
        this.setLayout(null);
        pause = new JLabel();
        pause.setBounds(320*2,313*2,130*2,55*2);
        pause.setVisible(true);
        this.add(pause);
    }


    //创建两个Tetromino对象
    public Tetromino currentOne = Tetromino.randomOne();//当前正在下落的方块
    public Tetromino nextOne = Tetromino.randomOne();//下一个将要下落的方块

    //用Cell数组wall显示落地的方块
    Cell[][] wall;

    //每一格cell长度
    final int CELL_SIZE = 26*2;


    //创建所有面板的背景及方块的cell图片
    public static BufferedImage blue;
    public static BufferedImage orange;
    public static BufferedImage green;
    public static BufferedImage purple;
    public static BufferedImage red;
    public static BufferedImage skyblue;
    public static BufferedImage yellow;
    public static BufferedImage background;
    public static BufferedImage gameOver;
    public static BufferedImage tetris;
    public static BufferedImage Pause;
    public static BufferedImage pauseButtonImage1;
    public static BufferedImage pauseButtonImage2;
    public static BufferedImage Rule;

    //为所有BufferedImage赋值
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
            gameOver = ImageIO.read(Tetris.class.getResource("GameOverBackground.png"));
            tetris = ImageIO.read(Tetris.class.getResource("q.png"));
            Pause=ImageIO.read(Tetris.class.getResource("pauseBackground.png"));
            pauseButtonImage1 = ImageIO.read(Tetris.class.getResource("pauseButtonImage1.png"));
            pauseButtonImage2 = ImageIO.read(Tetris.class.getResource("pauseButtonImage2.png"));
            Rule = ImageIO.read(Tetris.class.getResource("rule.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //定义四个变量 代表游戏状态
    public static final int PLAYING = 0;
    public static final int PAUSE = 1;
    public static final int GAMEOVER = 2;
    public static final int HOME = 3;

    //定义一个变量 代表当前游戏状态
    private static int game_state = HOME;

    //创建setGameState方法 以便在mainframe里用于设置游戏状态
    public static void setGameState(int a){
        game_state = a;
    }
    //创建showGameState方法 以便获取当前游戏状态
    public int showGameState () {
        return game_state;
    }


    //重写paint方法
    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);//绘制游戏背景图片
        g.translate(15*2, 15*2);//移动画笔初始坐标
        paintWall(g);//绘制游戏背景
        paintCurrentOne(g);//绘制当前正在下落的方块
        paintNextOne(g);//绘制下一个将要下落的方块
        paintScore(g);//绘制当前得分和前消行数
        paintState(g);//绘制PLAYING
        g.translate(-15*2, -15*2);//移动画笔初始坐标
        paintPauseLabel(g);//绘制pause label
    }

    //绘制游戏背景
    //若wall显示该处cell为空 则绘画黑格子 若不为空 则绘画方块图片
    public void paintWall(Graphics a) {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                int x = j * CELL_SIZE;
                int y = i * CELL_SIZE;
                if (wall[i][j].getCol() == -1 && wall[i][j].getRow() == -1) {
                    a.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                } else {
                    //得到每一个cell的图片并绘制
                    a.drawImage(wall[i][j].getImage(), x, y, null);
                }
            }
        }
    }

    //绘制当前正在下落的方块
    public void paintCurrentOne(Graphics a) {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int x = c.getCol() * CELL_SIZE;
            int y = c.getRow() * CELL_SIZE;
            //得到每一个cell的图片并绘制
            a.drawImage(c.getImage(), x, y, null);
        }
    }

    //绘制下一个将要下落的方块
    public void paintNextOne(Graphics g) {
        Cell[] cells = nextOne.cells;
        for (Cell c : cells) {
            int x = c.getCol() * CELL_SIZE + 260*2;
            int y = c.getRow() * CELL_SIZE + 26*2;
            //得到每一个cell的图片并绘制在右上角处
            g.drawImage(c.getImage(), x, y, null);
        }
    }

    //分数池
    //一次消0，1，2，3，4行  分别得0，1，2，5，10分
    int[] scores_pool = {0, 1, 2, 5, 10};
    //当前得分
    private static int totalScore = 0;
    //当前消行数
    private static int totalLine = 0;

    //绘制当前得分和前消行数
    public void paintScore(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30*2));
        g.drawString("SCORES:" + totalScore, 285*2, 160*2);
        g.drawString("LINES:" + totalLine, 285*2, 215*2);
    }

    //以便在mainFrame里get或者set totalScore和totalLine
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

    //绘制PLAYING
    public void paintState(Graphics g) {
        g.setFont(new Font(Font.SANS_SERIF, Font.ITALIC, 30*2));
        g.drawString("PLAYING", 285*2, 275*2);
    }

    //绘制游戏界面pause label
    //移入pause label，director设为true，移出设为false
    private static boolean director=false;

    public void paintPauseLabel(Graphics g){
        if(director){
            g.drawImage(pauseButtonImage2, 300*2+70,313*2+20, null);
        }
        else{g.drawImage(pauseButtonImage1, 300*2+70,313*2+20, null);
        }
    }

    //创建setDirector方法 以便在mainFrame里控制pause label图片
    public  void setDirector(boolean b){
        director = b;
    }


    //判断currentOne是否与已经落地的方块重合 返回boolean
    private boolean coincide() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row][col].getCol()!= -1 && wall[row][col].getRow()!=-1)
                return true;
        }
        return false;
    }

    //判断currentOne是否出界 返回boolean
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

    //判断currentOne是否左出界 返回boolean
    public boolean outOfLBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            if (col < 0)
                return true;
        }
        return false;
    }

    //判断currentOne是否右出界 返回boolean
    public boolean outOfRBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int col = c.getCol();
            if (col > 9)
                return true;
        }
        return false;
    }

    //判断currentOne是否上出界 返回boolean
    public boolean outOfUPBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            if (row <0)
                return true;
        }
        return false;
    }

    //判断currentOne是否下出界 返回boolean
    public boolean outOfBtmBounds() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            int row = c.getRow();
            if (row > 19)
                return true;
        }
        return false;
    }

    //使方块顺时针旋转
    public void RotateClockwise() {
        currentOne.rotateClockwise();//使currentOne顺时针旋转
        currentOne.rotateNumber++;
        if (outOfUPBounds()|| outOfBtmBounds()) {//若上出界或者下出界
            currentOne.rotateCounter();//使currentOne逆时针旋转
            currentOne.rotateNumber--;
            return;
        }
        //定义两个变量 代表方块旋转后出界的格数
        int leftStep=0;
        int rightStep=0;

        //若方块左出界
        if (outOfLBounds() ) {
            currentOne.moveRight();
            rightStep++;
        }
        if (outOfLBounds() ) {
            currentOne.moveRight();
            rightStep++;
        }

        //若方块右出界
        if (outOfRBounds() ) {
            currentOne.moveLeft();
            leftStep++;
        }
        if (outOfRBounds() ) {
            currentOne.moveLeft();
            leftStep++;
        }

        //若方块与已经落地的方块重合
        //使方块恢复到未旋转的状态
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


    //使方块逆时针旋转
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

        if (outOfRBounds() ) {
            currentOne.moveLeft();
            leftStep++;
        }
        if (outOfRBounds() ) {
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

    //使方块右移一格
    public void moveRightAction() {
        currentOne.moveRight();//方块的每一个cell col++
        if (outOfBounds() || coincide())//若方块右移后出界或者与落地方块重合
            currentOne.moveLeft();//方块的每一个cell col--
    }

    //使方块左移一格
    public void moveLeftAction() {
        currentOne.moveLeft();//方块的每一个cell col--
        if (outOfBounds() || coincide()) //若方块左移后出界或者与落地方块重合
            currentOne.moveRight();//方块的每一个cell col++
    }

    //判断方块能否下落 返回boolean
    public boolean canDrop() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            /* 获取currentOne每一个cell的行号,列号
             * 判断：
             * 只要有一个cell的下一行上有方块
             * 或者只要有一个cell到达最后一行
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

    //若方块不能再下落 则储存入wall中
    public void landToWall() {
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            //获取currentOne最终的行号和列号
            int row = c.getRow();
            int col = c.getCol();
            wall[row][col] = c;//储存入wall中
        }
    }

    //使方块下落一格
    public void softDropAction() {
        if (canDrop()) {//判断方块能否下落
            currentOne.softDrop();//若能 则每一个cell row++
        } else {//若不能下落
            landToWall();//储存到wall里
            destroyLine();//消行
            //判断是否游戏结束
            if (!isGameOver()) {//若游戏没有结束
                currentOne = nextOne;//下一个方块成为当前正在下落的方块
                nextOne = Tetromino.randomOne();//随机生成下一个方块
            } else {//若游戏结束
                game_state = GAMEOVER;//使当前游戏状态变成GAMEOVER
            }
        }
    }

    //使方块下落到底
    public void handDropAction() {
        while (true) {//若能下落 则方块一直下落
            if (canDrop()) {
                currentOne.softDrop();
            } else break;
        }
        //若不能下落
        landToWall();
        destroyLine();
        if (!isGameOver()) {
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
        } else {
            game_state = GAMEOVER;
        }
    }


    //判断游戏是否结束 返回boolean
    public boolean isGameOver() {
        Cell[] cells = nextOne.cells;//获取下一个方块的每一个cell的行号和列号
        for (Cell c : cells) {
            int row = c.getRow();
            int col = c.getCol();
            if (wall[row + 1][col].getCol()!=-1 && wall[row + 1][col].getRow()!=-1) {
                return true;//若下一个方块下面wall储存了方块 则gameOver
            }
        }
        return false;
    }

    //判断一行是否满了 返回boolean
    public boolean isFullLine(int row) {
        //取出当前row的所有cell
        Cell[] line = wall[row];
        for (Cell r : line) {
            if (r.getCol()==-1 && r.getRow()==-1) {//若有一个cell为空 则返回false
                return false;
            }
        }
        return true;
    }

    //若一行满了 则消行
    public void destroyLine() {
        //定义变量lines 统计消行数用于计分
        int lines = 0;
        Cell[] cells = currentOne.cells;
        for (Cell c : cells) {
            //取出每个cell所在的行号
            int row = c.getRow();
            //从当前所在行向下循环，依次判断每一行是否填满。
            while (row < 20) {
                if (isFullLine(row)) {//若该行满了
                    //计数+1
                    lines++;
                    //初始化该行
                    for(int j=0;j<10;j++){
                        wall[row][j] = new Cell(-1,-1);
                    }
                    //并将该行上面所有的行拷贝到对应行的下一行。
                    for (int i = row; i > 0; i--) {
                        System.arraycopy(wall[i - 1], 0, wall[i], 0, 10);
                    }
                    //补上缺失的第0行。
                    for(int j=0;j<10;j++){
                        wall[0][j] = new Cell(-1,-1);
                    }
                }
                row++;
            }
        }
        //从分数池中取出分数，加入总分数
        //line<=4
        totalScore += scores_pool[lines];
        //统计总行数
        totalLine += lines;
    }


    //定义一个变量Speed 代表方块下落速度
    private static int Speed = 400;

    //在mainframe里面设置Speed
    public static void setSpeed(int s){
        Speed=s;
    }
    public static int getSpeed(){
        return Speed;
    }

    //创建键盘监听
    public void startInitiation() {
        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int code = e.getKeyCode();
                switch (code) {
                    case KeyEvent.VK_DOWN:
                        softDropAction();//下降一格
                        break;
                    case KeyEvent.VK_LEFT:
                        moveLeftAction();//左移一格
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRightAction();//右移一格
                        break;
                    case KeyEvent.VK_A:
                        RotateCounter();//方块逆时针旋转
                        break;
                    case KeyEvent.VK_D:
                        RotateClockwise();//方块顺时针旋转
                        break;
                    case KeyEvent.VK_W:
                        handDropAction();//方块立刻落地
                        break;

                    default:
                        break;
                }
                repaint();//重新绘制
            }
        };
        //面板加入键盘监听
        this.addKeyListener(listener);
        this.requestFocus();
    }


    //Start方法为游戏面板能动起来的主要方法
    public void Start() throws FileNotFoundException {
        while (true) {
            //使随着消行数增加 方块下落速度越来越快
            if(Speed==800||Speed==700||Speed==550||Speed==410){
                if(totalLine>5)
                    Speed=700;
                if(totalLine>10)
                    Speed=550;
                if(totalLine>15)
                    Speed=410;
                if(totalLine>20)
                    Speed=300;
            }
            if(Speed==400||Speed==350||Speed==280||Speed==240){
                if(totalLine>5)
                    Speed=350;
                if(totalLine>10)
                    Speed=280;
                if(totalLine>15)
                    Speed=240;
                if(totalLine>20)
                    Speed=200;
            }
            if(Speed==250||Speed==230||Speed==210||Speed==200){
                if(totalLine>5)
                    Speed=230;
                if(totalLine>10)
                    Speed=210;
                if(totalLine>15)
                    Speed=200;
                if(totalLine>20)
                    Speed=180;
            }

            //点击pause label后 游戏状态变为PAUSE 退出循环
            if(game_state==PAUSE)
                break;;

            //如果当前游戏状态为PLAYING
            if (game_state == PLAYING) {
                /*
                 * 当程序运行到此，会进入睡眠状态，
                 * 睡眠时间为Speed毫秒,单位为毫秒
                 * Speed毫秒后，会自动执行后续代码
                 */
                try {
                    Thread.sleep(Speed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (canDrop()) {//若currentOne能下落
                    currentOne.softDrop();
                } else {//若currentOne不能下落
                    landToWall();
                    destroyLine();
                    if (!isGameOver()) {
                        currentOne = nextOne;
                        nextOne = Tetromino.randomOne();
                    } else {
                        game_state = GAMEOVER;//若游戏失败 设置当前游戏状态为GAMEOVER退出循环
                        break;
                    }
                }
            }
            repaint();//重新绘制
        }
    }


    //定义一个变量flag 用于控制播放的音乐
    public  static boolean flag = true;

    //创建playMusic方法 在mainFrame里调用 以便播放音乐
    public static void playMusic() {
        try {
            //创建两个AudioInputStream并导入音乐
            AudioInputStream music = AudioSystem.getAudioInputStream(new File("src/h.wav"));
            AudioInputStream Music = AudioSystem.getAudioInputStream(new File("src/visions.wav"));

            AudioFormat format = music.getFormat();
            final SourceDataLine line;

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            int nByte = 0;
            final int SIZE = 1024 * 64;
            byte[] buffer = new byte[SIZE];

            while (nByte != -1) {
                if (flag) {//若flag为true 播放music
                    nByte = music.read(buffer, 0, SIZE);
                    if (nByte != -1)
                        line.write(buffer, 0, nByte);
                } else {//若flag为false 播放Music
                    nByte = Music.read(buffer, 0, SIZE);
                    if (nByte != -1)
                        line.write(buffer, 0, nByte);
                }
            }
            line.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    static boolean exit = true;
    static boolean isExit = true;
    static boolean initiatLoad = true;

    //游戏面板的启动方法
    @Override
    public void run() {
        //若isExit为false 保留游戏界面按pause label时的状态
        //从而实现continue功能
        if (isExit) {//若isExit为true 对面板初始化
            //让wall中没有块的地方赋一个不为null的初值，以便之后读入load时方便调用对象赋图片。
            this.wall = new Cell[20][10];
            for(int i=0;i<20;i++){
                for(int j=0;j<10;j++){
                    wall[i][j] = new Cell(-1,-1);
                }
            }

            //若initiatLoad为true 开始新游戏
            if (initiatLoad) {
                this.setGameState(PLAYING);
                this.currentOne = Tetromino.randomOne();
                this.nextOne = Tetromino.randomOne();
                totalScore = 0;
                totalLine = 0;
            }
            else{//若initiatLoad为false 读取存档
                this.setGameState(PLAYING);

                try {
                    File file = new File("records.txt");
                    Scanner input = new Scanner(file);

                    //读入速度 得分数 消行数
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
                    input.close();
                }catch (FileNotFoundException e){
                    e.printStackTrace();
                }
            }
        }

        //若exit为true，创建一个gamePanel上的键盘监听器
        //最开始将exit为true，点击pause按钮或者游戏失败后就将exit保持为假
        if (exit) {
            this.startInitiation();
        }

        //保持焦点
        this.requestFocus();
        if(!exit){
            Tetris.setGameState(PLAYING);
        }

        //调用start方法 使游戏界面动起来
        try {
            this.Start();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
