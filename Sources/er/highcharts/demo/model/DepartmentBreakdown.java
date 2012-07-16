package er.highcharts.demo.model;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSMutableArray;

/**
 * Example class for a model.
 * 
 * A DepartmentBreakdown contains many double values, and is defined by a department name.
 *
 * @author matt
 *
 */
public class DepartmentBreakdown extends EOCustomObject {
	
	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private NSMutableArray<Double> values = new NSMutableArray<Double>();
	
	public DepartmentBreakdown(String nValue){
		this.name = nValue;
	}
	
	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public NSMutableArray<Double> values() {
		return values;
	}

	public void setValues(NSMutableArray<Double> values) {
		this.values = values;
	}
	
	
	public void addToValues(Double value){
		values.addObject(value);
	}
	
}
