/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package puzzle;

/**
 *
 * @author UcChwas
 */
import java.util.LinkedList;
import java.util.Queue;
import jdk.nashorn.internal.ir.BlockStatement;



public class Board {

    private static final int SPACE = 0;
    private int[][] blocks;
    public Board(int[][] blocks) {
        this.blocks = copy(blocks);
    }



    private int[][] copy(int[][] blocks) {

        int[][] copy = new int[blocks.length][blocks.length];

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                copy[row][col] = blocks[row][col];
        return copy;

    }



    public int dimension() {

        return blocks.length;

    }



    public int hamming() {

        int count = 0;

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                if (blockIsNotInPlace(row, col)) count++;

        return count;

    }
    
    public void moveZero(){
        int count = 0;
        int [][] matrix1 = null;
        int [][] matrix2 = null;
        int [][] matrix3 = null;
        int [][] matrix4 = null;
        int sum1=0;
        int sum2=0;
        int sum3=0;
        int sum4=0;
        for (int row = 0; row < blocks.length; row++){
             for (int col = 0; col < blocks.length; col++){
                 if(blocks[row][col] == 0){
                     if(row +1 < blocks.length){
                          matrix1 = swap(row, col, row + 1, col);
                          Board b1 = new Board(matrix1);
                          sum1 = b1.hamming() +1;
                          System.out.println("Sum1 :" +sum1);
                     }
                     if(row -1 < blocks.length){
                          matrix2 = swap(row, col, row - 1, col);
                          Board b2 = new Board(matrix2);
                          sum2 = b2.hamming() + 1;
                          System.out.println("Sum2 :" +sum2);
                     }
                     if(col +1 < blocks.length){
                          matrix3 = swap(row, col, row, col+1);
                          Board b3 = new Board(matrix3);
                          sum3 = b3.hamming() + 1;
                          System.out.println("Sum3 :" +sum3);
                     }
                     if(col -1 < blocks.length){
                          matrix4 = swap(row, col, row, col-1);
                          Board b4 = new Board(matrix4);
                          sum4 = b4.hamming() +1;
                          System.out.println("Sum4 :" +sum4);
                     }
                 }
            }
        }
        /*for (int row = 0; row < blocks.length; row++)
             for (int col = 0; col < blocks.length; col++)
                 System.out.println(" " + matrix4[row][col]);*/
        

    }

    private boolean blockIsNotInPlace(int row, int col) {
        int block = block(row, col);
        return !isSpace(block) && block != goalFor(row, col);

    }



    private int goalFor(int row, int col) {

        return row*dimension() + col + 1;

    }



    private boolean isSpace(int block) {

        return block == SPACE;

    }



    public int manhattan() {

        int sum = 0;

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                sum += calculateDistances(row, col);



        return sum;

    }

    private int linearVerticalConflict(int[][] state ) {
    //int state[] = s.getState();
    int dimension = (int) Math.sqrt(state.length);
    int linearConflict = 0;
    int count = 0;
    for (int row = 0; row < dimension; row++) {
        int max = -1;
        for (int column = 0; column < dimension; column++) {
            int cellValue = state[row][column];
            if (cellValue != 0 && (cellValue - 1) / dimension == row) {
                if (cellValue > max) {
                    max = cellValue;
                } else {
                    linearConflict += 2;
                }
            }

        }

    }
    return linearConflict;
}
    
    private int linearHorizontalConflict(int[][] state) {
    //int[] state = s.getState();
    int dimension = (int) Math.sqrt(state.length);
    int linearConflict = 0;
    int count = 0;
    for (int column = 0; column < dimension; column++) {
        int max = -1;
        for (int row = 0; row < dimension; row++) {
            int cellValue = state[row][column];
            if (cellValue != 0 && cellValue % dimension == column + 1) {
                if (cellValue > max) {
                    max = cellValue;
                } else {
                    linearConflict += 2;
                }
            }

        }

    }
    return linearConflict;
   }
    public int LinearConflict(){
        int linear=manhattan()+linearHorizontalConflict(blocks)+linearVerticalConflict(blocks);
        
        return linear;
        
    }

    private int calculateDistances(int row, int col) {

        int block = block(row, col);
        return (isSpace(block)) ? 0 : Math.abs(row - row(block)) + Math.abs(col - col(block));

    }



    private int block(int row, int col) {

        return blocks[row][col];

    }



    private int row (int block) {

        return (block - 1) / dimension();

    }



    private int col (int block) {

        return (block - 1) % dimension();

    }



    public boolean isGoal() {

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                if (blockIsInPlace(row, col)) return false;
        return true;

    }



    private boolean blockIsInPlace(int row, int col) {

        int block = block(row, col);



        return !isSpace(block) && block != goalFor(row, col);

    }



    public Board twin() {

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length - 1; col++)

                if (!isSpace(block(row, col)) && !isSpace(block(row, col + 1)))

                    return new Board(swap(row, col, row, col + 1));

        throw new RuntimeException();

    }



    private int[][] swap(int row1, int col1, int row2, int col2) {

        int[][] copy = copy(blocks);

        int tmp = copy[row1][col1];

        copy[row1][col1] = copy[row2][col2];

        copy[row2][col2] = tmp;



        return copy;

    }



    public boolean equals(Object y) {

        if (y==this) return true;

        if (y==null || !(y instanceof Board) || ((Board)y).blocks.length != blocks.length) return false;

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                if (((Board) y).blocks[row][col] != block(row, col)) return false;



        return true;

    }



    public Iterable<Board> neighbors() {

        Queue<Board> neighbors = new LinkedList<Board>();



        int[] location = spaceLocation();

        int spaceRow = location[0];

        int spaceCol = location[1];



        if (spaceRow > 0)               neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));

        if (spaceRow < dimension() - 1) neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));

        if (spaceCol > 0)               neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));

        if (spaceCol < dimension() - 1) neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));



        return neighbors;

    }



    private int[] spaceLocation() {

        for (int row = 0; row < blocks.length; row++)

            for (int col = 0; col < blocks.length; col++)

                if (isSpace(block(row, col))) {

                    int[] location = new int[2];

                    location[0] = row;

                    location[1] = col;



                    return location;

                }

        throw new RuntimeException();

    }



    public String toString() {

        StringBuilder str = new StringBuilder();

        str.append(dimension() + "\n");

        for (int row = 0; row < blocks.length; row++) {

            for (int col = 0; col < blocks.length; col++)

                str.append(String.format("%2d ", block(row, col)));

            str.append("\n");

        }



        return str.toString();

    }
/*    
    public static void main(String[] args) {

		int[][] initial = { {1, 2, 3}, {5, 6, 0}, {7, 8, 4} };

		int[][] goal    = { {1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0} };
                
                Board b = new Board(initial);
                b.moveZero();
                //System.out.println("Hamming Distance :"+b.manhattan());
    }
*/
}
