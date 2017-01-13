package factoryPattern;

interface Shape {void draw();}
interface Color {void fill();}

class Rectangle implements Shape {
   @Override
   public void draw() {System.out.println("Inside Rectangle::draw() method.");}
}

class Square implements Shape {
   @Override
   public void draw() {System.out.println("Inside Square::draw() method.");}
}

class Red implements Color {
   @Override
   public void fill() {System.out.println("Inside Red::fill() method.");}
}

class Green implements Color {
   @Override
   public void fill() {System.out.println("Inside Green::fill() method.");}
}


abstract class AbstractFactory {
   abstract Color getColor(String color);
   abstract Shape getShape(String shape) ;
}

class ShapeFactory extends AbstractFactory {
   @Override
   public Shape getShape(String shapeType){
      if(shapeType.equalsIgnoreCase("Rectangle"))
         return new Rectangle();
      else if(shapeType.equalsIgnoreCase("Square"))
         return new Square();	         
      return null;
   }
   @Override
   Color getColor(String color) {
      return null;
   }
}

class ColorFactory extends AbstractFactory {
   @Override
   public Shape getShape(String shapeType){
      return null;
   }
   @Override
   Color getColor(String color) {
      if(color.equalsIgnoreCase("RED"))
         return new Red();
      else if(color.equalsIgnoreCase("GREEN"))
         return new Green();
      return null;
   }
}

class FactoryProducer {
   public static AbstractFactory getFactory(String choice){
      if(choice.equalsIgnoreCase("SHAPE"))
         return new ShapeFactory();
      else if(choice.equalsIgnoreCase("COLOR"))
         return new ColorFactory();
      return null;
   }
}


public class AbstractFactoryPatternDemo {

	public static void main(String[] args) {
      AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");

      Shape shape1 = shapeFactory.getShape("Rectangle");
      shape1.draw();
      Shape shape2 = shapeFactory.getShape("Square");
      shape2.draw();

      AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
      Color color1 = colorFactory.getColor("RED");
      color1.fill();
      Color color2 = colorFactory.getColor("Green");
      color2.fill();
	}
}
/*
Reference
	https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
*/
