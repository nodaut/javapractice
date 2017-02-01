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
Statement : 쿼리입력에 대한 object
=========================================================================================================
createStatement 디폴트 세팅
	Statement stmt =connection.createStatement()의 감추어진 디폴트 세팅은 다음과 같음
	Statement stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);
=========================================================================================================
ResultSet
	- 쿼리의 결과에 대한 object로
	- select결과에 대한 row set과 현재 가리키고 있는 row에 대한 포인터(cursor)를 가지고 있음
-----------------------------------------------------------------------
ResultSet에 대한 탐색(Navigating)에 대한 method
	boolean absolute(int index)	: 무조건 index인  row로 cursor를 옮김. 성공하면 true, 실패하면 false 리턴
	boolean first() : 결과의 첫번째 row로 cursor를 옮김. 성공하면 true, 실패하면 false 리턴
	void last() : 결과의 마지막 row로 cursor를 옮김
	boolean next() : 결과의 다음 row로 cursor를 옮김. 성공하면 true, 실패하면 false 리턴
	int getRow() : 현재 cursor가 가리키고 있는 row의 번호를 리턴
-----------------------------------------------------------------------	
ResultSet의 개별 row의 데이터 조회에 대한 method
	int getInt(String column) : 명시된 column에 해당하는 데이터 속성이 INT인경우 해당값을 int로 가져옴	
	String getString(String column) : 명시된 column의 데이터를 String으로 가져옴. 만약 해당 데이터가 DB 스키마에서 int, date등 다른 타입이라면 getString 결과를 형변환 하면됨
-----------------------------------------------------------------------	
ResultSet의 개별 row의 데이터 update에 대한 method	
	void updateString(String column, String newData) : 명시된 column에 해당하는 데이터를 newData로 업데이트함(단 이 메서드는 현재의 ResultSet의 데이터만 업데이트할 뿐, 실제 DB는 업데이트 하지 않음)
		=> 그냥 update는 executeQuery를 이용하는것 같음. resultset의 update는 그냥 이런게 있다는 정도만 알아 두자.
=========================================================================================================
Reference
	https://www.tutorialspoint.com/jdbc/jdbc-result-sets.htm
	https://www.tutorialspoint.com/jdbc/updating-result-sets.htm
	https://www.tutorialspoint.com/jdbc/viewing-result-sets.htm

*/

