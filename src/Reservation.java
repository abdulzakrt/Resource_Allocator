import java.sql.Time;
import java.util.Date;
import java.io.Serializable;
public class Reservation implements Serializable{
	private Date Date_of_Reservation;
	private Date  Start_Time,End_Time;
	private User User_Who_Made_Reservation;
	private Resource Resource_Booked;
	private boolean Reservation_Cancelled;
	private Admin Admin_Who_Cancelled;
        private static int reservationCount;
        
	public Reservation(Resource c,Date Start, Date End,User u) {
		Date_of_Reservation=new Date();
		Resource_Booked=c;
		Start_Time=Start;
		End_Time=End;
		User_Who_Made_Reservation=u;
		Reservation_Cancelled=false;
		Admin_Who_Cancelled=null;
                reservationCount++;
	}
        
	public Resource getResource(){
		return Resource_Booked;
	}
        
        public Date getStartTiem(){
            return this.Start_Time;
        }
        
        public Date getEndTime(){
            return this.End_Time;
        }
}
