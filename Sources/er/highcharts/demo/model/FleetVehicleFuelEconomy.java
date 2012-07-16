package er.highcharts.demo.model;

import java.util.ArrayList;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSMutableArray;

/**
 * Example class for a model.
 * 
 * A FleetVehicleFuelEconomy contains many fuel economy readings, and is defined by a vehicle type
 *
 * @author matt
 *
 */
public class FleetVehicleFuelEconomy extends EOCustomObject {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	
	private NSMutableArray<ArrayList<Double>> values = new NSMutableArray<ArrayList<Double>>();

	public FleetVehicleFuelEconomy(String nValue){
		this.name = nValue;
	}
	
	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NSMutableArray<ArrayList<Double>> values() {
		return values;
	}

	public void setValues(NSMutableArray<ArrayList<Double>> values) {
		this.values = values;
	}
	
	
	public void addToValues(ArrayList<Double> value){
		values.addObject(value);
	}
	
	

}
