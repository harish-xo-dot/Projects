package busReserv;
import java.util.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;  
public class Booking {

	String PassengerName;
	int BusNo;
	Date date;
	Booking(){
		Scanner in=new Scanner(System.in);

		System.out.println("Enter Passenger Name:");
		PassengerName=in.nextLine();
		
		System.out.println("Enter BusNo");
		BusNo=in.nextInt();
		
		System.out.println("Enter Date dd/mm/yyyy");
		String DateInp=in.next();
		SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");  
		
		try {
			date=dateFormat.parse(DateInp);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public boolean isAvailable() throws SQLException {
		BusDAO busDAO = new BusDAO();
		int Capacity=busDAO.getCapacity(BusNo);
		
		BookingDAO bookingDAO = new BookingDAO();
		int AlreadyBooked=bookingDAO.getBookedCount(BusNo,date);
		
		
		return AlreadyBooked < Capacity;
	}
}
