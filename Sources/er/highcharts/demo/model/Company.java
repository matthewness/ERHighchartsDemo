package er.highcharts.demo.model;

import java.util.ArrayList;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.extensions.foundation.ERXArrayUtilities;

/**
 * Example class for a model.
 * 
 * A Company has many {@code Department} objects.
 * 
 * @author matt
 *
 */
public class Company extends EOCustomObject {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String gtin;
	private NSMutableArray<Department> departments = new NSMutableArray<Department>();
	
	/**
	 * Standard constructor
	 */
	public Company(String name, String gtin){
		super();
		this.setName(name);
		this.setGtin(gtin);
	}
	
	public String name() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String gtin() {
		return gtin;
	}

	public void setGtin(String gtin) {
		this.gtin = gtin;
	}

	
	public static Company buildCompany(){
		Company company = new Company("Aaron Aardvark Solutions", "123_ABC_456_DEF");
		
		Department dept1 = Department.buildDepartment(company, "Sales");
		company.departments.add(dept1);
		
		Department dept2 = Department.buildDepartment(company, "IT");
		company.departments.add(dept2);
		
		Department dept3 = Department.buildDepartment(company, "Finance");
		company.departments.add(dept3);
		
		Department dept4 = Department.buildDepartment(company, "Warehouse");
		company.departments.add(dept4);
		
		return company;
	}

	public NSMutableArray<Department> departments() {
		return departments;
	}

	public void setDepartments(NSMutableArray<Department> departments) {
		this.departments = departments;
	}
	
	@SuppressWarnings("unchecked")
	public NSArray<FleetVehicleFuelEconomy> fleetVehiclesFuelEconomyBreakdown(){
		NSMutableArray<FleetVehicleFuelEconomy> list = new NSMutableArray<FleetVehicleFuelEconomy>();
		
		NSArray<FleetVehicle> allVehicles = ERXArrayUtilities.flatten(
				(NSArray<FleetVehicle>) this.departments.valueForKeyPath("fleet")
				);
		
		NSArray<String> types = FleetVehicle.FLEET_TYPES;
		for(String fleetType : types){
			EOQualifier fleetTypeQual = new EOKeyValueQualifier("type", EOKeyValueQualifier.QualifierOperatorEqual, fleetType);
			NSArray<FleetVehicle> fleetVehiclesOfType = EOQualifier.filteredArrayWithQualifier(allVehicles, fleetTypeQual);
			FleetVehicleFuelEconomy fleetVehiclesOfTypeEconBreakdown = new FleetVehicleFuelEconomy(fleetType);
			for (FleetVehicle vehicle : fleetVehiclesOfType ){
				for (ArrayList<Double> vehicleValues : vehicle.economyReadings()){
					fleetVehiclesOfTypeEconBreakdown.addToValues(vehicleValues);
				}
			}
			
			list.addObject(fleetVehiclesOfTypeEconBreakdown);
		}
		return list;
	}
	
	public NSArray<DepartmentBreakdown> departmentFinancialsBreakdown(){
		NSMutableArray<DepartmentBreakdown> values = new NSMutableArray<DepartmentBreakdown>();
		
		DepartmentBreakdown breakdown2010 = new DepartmentBreakdown("Year 2010");
		DepartmentBreakdown breakdown2011 = new DepartmentBreakdown("Year 2011");
		DepartmentBreakdown breakdown2012 = new DepartmentBreakdown("Year 2012");
		
		
		for (Department dept : departments){
						
			breakdown2010.addToValues(dept.financialExpenditure().objectForKey(breakdown2010.name()));
			breakdown2011.addToValues(dept.financialExpenditure().objectForKey(breakdown2011.name()));
			breakdown2012.addToValues(dept.financialExpenditure().objectForKey(breakdown2012.name()));
		}
		values.addObject(breakdown2010);
		values.addObject(breakdown2011);
		values.addObject(breakdown2012);
		
		return values;
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("[[Company] name=["+name+"] gtin=["+gtin+"]\n");
		for (Department dept : departments){
			sb.append(dept.toString());
		}
		return sb.toString();
	}
	
}
