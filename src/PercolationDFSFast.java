import java.util.Arrays;

public class PercolationDFSFast extends PercolationDFS{

    public PercolationDFSFast(int size){
       super(size);
    }

    @Override
    protected void updateOnOpen(int row, int col) {
        // just in case row and col parameters are out of bonds; although I don't think this is possible in the visualizer
        if (! inBounds(row,col)) return;

        //Is the cell in the top row? If so, it should be marked as full.
        //Is the cell adjacent to a full cell? If so it should be marked as full.
        if(row == 0 || (inBounds(row - 1, col) && isFull(row - 1, col)) || (inBounds(row, col - 1) && isFull(row, col - 1)) || (inBounds(row + 1, col) && isFull(row + 1, col)) || (inBounds(row, col + 1) && isFull(row, col + 1))) {
            // fill it; must use inBounds(row ..., col...) because dfs will try to fill all neighboring open cells
            // must make sure the neighboring cells exist
            dfs(row, col);
        }


        // clearFull();
        // for (int k = 0; k < myGrid[0].length; k++)
        //    dfs(0, k);
    }
}
