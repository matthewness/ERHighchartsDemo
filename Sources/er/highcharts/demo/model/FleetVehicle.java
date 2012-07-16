package er.highcharts.demo.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSArray;

/**
 * Example class for a model.
 * 
 * A FleetVehicle has many fuel economy readings
 * 
 * @author matt
 *
 */
public class FleetVehicle extends EOCustomObject {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	

	private String type;
	private String uuid;
	private Department department;
	private List<ArrayList<Double>> economyReadings = new ArrayList<ArrayList<Double>>();
	
	/**
	 * Standard constructor
	 */
	public FleetVehicle(Department dept, String type, String uuid){
		super();
		this.setDepartment(dept);
		this.setType(type);
		this.setUuid(uuid);
	}
	
	public String type() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String uuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public Department department() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static final NSArray<String> FLEET_TYPES = new NSArray<String>(new String[]{
			"Car",
			"Truck",
			"Motorcycle",
			"MiniVan"
	});

	public static String randomType() {
		Random random = new Random();
	    return FLEET_TYPES.get(random.nextInt(4));
	}


	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\t\t[[Fleet vehicle] type=["+type+"] uuid=["+uuid+"]\n");
		return sb.toString();
	}

	public List<ArrayList<Double>> economyReadings() {
		return economyReadings;
	}

	public void setEconomyReadings(List<ArrayList<Double>> economyReadings) {
		this.economyReadings = economyReadings;
	}

	public static FleetVehicle buildFleetVehicle(Department dept) {
		FleetVehicle fv = new FleetVehicle(dept, FleetVehicle.randomType(), UUID.randomUUID().toString());
		Random random = new Random();
		fv.economyReadings.add(randomEconomyRating(random));
		fv.economyReadings.add(randomEconomyRating(random));
		fv.economyReadings.add(randomEconomyRating(random));
		fv.economyReadings.add(randomEconomyRating(random));
		fv.economyReadings.add(randomEconomyRating(random));
		fv.economyReadings.add(randomEconomyRating(random));
		return fv;
	}
	
	private static ArrayList<Double> randomEconomyRating(Random random){
		Double lpk = randomLitresPer100Kms(random);
		Double speed = randomSpeed(random);
		ArrayList<Double> list = new ArrayList<Double>();
		list.add(speed);
		list.add(lpk);
		return list;
	}

	private static Double randomLitresPer100Kms(Random random){
		Double dbl = new Double( random.nextDouble() * 10 );
		DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(dbl));
	}
	
	private static Double randomSpeed(Random random){
		Double dbl = new Double( random.nextDouble() * 100 );
		DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(dbl));
	}
	
}
