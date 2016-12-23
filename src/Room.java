

enum RoomType {conferanceRoom,classRoom,meetingRoom,computer_lab,electrical_lab,mechanical_lab  }

public class Room extends Resource {
    
        private String roomName;
        private int numberOfSeats;
        private boolean proj_availability;
   
        private RoomType roomType;  //conferance room, class rooms, meeting rooms, lab
        
        
		public Room() {
		}
        
        public void setRoomName(String roomName){
            this.roomName = roomName;
        }
        
        public void setNumberOfSeats(int numberOfSeats){
            this.numberOfSeats = numberOfSeats; 
        }
        

        
        public void setPojectorAvil(boolean isAvailable){
            this.proj_availability = isAvailable;
        }
        

        

        
        public String getRoomName(){
            return this.roomName;
        }        
        
        public int getNumberOfSeats(){
            return this.numberOfSeats;
        }
        


        public int get(){
            return this.numberOfSeats;
        }   
        
        public boolean isProjectAvailable(){
            return this.proj_availability;
        }
  
        public String toString(){
            return (super.toString()+" Room Name: "+this.roomName +" Number of Seats: "+this.numberOfSeats +" Room Type: "+this.roomType +" proj available: "+this.proj_availability);
        }



		public RoomType getRoomType() {
			return roomType;
		}

		public void setRoomType(RoomType roomType) {
			this.roomType = roomType;
		}
}
