
import java.sql.*;
import java.util.*;
public class mydb {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		
		batchAndCommit();
		
	}
	public static void batchAndCommit() throws SQLException {
		Scanner in=new Scanner(System.in);
		
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		System.out.println("Enter the GPA :");
		Float GPA=in.nextFloat();
		
		String query1="Update students set GPA="+GPA+" Where id=1";
		String query2="Update students set GPA="+GPA+" Where id=2";
		String query3="Update students set GPA="+GPA+" Where id=3";
		String query4="Update students set GPA="+GPA+" Where id=4";
		String query5="Updat students set GPA="+GPA+" Where id=5";	//Making Error by myself For
																	//Checking the commit , Rollback.
		
		
		Connection con=DriverManager.getConnection(url,username,password);
		Statement st=con.createStatement();
		con.setAutoCommit(false);
		st.addBatch(query1);
		st.addBatch(query2);
		st.addBatch(query3);
		st.addBatch(query4);
		st.addBatch(query5);
		
		int[] res=st.executeBatch();
		for(int i : res) {
			if(i > 0) {
				continue;
			}
			else {
				con.rollback();
			}
		}
		con.commit();
		con.close();
	}
	public static void spInOut() throws SQLException {
		Scanner in=new Scanner(System.in);
		
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		System.out.println("Enter the Row :");
		int id=in.nextInt();
		
		Connection con=DriverManager.getConnection(url,username,password);
		CallableStatement cst=con.prepareCall("{call getMyName(?,?)}");
		cst.setInt(1, id);
		cst.registerOutParameter(2, Types.VARCHAR);
		cst.executeUpdate();
		
		System.out.println("Given Id Name is : "+cst.getString(2));
	}
	public static void spIn() throws SQLException {
		Scanner in=new Scanner(System.in);
		
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		System.out.println("Enter the Row :");
		int id=in.nextInt();
		
		Connection con=DriverManager.getConnection(url,username,password);
		CallableStatement cst=con.prepareCall("{call getMyRow(?)}");
		cst.setInt(1,id);
		ResultSet rs=cst.executeQuery();
		if(rs.next()) {
			System.out.println(
					 "Id : "+rs.getInt(1)
					+" , Name : "+rs.getString(2)
					+" , GPA :"+rs.getFloat(3)
			);
		}
	}
	public static void sp() throws SQLException {
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		Connection con=DriverManager.getConnection(url,username,password);
		
		CallableStatement cst=con.prepareCall("{Call get_Names()}");
		ResultSet rs=cst.executeQuery();
		System.out.println("Id"+" Name");
		while(rs.next()){
			System.out.println(rs.getInt(1)+"  "+rs.getString(2));
		}
	}
	public static void updateRecord() throws SQLException {
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		Connection con=DriverManager.getConnection(url,username,password);
		
		Scanner in=new Scanner(System.in);
		System.out.println("Enter Which Row to be updated : ");
		int id=in.nextInt();
		System.out.println("Which one do you want to be updated : \n 1)Name \n 2)GPA \n 3)Both Name and GPA");
		int opt=in.nextInt();
		in.nextLine();
		String query="";
		if(opt==1) {
			System.out.println("Enter New Name: ");
			String newName=in.nextLine();
			query="Update students Set Name= '"+newName+"' Where Id="+id;
		}
		else if(opt==2){
			System.out.println("Enter New GPA: ");
			float newGpa=in.nextFloat();
			query="Update students Set GPA= "+newGpa+" Where Id="+id;
		}
		else if(opt==3) {
			System.out.println("Enter New GPA: ");
			float newGpa=in.nextFloat();
			
			in.nextLine();
			
			System.out.println("Enter New Name: ");
			String newName=in.nextLine();
			 
			query="Update students Set Name= '" + newName + "',"+ " GPA=" +newGpa+ " Where Id=" +id;
		}
		else {
			System.out.println("Invalid Input");
			return;
		}
		Statement st=con.createStatement();
		st.executeUpdate(query);
		
		con.close();
	}
	public static void deleteRecord() throws SQLException {
		String url="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		Connection con=DriverManager.getConnection(url,username,password);
		
		Scanner in=new Scanner(System.in);
		System.out.println("Enter the row number to be deleted : ");
		int id=in.nextInt();
		
		String query="Delete From students where Id="+id;
		
		Statement st=con.createStatement();
		st.executeUpdate(query);
		
		String query2="Alter table students Auto_increment="+id;
		st.executeUpdate(query2);
		System.out.println("Row No : "+id+" is Deleted");
		
		con.close();
	}
	public static void insertRecords() throws SQLException {
		String url ="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		Connection con=DriverManager.getConnection(url, username, password);
		
		Scanner in =new Scanner(System.in);
		System.out.println("Enter the Name");
		String name=in.nextLine();
		
		System.out.println("Enter GPA");
		float gpa= in.nextFloat();
		
	/*	
	 	One method to Insert
	 	--------------------
	 	
	 	String query="Insert Into students(Name,GPA) Values ('" +name+ "'," +gpa+ ");";
		Statement st=con.createStatement();
	*/	
		
	// Another Method to insert
	// ------------------------
		
		String query="Insert Into students(Name,GPA) Values(?,?);";
		PreparedStatement pst=con.prepareStatement(query);
		pst.setString(1,name);
		pst.setFloat(2,gpa);
		
		int rows=pst.executeUpdate();
		System.out.println("No of Rows Inserted : "+rows);
			
		con.close();
	}
	public static void readRecords() throws SQLException {
		String url ="jdbc:mysql://127.0.0.1:3306/jdbcdemo";
		String username="root";
		String password="Muthu@2003";
		
		Connection con=DriverManager.getConnection(url, username, password);
		
		String query="Select * from students";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(query);
		
		System.out.println("Id"+"     Name    "+"    GPA");
		while( rs.next() ){
			System.out.println(rs.getInt(1)+"   "+rs.getString(2)+" "+rs.getFloat(3));
		}
		
		con.close();
	}

}
