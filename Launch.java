package AreaOfGeometricShapes;
import java.util.*;


 abstract class Shape          		// Parent Class
{
	float area;
	public abstract void getInput();
	public abstract void computeArea();
	public abstract void displayResult();
}


 class Square extends Shape 		// Child of Parent and achieving Encapsuling along with Inheritance
{
	private float length;
	
	public void getInput() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter length of Square : ");
		length = scan.nextFloat();
	}
	
	public void computeArea() 
	{
		area = length*length;
	}
	
	public void displayResult()
	{
		System.out.println("Area Of Square is : "+ area);
		System.out.println("---------------------------");
	}		
}


 class Rectangle extends Shape  		// Achieving Inheritance and Encapsulation
{
	private float length;
	private float breadth;
	
	public void getInput() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter length of Rectangle : ");
		length = scan.nextFloat();
		System.out.print("Enter breadth of Rectangle : ");
		breadth = scan.nextFloat();
	}
	
	public void computeArea() 
	{
		area = length*breadth;
	}
	
	public void displayResult()
	{
		System.out.println("Area Of Rectangle is : "+ area);
		System.out.println("---------------------------");
	}		
}


 class Circle extends Shape				// Achieving Inheritance and Encapsulation
{
	private float radius;
	
	public void getInput() 
	{
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter Radius of Circle : ");
		radius = scan.nextFloat();
	}
	
	public void computeArea() 
	{
		area = 22/7 * radius * radius ;
	}
	
	public void displayResult()
	{
		System.out.println("Area Of Circle is : "+ area);
		System.out.println("---------------------------");
	}		
}


 class Geomatry								// for achieving  Polymorphism
{
	public void permit(Shape ref)			// getting reference of classes and achieving Polymorphism
	{
		ref.getInput();
		ref.computeArea();
		ref.displayResult();
	}
}
public class Launch							// Final Launch class with main method
{
	public static void main(String[] args)
	{
		Square s = new Square();			// creation of object and references
		Rectangle r = new Rectangle();
		Circle c = new Circle();
		
		Geomatry g = new Geomatry();		
		
		g.permit(s);
		g.permit(r);
		g.permit(c);
		
	}
}
