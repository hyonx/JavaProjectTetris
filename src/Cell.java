import java.awt.image.BufferedImage;
public class Cell {
    private int col;
    private int row;
    private BufferedImage image;
    public Cell(){}
    public Cell(int col, int row, BufferedImage image){
        this.col = col;
        this.row = row;
        this.image = image;
    }

    //move left
    public void left(){
        col--;
    }
    //move right
    public void right(){
        col++;
    }
    //drop
    public void drop(){
        row++;
    }

    //The getters and setters
    public int getCol(){
        return col;
    }
    public void setCol(int col){
        ;this.col = col;
    }
    public int getRow(){
        return row;
    }
    public void setRow(int row){
        ;this.row = row;
    }

    //Set the col and row of a cell at the same time.
    public void setColRow(int col,int row){
        this.col = col;
        this.row = row;
    }
    //This method is for adding or subtracting a number from col and row.
    public void manipulate(int col,int row){
        int m = this.col + col;
        int n = this.row + row;
        this.setColRow(m,n);
    }
    public BufferedImage getImage(){
        return image;
    }
    public void setImage(BufferedImage image){
        this.image = image;
    }
}
