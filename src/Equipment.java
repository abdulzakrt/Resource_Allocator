
enum equipmentType {Printer, Computer, TinnesRackets, Bicycle}

public class Equipment extends Resource{
        
        private static int equipnentCounter;
        private String equipmentName;
        private equipmentType equipment;   
        
        
	public Equipment() {
		equipnentCounter++;
	}
        
        public void setEquipmentName(String EquimpentName){
            this.equipmentName = EquimpentName;
        }
        
        public void setEquipmentType(equipmentType EquipmentType){
            this.equipment = EquipmentType;
        }
        
        public String getEquipmnetName(){
            return this.equipmentName;
        }

        public equipmentType getEquipmnetType(){
            return this.equipment;
        }        
}
