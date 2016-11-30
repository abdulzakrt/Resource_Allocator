
public class Resource {
	private int ID,Resource_Status;
	private String location,Start_Time,End_Time,Start_Date,End_Date;
	private char Allowance_time;
	public Resource() {
		// TODO Auto-generated constructor stub
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getResource_Status() {
		return Resource_Status;
	}
	public void setResource_Status(int resource_Status) {
		Resource_Status = resource_Status;
	}
	public String getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(String start_Time) {
		Start_Time = start_Time;
	}
	public char getAllowance_time() {
		return Allowance_time;
	}
	public void setAllowance_time(char allowance_time) {
		Allowance_time = allowance_time;
	}
	public String getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(String start_Date) {
		Start_Date = start_Date;
	}
}
