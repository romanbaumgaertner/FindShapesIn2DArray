package application;

final  class TestShapes{
	
	final static int [][] ts0 = 
		{ {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};
	
	final static int [][] ts1 = 
			{ {1,1,1,0,0,0,0},
			  {1,1,1,0,0,0,0},
			  {0,0,0,0,0,0,0}
			};
	
	final static int [][] ts2 = 
		{ {1,0,0,0,0,0,0},
		  {1,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};
	
	final static int [][] ts3 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,0},
		  {0,0,0,0,0,0,0}
		};	
	
	// multiple shapes #2
	final static int [][] ts4 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,1},
		  {0,0,0,0,0,1,1}
		};		

	// multiple shapes #3, 2 unique shapes, flipped
	final static int [][] ts5 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,1},
		  {0,0,0,0,0,1,1},
		  {0,0,0,0,0,1,1},
		  {0,1,0,0,0,1,1},
		  {1,1,1,0,0,1,1}
		};	
	
	
	// multiple shapes #3, 1 unique shapes
	final static int [][] ts6 = 
		{ {0,1,1,1,0,0,0},
		  {0,0,1,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,1,1,0,0,0,0},
		  {0,1,0,0,0,0,0}
		};	
	
	
	// multiple shapes #3, 1 unique shapes
	final static int [][] ts7 = 
		{ {0,1,0,1,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {0,0,0,0,0,0,0},
		  {1,0,1,0,0,0,0},
		  {0,1,0,0,0,0,0}
		};	
	
	// multiple shapes #3, 1 unique shapes
	final static int [][] ts8 = 
		{ {0,1,0,1,0,0,1}
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,1,0,0,0,0},
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,0,0,0,0,0},
	//	  {0,0,0,0,0,0,0}
		};	
}