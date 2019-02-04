package com.shapedetector.application;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;

class TestData{
	static Shape s1;
	static Shape s2;
	static Shape s3;
	static Shape s4;
	static Shape s5;

	static {
		s1 = new Shape();
		s1.add( new Point(3,0) );
		s1.add( new Point(0,0) );
		s1.add( new Point(1,0) );
		s1.add( new Point(2,0) );
	}

	static {
		s2 = new Shape();

		s2.add( new Point(1,0) );
		s2.add( new Point(2,0) );
	}

	static {
		s3 = new Shape();

		s3.add( new Point(1,3) );
		s3.add( new Point(2,3) );
		s3.add( new Point(1,2) );
	}

	static {
		s4 = new Shape();

		s4.add( new Point(3,3) );
		s4.add( new Point(4,3) );
		s4.add( new Point(5,3) );
		s4.add( new Point(3,4) );
		s4.add( new Point(4,4) );
		s4.add( new Point(5,4) );
	}
	
	static {
		s5 = new Shape();

		s5.add( new Point(3,3) );
		s5.add( new Point(4,3) );
		s5.add( new Point(5,3) );
		s5.add( new Point(3,4) );
		s5.add( new Point(4,4) );
	}

	final static int [][] tm0 =
		{{0}};

	final static int [][] tm1 = 
		{ {1,1,1},
		  {1,1,1},
		};


	final static int [][] tm2 = 
		{ {1,0,0},
		  {1,1,1},
		};

	final static int [][] tm3 = 
		{ {0,0,1},
		  {1,1,1},
		};

	final static int [][] tm4 = 
		{ {1,0},
		  {1,0},
		  {1,1}
		};
			
}

/*
Example

3 3 0
-----
1 1 0| 2  
1 1 0| 2
1 1 0| 2

--> result X = [3,3,0], Y=[2,2,2]

2 2 2
-----
1 1 1| 3  
1 1 1| 3
0 0 0| 0

--> result X = [2,2,2], Y=[3,3,0]

To check whether to shapes are similar the following has to be true

1.


*/

class Matrix{
	int [][] matrix;
	int [] X; // sum of 1s for columns
	int [] Y; // sum of 1s for rows
}

public class MatrixOps {

	static final Point P_ORIGIN = new Point(0,0);

	public int matrix;

	// normalizes the coordinates of points that the shape's 
	// can be found on the top left

	/* --> input 
	   0 0 1 0 
	   0 1 1 1
	   [ (0,2) (1,1) (1,2) (1,3) ]

	   --> result

	   0 1 0 
	   1 1 1 
	   [ (0,1) (1,0) (1,1) (1,2) ]
	 */

	// from the normalized shape we can create a squared matrix by iterating through the shape 
	// and at the points to the 2s matrix array

	// for the detection of unique shapes we can validate that

	public Shape sortX(Shape s) {
		Collections.sort(s.getShape(), new Comparator<Point>() {

			public int compare(Point o1, Point o2) {
				return Double.compare(o1.getX(), o2.getX());
			}
		});

		return s;
	}

	public Shape sortY(Shape s) {
		Collections.sort(s.getShape(), new Comparator<Point>() {

			public int compare(Point o1, Point o2) {
				return Double.compare(o1.getY(), o2.getY());
			}
		});

		return s;
	}

	// moves the shape in direction of the origin 
	// we make sure that at least from the points an X and Y is moved to 0
	// Note: after the move operation the element p(0,0) has not to be filled!
	public Shape normalize(Shape shape) {

		sortX(shape);
		Point firstPointX = shape.getShape().get(0);
		sortY(shape);
		Point firstPointY = shape.getShape().get(0);

		// delta for origin for x, y
		int deltaX = (int) (firstPointX.getX() - P_ORIGIN.getX() );
		int deltaY = (int) (firstPointY.getY() - P_ORIGIN.getY() );

		// move operation
		for(int i = 0; i < shape.size(); i++ ) {
			Point p =shape.getShape().get(i);
			int x_new = (int)p.getX()-deltaX;
			int y_new = (int)p.getY()-deltaY;

			Point newP = new Point(x_new, y_new);
			shape.getShape().set(i, newP);
		}

		return shape;
	}

