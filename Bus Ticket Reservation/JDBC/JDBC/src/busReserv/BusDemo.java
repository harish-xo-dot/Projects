package busReserv;

import java.util.*;
import java.sql.*;
public class BusDemo {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		Connection con=DbConnection.getConnection();
		
		//For Fetching Bus Details.
		BusDAO busDAO =new BusDAO();
		busDAO.displayInfo();
		
		int userInput=1;
		Scanner in=new Scanner(System.in);
		while(userInput==1) {
			
			System.out.println("Enter 1 to Book and 2 to exit");
			userInput=in.nextInt();
			
			if(userInput==1) {
				Booking booking=new Booking();
				
				if(booking.isAvailable()) {
					BookingDAO bookingDAO = new BookingDAO();
					bookingDAO.addBooking(booking);
					System.out.println("Your Booking is Confirmed");
					bookingDAO.getPassengersList();
				}
				else {
					System.out.println("Sorry! Bus is Full. Try another Date or Bus");
				}
			}
		}
	}

}
