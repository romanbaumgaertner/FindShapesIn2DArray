package application;

import java.util.ArrayList;
import java.util.List;

public class AppRunner {
	
	public static List<Shape> uniqueShapes = new ArrayList<Shape>();
	
	// looks for unique shapes in the Shape container
	//
	// 1. Normalize shapes
	// 2  Create Matrices from normalized shapes
	// 3  Find unique shapes by interating over normalized shapes  
	public int numberUniqueShapes(ShapeDetector shapeDetector) {
		boolean uniqueFound = false;
		
		AppRunner ar = new AppRunner();
		MatrixOps matrixOp = new MatrixOps();
		
		// we are adding first shape to list of unique shapes
		uniqueShapes.add(shapeDetector.getShapes().get(0));
		
		// start from 2n shape in list of detected shapes
		for( int i = 1; i < shapeDetector.getNumberOfShapes(); i++ ) {
			
			uniqueFound = false;
			
			for( Shape uniqueShape:ar.uniqueShapes) {
				
				Shape normalizedUniqueShape = matrixOp.normalize(uniqueShape);
				Shape normalizedShape = matrixOp.normalize( shapeDetector.getShapes().get(i) );
				
				int[][] matrixUnique = matrixOp.createMatrix(normalizedUniqueShape);
				
				// we get all possible rotation
				int[][] matrixShape = matrixOp.createMatrix(normalizedShape);
				int[][] matrixShapeRot1 = matrixOp.getRotatedMatrixList(matrixShape);
				int[][] matrixShapeRot2 = matrixOp.getRotatedMatrixList(matrixShapeRot1);
				int[][] matrixShapeRot3 = matrixOp.getRotatedMatrixList(matrixShapeRot2);
				
				//matrixOp.debugPrintMatrix(matrixShape);
				//matrixOp.debugPrintMatrix(matrixShapeRot1);
				//matrixOp.debugPrintMatrix(matrixShapeRot2);
				//matrixOp.debugPrintMatrix(matrixShapeRot3);
				
				// we are checking all the possible rotations
				// I guess I could have optimize this part by using a square matrix
				// and computing matrix properties like determinate, eigenvalue, ...
				if( matrixOp.compareMatrix(matrixUnique, matrixShape))
					// shape is not unique
					break;
				else if( matrixOp.compareMatrix(matrixUnique, matrixShapeRot1)) 
					break;
				else if( matrixOp.compareMatrix(matrixUnique, matrixShapeRot2)) 
					break;
				else if( matrixOp.compareMatrix(matrixUnique, matrixShapeRot3)) 
					break;
				
				uniqueFound = true;
				
			}
			
			// found q unique shape, lets add it to our solution 
			if(uniqueFound) {
				ar.uniqueShapes.add(shapeDetector.getShapes().get(i));
			}
		}
		
		return ar.uniqueShapes.size();
	}
	
	public static void main(String []argv) {
		
		ShapeDetector shapeDetector = new ShapeDetector();	
		AppRunner ar = new AppRunner();
		
		// finding all shapes in a 2d array
		shapeDetector.detectShapes(TestShapes.ts7);
		System.out.println("Found " + shapeDetector.getNumberOfShapes() + " shapes");
		
		if( shapeDetector.getNumberOfShapes() >0) {
			int numberUniqueShapes = ar.numberUniqueShapes(shapeDetector);
			System.out.println("Found " + numberUniqueShapes+ " unique shape(s)" );
		}
		
	}

}
