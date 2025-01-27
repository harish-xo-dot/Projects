package busReserv;
import java.sql.*;
public class DbConnection {
	
	private static final String url="jdbc:mysql://127.0.0.1:3306/busreserv";
	private static final String username="root";
	private static final String password="Muthu@2003";

	public static Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub
		return DriverManager.getConnection(url,username,password);
	}
}
