import java.util.LinkedList;
import java.util.Queue;

public class PercolationBFS extends PercolationDFSFast{
    public PercolationBFS(int size){
        super(size);
    }

    @Override
    protected void dfs(int row, int col) {
        // out of bounds?
        if (! inBounds(row,col)) return;

        // full or NOT open, don't process
        if (isFull(row, col) || !isOpen(row, col))
            return;

        //Create a Queue<int[]>
        Queue<int[]> q = new LinkedList<>();
        //  mark the cell at myGrid[row][col] as FULL and put the cell in the queue
        myGrid[row][col] = FULL;
        q.add(new int[]{row, col});
        // for all elements in the queue
        while(!q.isEmpty()){
            // dequeue a cell
            int[] cell = q.remove();

            // Check the dequeued cell’s four neighbors
            // if the neighboring cell is open and not full, mark as FULL and add to queue
            if(inBounds(row - 1, col) && isOpen(row - 1, col)){myGrid[row - 1][col] = FULL; q.add(new int[]{row - 1, col});}
            if(inBounds(row, col - 1) && isOpen(row, col - 1)){myGrid[row][col - 1] = FULL; q.add(new int[]{row, col - 1});}
            if(inBounds(row, col + 1) && isOpen(row, col + 1)){myGrid[row][col + 1] = FULL; q.add(new int[]{row, col + 1});}
            if(inBounds(row + 1, col) && isOpen(row+1, col)){myGrid[row + 1][col] = FULL; q.add(new int[]{row + 1, col});}
            //dfs(row - 1, col);
            //dfs(row, col - 1);
            //dfs(row, col + 1);
            //dfs(row + 1, col);
        }


        //myGrid[row][col] = FULL;
        //dfs(row - 1, col);
        //dfs(row, col - 1);
        //dfs(row, col + 1);
        //dfs(row + 1, col);
    }
}
