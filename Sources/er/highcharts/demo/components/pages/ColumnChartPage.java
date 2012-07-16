package er.highcharts.demo.components.pages;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSDictionary;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.ERHighchartsFunction;
import er.highcharts.model.KVCAExtensionGraph;

public class ColumnChartPage extends ERHighchartsDemoComponent {


	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(ColumnChartPage.class);

	/**
	 * Standard constructor
	 * @param context
	 */public ColumnChartPage(WOContext context) {
        super(context);
    }
    
    public NSArray<String> categories = new NSArray<String>(new String[]{
    "Jan",
	"Feb",
	"Mar",
	"Apr",
	"May",
	"Jun",
	"Jul",
	"Aug",
	"Sep",
	"Oct",
	"Nov",
	"Dec"});
    
    /**
     * The graph mappings for the bound series List.
     * 
     * As the series is a List/NSArray of Departments, and we want to show the
     * financial data for each Department as a line in the chart,
     * we tell the ERColumnChart to map the value of 'monthlyResults' to the
     * series 'data' key.
     * 
     * @return
     */
	public NSDictionary<String, String> mappings() {
		return new NSDictionary<String, String>("monthlyResults", "data");
	}
	
	private KVCAExtensionGraph _graph = null;

	public KVCAExtensionGraph graph() {

		if (_graph == null) {
			_graph = new KVCAExtensionGraph();

			_graph.takeValueForKeyPath("Department Monthly Results", "title.text");
			_graph.takeValueForKeyPath("2012", "subtitle.text");
			_graph.takeValueForKeyPath("Number of Widgets", "yAxis.title.text");
			
			_graph.takeValueForKeyPath(
					new ERHighchartsFunction(
							"function() { return 'The value for <b>'+ this.x + '</b>  is <b>'+ this.y +'</b>';}"
						), 
					"tooltip.formatter");

		}		
		return _graph;
	}

}