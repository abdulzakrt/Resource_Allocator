import java.util.Scanner;
public class Admin extends Member {

	public Admin() {
		// TODO Auto-generated constructor stub
	}
	
	public void add_user(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option=0;
		while(option!=4){
			System.out.println("1-Add User ");
			System.out.println("2- ");
			System.out.println("4-No more users ");
			System.out.println("Enter An option>");
			option=in.nextInt();
			switch(option){
			case 1:{
				System.out.println("Enter ID>");
				int id=in.nextInt();
				System.out.println("Enter Password>");
				int pass=in.nextInt();
				system.addUser_to_system(id, pass);
				break;
			}
			
			
			}
			
		}
	}
	
	public void Add_Resources(Resource_Management_System system){
		Scanner in = new Scanner(System.in);
		int option2=0;
		while(option2!=4){
		System.out.println("1-Resource ");
		System.out.println("2-Room ");
		System.out.println("3-Equipment ");
		System.out.println("4-No more resources ");
		System.out.println("Enter An option>");
		option2=in.nextInt();
			switch(option2){
			case 1:{
				System.out.println("Enter ID>");
				Resource temp=new Resource();
				System.out.println("Enter ID>");
				temp.setID(in.nextInt());
				system.add_resource_to_array(temp);
				break;
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
