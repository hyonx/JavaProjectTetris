import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Tetris extends JPanel {
    //This is the main class, which contains the main method, and other major methods.
    Tetromino currentOne = Tetromino.randomOne();
    Tetromino nextOne = Tetromino.randomOne();
    Cell[][] wall = new Cell[20][10]; // Wall is used to memorize every cell except those who belongs to currentOne and nextOne.
    final int CELL_SIZE = 26;         // The length of every cell in wall and blocks.
    public static BufferedImage T;
    public static BufferedImage O;
    public static BufferedImage I;
    public static BufferedImage J;
    public static BufferedImage L;
    public static BufferedImage S;
    public static BufferedImage Z;
    public static BufferedImage background;
    public static BufferedImage gameOver;

    static {
        try {
            T = ImageIO.read(new File("data/blue.png"));
            I = ImageIO.read(Tetris.class.getResource("green.png"));
            O = ImageIO.read(Tetris.class.getResource("orange.png"));
            J = ImageIO.read(Tetris.class.getResource("purple.png"));
            L = ImageIO.read(Tetris.class.getResource("red.png"));
            S = ImageIO.read(Tetris.class.getResource("skyblue.png"));
            Z = ImageIO.read(Tetris.class.getResource("yellow.png"));
            background = ImageIO.read(Tetris.class.getResource("background.png"));
            gameOver = ImageIO.read(Tetris.class.getResource("gameover&start.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.translate(15, 15);
        paintWall(g);
        paintCurrentOne(g);
        paintNextOne(g);
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

    public void start() {
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
                    case KeyEvent.VK_UP:
                        break;
                    default:
                        break;
                }
                repaint();
            }
        };

        this.addKeyListener(listener1);
        this.requestFocus();


        while(true) {
            /*
             * 当程序运行到此，会进入睡眠状态，
             * 睡眠时间为300毫秒,单位为毫秒
             * 300毫秒后，会自动执行后续代码
             */
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                // 抓取打断异常
                e.printStackTrace();
            }

            if(canDrop()) {
                currentOne.softDrop();
            }
            else {
                landToWall();
                //将下一个下落的四格方块赋值给正在下落的变量
                currentOne = nextOne;
                nextOne = Tetromino.randomOne();
            }
            /*
             * 下落之后，要重新进行绘制，才会看到下落后的位置
             * repaint方法，也是JPanel类中提供的
             * 此方法中调用了paint方法
             */
            repaint();
        }
    }
    public void moveRightAction() {
        currentOne.moveRight();
        if(outOfBounds()||coincide())
            currentOne.moveLeft();
    }

    /*
     * 使用Left键控制四格方块左移
     */
    public void moveLeftAction() {
        currentOne.moveLeft();
        if(outOfBounds()||coincide()) {
            currentOne.moveRight();
        }
    }
    private boolean coincide() {
        Cell[] cells = currentOne.cells;
        for(Cell c:cells) {
            int row = c.getRow();
            int col = c.getCol();
            if(wall[row][col]!=null)
                return true;
        }
        return false;
    }
    public boolean outOfBounds() {
        Cell[] cells = currentOne.cells;
        for(Cell c:cells) {
            int col = c.getCol();
            if(col<0||col>9)
                return true;
        }
        return false;
    }

    /*
     * 使用Down键控制四格方块的下落
     */
    public void softDropAction() {
        if(canDrop()) {
            currentOne.softDrop();
        }
        else {
            landToWall();
            currentOne = nextOne;
            nextOne = Tetromino.randomOne();
        }
    }
    public boolean canDrop() {
        Cell[] cells = currentOne.cells;
        /*
         *
         */
        for(Cell c:cells) {
            /* 获取每个元素的行号,列号
             * 判断：
             * 只要有一个元素的下一行上有方块
             * 或者只要有一个元素到达最后一行
             * 就不能再下落了
             */
            int row = c.getRow();
            int col = c.getCol();
            if (row==19) {//判断是否到达底部
                return false;
            }
            else if(wall[row+1][col]!=null) {//判断下方是否有方块
                return false;
            }

        }
        return true;
    }
    /*
     * 当不能再下落时，需要将四格方块嵌入到墙中
     * 也就是存储到二维数组中相应的位置上
     */
    public void landToWall() {
        Cell[] cells = currentOne.cells;
        for(Cell c:cells) {
            //获取最终的行号和列号
            int row = c.getRow();
            int col = c.getCol();
            wall[row][col] = c;
        }
    }

    public static void main(String[] args) {
        JFrame frame=new JFrame("玩玩俄罗斯方块");

        //创建游戏界面,即画板(面板)
        Tetris panel = new Tetris();
        //将面板嵌入窗口
        frame.add(panel);

        //2:设置为可见
        frame.setVisible(true);
        //3:设置窗口的尺寸
        frame.setSize(535, 595);
        //4:设置窗口居中
        frame.setLocationRelativeTo(null);
        //5:设置窗口关闭,即程序中止
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //游戏的主要逻辑封装在start方法中
        panel.start();


    }

}








