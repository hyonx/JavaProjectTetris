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
        int i = (int)(Math.random()*7);
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

    //Rotate the block, which needs to be rewrite in specific block class
    public void rotate(){}
}
class T extends Tetromino{
        public T(){
            cells[0] = new Cell(4,0,Tetris.T);
            cells[1] = new Cell(3,0,Tetris.T);
            cells[2] = new Cell(5,0,Tetris.T);
            cells[3] = new Cell(4,1,Tetris.T);
        }
}
class O extends Tetromino{
    public O(){
        cells[0] = new Cell(4,0,Tetris.O);
        cells[1] = new Cell(5,0,Tetris.O);
        cells[2] = new Cell(4,0,Tetris.O);
        cells[3] = new Cell(5,1,Tetris.O);
    }
}
class I extends Tetromino{
    public I(){
        cells[0] = new Cell(4,0,Tetris.I);
        cells[1] = new Cell(3,0,Tetris.I);
        cells[2] = new Cell(5,0,Tetris.I);
        cells[3] = new Cell(6,0,Tetris.I);
    }
}
class J extends Tetromino{
    public J(){
        cells[0] = new Cell(4,0,Tetris.J);
        cells[1] = new Cell(3,0,Tetris.J);
        cells[2] = new Cell(5,0,Tetris.J);
        cells[3] = new Cell(5,1,Tetris.J);
    }
}
class L extends Tetromino{
    public L(){
        cells[0] = new Cell(4,0,Tetris.L);
        cells[1] = new Cell(3,0,Tetris.L);
        cells[2] = new Cell(5,0,Tetris.L);
        cells[3] = new Cell(3,1,Tetris.L);
    }
}
class S extends Tetromino{
    public S(){
        cells[0] = new Cell(4,0,Tetris.S);
        cells[1] = new Cell(5,0,Tetris.S);
        cells[2] = new Cell(3,1,Tetris.S);
        cells[3] = new Cell(3,1,Tetris.S);
    }
}
class Z extends Tetromino{
    public Z (){
        cells[0] = new Cell(4,1,Tetris.Z);
        cells[1] = new Cell(3,0,Tetris.Z);
        cells[2] = new Cell(4,0,Tetris.Z);
        cells[3] = new Cell(5,1,Tetris.Z);
    }
}


