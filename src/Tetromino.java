import java.awt.image.BufferedImage;

public class Tetromino {
    //每个俄罗斯方块有四个cell
    Cell[] cells=new Cell[4]; 
    State[] states;

    //rotateNumber用于记录当前方块处于哪种旋转状态，以便在旋转中对四个cell做相应的行列变换。
    int rotateNumber;
    //所有cell左移
    public void moveLeft(){
        for(Cell c:cells){
            c.left();
        }
    }

    //右移
    public void moveRight(){
        for(Cell c: cells){
            c.right();
        }
    }

    //顺时针旋转
    public void rotateClockwise(){
        State s = this.states[rotateNumber%4];
        cells[0].manipulate(s.getCol0(),s.getRow0());
        cells[1].manipulate(s.getCol1(),s.getRow1());
        cells[2].manipulate(s.getCol2(),s.getRow2());
        cells[3].manipulate(s.getCol3(),s.getRow3());
    }

    //逆时针旋转
    public void rotateCounter(){
        State s = this.states[(rotateNumber%4+3)%4];
        cells[0].manipulate(-s.getCol0(),-s.getRow0());
        cells[1].manipulate(-s.getCol1(),-s.getRow1());
        cells[2].manipulate(-s.getCol2(),-s.getRow2());
        cells[3].manipulate(-s.getCol3(),-s.getRow3());
    }

    //慢慢掉落
    public void softDrop(){
        for(Cell c: cells){
            c.drop();
        }
    }

    //随机产生一个俄罗斯方块
    public static Tetromino randomOne(){
        int i = (int)(Math.random()*7);// This is for shape of the block
        Tetromino t = null;
        switch (i){
            case 0:t=new T();break;
            case 1:t=new O();break;
            case 2:t=new I();break;
            case 3:t=new J();break;
            case 4:t=new L();break;
            case 5:t=new S();break;
            case 6:t=new Z();break;
        }
        return t;
    }

    //通过传入tetromino的字符串，返回一个相应类型的tetromino。
    public static Tetromino generateByName(String x){
        Tetromino y = null;
        if(x.equals("T")){
            T t = new T();
            y=t;
        }
        if(x.equals("O")){
            O o = new O();
            y=o;
        }
        else if(x.equals("I")){
            I i = new I();
            y=i;
        }
        else if(x.equals("J")){
            J j = new J();
            y=j;
        }
        else if(x.equals("L")){
            L l = new L();
            y=l;
        }
        else if(x.equals("S")){
            S s = new S();
            y=s;
        }
        else if(x.equals("Z")){
            Z z = new Z();
            y=z;
        }
        return y;
    }

