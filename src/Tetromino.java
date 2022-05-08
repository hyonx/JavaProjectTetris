import java.awt.image.BufferedImage;

public class Tetromino {
    Cell[] cells=new Cell[4];  //Every block has 4 cells.
    State[] states;
    int rotateNumber;
   //This number is used to record the current state of one specific block. as large as possible.

    //all cells in a block move leftward
    public void moveLeft(){
        for(Cell c:cells){
            c.left();
        }
    }

    //rightward
    public void moveRight(){
        for(Cell c: cells){
            c.right();
        }
    }

    //rotate clockwise
    public void rotateClockwise(){
        State s = this.states[rotateNumber%4];
        cells[0].manipulate(s.getCol0(),s.getRow0());
        cells[1].manipulate(s.getCol1(),s.getRow1());
        cells[2].manipulate(s.getCol2(),s.getRow2());
        cells[3].manipulate(s.getCol3(),s.getRow3());
    }

    //rotate counterclockwise
    public void rotateCounter(){
        State s = this.states[(rotateNumber%4+3)%4];
        cells[0].manipulate(s.getCol0(),s.getRow0());
        cells[1].manipulate(s.getCol1(),s.getRow1());
        cells[2].manipulate(s.getCol2(),s.getRow2());
        cells[3].manipulate(s.getCol3(),s.getRow3());
    }

    //drop
    public void softDrop(){
        for(Cell c: cells){
            c.drop();
        }
    }

    //Randomly generate a type of block
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
        Tetromino s = new T();
        return t;
    }

    //This is for generating random colors with certain pictures.It has the return value of BufferedImage.
    public static BufferedImage randomColor(){
        BufferedImage t = null;
        int i = (int)(Math.random()*7); //There are totally seven kind of colors.
        switch (i){
            case 0:t=Tetris.blue;break;
            case 1:t=Tetris.orange;break;
            case 2:t=Tetris.green;break;
            case 3:t=Tetris.purple;break;
            case 4:t=Tetris.red;break;
            case 5:t=Tetris.skyblue;break;
            case 6:t=Tetris.yellow;break;
        }
        return t;
    }

    //Rotate the block, which needs to be rewrite in specific block class
    public void rotate(){}

}
class T extends Tetromino{
        public T(){
            rotateNumber = 10000;
            states = new State[4];
            BufferedImage t = randomColor();
            cells[0] = new Cell(4,5,t);
            cells[1] = new Cell(3,5,t);
            cells[2] = new Cell(5,5,t);
            cells[3] = new Cell(4,6,t);
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
                states[0]= new State(0,0,1,-1,-1,1,-2,0);
                states[1]= new State(0,0,1,1,-1,-1,0,-2);
                states[2]= new State(0,0,-1,1,1,-1,2,0);
                states[3]= new State(0,0,-1,-1,1,1,0,2);
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
                states[0]= new State(0,0,-1,1,-1,-1,0,-2);
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

