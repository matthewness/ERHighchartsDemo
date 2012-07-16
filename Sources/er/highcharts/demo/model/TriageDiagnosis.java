package er.highcharts.demo.model;

import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableDictionary;

public class TriageDiagnosis {

	public String name;
	public int refIndex;
	
	public NSMutableDictionary<String, Integer> divisions = new NSMutableDictionary<String, Integer>();
	
	public TriageDiagnosis(String n, int ri){
		super();
		this.name = n;
		this.refIndex = ri;
	}
	
	public void addDivision(String divName, Integer count){
		divisions.setObjectForKey(count, divName);
	}
	
	public int total(){
		int total = 0;
		NSArray<Integer> ints = divisions.allValues();
		for (Integer i : ints){
			total += i.intValue();
		}
		return total;
	}
}