    //返回表示某一个tetromino类型的字符串。
    public  String getTetrominoType(){
        String s = "";
        if(this instanceof T){
            s="T";
        }
        if(this instanceof O){
            s="O";
        }
        if(this instanceof I){
            s="I";
        }
        if(this instanceof J){
            s="J";
        }
        if(this instanceof L){
            s="L";
        }
        if(this instanceof S){
            s="S";
        }
        if(this instanceof Z){
            s="Z";
        }
        return s;
    }
    //随机返回某个颜色的小方块图片
    public static BufferedImage randomColor(){
        BufferedImage t = null;
        int i = (int)(Math.random()*7); //总过七种颜色
        switch (i){
            case 0:t=Tetris.blue;break;
            case 1:t=Tetris.orange;break;
            case 2:t=Tetris.orange;break;
            case 3:t=Tetris.purple;break;
            case 4:t=Tetris.red;break;
            case 5:t=Tetris.skyblue;break;
            case 6:t=Tetris.yellow;break;
        }
        return t;
    }
    
}
class T extends Tetromino{
        public T(){
            rotateNumber = 10000;
            states = new State[4];
            BufferedImage t = randomColor();
            cells[0] = new Cell(4,0,t);
            cells[1] = new Cell(3,0,t);
            cells[2] = new Cell(5,0,t);
            cells[3] = new Cell(4,1,t);
            states[0]= new State(0,0,1,-1,-1,1,-1,-1);
            states[1]= new State(0,0,1,1,-1,-1,1,-1);
            states[2]= new State(0,0,-1,1,1,-1,1,1);
            states[3]= new State(0,0,-1,-1,1,1,-1,1);
        }
}
class O extends Tetromino{
    public O(){
        rotateNumber = 10000;
        states = new State[4];
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(5,0,t);
        cells[2] = new Cell(4,1,t);
        cells[3] = new Cell(5,1,t);
        states[0] = new State(0,0,0,0,0,0,0,0);
        states[1] = new State(0,0,0,0,0,0,0,0);
        states[2] = new State(0,0,0,0,0,0,0,0);
        states[3] = new State(0,0,0,0,0,0,0,0);
    }
}
class I extends Tetromino {
    public I() {
        rotateNumber = 10000;
        states = new State[4];
        BufferedImage t = randomColor();
        cells[0] = new Cell(4, 0, t);
        cells[1] = new Cell(3, 0, t);
        cells[2] = new Cell(5, 0, t);
        cells[3] = new Cell(6, 0, t);
        states[0] = new State(0, 0, 1, -1, -1, 1, -2, 2);
        states[1] = new State(0, 0, 1, 1, -1, -1, -2, -2);
        states[2] = new State(0, 0, -1, 1, 1, -1, 2, -2);
        states[3] = new State(0, 0, -1, -1, 1, 1, 2, 2);
    }
}
        class J extends Tetromino {
            public J() {
                rotateNumber = 10000;
                states = new State[4];
                BufferedImage t = randomColor();
                cells[0] = new Cell(4, 0, t);
                cells[1] = new Cell(3, 0, t);
                cells[2] = new Cell(5, 0, t);
                cells[3] = new Cell(5, 1, t);
                states[0]= new State(0,0,1,-1,-1,1,-2,0);
                states[1]= new State(0,0,1,1,-1,-1,0,-2);
                states[2]= new State(0,0,-1,1,1,-1,2,0);
                states[3]= new State(0,0,-1,-1,1,1,0,2);
            }
            }
        class L extends Tetromino {
            public L() {
                rotateNumber = 10000;
                states = new State[4];
                BufferedImage t = randomColor();
                cells[0] = new Cell(4, 0, t);
                cells[1] = new Cell(3, 0, t);
                cells[2] = new Cell(5, 0, t);
                cells[3] = new Cell(3, 1, t);
                states[0]= new State(0,0,1,-1,-1,1,0,-2);
                states[1]= new State(0,0,1,1,-1,-1,2,0);
                states[2]= new State(0,0,-1,1,1,-1,0,2);
                states[3]= new State(0,0,-1,-1,1,1,-2,0);
            }
        }
        class S extends Tetromino {
            public S() {
                rotateNumber = 10000;
                states = new State[4];
                BufferedImage t = randomColor();
                cells[0] = new Cell(4, 0, t);
                cells[1] = new Cell(5, 0, t);
                cells[2] = new Cell(3, 1, t);
                cells[3] = new Cell(4, 1, t);
                states[0]= new State(0,0,-1,1,0,-2,-1,-1);
                states[1]= new State(0,0,-1,-1,2,0,1,-1);
                states[2]= new State(0,0,1,-1,0,2,1,1);
                states[3]= new State(0,0,1,1,-2,0,-1,1);
            }
        }
        class Z extends Tetromino {
            public Z() {
                rotateNumber = 10000;
                states = new State[4];
                BufferedImage t = randomColor();
                cells[0] = new Cell(4, 1, t);
                cells[1] = new Cell(3, 0, t);
                cells[2] = new Cell(4, 0, t);
                cells[3] = new Cell(5, 1, t);
                states[0]= new State(0,0,2,0,1,1,-1,1);
                states[1]= new State(0,0,0,2,-1,1,-1,-1);
                states[2]= new State(0,0,-2,0,-1,-1,1,-1);
                states[3]= new State(0,0,0,-2,1,-1,1,1);
            }
        }

