import java.time.LocalDate;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Admin extends Member {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public void add_user(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option=0;
		while(option!=4){
			System.out.println("1-Add User ");
			System.out.println("2-Modify A user ");
			System.out.println("4-No more users ");
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
				System.out.println("Enter new password>");
				temp.setPassword(in.nextInt());
				break;
			}
			
			}
			
		}
	}
	
	public void Add_Resources(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option2=0;
		while(option2!=4){
		System.out.println("1-Room ");
		System.out.println("2- ");
		System.out.println("3-");
		System.out.println("4-No more resources ");
		System.out.println("Enter An option>");
		option2=in.nextInt();
			switch(option2){
			case 1:{
				Room temp=new Room();
				System.out.println("Enter ID>");
				temp.setID(in.nextInt());
                                System.out.println("Enter Room Name>");
                                temp.setRoomName(in.next());
                                System.out.println("Enter the room location>");
                                temp.setResourceLocation(in.next());
                                System.out.println("Enter resource User Type>");
                                System.out.println("1-Professor");
                                System.out.println("2-Staff");
                                System.out.println("3-Student");
				temp.setResource_UserType(userType.values()[(in.nextInt())-1]);
                                System.out.println("Enter Start Date yyyy-MM-dd>"); 
                                String input = in.next();  
                                LocalDate startDate = LocalDate.parse(input);
                                temp.setStart_date(startDate);
                                
                                System.out.println("Enter End Date yyyy-MM-dd>");  //The End date and time should be in this format!!!!!!
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
                                Room temp = new Room();
                                System.out.println("Enter Room ID>");
                                temp.setID(in.nextInt());
                                System.out.println();
                        }
			
			
			}
		}
		
	}
	
	
	public void Allocate_Resources(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option=0;
		while(option!=4){
			System.out.println("1-Add Resource");
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
