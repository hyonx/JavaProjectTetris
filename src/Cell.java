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
    public String getImageLocation(){
        String s = null;
        if(this.image==Tetris.blue){
            s = "blue.png";
        }
        else if(this.image==Tetris.orange){
            s = "orange.png";
        }else if(this.image==Tetris.green){
            s = "green.png";
        }else if(this.image==Tetris.purple){
            s = "purple.png";
        }else if(this.image==Tetris.red){
            s = "red.png";
        }else if(this.image==Tetris.skyblue){
            s = "skyblue.png";
        }else if(this.image==Tetris.yellow){
            s = "yellow.png";
        }
        else s = "null";
        return s;
    }
    public void setImageByLocation(String s){
        switch(s){
            case "blue":this.setImage(Tetris.blue);break;
            case "orange":this.setImage(Tetris.orange);break;
            case "green":this.setImage(Tetris.green);break;
            case "purple":this.setImage(Tetris.purple);break;
            case "red":this.setImage(Tetris.red);break;
            case "skyblue":this.setImage(Tetris.skyblue);break;
            case "yellow":this.setImage(Tetris.yellow);break;
        }
    }
}
