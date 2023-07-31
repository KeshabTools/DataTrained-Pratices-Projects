package Com.test;

public class LatticePointsOnCircumference {

    // Function to count Lattice points on a circle
    static int countLattice(int r) {
        if (r <= 0)
            return 0;

        // Initialize result as 4 for (r, 0), (-r. 0),
        // (0, r) and (0, -r)
        int result = 4;
    	for (int x=1; x<r; x++)
    	{
    		int ySquare = r*r - x*x;
    		int y = (int)Math.sqrt(ySquare);

    		
    		if (y*y == ySquare)
    			result += 4;
    	}

    	return result;
    }


    public static void main(String arg[])
    {
    	int r = 88;
    	System.out.println(countLattice(r));
    	System.out.println("hii.......................");
    }
}
