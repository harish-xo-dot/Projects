package busReserv;
import java.sql.*;
public class BusDAO {
	public void displayInfo() throws SQLException {
		
		String query="Select * From bus;";
		
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		System.out.println(" Bus-No |  AC  | Capacity ");
		System.out.println("--------------------------");
		
		while(rs.next()) {
			System.out.println("    "+ rs.getInt(1)+"    "
									 + (rs.getInt(2)==1?"  Ac     ":"Non-Ac   ")
									 + rs.getInt(3)	);
		}
	}

		public int getCapacity(int id) throws SQLException {
		// TODO Auto-generated method stub
		String query="Select Capacity From bus where BusNo=" +id;
			  
		Connection con=DbConnection.getConnection();
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		rs.next();
		return rs.getInt(1);
	}
}
