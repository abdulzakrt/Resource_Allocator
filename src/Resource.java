
import java.time.LocalDate;
import java.time.LocalTime;
import java.sql.Time;
import java.util.Date;
import java.io.Serializable;



public abstract class Resource implements Serializable{

	protected int ID;
	boolean Resource_Status;
	protected LocalDate startDate,endDate;   
	protected LocalTime startTime,endTime;
	protected String location;
	protected userType resourceUserType;
	protected long Allowance_time;
	public Resource() {
		
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public boolean getResource_Status() {
		return Resource_Status;
	}
	public void setResource_Status(boolean resource_Status) {
		Resource_Status = resource_Status;
	}
	public LocalTime getStart_Time() {
		return startTime;
	}
	public void setStart_Time(LocalTime startTime) {
                this.startTime = startTime;
	}

	public LocalTime getEnd_Time() {
		return this.endTime;
	}
	public void setEnd_Time(LocalTime end) {
		this.endTime = end;
	}
    public void setResourceLocation(String Location){
         this.location = Location;
    }
    public String getResourceLocation(){
         return this.location;
    }
    public void setStart_date(LocalDate startDate){
         this.startDate = startDate;
    }
    public LocalDate getStart_date(){
         return this.startDate;
    }
    public void setEnd_date(LocalDate endDate){
         this.endDate = endDate;
    }
    public LocalDate getend_date(){
         return this.endDate;
    }
    public userType getResource_UserType() {
    	return resourceUserType;
	}
    public userType getResource_UserType(userType x) {
    	if((userType.values()[3]==resourceUserType)&&((x==userType.values()[0])||(x==userType.values()[1])))
    		return x;
    	else if((userType.values()[4]==resourceUserType)&&((x==userType.values()[0])||(x==userType.values()[2])))
    		return x;
    	else if((userType.values()[5]==resourceUserType)&&((x==userType.values()[1])||(x==userType.values()[2])))
    		return x;
    	else if((userType.values()[6]==resourceUserType)&&((x==userType.values()[0])||(x==userType.values()[1])||(x==userType.values()[2])))
    		return x;
    	else return resourceUserType;
	}
	public void setResource_UserType(userType resource_userType) {
		resourceUserType = resource_userType;
	}

	public String toString(){
		return ("id: "+this.ID + " Location: "+ this.location + " Start Date: " + this.startDate +" End Date: "+this.endDate+" Start Time: "+this.startTime + " End Time: "+ this.endTime +" User Type: " + this.resourceUserType);
  	}
	public long getAllowance_time() {
		return Allowance_time;
	}
	public void setAllowance_time(long allowance_time) {
		Allowance_time = allowance_time;
	}     


        
}
