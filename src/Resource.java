import java.sql.Time;
import java.util.Date;
import java.io.Serializable;
public class Resource implements Serializable{
	private int ID,Resource_Status;
	private Date Start_Time,End_Time;
	private Date Start_Date,End_Date;
	private String location;
	private char Allowance_time;
	public Resource() {
		
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
	public Date getStart_Time() {
		return Start_Time;
	}
	public void setStart_Time(Date start_Time) {
		Start_Time = start_Time;
	}
	public char getAllowance_time() {
		return Allowance_time;
	}
	public void setAllowance_time(char allowance_time) {
		Allowance_time = allowance_time;
	}
	public Date getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(Date start_Date) {
		Start_Date = start_Date;
	}
}
