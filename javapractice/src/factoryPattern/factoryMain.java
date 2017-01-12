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

//---------------------------�⺻ factory pattern---------------------------
/*class ProductFactory {
    public static Product getProduct(String type, String name) {
        if ( "ticket".equals(type) ) return new Ticket(name);	//���� �����Ǵ� �ڽ� Ŭ���� Ticket�� ��ü�� �θ� Ŭ���� Ÿ�� 'Product'�� ���� �� ����
        else if ( "computer".equals(type) ) return new Computer(name);	//���� �����Ǵ� �ڽ� Ŭ���� Computer�� ��ü�� �θ� Ŭ���� Ÿ�� 'Product'�� ���� �� ����
        else return null;
    }
}*/
//--------------------------------------------------------------------------


//------------------abstract factory pattern (�߻� ���丮 ����)------------------
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
		//�⺻ factory pattern �׽�Ʈ
/*		System.out.println("---Basic factory pattern test start---");
		Product ticket = ProductFactory.getProduct("ticket", "Japan trip");
		Product computer = ProductFactory.getProduct("computer", "apple");
		System.out.println(ticket.toString());
		System.out.println(computer.toString());
		System.out.println("---Basic factory pattern test end---");*/
		
		Product com = ProductFactory.getProduct(new ComputerFactory("apple"));

		

	}
}



/* ���丮 class�� ���ڸ� �޾� ���ڿ� ���� ������ class�� ����� �ش�.
 * Reference
 * 	https://blog.seotory.com/post/2016/08/java-factory-pattern
 * 	https://blog.seotory.com/post/2016/08/java-abstract-factory-pattern
 */