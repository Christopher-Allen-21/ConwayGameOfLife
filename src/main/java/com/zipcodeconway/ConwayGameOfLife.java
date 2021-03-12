package com.zipcodeconway;

import java.util.Arrays;

public class ConwayGameOfLife {
    Integer dimension;
    int[][] startMatrix;

    public ConwayGameOfLife(Integer dimension) {
        this.dimension = dimension;
        this.startMatrix = createRandomStart(dimension);
     }

    public ConwayGameOfLife(Integer dimension, int[][] startMatrix) {
        this.dimension = dimension;
        this.startMatrix = startMatrix;
    }

    public static void main(String[] args) {
        ConwayGameOfLife sim = new ConwayGameOfLife(50);
        int[][] endingWorld = sim.simulate(50);
    }

    // Contains the logic for the starting scenario.
    // Which cells are alive or dead in generation 0.
    // allocates and returns the starting matrix of size 'dimension'
    private int[][] createRandomStart(Integer dimension) {
        int[][] randomStart = new int[dimension][dimension];

        for(int i=0;i<randomStart.length;i++){
            for(int j=0;j<randomStart.length;j++){
                randomStart[i][j] = (int)Math.round(Math.random());
            }
        }

        System.out.print(Arrays.deepToString(randomStart));
        return randomStart;
    }

    public int[][] simulate(Integer maxGenerations) {
        int[][] nextGen = new int[startMatrix.length][startMatrix.length];

        //iterates through once; need to figure out how to do for multiple generations
        for(int j=0;j<startMatrix.length;j++){
            for(int k=0;k<startMatrix.length;k++){
                nextGen[j][k] = isAlive(j,k,startMatrix);
            }
        }


        return nextGen;
    }

    // copy the values of 'next' matrix to 'current' matrix,
    // and then zero out the contents of 'next' matrix
    public void copyAndZeroOut(int [][] next, int[][] current) {
        for(int i=0;i<next.length;i++){
            for(int j=0;j<next.length;j++){
                current[i][j] = next[i][j];
            }
        }

        for(int i=0;i<next.length;i++){
            for(int j=0;j<next.length;j++){
                next[i][j] = 0;
            }
        }
    }

    // Calculate if an individual cell should be alive in the next generation.
    // Based on the game logic:
	/*
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private int isAlive(int row, int col, int[][] world) {
        int count = 0;
        boolean cellIsAlive;

        if(world[row][col]==1){
            cellIsAlive = true;
        }
        else{
            cellIsAlive = false;
        }


        // cell is alive
        if(cellIsAlive){
            if(col==0){
                count += checkForLifeTopLeftCorner(row, col, world);
                count += checkForLifeBottomLeftCorner(row, col, world);
            }
            else if(col==world.length-1){
                count += checkForLifeTopRightCorner(row,col,world);
                count += checkForLifeBottomRightCorner(row, col, world);
            }
            else{
                count += checkForLifeMiddle(row,col,world);
            }
        }
        else{
            if(col==0){
                count += checkForLifeTopLeftCorner(row, col, world);
                count += checkForLifeBottomLeftCorner(row, col, world);
            }
            else if(col==world.length-1){
                count += checkForLifeTopRightCorner(row,col,world);
                count += checkForLifeBottomRightCorner(row, col, world);
            }
            else{
                count += checkForLifeMiddle(row,col,world);
            }
        }


        if(cellIsAlive && count==2 || count==3){
            return 1;
        }
        else if(!cellIsAlive && count == 3){
            return 1;
        }
        else{
            return 0;
        }
    }



    private int checkForLifeMiddle(int row, int col, int[][] world){
        int count = 0;
        //top left diag
        if(world[row-1][col-1]==1){
            count++;
        }
        //above
        else if(world[row-1][col]==1){

        }
        //top right diag
        else if(world[row-1][col+1]==1){
            count++;
        }
        //right
        else if(world[row][col+1]==1){
            count++;
        }
        //bottom right diag
        else if(world[row+1][col+1]==1){
            count++;
        }
        //bottom
        else if(world[row+1][col]==1){
            count++;
        }
        //bottom left diag
        else if(world[row+1][col-1]==1){
            count++;
        }
        //left
        else if(world[row][col-1]==1){
            count++;
        }
        return count;
    }

    private int checkForLifeTopLeftCorner(int row, int col, int[][] world) {
        int count = 0;

        //cell is top left corner
        if(row == 0){
            //right
            if(world[row][col+1]==1){
                count++;
            }
            //bottom right diag
            else if(world[row+1][col+1]==1){
                count++;
            }
            //bottom
            else if(world[row+1][col]==1){
                count++;
            }
        }
        return count;
    }

    private int checkForLifeBottomLeftCorner(int row, int col, int[][] world) {
        int count = 0;
        //cell is bottom left corner
        //top
        if(world[row-1][col]==1){
            count++;
        }
        //top right diag
        else if(world[row-1][col+1]==1){
            count++;
        }
        //right
        else if(world[row][col+1]==1){
            count++;
        }
        return count;
    }

    private int checkForLifeTopRightCorner(int row, int col, int[][] world) {
        int count = 0;
        //cell is top right corner
            //right
            if(world[row][col-1]==1){
                count++;
            }
            //bottom right diag
            else if(world[row+1][col-1]==1){
                count++;
            }
            //bottom
            else if(world[row+1][col]==1){
                count++;
            }
        return count;
    }

    private int checkForLifeBottomRightCorner(int row, int col, int[][] world) {
        int count = 0;
        //cell is bottom right corner
            //top
            if(world[row-1][col]==1){
                count++;
            }
            //top left diag
            else if(world[row-1][col-1]==1){
                count++;
            }
            //left
            else if(world[row][col-1]==1){
                count++;
            }
        return count;
    }
}
