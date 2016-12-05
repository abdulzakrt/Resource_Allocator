
enum equipmentType {Printer, Computer, Speaker,TennisRackets, Bicycle}

public class Equipment extends Resource{
        
        private equipmentType equipment;   
        
        
	public Equipment() {

	}
        

        
        public void setEquipmentType(equipmentType EquipmentType){
            this.equipment = EquipmentType;
        }
        


        public equipmentType getEquipmnetType(){
            return this.equipment;
        }        
        public String toString(){
            return (super.toString()+" Equipment Type: "+this.equipment );
        }
}
