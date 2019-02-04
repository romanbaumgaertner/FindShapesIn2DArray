package com.shapedetector.application;

public final  class TestShapes{
	
	public final static int [][] ts0 = 
		{ {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};
	
	public final static int [][] ts1 = 
			{ {1,1,1,0,0,0,0},
			  {1,1,1,0,0,0,0},
			  {0,0,0,0,0,0,0}
			};
	
	public final static int [][] ts2 = 
		{ {1,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};
	
	public final static int [][] ts3 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};	
	
	// multiple shapes #2
	public final static int [][] ts4 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,1},
		  {0,0,0,0,0,1,1}
		};		

	// multiple shapes #3, 2 unique shapes, flipped
	public final static int [][] ts5 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,1},
		  {0,0,0,0,0,1,1},
		  {0,0,0,0,0,1,1},
		  {0,1,0,0,0,1,1},
		  {1,1,1,0,0,1,1}
		};	
	
	
	// multiple shapes #3, 1 unique shapes
	public final static int [][] ts6 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,1,1,0,0,0,0},
		  {0,1,0,0,0,0,0}
		};	
	
	
	// multiple shapes #3, 1 unique shapes
	public final static int [][] ts7 = 
		{ {0,1,0,1,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,0,1,0,0,0,0},
		  {0,1,0,0,0,0,0}
		};	
	
	// multiple shapes #3, 1 unique shapes
	public final static int [][] ts8 = 
		{ {0,1,0,1,0,0,1}
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,1,0,0,0,0},
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,0,0,0,0,0}
		};	
}