	// Returns a square matrix
	//
	// we create from the given shape a square matrix 
	// we determine the dimensions of the shape and 
	// adding missing row/columns to have a squared matrix shape
	public int[][] createMatrix(Shape shape){

		sortX(shape);
		Point firstPointX = shape.getShape().get(0);
		sortY(shape);
		Point firstPointY = shape.getShape().get(0);

		int dimX = shape.getDimX();
		int dimY = shape.getDimY();

		// creating square matrix
		int maxDim = Math.max(dimX, dimY);
		int [][] m = new int [dimY][dimX];
		
		for(int i=0; i < shape.size(); i++) {
			m[ (int)shape.getShape().get(i).getY()][ (int) shape.getShape().get(i).getX()] = 1;		
		}
		
		return m;
	}

	// rotate the matrix by 90
	public int[][] getRotatedMatrixList(int [][] matrix){

		int dimY = matrix[0].length; 
		int dimX = matrix.length; 

		int[][] rotatedMatrix = new int[dimY][dimX];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				rotatedMatrix[j][ (dimX-1)- i] = matrix[i][j]; 
			}
		}
		return rotatedMatrix;
	}
	
	// compares two matrices whether they are the same
	public boolean compareMatrix(int [][] m1, int[][] m2) {
		boolean similar= true;
		
		if( m1.length == m2.length && m1[0].length == m2[0].length) {
			
			for(int i=0; i< m1.length; i++) {
				for(int j = 0; j < m1[0].length; j++) {
					if( m1[i][j] != m2[i][j])
						return false;
				}
			}
		} else {
			return false;
		}
				
		return similar;
	}


	public boolean isShapeUnique(int[][] matrix, Vector<Integer[][]> v) {

		if( !v.isEmpty()) {

			// all matrices in the vector are from the same
			// dimension. We only need to check the fist one
			if(v.get(0).length != matrix.length) {
				//dimension is not the same
				return false;
			}

			// compare matrix with each other
		}

		return true;
	}
	
	// Function to print the matrix 
	void debugPrintMatrix(int mat[][]) { 

		// we know matrix is square, that's how we created it
		int dimY = mat.length;
		int dimX = mat[0].length;

		for (int i = 0; i < dimY; i++) 
		{ 
			for (int j = 0; j < dimX; j++) 
				System.out.print(" " + mat[i][j]); 

			System.out.println(""); 
		} 
		System.out.println(""); 
	} 


	public static void main(String [] argv) {
		MatrixOps mo = new MatrixOps();

		Shape s4= mo.normalize(TestData.s4);
		System.out.println("S4");
		int [][] m4 = mo.createMatrix(s4);
		mo.debugPrintMatrix(m4);
		int [][] newMatrix = mo.getRotatedMatrixList(m4);
		newMatrix = mo.getRotatedMatrixList(newMatrix);
		
		
		Shape s5= mo.normalize(TestData.s5);
		System.out.println("S5");
		int [][] m5 = mo.createMatrix(s5);
		mo.debugPrintMatrix(m5);
		
		System.out.println(mo.compareMatrix(m4, m5) );

		mo.getRotatedMatrixList(m4);
		//Integer [][] rotated = mo.rotateMatrixBy90DegreeClockwise(m4);
		//mo.debugPrintMatrix(rotated);

		Shape s3= mo.normalize(TestData.s3);
		System.out.println("S3");
		int [][] m3 = mo.createMatrix(s3);
		mo.debugPrintMatrix(m3);

		System.out.println(mo.compareMatrix(m4, m3) );

		Shape s1= mo.normalize(TestData.s1);
		int [][] m1 = mo.createMatrix(s1);
		System.out.println("S1");
		mo.debugPrintMatrix(m1);


		Shape s2= mo.normalize(TestData.s2);
		System.out.println("S2");
		int [][] m2 = mo.createMatrix(s2);
		mo.debugPrintMatrix(m2);

		//System.out.println( mo.normalize(TestData.s1).toString() );
		//System.out.println( mo.normalize(TestData.s2).toString() );
		//System.out.println( mo.normalize(TestData.s3).toString() );
		//System.out.println( mo.normalize(TestData.s4).toString() );
	}

}
