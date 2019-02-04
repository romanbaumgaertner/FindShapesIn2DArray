# FindShapesIn2DArray

The project consists of a package called application which contains the following classes

+ AppRunner -  executable which contains the main 
+ ShapeDetector - class to find shapes in a 2d array
+ MatrixOps - class which does some data transformation (normalization, matrix creation and rotation)
+ Shape - defines the Shape, used from ShapeDetector
+ TestShapes - tests used during testing


# Build the project 

+ cd shapeDetector
+ gradle assemble

# Run the project 

+ java -jar build/libs/shapeDetector.jar 
