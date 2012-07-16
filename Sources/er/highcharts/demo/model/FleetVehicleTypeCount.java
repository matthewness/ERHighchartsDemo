package er.highcharts.demo.model;

import com.webobjects.eocontrol.EOCustomObject;

public class FleetVehicleTypeCount extends EOCustomObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public String type;
	public int value;
	public FleetVehicleTypeCount(String type, int value){
		super();
		this.type = type;
		this.value = value;
	}
}
