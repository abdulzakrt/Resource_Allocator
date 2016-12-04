import java.time.LocalDateTime;
import java.util.Date;

public class Reservation {
	private LocalDateTime Date_of_Reservation;
	private LocalDateTime  Start_Time,End_Time;
	private User User_Who_Made_Reservation;
	private Resource Resource_Booked;
	private boolean Reservation_Cancelled;
	private Admin Admin_Who_Cancelled;
        private static int reservationCount;
        
	public Reservation(Resource c,LocalDateTime Start, LocalDateTime End,User u) {
		Date_of_Reservation = LocalDateTime.now();
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
        
        public LocalDateTime getStartTiem(){
            return this.Start_Time;
        }
        
        public LocalDateTime getEndTime(){
            return this.End_Time;
        }
}
