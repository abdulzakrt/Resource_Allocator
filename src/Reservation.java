import java.time.LocalDateTime;
import java.time.LocalDate;
import java.io.Serializable;




public class Reservation implements Serializable{
	private LocalDate Date_of_Reservation;
	private LocalDate  Start_Time,End_Time;
	private User User_Who_Made_Reservation;
	private Resource Resource_Booked;
	private boolean Reservation_Cancelled;
	private Admin Admin_Who_Cancelled;
        private static int reservationCount;
        
	public Reservation(Resource c,LocalDate Start, LocalDate End,User u) {
		Date_of_Reservation = LocalDate.now();
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
        
        public LocalDate getStartTiem(){
            return this.Start_Time;
        }
        
        public LocalDate getEndTime(){
            return this.End_Time;
        }
}
