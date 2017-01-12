package factoryPattern;

abstract class Product {
    public abstract String getName();

    @Override
    public String toString() {return "product name : " + getName();}
}

class Computer extends Product {
    private String name;
    public Computer (String name) {this.name = name;}

    @Override
    public String getName() {return "[child]"+this.name;}
}

class Ticket extends Product {
    private String name;
    public Ticket (String name) {this.name = name;}

    @Override
    public String getName() {return "[child]"+this.name;}
}

//---------------------------기본 factory pattern---------------------------
/*class ProductFactory {
    public static Product getProduct(String type, String name) {
        if ( "ticket".equals(type) ) return new Ticket(name);	//새로 생성되는 자식 클래스 Ticket의 객체는 부모 클래스 타입 'Product'로 받을 수 있음
        else if ( "computer".equals(type) ) return new Computer(name);	//새로 생성되는 자식 클래스 Computer의 객체는 부모 클래스 타입 'Product'로 받을 수 있음
        else return null;
    }
}*/
//--------------------------------------------------------------------------


//------------------abstract factory pattern (추상 팩토리 패턴)------------------
interface ProductAbstractFactory {public Product createProduct();}

class ComputerFactory implements ProductAbstractFactory {
    private String name;
    public ComputerFactory (String name) {this.name = name;}
    public Product createProduct() {return new Computer(name);}
}

class ProductFactory {
    public static Product getProduct(ProductAbstractFactory product) {
        return product.createProduct();
    }
}

//--------------------------------------------------------------------------

public class factoryMain {
	public static void main(String[] args) {
		//기본 factory pattern 테스트
/*		System.out.println("---Basic factory pattern test start---");
		Product ticket = ProductFactory.getProduct("ticket", "Japan trip");
		Product computer = ProductFactory.getProduct("computer", "apple");
		System.out.println(ticket.toString());
		System.out.println(computer.toString());
		System.out.println("---Basic factory pattern test end---");*/
		
		Product com = ProductFactory.getProduct(new ComputerFactory("apple"));

		

	}
}



/* 팩토리 class는 인자를 받아 인자에 대한 적합한 class를 만들어 준다.
 * Reference
 * 	https://blog.seotory.com/post/2016/08/java-factory-pattern
 * 	https://blog.seotory.com/post/2016/08/java-abstract-factory-pattern
 */