package com.shapedetector.application;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;

public class Shape{
	// ([0,0,][0,1] ...
	List<Point> shape;

	
	HashSet<Integer> x = new HashSet<Integer>();
	HashSet<Integer> y = new HashSet<Integer>();
	
	Shape(){
		shape = new ArrayList<Point>();
	}

	public List<Point> getShape() {
		return shape;
	}
	
	public int getDimX() {
		return x.size();
	}
	
	public int getDimY() {
		return y.size();
	}

	public void setCoord(Vector<Point> shape) {
		this.shape = shape;
	}
	
	public Shape add(Point c) {
		this.shape.add(c);	
		
		x.add( (int)c.getX());
		y.add( (int)c.getY());
		
		return this;
	}
	
	public boolean isEmpty() {
		return this.shape.size() == 0? true:false;
	}
	
	public int size() {
		return this.shape.size();
	}
	
	public String toString() {
		StringBuilder sB =new StringBuilder();
		int l = shape.size();
		sB.append("( ");
		for(int i=0; i< l; i++) {
			
			if( i == l-1)
				sB.append( shape.get(i).toString()+" ");
			else
				sB.append( shape.get(i).toString()+" , ");
		}
		sB.append(" ) ");
		
		return sB.toString();
	}
	
	
	public static void main(String [] argv) {
		
		Point c1 = new Point(0,0);
		Point c2 = new Point(0,1);
		
		Shape s1 = new Shape().add(c1).add(c2);
		System.out.println(s1);
		
		// empty test
		System.out.println(s1.isEmpty());
		Shape s2 = new Shape();
		System.out.println(s2.isEmpty());
	}
}