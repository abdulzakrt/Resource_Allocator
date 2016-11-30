
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
}
