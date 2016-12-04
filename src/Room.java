

enum RoomType {conferanceRoom,classRoom,meetingRoom }

public class Room extends Resource {
    
        private String roomName;
        private static int roomCounter;
        private int numberOfSeats;
        private char size;     //small s , mid. m, large l
        private boolean proj_availability;
        private char labType;    
        private RoomType roomType;  //conferance room, class rooms, meeting rooms
        
        
	public Room() {
		roomCounter++;
	}
        
        public void setRoomName(String roomName){
            this.roomName = roomName;
        }
        
        public void setNumberOfSeats(int numberOfSeats){
            this.numberOfSeats = numberOfSeats; 
        }
        
        public void setRoomSize(char roomSize ){
            this.size = roomSize;
        }
        
        public void setPojectorAvil(boolean isAvailable){
            this.proj_availability = isAvailable;
        }
        
        public void setLabType(char labType){
            this.labType = labType;
        }
        
        public void setRoomType(RoomType roomType){
            this.roomType = roomType;
        }
        
        public String getRoomName(){
            return this.roomName;
        }        
        
        public int getNumberOfSeats(){
            return this.numberOfSeats;
        }
        
        public char getSizeOfRoom(){
            return this.size;
        }

        public int get(){
            return this.numberOfSeats;
        }   
        
        public boolean isProjectAvailable(){
            return this.proj_availability;
        }
        
        public char setLabType(){
            return this.labType;
        }
        
        public RoomType setRoomType(){
            return this.roomType;
        }        
        /*
        public String toString(){
            return ("Start Date: " + startDate);
        }     */
}
