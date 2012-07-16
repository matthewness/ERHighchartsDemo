package er.highcharts.demo.components.pages;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;
import com.webobjects.eocontrol.EOCustomObject;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSMutableArray;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.ERHighchartsFunction;
import er.highcharts.model.KVCAExtensionGraph;

public class PieChartPage extends ERHighchartsDemoComponent {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(PieChartPage.class);


	/**
	 * Standard constructor
	 * @param context
	 */
    public PieChartPage(WOContext context) {
        super(context);
    }
   
    /**
     * The graph mappings for the bound series List.
     * 
     * As the series is a List/NSArray of Departments, and we want to show the
     * number of Fleet Vehicles for each Department as a wedge of the pie,
     * we tell the ERPieChart to map the value of 'fleet.count' to the
     * series data 'y' key.
     * 
     * @return
     */
	public NSDictionary<String, String> mappings() {
		return new NSDictionary<String, String>("fleet.count", "y");
	}

	private KVCAExtensionGraph _graph = null;

	public KVCAExtensionGraph graph() {
		log.debug(company());

		if (_graph == null) {
			_graph = new KVCAExtensionGraph();

			_graph.takeValueForKeyPath("", "chart.plotBackgroundColor");
			_graph.takeValueForKeyPath(false, "chart.plotWidth");

			_graph.takeValueForKeyPath("Department Fleet Numbers", "title.text");

			_graph.takeValueForKeyPath(true, "plotOptions.pie.allowPointSelect");
			_graph.takeValueForKeyPath("pointer", "plotOptions.pie.cursor");
			_graph.takeValueForKeyPath(true, "plotOptions.pie.dataLabels.enabled");
			_graph.takeValueForKeyPath("#000000", "plotOptions.pie.dataLabels.color");
			_graph.takeValueForKeyPath("#000000", "plotOptions.pie.dataLabels.connectorColor");

			//an example of using a JavaScript function external to the JSON 
			//chart generation, called from within the JSON
			_graph.takeValueForKeyPath(new ERHighchartsFunction(pieChartTooltipFunctionName()), "tooltip.formatter");


		}
		return _graph;
	}

	/**
	 * An example of dynamically generating the JavaScript function name
	 * in a component which may harbour more than one external Highchart
	 * Javascript function calls.
	 * 
	 * @return
	 */
    public String pieChartTooltipFunctionName(){
    	return "pieChartTooltipFunction_"+this.hashCode();
    }
    
    
    public NSMutableArray<Wedge> byoChartWedges = new NSMutableArray<PieChartPage.Wedge>();

    public WOComponent addEntryAction(){
    	byoChartWedges.add(new Wedge(byoName, byoValue));
    	byoName=null;
    	byoValue=null;
    	return null;
    }
    
    public WOComponent removeEntryAction(){
    	byoChartWedges.removeObject(wedgeIter);
    	return null;
    }
    
    public Wedge wedgeIter;
    
	public class Wedge extends EOCustomObject {
		public String name;
		public Object y;
		
		public Wedge(String name, Object y){
			this.name = name;
			this.y = y;
		}
		
	}
	
}