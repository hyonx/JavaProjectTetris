import java.awt.image.BufferedImage;

public class Tetromino {
    Cell[] cells=new Cell[4];  //Every block has 4 cells.

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
            case 3:t=Tetris.purple;;break;
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
            BufferedImage t = randomColor();
            cells[0] = new Cell(4,0,t);
            cells[1] = new Cell(3,0,t);
            cells[2] = new Cell(5,0,t);
            cells[3] = new Cell(4,1,t);
        }
}
class O extends Tetromino{
    public O(){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(5,0,t);
        cells[2] = new Cell(4,0,t);
        cells[3] = new Cell(5,1,t);
    }
}
class I extends Tetromino{
    public I(){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(3,0,t);
        cells[2] = new Cell(5,0,t);
        cells[3] = new Cell(6,0,t);
    }
}
class J extends Tetromino{
    public J(){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(3,0,t);
        cells[2] = new Cell(5,0,t);
        cells[3] = new Cell(5,1,t);
    }
}
class L extends Tetromino{
    public L(){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(3,0,t);
        cells[2] = new Cell(5,0,t);
        cells[3] = new Cell(3,1,t);
    }
}
class S extends Tetromino{
    public S(){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,0,t);
        cells[1] = new Cell(5,0,t);
        cells[2] = new Cell(3,1,t);
        cells[3] = new Cell(3,1,t);
    }
}
class Z extends Tetromino{
    public Z (){
        BufferedImage t = randomColor();
        cells[0] = new Cell(4,1,t);
        cells[1] = new Cell(3,0,t);
        cells[2] = new Cell(4,0,t);
        cells[3] = new Cell(5,1,t);
    }
}


