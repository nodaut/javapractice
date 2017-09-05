package derbyDBTest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class DerbyDBTest {
	public static void main(String[] args) {
		try{
			Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
			//for server mode
			
			//derby db server mode..			
			Connection connect = DriverManager.getConnection("jdbc:derby://localhost/D:/derbyTest/db2");
			PreparedStatement statement = connect.prepareStatement("SELECT * from testDB");
			ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()){
				String num = resultSet.getString("num");
				String addr = resultSet.getString("addr");
				System.out.println("num: " + num);
				System.out.println("ID: " + addr);
			}		
		}catch(Exception e){
			System.out.println(e);
		}
	}
}