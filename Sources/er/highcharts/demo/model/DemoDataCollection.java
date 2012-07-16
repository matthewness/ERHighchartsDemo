package er.highcharts.demo.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSMutableArray;

public class DemoDataCollection extends EOCustomObject {
	
	
	public String name;
	public NSMutableArray<List<Object>> data = new NSMutableArray<List<Object>>();
	
	public DemoDataCollection(String name){
		this.name = name;

	}
	
	public void addData(DateTime date, BigDecimal value){
		List<Object> list = new ArrayList<Object>();
		list.add(date);
		list.add(value);
		data.add(list);
	}	
	
	public String toString(){
		return "[DemoDataCollection name["+name+"] list ["+data+"]]";
	}
	
}

