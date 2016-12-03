
public class Equipment extends Resource{
        
        private static int equipnentCounter;
        private String equipmentName;
        private String equipmentType;
        
        
	public Equipment() {
		equipnentCounter++;
	}
        
        public void setEquipmentName(String EquimpentName){
            this.equipmentName = EquimpentName;
        }
        
        public void setEquipmentType(String EquipmentType){
            this.equipmentType = EquipmentType;
        }
        
        public String getEquipmnetName(){
            return this.equipmentName;
        }

        public String getEquipmnetType(){
            return this.equipmentType;
        }        
}
