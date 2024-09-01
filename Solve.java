package Sudoku;
import java.util.Scanner;
public class solvePuzzle
{
    private Sudoku sud;
    private int size;


    public solvePuzzle()
    {
        sud = new Sudoku();
        size = sud.length();
    }

    public boolean solve(int row, int col)
    {
        if (col == size) {                                  
            col = 0;                                        
            row+=1;                                        
            if (row == size) {
                return true;
            }
        }

        for (int num = 1; num < size+1; num++)              
        {
            if (sud.puz[row][col] == 0)                     
            {                                               
                if (isLegal(row, col, num))                 
                {                                          
                    sud.puz[row][col] = num;
                    if (solve(row, col + 1))            
                    {                                       
                        return true;                        
                    }                                       
                    else                                    
                    {                                       
                        sud.puz[row][col] = 0;              
                    }                                       
                }                                           
            }                                              
            else
                return solve(row, col + 1);             
        }
        return false;                                       
    }
                                                            
                                                           

    public boolean isLegal(int row, int col, int num)
    {
        for (int j = 0; j < size; j++)                      
        {
            if (sud.puz[row][j] == num) {return false;}
        }

        for (int r = 0; r < size; r++)                      
        {
            if (sud.puz[r][col] == num) {return false;}
        }

        int topRightRow = (row / sud.getBox_r()) * sud.getBox_r();           
        int topRightCol = (col / sud.getBox_c()) * sud.getBox_c();
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if (sud.puz[topRightRow+i][topRightCol+j] == num)
                {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        solvePuzzle sp = new solvePuzzle();
        if(!sp.sud.loadSudoku())   {return;}
        long start = System.currentTimeMillis();
        if(sp.solve(0,0)) {
            System.out.println("Solution found!\n");
            sp.sud.printSudoku();
            System.out.println("\nIt took me " + (System.currentTimeMillis() - start) + "ms to solve this puzzle.");
        }
        else
        {
            System.out.println("No Solution Found!");
        }

    }
}