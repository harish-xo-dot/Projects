package busReserv;
import java.sql.*;
import java.util.Date;
public class BookingDAO {

	public int getBookedCount(int busNo, Date date) throws SQLException {
		
		String query="Select Count(PassengerName) From bookings where BusNo=? and Travel_date=?";
		
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, busNo);
		java.sql.Date sqlDate=new java.sql.Date(date.getTime());
		pst.setDate(2, sqlDate);
		ResultSet rs=pst.executeQuery();
		rs.next();
		return rs.getInt(1);
	}

	public void addBooking(Booking booking) throws SQLException {
		// TODO Auto-generated method stub
		String query="Insert Into bookings values(?,?,?)";
		Connection con=DbConnection.getConnection();
		PreparedStatement pst=con.prepareStatement(query);
		pst.setInt(1, booking.BusNo);
		pst.setString(2,booking.PassengerName);
		java.sql.Date sqlDate=new java.sql.Date(booking.date.getTime());
		pst.setDate(3,sqlDate);
		pst.executeUpdate();
	}

	public void getPassengersList() throws SQLException {
		// TODO Auto-generated method stub
		String query = "Select * From bookings";
		Connection con = DbConnection.getConnection();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		System.out.println("BusNo | PassengerName | Travel Date");
		while(rs.next()) {
		System.out.println("  "+rs.getInt(1)+"   |  "+rs.getString(2)+"  |  "+rs.getDate(3));
		}
		
	}
	
	
}
