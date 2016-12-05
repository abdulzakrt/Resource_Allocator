enum courtType {Basketball,Tennis,Football,Squash}
public class Sports_Courts extends Resource{
    
        private static int courtCounter;
        private courtType Court_Type; 
        private boolean lightAvailable;
        private boolean indoorCourt;
        private String CourtName;

        public Sports_Courts() {
        	courtCounter++;
        }
		public String getCourtName() {
			return CourtName;
		}
		public void setCourtName(String CourtName) {
			this.CourtName = CourtName;
		}
		public courtType getCourt_Type() {
			return Court_Type;
		}
		public void setCourt_Type(courtType court_Type) {
			Court_Type = court_Type;
		}
		public boolean isIndoorCourt() {
			return indoorCourt;
		}
		public void setIndoorCourt(boolean indoorCourt) {
			this.indoorCourt = indoorCourt;
		}
		public boolean isLightAvailable() {
			return lightAvailable;
		}
		public void setLightAvailable(boolean lightAvailable) {
			this.lightAvailable = lightAvailable;
		}//
		public String toString(){
            return (super.toString()+" Court Name: "+this.CourtName +" Number of Rooms: "+ courtCounter+ " Lights Available: "+ this.lightAvailable + " Indoor Available: "+this.indoorCourt + " Lights Available: "+this.Court_Type);
        }
}
