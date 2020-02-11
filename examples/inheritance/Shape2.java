

abstract class Shape { 
    protected int x, y; 
    protected String color; 

    public Shape(int initX, int initY, String initColor) { 
          x = initX;
          y = initY;
          color = initColor;
      } 
      
   public abstract void draw(); 
   
   public void moveTo(int newX, int newY) {  
      x = newX;  
      y = newY; 
   } 
}
 
class Circle extends Shape { 
    protected int radius; 

    public Circle(int initX, int initY, String initColor, int initRadius) { 
      super(initX, initY, initColor) ;
      radius = initRadius;
    }
      
   @Override
   public void draw() { 
      System.out.println("Drawing a circle!"); 
   } 
}
 
