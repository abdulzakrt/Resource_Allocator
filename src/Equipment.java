
enum equipmentType {Printer, Computer, Speaker,TennisRackets, Bicycle}

public class Equipment extends Resource{
        
        private static int equipmentCounter;
        private equipmentType equipment;   
        
        
	public Equipment() {
		equipmentCounter++;
	}
        

        
        public void setEquipmentType(equipmentType EquipmentType){
            this.equipment = EquipmentType;
        }
        


        public equipmentType getEquipmnetType(){
            return this.equipment;
        }        
        public String toString(){
            return (super.toString()+" Equipment Type: "+this.equipment +" Number of Equipments: "+ equipmentCounter);
        }
}
