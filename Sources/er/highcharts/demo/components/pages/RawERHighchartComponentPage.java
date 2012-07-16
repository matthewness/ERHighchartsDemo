package er.highcharts.demo.components.pages;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;
import com.webobjects.foundation.NSMutableDictionary;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.ERHighchartsFunction;
import er.highcharts.model.KVCAExtensionGraph;

public class RawERHighchartComponentPage extends ERHighchartsDemoComponent {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RawERHighchartComponentPage(WOContext context) {
        super(context);
    }
	

	/**
	 * Some data to show in the page.
	 */
	public static NSMutableDictionary<String, NSMutableArray<BigDecimal>> data = new NSMutableDictionary<String, NSMutableArray<BigDecimal>>();
	
	//build up some data
	static {
		
		NSMutableArray<BigDecimal> bananaList = new NSMutableArray<BigDecimal>();
		bananaList.add(new BigDecimal(60.78));
		bananaList.add(new BigDecimal(12.29));
		bananaList.add(new BigDecimal(20.36));
		bananaList.add(new BigDecimal(15.12));
		bananaList.add(new BigDecimal(41.49));
		data.setObjectForKey(bananaList, "Bananas");

		NSMutableArray<BigDecimal> appleList = new NSMutableArray<BigDecimal>();
		appleList.add(new BigDecimal(35.87));
		appleList.add(new BigDecimal(88.57));
		appleList.add(new BigDecimal(30.03));
		appleList.add(new BigDecimal(63.56));
		appleList.add(new BigDecimal(99.69));
		data.setObjectForKey(appleList, "Apples");
		
		NSMutableArray<BigDecimal> guavaList = new NSMutableArray<BigDecimal>();
		guavaList.add(new BigDecimal(34.88));
		guavaList.add(new BigDecimal(45.65));
		guavaList.add(new BigDecimal(86.98));
		guavaList.add(new BigDecimal(82.21));
		guavaList.add(new BigDecimal(26.38));
		data.setObjectForKey(guavaList, "Guava");

		NSMutableArray<BigDecimal> pearList = new NSMutableArray<BigDecimal>();
		pearList.add(new BigDecimal(94.48));
		pearList.add(new BigDecimal(43.24));
		pearList.add(new BigDecimal(97.5));
		pearList.add(new BigDecimal(45.05));
		pearList.add(new BigDecimal(4.85));
		data.setObjectForKey(pearList, "Pear");

	}
	
	//some nicer category descriptions
	 public NSArray<String> categories = new NSArray<String>(new String[]{
			 	"Sol",
			 	"Mecury",
				"Venus",
				"Earth",
				"Mars",
				});
			    
			 
	/**
	 * Build the graph.
	 * 
	 * Push the data into the series and set the categories.
	 * 
	 * @return
	 */
	public KVCAExtensionGraph graph() {
		KVCAExtensionGraph graph = new KVCAExtensionGraph();
		
		graph.takeValueForKeyPath("column", "chart.type");
		
		graph.takeValueForKeyPath("Fruit Barrel Count", "title.text");
		graph.takeValueForKeyPath("Last Five Iterations", "subtitle.text");
		graph.takeValueForKeyPath(0, "yAxis.min");
		graph.takeValueForKeyPath("Barrels", "yAxis.title.text");
		graph.takeValueForKeyPath(true, "plotOptions.bar.dataLabels.enabled");
		
		graph.takeValueForKeyPath("vertical", "legend.layout");
		graph.takeValueForKeyPath("right", "legend.align");
		graph.takeValueForKeyPath("top", "legend.verticalAlign");
		graph.takeValueForKeyPath(-100, "legend.x");
		graph.takeValueForKeyPath(10, "legend.y");
		graph.takeValueForKeyPath(true, "legend.floating");
		graph.takeValueForKeyPath(1, "legend.borderWidth");
		graph.takeValueForKeyPath("#FFFFFF", "legend.backgroundColor");
		graph.takeValueForKeyPath(true, "legend.shadow");
		
		//now build the series from the 'data' object
		List<Object> seriesList = new ArrayList<Object>();

		NSArray<String> keys = data.allKeys();
		for (String key : keys) {
			NSMutableArray<BigDecimal> list = data.objectForKey(key);
			KVCAExtensionGraph series = new KVCAExtensionGraph();
			series.takeValueForKeyPath(key, "name");
			series.takeValueForKeyPath(list, "data");
			seriesList.add(series);
		}

		graph.takeValueForKeyPath(seriesList, "series");
		graph.takeValueForKeyPath(categories, "xAxis.categories");

		//a nicer tool tip
		graph.takeValueForKeyPath(new ERHighchartsFunction(
				"function() { return '' + this.x + ' : '+this.series.name+' <b>'+ this.y +' Barrels</b>'; }"
				), "tooltip.formatter");

		graph.takeValueForKeyPath("Barrels of Fruit per Inner System Object", "exporting.filename");		

		return graph;
	}
	
}
