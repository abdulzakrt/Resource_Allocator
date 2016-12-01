import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.Scanner;

public class Resource_Management_System {
	private Resource[] Resources;
	private User[] Users;
	private Admin[] Admins;
	private Reservation[] Reservations;
	private int resourcecount,usercount,admincount,reservationcount;
	public Resource_Management_System() {
		Resources=new Resource[100];
		Users=new User[100];
		Admins=new Admin[100];
		Reservations=new Reservation[100];
		resourcecount=usercount=admincount=reservationcount=0;
		
	}
	public void add_resource_to_array(Resource temp){
		Resources[resourcecount]=temp;
		resourcecount++;
		
	}
	public void Display_resource(){
		for(int i=0;i<resourcecount;i++){
			System.out.println(Resources[i].getID());
			
		}
	}
	public void Display_user(){
		System.out.println("Users:");
		for(int i=0;i<usercount;i++){
			System.out.println(Users[i].getLog_in_ID());
			
		}
	}
	public void Display_admins(){
		System.out.println("Admins:");
		for(int i=0;i<admincount;i++){
			System.out.println(Admins[i].getLog_in_ID());
			
		}
	}
	public int Reserve(User u ,Resource c, Date start_date,Date end_date){
		//Checking resource availability availability
		for(int i=0;i<resourcecount;i++){
			if(c.getID()==Resources[i].getID()){
				for(int j=0;j<reservationcount;j++){
				
						
					}
				}
				//Reserving
				
			}
			
		Reservation temp= new Reservation(c,start_date,end_date,u);
		Reservations[reservationcount]=temp;
		reservationcount++;
		return 0;
				
	}
	public void addUser_to_system(int id,int pass){
			User temp= new User();
			temp.setLog_in_ID(id);
			temp.setPassword(pass);
			Users[usercount]=temp;
			usercount++;
			
	}
	
	public void login(Resource_Management_System system){
		int ID,pass;
		Admin a;
		User u;
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter ID>");
		ID=in.nextInt();
		System.out.println("Please Enter Password>");
		pass=in.nextInt();
		//checking admins
		for(int i=0;i<admincount;i++){
			if (Admins[i].getLog_in_ID()==ID && Admins[i].getPassword()==pass){
				a=Admins[i];
				System.out.println("Welcome" +" "+ a.getLog_in_ID());
				a.add_user(system);
				return ;
			}
		}
		//checking users
		for(int i=0;i<usercount;i++){
			if (Users[i].getLog_in_ID()==ID && Users[i].getPassword()==pass){
				u=Users[i];
				System.out.println("Welcome" + u.getLog_in_ID());
				return;
			}
		}
		System.out.println("User ID or password is incorrect!");
		
	}
	
	
	
	
	public void openSystem(){
		ObjectInputStream inusers =null,inadmins = null;
		//importingadmins
		try {
			inadmins = new ObjectInputStream( new BufferedInputStream(new FileInputStream("admins.ser")));
			for(;;){
				Admin a = (Admin)inadmins.readObject();
					Admins[admincount]=a;
					admincount++;
				}
		}catch(EOFException e){
            //e.printStackTrace();
        }
		catch (FileNotFoundException e1) {
			//creating default admin
			try {
				ObjectOutputStream outadmin=null;
				outadmin = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream("admins.ser")));
				Admin a=new Admin();
				a.setLog_in_ID(123);
				a.setPassword(123);
				outadmin.writeObject(a);
				outadmin.flush();
				outadmin.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("No Admins Found... Creating admins.ser with default admin");
			System.out.println("Please Run the program again");
			System.exit(0);

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//importing users
		try {
			inusers = new ObjectInputStream( new BufferedInputStream(new FileInputStream("users.ser")));
			for(;;){
				User u = (User)inusers.readObject();
					Users[usercount]=u;
					usercount++;
				}
			
		}catch(EOFException e){
            //e.printStackTrace();
        }
		catch (FileNotFoundException e1) {
			System.out.println("No Saved Users");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void closeSystem(){
		//saving admins
				try {
					FileOutputStream f = new FileOutputStream("admins.ser");
					ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
					for(int i=0;i<admincount;i++){
					out.writeObject(Admins[i]); 
					}
					out.flush();
					out.close();

				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//saving users
		try {
			FileOutputStream f = new FileOutputStream("users.ser");
			ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
			for(int i=0;i<usercount;i++){
			out.writeObject(Users[i]); 
			}
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
