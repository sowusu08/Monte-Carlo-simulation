import java.util.Arrays;

public class PercolationUF implements IPercolate{
    private IUnionFind myFinder;
    private boolean[][] myGrid;
    private final int VTOP;
    private final int VBOTTOM;
    private int myOpenCount;

    /**
     * construct and initialize the NxN grid stored in the instance variable myGrid (where N is given by the size parameter)
     * initialize the IUnionFind object of size NxN + 2 by calling finder.initialize appropriately and then storing this object in the appropriate instance variable which is myFinder
     * Two final values for VTOP and VBOTTOM, set to $size * size$ and $size*size+1$,
     *
     *
     */
    public PercolationUF(IUnionFind finder, int size) {
        myGrid = new boolean[size][size];
        myFinder = finder;
        myFinder.initialize((size*size + 2));
        /*finder.initialize(size * size + 2);
        myFinder = finder;*/

        VTOP = size * size;
        VBOTTOM = size * size + 1;
        myOpenCount = 0;
    }

    // method to convert row, col index to single integer location
    public int getIndex(int row, int col){
        return (row*myGrid.length) + col;
    }

    // Method isOpen throws an Exception when needed
    // otherwise simply returns the appropriate myGrid value
    @Override
    public boolean isOpen(int row, int col) {
        // Method isOpen throws an Exception when needed and otherwise simply returns the appropriate myGrid value
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }
        return myGrid[row][col];
    }

    // Method isFull throws an Exception when needed
    // otherwise checks if the (row,col) cell is connected to VTOP (call myFinder.connected)
    @Override
    public boolean isFull(int row, int col) {
        if (! inBounds(row,col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }

        //System.out.println(VTOP); // 100
        //System.out.println(getIndex(row,col));
        return myFinder.connected(VTOP, getIndex(row, col));
    }

    // Method percolates checks to see if VTOP is connected to VBOTTOM using myFinder
    @Override
    public boolean percolates() {
        return myFinder.connected(VTOP, VBOTTOM);
    }

    // Method numberOfOpenSites simply returns the value of the appropriate instance variable
    @Override
    public int numberOfOpenSites() {
        return myOpenCount;
    }

    // Method open throws an Exception when needed
    // otherwise
    // checks to be sure the cell is not already open
    // then marks the cell as open and connects with open neighbors as described below

    @Override
    public void open(int row, int col){
        if (! inBounds(row, col)) {
            throw new IndexOutOfBoundsException(
                    String.format("(%d,%d) not in bounds", row,col));
        }

        //checks to be sure the cell is not already open (true)
        System.out.println(myGrid[row][col]);
        /*if (!myGrid[row][col])      // if not false (open) stop the call
            return;*/
        if (myGrid[row][col] == true)      // if already open (true) stop the call
            return;

        // otherwise the cell is closed so open it
        myOpenCount += 1;
        myGrid[row][col] = true;

        // then connect it with its open neighbors
        //If any of these cells is open, the newly marked cell and the open neighbor should be connected by a call to myFinder.union
        if(inBounds(row + 1, col) && isOpen(row+1, col)){myFinder.union(getIndex(row, col), getIndex(row + 1, col));}
        if(inBounds(row, col+1) && isOpen(row, col+1)){myFinder.union(getIndex(row, col), getIndex(row, col+1 ));}
        if(inBounds(row - 1, col) && isOpen(row-1, col)){myFinder.union(getIndex(row, col), getIndex(row-1, col));}
        if(inBounds(row, col-1) && isOpen(row, col-1)){myFinder.union(getIndex(row, col), getIndex(row, col-1));}

        // If it's in the top row it should be connected to VTOP by a call to myFinder.union.
        if(row == 0){myFinder.union(VTOP, getIndex(row,col));}
        // If it's in the bottom row it should be connected to VBOTTOM by a call to myFinder.union.
        if(row == myGrid.length-1){myFinder.union(VBOTTOM, getIndex(row, col));}

    }

    protected boolean inBounds(int row, int col) {
        if (row < 0 || row >= myGrid.length) return false;
        if (col < 0 || col >= myGrid[0].length) return false;
        return true;
    }

}
