import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;
import java.io.Serializable;




public class Reservation implements Serializable{
	private LocalDate Date_of_Reservation;
	private LocalDate  Start_Date;
        private LocalTime  Start_Time,End_Time;
	private User User_Who_Made_Reservation;
	private Resource Resource_Booked;
	private boolean Reservation_Cancelled;
	private Admin Admin_Who_Cancelled;

        
	public Reservation(Resource c,LocalDate Start,LocalTime stime, LocalTime etime,User u) {
		setDate_of_Reservation(LocalDate.now());
		Resource_Booked=c;
		Start_Date=Start;
		
                Start_Time = stime;
                End_Time = etime;
		User_Who_Made_Reservation=u;
		Reservation_Cancelled=false;
		Admin_Who_Cancelled=null;
            
	}
        
	public Resource getResource(){
		return Resource_Booked;
	}
	public int get_user_ID(){
		return User_Who_Made_Reservation.getLog_in_ID();
	}

	public void setCancelled(){
		Reservation_Cancelled = true;
	}
	
	public boolean isCancelled(){
		return Reservation_Cancelled;
	}
        
    public LocalDate getStartDate(){
        return this.Start_Date;
    }
            
    public LocalTime getStartTime(){
        return this.Start_Time;
    }
        
    public LocalTime getEndTime(){
        return this.End_Time;
    }
    
    public String toString(){
    	return ("id: "+this.Resource_Booked.getID() + " Location: "+ this.User_Who_Made_Reservation.getFirst_Name() + " Start Date: " + this.Start_Date.toString()+ " Start time: "+ this.Start_Time.toString());
    }

	public LocalDate getDate_of_Reservation() {
		return Date_of_Reservation;
	}

	public void setDate_of_Reservation(LocalDate date_of_Reservation) {
		Date_of_Reservation = date_of_Reservation;
	}  
}
