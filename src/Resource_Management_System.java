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
		for(int i=0;i<usercount;i++){
			System.out.println(Users[i].getLog_in_ID());
			
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
	public void openSystem(){
		ObjectInputStream in = null;
		
		try {
			in = new ObjectInputStream( new BufferedInputStream(new FileInputStream("users.ser")));
			for(;;){
				User u = (User)in.readObject();
					Users[usercount]=u;
					usercount++;
				}
		}catch(EOFException e){
            //e.printStackTrace();
        }
		catch (FileNotFoundException e1) {
			System.out.println("No Saved Data");

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
