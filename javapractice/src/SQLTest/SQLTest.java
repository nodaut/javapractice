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
			ResultSet rs = stmt.executeQuery("SELECT * FROM CITY LIMIT 3");
			
			while (rs.next()) {
				int ID = Integer.parseInt(rs.getString("ID"));
				String Name = rs.getString("Name");
				int Population = Integer.parseInt(rs.getString("Population"));
				System.out.println(ID);
				System.out.println(Name);
				System.out.println(Population);
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