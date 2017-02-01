package SQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Do not import com.mysql.jdbc.*

public class SQLTest 
{
	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/world";
		String user = "root";
		String pass = "1111";
		
		try (Connection connection = DriverManager.getConnection(url, user, pass)){
			System.out.println("Database connected!");
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM CITY WHERE ID>3 LIMIT 3");
			
			while (rs.next()) {
				//int ID = Integer.parseInt(rs.getString("ID"));
				int ID = rs.getInt("ID");
				
				String Name = rs.getString("Name");
				
				//int Population = Integer.parseInt(rs.getString("Population"));
				int Population = rs.getInt("Population");
				rs.updateRow();
				
				System.out.println(ID);
				System.out.println(Name);
				System.out.println(Population);
				System.out.println("Current index : "+rs.getRow());
				System.out.println("----------");
			}
		}catch (SQLException e) {
			System.out.println("SQLException");
			System.out.println(e);
		}catch (Exception e){
			System.out.println("Other exception");
			System.out.println(e);
		}
	}
}

/*
Statement : �����Է¿� ���� object
=========================================================================================================
createStatement ����Ʈ ����
	Statement stmt =connection.createStatement()�� ���߾��� ����Ʈ ������ ������ ����
	Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
=========================================================================================================
ResultSet
	- ������ ����� ���� object��
	- select����� ���� row set�� ���� ����Ű�� �ִ� row�� ���� ������(cursor)�� ������ ����
-----------------------------------------------------------------------
ResultSet�� ���� Ž��(Navigating)�� ���� method
	boolean absolute(int index)	: ������ index��  row�� cursor�� �ű�. �����ϸ� true, �����ϸ� false ����
	boolean first() : ����� ù��° row�� cursor�� �ű�. �����ϸ� true, �����ϸ� false ����
	void last() : ����� ������ row�� cursor�� �ű�
	boolean next() : ����� ���� row�� cursor�� �ű�. �����ϸ� true, �����ϸ� false ����
	int getRow() : ���� cursor�� ����Ű�� �ִ� row�� ��ȣ�� ����
-----------------------------------------------------------------------	
ResultSet�� ���� row�� ������ ��ȸ�� ���� method
	int getInt(String column) : ��õ� column�� �ش��ϴ� ������ �Ӽ��� INT�ΰ�� �ش簪�� int�� ������	
	String getString(String column) : ��õ� column�� �����͸� String���� ������. ���� �ش� �����Ͱ� DB ��Ű������ int, date�� �ٸ� Ÿ���̶�� getString ����� ����ȯ �ϸ��
-----------------------------------------------------------------------	
ResultSet�� ���� row�� ������ update�� ���� method	
	void updateString(String column, String newData) : ��õ� column�� �ش��ϴ� �����͸� newData�� ������Ʈ��(�� �� �޼���� ������ ResultSet�� �����͸� ������Ʈ�� ��, ���� DB�� ������Ʈ ���� ����)
		=> �׳� update�� executeQuery�� �̿��ϴ°� ����. resultset�� update�� �׳� �̷��� �ִٴ� ������ �˾� ����.
=========================================================================================================
Reference
	https://www.tutorialspoint.com/jdbc/jdbc-result-sets.htm
	https://www.tutorialspoint.com/jdbc/updating-result-sets.htm
	https://www.tutorialspoint.com/jdbc/viewing-result-sets.htm

*/

