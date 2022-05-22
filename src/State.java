//This class is used to describe the rotation state of a certain block.
public class State {
    //分别定义顺时针旋转中四个行标和四个列标的变换为row0....col3.
        private int row0,col0,row1,col1,row2,col2,row3,col3;
        public State(){}
        public State(int col0,int row0,int col1,int row1,int col2,int row2,int col3,int row3){
            this.row0 = row0;
            this.row1 = row1;
            this.row2 = row2;
            this.row3 = row3;
            this.col0 = col0;
            this.col1 = col1;
            this.col2 = col2;
            this.col3 = col3;
        }
    public int getRow0(){
        return row0;
    }
    public int getRow1(){
        return row1;
    }
    public int getRow2(){
        return row2;
    }
    public int getRow3(){
        return row3;
    }
    public int getCol0(){
            return col0;
    }
    public int getCol1(){
        return col1;
    }
    public int getCol2(){
        return col2;
    }
    public int getCol3(){
        return col3;
    }
    public void setRow0(int row0){
            this.row0 = row0;
    }
    public void setRow1(int row1){
        this.row1 = row1;
    }
    public void setRow2(int row2){
        this.row2 = row2;
    }
    public void setRow3(int row3){
        this.row3 = row3;
    }
    public void setCol0(int col0){
        this.col0 = col0;
    }
    public void setCol1(int col1){
        this.col1 = col1;
    }
    public void setCol2(int col2){
        this.col2 = col2;
    }
    public void setCol3(int col3){
        this.col3 = col3;
    }
}
