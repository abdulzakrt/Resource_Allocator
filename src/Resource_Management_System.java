
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
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;
import java.time.temporal.ChronoUnit;//New
public class Resource_Management_System implements Serializable{
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
	public void add_resource_to_array(Resource temp) throws IDexisterror{
		if(this.Does_resourse_exist(temp.getID())){
			throw new IDexisterror();
		}
		Resources[resourcecount]=temp;
		resourcecount++;
		
	}
	public void Display_resource(){
		System.out.println("Resources:");
		for(int i=0;i<resourcecount;i++){
			System.out.println(Resources[i]);
			
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
	public boolean Does_admin_exist(int id){
		for (int i=0;i<admincount;i++){
			if (Admins[i].getLog_in_ID()==id)
				return true;
		}
		return false;
	}
	public boolean Does_user_exist(int id){
		for (int i=0;i<usercount;i++){
			if (Users[i].getLog_in_ID()==id)
				return true;
		}
		return false;
	}
	public boolean Does_resourse_exist(int id){
		for (int i=0;i<resourcecount;i++){
			if (Resources[i].getID()==id)
				return true;
		}
		return false;
	}
        public void Display_reservation(){
		System.out.println("reservations :");
		for(int i=0;i<reservationcount;i++){
			System.out.println(Reservations[i]);
		}
	}
        public int get_reservation_count(){
    		return reservationcount;
    	}
        public int get_reservation_count(User x){
        	int res_count=0;
    		for(int i=0;i<reservationcount;i++)
    		{
    			if((Reservations[i].get_user_ID()==x.getLog_in_ID())&&(Reservations[i].isCancelled()==false))
    			{
    				res_count++;
    			}
    		}
    		return res_count;
    	}
        public Reservation[] get_reservations_of_user(User x){
        	int res_count=0;
    		for(int i=0;i<reservationcount;i++)
    		{
    			if((Reservations[i].get_user_ID()==x.getLog_in_ID())&&(Reservations[i].isCancelled()==false))
    			{
    				res_count++;
    			}
    		}
    		Reservation[] array_of_res= new Reservation[res_count];
    		int j=0;
    		for(int i=0;i<reservationcount;i++)
    		{
    			if((Reservations[i].get_user_ID()==x.getLog_in_ID())&&(Reservations[i].isCancelled()==false))
    			{
    				array_of_res[j]=Reservations[i];
    				j++;
    			}
    		}
    		return array_of_res;
    	}
        //todo list:
        //a person can't reserve two resources at the same time,//
        //unique ID for users, resources should have a unique id
        //if date is before start date of resource or after end date of resource 
        //allowance time check  Done  
        //input error checking
	public void Reserve(User u){
		
                for(int i=0;i<resourcecount;i++)
                {
                    System.out.println(Resources[i].getID());
                }
                //Resource temp = new Resource();
                Scanner in = new Scanner(System.in);
                System.out.println("Enter resource ID >"); 
                int ID = in.nextInt(); 
                System.out.println("Enter Start Date yyyy-MM-dd>"); 
                String input = in.next();  
                LocalDate sdate = LocalDate.parse(input);
                                
                System.out.println("Enter Start time HH:MM >"); 
                input = in.next();  
                LocalTime stime = LocalTime.parse(input);
                                
                System.out.println("Enter end time HH:MM >"); 
                input = in.next();  
                LocalTime etime = LocalTime.parse(input);
                Reservation temp; 
                Resource temp1=null;
                long duration = ChronoUnit.MINUTES.between(stime, etime);  //To get reservation duration in min.
                for(int i=0;i<resourcecount;i++)
                {
                    if(Resources[i].getID()==ID){
                        temp1=Resources[i];
                        break;
                    }                        
                    
                }
                if ((temp1!=null)){
                	if((sdate.isBefore(temp1.getStart_date()))||(sdate.isAfter(temp1.getend_date())))
                	{
                		System.out.println("error wrong starting date !");
                		return;
                	}
                	if(duration > temp1.getAllowance_time()){  //To check if the users exceeded the allowed time
                		System.out.println("error The allowed time is: "+ temp1.getAllowance_time() + " minute(s)");
                		return;
                	}
	                if((check_source(u,ID, stime, etime, sdate)==1)&&(temp1.isUserCompatible(u.getUser_type())))
	                {         	 
  	                    temp= new Reservation(temp1,sdate,stime, etime,u);	                  
	                    Reservations[reservationcount]=temp;
	                    reservationcount++;
	                    return ;
	                }
                }
                System.out.println("No resarvation was made!!!");
                return ;
				
	}
	public void Reserve(User u, Resource x, LocalTime[] hours, LocalDate strtdte )
	{
		LocalTime strt, end;
		strt = hours[0];
		int hour = 1;
		end = hours[hours.length - 1].plusHours((long)hour);
		Reservation temp= new Reservation(x,strtdte,strt, end,u);	                  
        Reservations[reservationcount]=temp;
        reservationcount++;
	}
	public void Cancel_reservation( Reservation x)
	{
		int i=0;
		for(i=0;i<reservationcount;i++)
		{
			if((Reservations[i].getStartDate().equals(x.getStartDate()))&&(x.get_user_ID()==Reservations[i].get_user_ID()))
			{
				Reservations[i].setCancelled();
			}
		}
	}
	public LocalTime[] check_source(int x,LocalDate sdate)
    {
		LocalTime[] times_reserved = new LocalTime[24];
		for(int i = 0;i<24;i++)
        {
    		times_reserved[i]=null;
       	}
		int j=0;
    	for(int i = 0;i<reservationcount;i++)
        {
    		if((Reservations[i].getStartDate().equals(sdate) && Reservations[i].getResource().getID()==x)&&(Reservations[i].isCancelled()==false))
    		{
    			//ChronoUnit.MINUTES.between(startT, endT);
    			if(ChronoUnit.MINUTES.between(Reservations[i].getStartTime(), Reservations[i].getEndTime())>60)
    			{
    				int mins = (int)ChronoUnit.MINUTES.between(Reservations[i].getStartTime(), Reservations[i].getEndTime());
    				times_reserved[j] =Reservations[i].getStartTime();
    				j++;
    				int hours = (mins/60);
    				for(int k=0; k<hours; k++)
    				{
    					
    					times_reserved[j] = Reservations[i].getStartTime().plusHours((long)k);
    					j++;
    				}
    			}
    			else
    			{
	    			times_reserved[j] = Reservations[i].getStartTime();
	    			j++;
    			}
    			
    		}
       	}
       	return times_reserved;
    }
	public boolean check_user_reserved_on_date(int id, LocalDate sdate,int uid)
    {
		for(int i=0; i<reservationcount; i++)
		{
			if((Reservations[i].get_user_ID()==uid)&&(id==Reservations[i].getResource().getID())&&(Reservations[i].getStartDate().equals(sdate))&&(Reservations[i].isCancelled()==false))
				return true;
			
		}
		return false;	
    }
	
    public int check_source(User u,int id, LocalTime stime, LocalTime etime, LocalDate sdate)
    {
    	for(int i = 0;i<reservationcount;i++)
        {
    		Reservation temp = Reservations[i];
    		if((temp.getResource().getID()==id)&&((temp.getStartDate().equals(sdate)))&&(temp.getResource().isUserCompatible(u.getUser_type())))
    		{
    			if((temp.getStartTime().equals(stime))&&(temp.getEndTime().equals(etime)))
    				return 0;
                else if((temp.getStartTime().isBefore(stime))&&(temp.getEndTime().isAfter(stime)))
               		return 0;
              	else if((temp.getStartTime().isBefore(etime))&&(temp.getEndTime().isAfter(etime)))
               		return 0;
              	else if((stime.isBefore(temp.getStartTime()))&&(etime.isAfter(temp.getEndTime())))
              		return 0;   
           	}
       	}
       	return 1;
    }
	public void addUser_to_system(User temp) throws IDexisterror{
			if (this.Does_user_exist(temp.getLog_in_ID()))
				throw new IDexisterror();
			Users[usercount]=temp;
			usercount++;
			
	}
	public void addAdmin_to_system(Admin temp) throws IDexisterror{
		if (this.Does_admin_exist(temp.getLog_in_ID()))
			throw new IDexisterror();
		Admins[admincount]=temp;
		admincount++;
		
        }
	public int get_resource_count()
	{
		return this.resourcecount;
	}
	public Resource get_resource_of_index(int i)
	{
		return Resources[i];
	}
	public Resource get_resource_of_id(int id){
		for(int i =0;i<resourcecount;i++){
			if (Resources[i].getID()==id)
				return Resources[i];
		}
		return null;
	}

/*	public void login(Resource_Management_System system){
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
                System.out.println("Welcome Admin" +" "+ a.getLog_in_ID());
				while(option!=4){
					System.out.println("1-Users settings>");
					System.out.println("2-Resources settings>");
					System.out.println("3-Admins settings>");
					System.out.println("4-Logout>");
					System.out.println("Enter option>");
					option= in.nextInt();
					switch(option){
					case 1:{
						a.modify_user(system);
						break;
					}
					case 2:{
						a.Allocate_Resources(system);
						
						break;
					}
					case 3:{
						a.add_admin(system);
						
						break;
					}
					case 4:{
						System.out.println("Logout Successful!");
						break;
					}
					default:{
						System.out.println("Wrong input !");
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
				System.out.println("Welcome user " + u.getFirst_Name());
                                
                                int option=0;
                                while(option!=3){
                                System.out.println("1-reserve>");
                                System.out.println("2-Change Password>");
                                System.out.println("3-logout>");
                                System.out.println("Enter option>");
                                option= in.nextInt();
					switch(option){
						case 1:{
							this.Reserve(u);
						
							break;
						}
						case 2:{
							System.out.println("Enter new Password>");
							u.setPassword(in.nextInt());
							System.out.println("New password set successfully!");
							break;
						}
						case 3:{
							System.out.println("Logout Successful!");
							break;
							
						}
						default:{
							System.out.println("Wrong Input!");
						}
					}
                }
				return ;
			}
		}
		System.out.println("User ID or password is incorrect!");
		
	}
	*/
	
	public Member login(int ID,int pass){
		Admin a;
		User u;


		//checking admins
		for(int i=0;i<admincount;i++){
			if (Admins[i].getLog_in_ID()==ID && Admins[i].getPassword()==pass){			
				return Admins[i];
			}
		}
		//checking users
		for(int i=0;i<usercount;i++){
			if (Users[i].getLog_in_ID()==ID && Users[i].getPassword()==pass){				
				return Users[i];
			}
		}
		return null;
		
	}	
	
	public Resource_Management_System openSystem(){
//		ObjectInputStream inusers =null,inadmins = null;
//		//importingadmins
//		try {
//			inadmins = new ObjectInputStream( new BufferedInputStream(new FileInputStream("admins.ser")));
//			for(;;){
//				Admin a = (Admin)inadmins.readObject();
//					Admins[admincount]=a;
//					admincount++;
//				}
//		}catch(EOFException e){
//            //e.printStackTrace();
//        }
//		catch (FileNotFoundException e1) {
//			//creating default admin
//			try {
//				ObjectOutputStream outadmin=null;
//				outadmin = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream("admins.ser")));
//				Admin a=new Admin();
//				a.setLog_in_ID(123);
//				a.setPassword(123);
//				outadmin.writeObject(a);
//				outadmin.flush();
//				outadmin.close();
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			System.out.println("No Admins Found... Creating admins.ser with default admin");
//			System.out.println("Please Run the program again");
//			System.exit(0);
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		
//		//importing users
//		try {
//			inusers = new ObjectInputStream( new BufferedInputStream(new FileInputStream("users.ser")));
//			for(;;){
//				User u = (User)inusers.readObject();
//					Users[usercount]=u;
//					usercount++;
//				}
//			
//		}catch(EOFException e){
//            //e.printStackTrace();
//        }
//		catch (FileNotFoundException e1) {
//			System.out.println("No Saved Users");
//
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		ObjectInputStream insystem=null;
		//importingSystem
		try {
			Resource_Management_System temp;
			insystem = new ObjectInputStream( new BufferedInputStream(new FileInputStream("system.ser")));
				temp = (Resource_Management_System)insystem.readObject();
				return temp;
		}catch(EOFException e){
            //e.printStackTrace();
        }
		catch (FileNotFoundException e1) {
			try {
				FileOutputStream f = new FileOutputStream("system.ser");
				ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
				Admin temp =new Admin();
				temp.setLog_in_ID(123);
				temp.setPassword(123);
				this.addAdmin_to_system(temp);
				out.writeObject(this); 
				out.flush();
				out.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IDexisterror e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("No Saved Data...Creating system.ser with default admin");

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this;
	}
	public void closeSystem(){         
		//saving admins

		/*try {
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
		}*/
		//saving users

//				try {
//					FileOutputStream f = new FileOutputStream("admins.ser");
//					ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
//					for(int i=0;i<admincount;i++){
//					out.writeObject(Admins[i]); 
//					}
//					out.flush();
//					out.close();
//
//				} catch (FileNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		//saving users
//		try {
//			FileOutputStream f = new FileOutputStream("users.ser");
//			ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
//			for(int i=0;i<usercount;i++){
//			out.writeObject(Users[i]); 
//			}
//			out.flush();
//			out.close();
//
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		//saving admins

		try {
			FileOutputStream f = new FileOutputStream("system.ser");
			ObjectOutputStream out =   new ObjectOutputStream( new BufferedOutputStream(f));
			out.writeObject(this); 
			out.flush();
			out.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
               /* //saving resources
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
                }*/
	}
	public Reservation[] get_reservations() {
		// TODO Auto-generated method stub
		return Reservations;
	}
	public Resource[] get_resources() {
		// TODO Auto-generated method stub
		return Resources;
	}
	public int get_user_resources_count(User u) {
		
		int numofresource=0;
		for(int i=0;i<resourcecount;i++){
			if(Resources[i].isUserCompatible(u.getUser_type())){
				numofresource++;
			}
		}
		return numofresource;
	}
	public Resource[] get_user_resources(User u) {
		Resource[] temp;
		int numofresource=get_user_resources_count(u);
		temp=new Resource[numofresource];
		int j=0;
		for(int i=0;i<resourcecount;i++){
			if(Resources[i].isUserCompatible(u.getUser_type())){
				temp[j]=Resources[i];
				j++;
			}
		}
		return temp;
	}
	public User[] get_users(){
		return Users;
	}
	public int get_user_count(){
		return usercount;
	}
}
