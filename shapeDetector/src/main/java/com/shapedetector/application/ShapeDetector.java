package com.shapedetector.application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;


// class which implements the algorithm to detect
// multiple shapes (marked with 1s) in a 2d array
public class ShapeDetector {
		
	// used to mark the visited fields
	HashSet<Point> markHs = new HashSet<Point>();
	// container for found shapes
	Vector<Shape> shapes = new Vector<Shape>();
	
	// checks whether coord is already marked during shape detection
	boolean isMarked(Point coord) {
		boolean isMarked = false;
		
		if(markHs.contains(coord))
			isMarked = true;
		
		return isMarked;
	}
	
	// marks a field to be visited
	void mark(Point coord) {
		markHs.add(coord);
	}
	
	// add a shape to the shape container
	void addShape(Shape s) {
		shapes.add(s);
	}
	
	public Vector<Shape> getShapes() {
		return shapes;
	}
	
	// debug print
	public void debugPrint() {
		StringBuilder sB = new StringBuilder();
		
		for (Shape s: shapes)
			System.out.println(s);
	}
	
	// checks whether the point is still in the boundary of the matrix
	public boolean isValidField(Point c, int columns, int rows) {
		boolean isValidX = true;
		boolean isValidY = true;
		
		isValidX = (c.getX() > columns-1 || c.getX() <0) ? false:true;
		isValidY = (c.getY() > rows-1 || c.getY() <0) ? false:true;
		
		return isValidX && isValidY;
	}
	
	// shape detection algorithm
	public void runDetection(int [][] arr, Shape s, int x, int y) {
		
		int rows = arr.length;
		int columns = arr[0].length;
		
		Point coord = new Point(x,y);
		
		// if already marked, we don't care anymore
		if(!isMarked(coord)) {
	
			this.mark(coord);
			// field is part of a shape
			if( arr[y][x] == 1) {
				// adding field to shape
				s.add(coord);	
			
				// when we have found a field which is painted (filled with 1) we
				// recursively check left, right and next row
				Point nextFieldRightCoordX = new Point(x+1,y);
				if( this.isValidField(nextFieldRightCoordX, columns, rows))
					if( !isMarked(nextFieldRightCoordX) )
						runDetection(arr, s,x+1, y );
				
				// go to the left (--> this was the case I initially missed in the interview :-) )
				Point nextFieldLeftCoordX = new Point(x-1,y);
				if( this.isValidField(nextFieldLeftCoordX, columns, rows))
					if( !isMarked(nextFieldLeftCoordX) )
						runDetection(arr, s,x-1, y );
				
				// go from current position one row down
				Point nextFieldCoordY = new Point(x,y+1);
				if( this.isValidField(nextFieldCoordY, columns, rows))
					if( !isMarked(nextFieldCoordY) )
						runDetection(arr, s,x, y+1 );	
			}
		}
	}
	
	// returns a shape of the form ([x1,y1], ... [xn,yx])
	public Vector<Shape> detectShapes(int [][] arr){
		Shape shape = new Shape();
		int rows = arr.length;
		int columns = arr[0].length;
		
		int x = 0;
		// looping through all the rows
		// after a loop is completed we know that for a specific row 
		// we found a shape or not (empty shape). If not empy we add
		// the found shape as solution to our Shape Vector
		for(int i = 0; i<rows; i++) {
			x = 0; 
			while( x < columns) {
				runDetection(arr,shape,x,i);
			
				// need to check for emptiness before adding
				if( !shape.isEmpty())
					shapes.add(shape);
			
				// empty for the next shape
				shape = new Shape();
				x++;
			}
			
		}
		
		return shapes;
	}
	
	// returns the elements in the found Shape vector
	public int getNumberOfShapes() {
		return shapes.size();
	}
	
	public static void main(String [] argv) {
		ShapeDetector shapeDetector = new ShapeDetector();
		shapeDetector.detectShapes(TestShapes.ts8);
		
		System.out.println(shapeDetector.getNumberOfShapes() );
		System.out.println(shapeDetector.getShapes().get(0) );
		System.out.println(shapeDetector.getShapes().get(1) );
		System.out.println(shapeDetector.getShapes().get(2) );
		
		//shapeDetector.debugPrint();
		//shapeDetector.isMarkedTest();
		//shapeDetector.findShapeTest1();
		//shapeDetector.findShapeValidField();
	}
	
	
	//test isMarked
	public void isMarkedTest() {
		Point c1 = new Point(0,0);
		Point c2 = new Point(0,1);
		
		markHs.add(c1);
		
		System.out.println("Correct - isMarked " + this.isMarked(c1) );
		System.out.println("Not Coorect - isMarked " + this.isMarked(c2));
	}
	
	public void findShapeTest1() {
		ShapeDetector shapeDetector = new ShapeDetector();
		
		List<Shape> shapes = shapeDetector.detectShapes(TestShapes.ts2);
		System.out.println(shapes);
		
	}
	
	public void findShapeValidField() {
		ShapeDetector shapeDetector = new ShapeDetector();
		
		Point c1 = new Point(0,0);
		Point c2 = new Point(-1,1);  //negative X
		Point c3 = new Point(0,5);   //out of bound
		Point c4 = new Point(0,-1);   //negative Y
		
		System.out.println( shapeDetector.isValidField(c1, 4, 4) );
		System.out.println( shapeDetector.isValidField(c2, 4, 4) );
		System.out.println( shapeDetector.isValidField(c3, 4, 4) );
		System.out.println( shapeDetector.isValidField(c4, 4, 4) );
		
	}
	
}
	

