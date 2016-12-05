import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Admin extends Member {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	public void add_admin(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter ID>");
		int id=in.nextInt();
		System.out.println("Enter Password>");
		int pass=in.nextInt();
		Admin temp=new Admin();
		temp.setLog_in_ID(id);
		temp.setPassword(pass);
		system.addAdmin_to_system(temp);
		
		
		
	}
	public void modify_user(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option=0;
		while(option!=3){
			System.out.println("1-Add User ");
			System.out.println("2-Modify A user ");
			System.out.println("3-No more users ");
			System.out.println("Enter An option>");
			option=in.nextInt();
			switch(option){
			case 1:{
				System.out.println("Enter ID>");
				int id=in.nextInt();
				System.out.println("Enter Password>");
				int pass=in.nextInt();
				System.out.println("Enter First Name>");
				String First=in.next();
				System.out.println("Enter Last Name>");
				String Last=in.next();
				System.out.println("Enter Age>");
				int age=in.nextInt();			
                System.out.println("1-Professor");
                System.out.println("2-Staff");
                System.out.println("3-Student");                                  
                System.out.println("Enter User_type(1-2-3)>");
				int type=  in.nextInt();
				User temp=new User(First,Last,age,userType.values()[type-1],id,pass);					
				system.addUser_to_system(temp);
				break;
			}
			case 2:{
				System.out.println("Enter User ID to Modify>");
				int id=in.nextInt();
				User temp= system.searchUser(id);
				if(temp!=null){
				System.out.println("Enter new ID>");
				int id2=in.nextInt();
				temp.setLog_in_ID(id2);
				System.out.println("Enter new password>");
				temp.setPassword(in.nextInt());
				}
				else{
					System.out.println("No Such User Found!");
				}
				break;
			}
			case 3:{break;}
			default:{
				System.out.println("Wrong Input!");
			}
			}
			
		}
	}
	//Administrator can add resources 
	public void Add_Resources(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option2=0;
		//as long as it is not exit  the menu
		while(option2!=4){
			//print possible resources
			System.out.println("1-Room ");
			System.out.println("2-Equipment ");
			System.out.println("3-Court");
			System.out.println("4-No more resources ");
			System.out.println("Enter An option>");
			option2=in.nextInt();
			
			switch(option2){
				case 1:{//if it is a room type resource
					Room temp=new Room();//new room object
					System.out.println("Enter ID>");//take ID
					temp.setID(in.nextInt());
					System.out.println("Enter Room Name>");//take room name
					temp.setRoomName(in.next());
					System.out.println("Enter the room location>");//take room location
					temp.setResourceLocation(in.next());
					//setting allowance time
					System.out.println("Enter the allowance time in minutes (int)>");//take room location
					temp.setAllowance_time(in.nextLong());
					System.out.println("1-Professor");
					System.out.println("2-Staff");
					System.out.println("3-Student");
					System.out.println("Enter room User Type>");//take the type of the user that cn use it
					temp.setResource_UserType(userType.values()[(in.nextInt())-1]);
					//Room Type
					
					System.out.println("1-ConferanceRoom");
					System.out.println("2-ClassRoom");
					System.out.println("3-MeetingRoom");
					System.out.println("4-Computer lab");
					System.out.println("5-Electrical lab");
					System.out.println("6-Mechanical lab");
					System.out.println("Enter room Type>");//take the type of the user that cn use it
					temp.setRoomType(RoomType.values()[(in.nextInt())-1]);
					//set number of seats
					System.out.println("Enter the number of seats>");//take room location
					temp.setNumberOfSeats(in.nextInt());
					//projector availability
					System.out.println("Does the room have a projector(true/false)>");//take room location
					temp.setPojectorAvil(in.nextBoolean());
					System.out.println("Enter Start Date yyyy-MM-dd>"); //when will be available 
					String input = in.next();  
					LocalDate startDate = LocalDate.parse(input);
					temp.setStart_date(startDate);
					
	                System.out.println("Enter End Date yyyy-MM-dd>");  //when will it terminate
	                input = in.next();  //input is a string in order to store the starting time
	                LocalDate endDate = LocalDate.parse(input);
	                temp.setEnd_date(endDate);
	       
	                System.out.println("Enter Start time HH:MM >"); 
	                input = in.next();  
	                LocalTime startTime = LocalTime.parse(input);
	                temp.setStart_Time(startTime);
	                                
	                System.out.println("Enter end time HH:MM >"); 
	                input = in.next();  
	                LocalTime endTime = LocalTime.parse(input);
	                temp.setEnd_Time(endTime);
	                system.add_resource_to_array(temp);                  
	                    
	
					break;
				}
	            case 2:{
	            	Equipment temp = new Equipment();
	                System.out.println("Enter Equipment ID>");
	                temp.setID(in.nextInt());	                
					System.out.println("1-Professor");
					System.out.println("2-Staff");
					System.out.println("3-Student");
					System.out.println("Enter Equipment User Type>");//take the type of the user that cn use it
					temp.setResource_UserType(userType.values()[(in.nextInt())-1]);	     
					System.out.println("Enter the equipment location>");//take room location
					temp.setResourceLocation(in.next());
					System.out.println("Enter the allowance time in minutes (int)>");//take room location
					temp.setAllowance_time(in.nextLong());
	                System.out.println("1-Printer");
	                System.out.println("2-Computer");
	                System.out.println("3-Speaker");
	                System.out.println("4-Tennis Racket");
	                System.out.println("5-Bicycle");
	                System.out.println("Enter Equipment Type>");
	                temp.setEquipmentType(equipmentType.values()[(in.nextInt())-1]);
					System.out.println("Enter Start Date yyyy-MM-dd>"); //when will be available 
					String input = in.next();  
					LocalDate startDate = LocalDate.parse(input);
					temp.setStart_date(startDate);
					
	                System.out.println("Enter End Date yyyy-MM-dd>");  //when will it terminate
	                input = in.next();  //input is a string in order to store the starting time
	                LocalDate endDate = LocalDate.parse(input);
	                temp.setEnd_date(endDate);
	       
	                System.out.println("Enter Start time HH:MM >"); 
	                input = in.next();  
	                LocalTime startTime = LocalTime.parse(input);
	                temp.setStart_Time(startTime);
	                                
	                System.out.println("Enter end time HH:MM >"); 
	                input = in.next();  
	                LocalTime endTime = LocalTime.parse(input);
	                temp.setEnd_Time(endTime);                 
	                    
	                system.add_resource_to_array(temp);   
	                break;
	                     }
	            case 3:{
	            	
	            	Sports_Courts temp=new Sports_Courts();//new room object
					System.out.println("Enter ID>");//take ID
					temp.setID(in.nextInt());
					System.out.println("Enter Sports_Court Name>");//take room name
					temp.setCourtName(in.next());
					System.out.println("Enter the Court location>");//take room location
					temp.setResourceLocation(in.next());
					//setting allowance time
					System.out.println("Enter the allowance time in minutes (int)>");//take room location
					temp.setAllowance_time(in.nextLong());
					System.out.println("1-Professor");
					System.out.println("2-Staff");
					System.out.println("3-Student");
					System.out.println("Enter Court User Type>");//take the type of the user that cn use it
					temp.setResource_UserType(userType.values()[(in.nextInt())-1]);
				
					System.out.println("1-BasketBall");
					System.out.println("2-Tennis");
					System.out.println("3-FootBall");
					System.out.println("4-Squash");
					System.out.println("Enter Court Type>");//take the type of the user that cn use it
					temp.setCourt_Type(courtType.values()[(in.nextInt())-1]);
					System.out.println("Is it indoor or outdoor? (true/false)>");
					temp.setIndoorCourt(in.nextBoolean());
					System.out.println("Does it have lights? (true/false)>");
					temp.setLightAvailable(in.nextBoolean());
					System.out.println("Enter Start Date yyyy-MM-dd>"); //when will be available 
					String input = in.next();  
					LocalDate startDate = LocalDate.parse(input);
					temp.setStart_date(startDate);
					
	                System.out.println("Enter End Date yyyy-MM-dd>");  //when will it terminate
	                input = in.next();  //input is a string in order to store the starting time
	                LocalDate endDate = LocalDate.parse(input);
	                temp.setEnd_date(endDate);
	       
	                System.out.println("Enter Start time HH:MM >"); 
	                input = in.next();  
	                LocalTime startTime = LocalTime.parse(input);
	                temp.setStart_Time(startTime);
	                                
	                System.out.println("Enter end time HH:MM >"); 
	                input = in.next();  
	                LocalTime endTime = LocalTime.parse(input);
	                temp.setEnd_Time(endTime);
	                system.add_resource_to_array(temp);                  
	                    
	
	            	break;}
	            case 4:{break;}
	            default:{System.out.println("Wrong Input!");}
				
			}
		}
		
	}
	
	//it includes modify 
	public void Allocate_Resources(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option=0;
		while(option!=4){
			System.out.println("1-Add Resource");
			System.out.println("2-modify Resource");
			System.out.println("4-No option");
			System.out.println("Enter An option>");
			option=in.nextInt();
			switch(option){
			case 1:{
				Add_Resources(system);
				break;
			}
			default:{
				System.out.println("Bye!");
				break;
			}
			
			
			
			}
		}
	}
}
