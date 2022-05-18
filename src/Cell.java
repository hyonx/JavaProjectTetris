import java.awt.image.BufferedImage;

import javax.crypto.NullCipher;
import javax.imageio.ImageIO;

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
    public Cell(int col, int row){
        this.col = col;
        this.row = row;
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
        if(this.image!=null){
            if(Tetris.blue==this.image){
                s = "blue.png";
        }
            if(Tetris.orange==this.image){
                s = "orange.png";
        }
            if(Tetris.green==this.image){
                s = "green.png";
        }
            if(Tetris.purple==this.image){
                s = "purple.png";
        }
             if(this.image==Tetris.red){
                s = "red.png";
        }
             if(Tetris.skyblue==this.image){
                s = "skyblue.png";
        }
             if(Tetris.yellow==this.image){
                s = "yellow.png";
        }
    }
        return s;
    }
    public void setImageByLocation(String s){
        switch(s){
            case "blue.png":this.setImage(Tetris.blue);break;
            case "orange.png":this.setImage(Tetris.orange);break;
            case "green.png":this.setImage(Tetris.green);break;
            case "purple.png":this.setImage(Tetris.purple);break;
            case "red.png":this.setImage(Tetris.red);break;
            case "skyblue.png":this.setImage(Tetris.skyblue);break;
            case "yellow.png":this.setImage(Tetris.yellow);break;
        }
    }
}
