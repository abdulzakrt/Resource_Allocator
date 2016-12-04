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
import java.time.LocalDateTime;
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
	public User searchUser(int id){
		for (int i=0;i<usercount;i++){
			if (Users[i].getLog_in_ID()==id)
				return Users[i];
			
		}
		return null;
		
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
			System.out.println(Users[i]);
			
		}
	}
	public void Display_admins(){
		System.out.println("Admins:");
		for(int i=0;i<admincount;i++){
			System.out.println(Admins[i].getLog_in_ID());
			
		}
	}
	public int Reserve(User u ,Resource c, LocalDateTime start_time,LocalDateTime end_time){
		//Checking resource availability availability
		for(int i=0;i<resourcecount;i++){
			if(c.getID()==Resources[i].getID()){
				for(int j=0;j<reservationcount;j++){
				
						
					}
				}
				//Reserving
				
			}
			
		Reservation temp= new Reservation(c,start_time,end_time,u);
		Reservations[reservationcount]=temp;
		reservationcount++;
		return 0;
				
	}
	public void addUser_to_system(User temp){
			Users[usercount]=temp;
			usercount++;
			
	}
	
	public void login(Resource_Management_System system){
		int ID,pass;
		Admin a;
		User u;
		Scanner in = new Scanner(System.in);
		System.out.println("Please Enter ID to login>");
		ID=in.nextInt();
		System.out.println("Please Enter Password>");
		pass=in.nextInt();
		//checking admins
		for(int i=0;i<admincount;i++){
			if (Admins[i].getLog_in_ID()==ID && Admins[i].getPassword()==pass){
				a=Admins[i];
				int option=0;
				while(option!=4){
					System.out.println("Welcome Admin" +" "+ a.getLog_in_ID());
					System.out.println("1-Modify Users>");
					System.out.println("2-Modify Resources>");
					System.out.println("3-Modify Admins>");
					System.out.println("4-Logout>");
					System.out.println("Enter option>");
					option= in.nextInt();
					switch(option){
					case 1:{
						a.add_user(system);
						break;
					}
					case 2:{
						a.Add_Resources(system);
						
						break;
					}
					case 3:{
						
						
						break;
					}
					
					}
				}
				
				return ;
			}
		}
		//checking users
		for(int i=0;i<usercount;i++){
			if (Users[i].getLog_in_ID()==ID && Users[i].getPassword()==pass){
				u=Users[i];
				System.out.println("Welcome user " + u.getLog_in_ID());
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
                //saving resources
                try{
			FileOutputStream f = new FileOutputStream("resources.ser");
			ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
			for(int i=0;i<usercount;i++){
			out.writeObject(Resources[i]); 
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
