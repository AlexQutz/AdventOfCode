
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {


        BufferedReader  br = new BufferedReader(new FileReader("./input.txt"));

        List<String> rows = new ArrayList<>();

        String line;
        while((line = br.readLine()) != null) {
            rows.add(line);
        }


        int numberOfRows = rows.size();
        int numberOfColumns = rows.get(0).length();

        int [][] forest = new int[numberOfRows][];

        int i = 0;
        for (String row : rows ) {
            forest[i] = new int[numberOfColumns];
            String[] rowArray = row.split("");
            int j = 0 ;
            for (String tree : rowArray) {
                forest [i][j] = Integer.parseInt(tree);
                j++;
            }
            i++;
        }

        int count = 0 ;
        for (int x = 0 ; x<numberOfColumns ; x++){
            for(int y = 0 ; y<numberOfRows ; y ++){
                count += isVisible(forest , x , y) ? 1:0;
            }
        }
        System.out.println(count);


        //-------------PART TWO------------------------

        int largestView = 0;
        for (int x = 0; x < numberOfRows; x++) {
            for (int y = 0; y < numberOfColumns; y++) {
                int View = getView(forest, x, y);
                if ( View > largestView) {
                    largestView = View;
                }
            }
        }

        System.out.println(largestView);
    }

    public static final int WEST = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int NORTH = 3;




    /**
     * Function that checks if the tree is visible.
     * @param forest
     * @param x
     * @param y
     * @return
     */
    public static boolean isVisible(int [][] forest, int x , int y) {

        if(tallest(forest, x-1 , y , EAST) < forest[y][x] ) {
            return true;
        }
        if(tallest(forest, x+1 , y , WEST) < forest[y][x] ) {
            return true;
        }
        if(tallest(forest, x  , y+1 , SOUTH) < forest[y][x] ) {
            return true;
        }
        if(tallest(forest, x , y-1 , NORTH) < forest[y][x] ) {
            return true;
        }
        return false;
    }

    /**
     * Function that checks for the tallest tree each direction.
     * @param forest
     * @param x
     * @param y
     * @param dir
     * @return
     */
    public static int tallest(int [][] forest, int x , int y , int dir) {
        switch (dir) {
            case EAST:
                if (x > -1) {
                    int val = tallest(forest, x-1, y, dir);
                    return Math.max(val, forest[y][x]);
                } else {
                    return -1;
                }
            case WEST:
                if (x < forest[0].length) {
                    int val = tallest(forest, x+1, y, dir);
                    return Math.max(val, forest[y][x]);
                } else {
                    return -1;
                }
            case NORTH:
                if (y > -1) {
                    int val = tallest(forest, x , y-1, dir);
                    return Math.max(val, forest[y][x]);
                } else {
                    return -1;
                }
            case SOUTH:
                if (y < forest.length) {
                    int val = tallest(forest, x, y+1, dir);
                    return Math.max(val, forest[y][x]);
                } else {
                    return -1;
                }
        }
        return 0;
    }


    //----------------PART TWO------------------------

    /**
     * Gets the view from specific tree.
     * @param forest
     * @param x
     * @param y
     * @return
     */

    private static int getView(int[][] forest, int x, int y) {
        int eastView = numOfTrees(forest, x - 1, y, forest[y][x], EAST);
        int westView = numOfTrees(forest, x + 1, y, forest[y][x], WEST);
        int southView = numOfTrees(forest, x, y + 1, forest[y][x], SOUTH);
        int northView = numOfTrees(forest, x, y - 1, forest[y][x], NORTH);

        return eastView * westView * northView * southView;
    }

    /**
     * Returns the number of trees seen in that direction
     * @param forest
     * @param x
     * @param y
     * @param val
     * @param dir
     * @return
     */
    public static int numOfTrees(int[][] forest, int x, int y, int val, int dir) {
        switch (dir) {
            case EAST:
                if (x > -1) {
                    if (val > forest[y][x]) {
                        return 1 + numOfTrees(forest, x - 1, y, val, dir);
                    }
                    return 1;
                }
                return 0;
            case WEST:
                if (x < forest[0].length) {
                    if (val > forest[y][x]) {
                        return 1 + numOfTrees(forest, x + 1, y, val, dir);
                    }
                    return 1;
                }
                return 0;
            case SOUTH:
                if (y < forest.length) {
                    if (val > forest[y][x]) {
                        return 1 + numOfTrees(forest, x, y + 1, val, dir);
                    }
                    return 1;
                }
                return 0;
            case NORTH:
                if (y > -1) {
                    if (val > forest[y][x]) {
                        return 1 + numOfTrees(forest, x, y - 1, val, dir);
                    }
                    return 1;
                }
                return 0;
        }
        return 0;
    }
}