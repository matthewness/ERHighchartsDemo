package er.highcharts.demo.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.eocontrol.EOKeyValueQualifier;
import com.webobjects.eocontrol.EOQualifier;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

import er.extensions.foundation.ERXArrayUtilities;
/**
 * Example class for a model.
 * 
 * A Department has many {@code FleetVehicle} objects, some monthly results, and financial expenditures.
 * 
 * @author matt
 *
 */
public class Department extends EOCustomObject {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String code;
	private Company company;
	private NSMutableArray<FleetVehicle> fleet = new NSMutableArray<FleetVehicle>();
	private List<Double> monthlyResults = new ArrayList<Double>();
	private NSMutableDictionary<String, Double> financialExpenditure = new NSMutableDictionary<String, Double>();


	/**
	 * Standard constructor
	 */
	public Department(Company company, String name, String code) {
		super();
		this.setCompany(company);
		this.setName(name);
		this.setCode(code);
	}

	public String code() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String name() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Company company() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public NSMutableArray<FleetVehicle> fleet() {
		return fleet;
	}

	public void setFleet(NSMutableArray<FleetVehicle> fleet) {
		this.fleet = fleet;
	}


	public List<Double> monthlyResults() {
		return monthlyResults;
	}

	public void setMonthlyResults(List<Double> value) {
		this.monthlyResults = value;
	}
	
	public NSMutableDictionary<String, Double> financialExpenditure() {
		return financialExpenditure;
	}

	public void setFinancialExpenditure(
			NSMutableDictionary<String, Double> financialExpenditure) {
		this.financialExpenditure = financialExpenditure;
	}

	
	
	public static Department buildDepartment(Company company, String name) {
		Department dept = new Department(company, name, UUID.randomUUID().toString());

		// add the fleet: a psuedo-random number of FleetVehicle objects
		Random random = new Random();
		int fleetNumber = random.nextInt(100);
		for (int i=0; i<fleetNumber; i++){
			FleetVehicle vehicle = FleetVehicle.buildFleetVehicle(dept);
			dept.fleet.add(vehicle);
		}
		
		//add some monthly results
		for (int j=0; j<12; j++){
			double result = random.nextDouble() * 100;
			dept.monthlyResults.add(Double.valueOf(twoDForm.format(result)));
		}
		
		//add some expenditure details
		
		dept.financialExpenditure.setObjectForKey(randomDouble(random), "Year 2010");
		dept.financialExpenditure.setObjectForKey(randomDouble(random), "Year 2011");
		dept.financialExpenditure.setObjectForKey(randomDouble(random), "Year 2012");
		
		//add some important dates
		
		
		return dept;
	}

	private static DecimalFormat twoDForm = new DecimalFormat("#.##");
	
	private static Double randomDouble(Random random){
		Double dbl = new Double(random.nextDouble() * 1000 * random.nextDouble());
        return Double.valueOf(twoDForm.format(dbl));
	}

	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\t[[Department] name=["+name+"] code=["+code+"]\n");
		for (FleetVehicle vehicle : fleet){
			sb.append(vehicle.toString());
		}
		int i=0;
		for(Double result : monthlyResults){
			sb.append("\t\tmonthly result ["+(++i)+"] ["+result+"]\n");
		}
		for (String oldFinancials : financialExpenditure.allKeys()){
			sb.append("\t\tfinancial details [").append(oldFinancials).append("] [")
			.append(financialExpenditure.objectForKey(oldFinancials)).append("]\n");
		}
			
		return sb.toString();
	}

	
	public NSArray<FleetVehicleTypeCount> fleetVehiclesTypeCountBreakdown(){
		NSMutableArray<FleetVehicleTypeCount> list = new NSMutableArray<FleetVehicleTypeCount>();
		
		NSArray<FleetVehicle> allVehicles = fleet();
		
		NSArray<String> types = FleetVehicle.FLEET_TYPES;
		for(String fleetType : types){
			EOQualifier fleetTypeQual = new EOKeyValueQualifier("type", EOKeyValueQualifier.QualifierOperatorEqual, fleetType);
			NSArray<FleetVehicle> fleetVehiclesOfType = EOQualifier.filteredArrayWithQualifier(allVehicles, fleetTypeQual);
			
			FleetVehicleTypeCount aTypeCount = new FleetVehicleTypeCount(fleetType, fleetVehiclesOfType.count());

			list.addObject(aTypeCount);
		}
		return list;
	}

}
