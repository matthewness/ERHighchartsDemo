package er.highcharts.demo.components.pages;

import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;
import com.webobjects.foundation.NSTimestamp;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.ERHighchartsFunction;
import er.highcharts.model.KVCAExtensionGraph;

public class LineChartPage extends ERHighchartsDemoComponent {


	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(LineChartPage.class);

	/**
	 * Standard constructor
	 * @param context
	 */
	public LineChartPage(WOContext context) {
        super(context);
    }
    
	   
    /**
     * The graph mappings for the bound series List.
     * 
     * As the series is a List/NSArray of Departments, and we want to show the
     * financial data for each Department as a line in the chart,
     * we tell the ERLineChart to map the value of 'monthlyResults' to the
     * series 'data' key.
     * 
     * @return
     */
	public NSDictionary<String, String> mappings() {
		return new NSDictionary<String, String>("monthlyResults", "data");
	}

	private KVCAExtensionGraph _graph = null;

	public KVCAExtensionGraph graph() {
		log.debug(company());

		if (_graph == null) {
			_graph = new KVCAExtensionGraph();

			_graph.takeValueForKeyPath("$$$$", "yAxis.title");
			_graph.takeValueForKeyPath("Department Financials", "title.text");
			
		}
		return _graph;
	}

	
       
	
	public KVCAExtensionGraph byoGraph() {
		KVCAExtensionGraph graph = super.byoGraph();
		
		graph.takeValueForKeyPath("datetime", "xAxis.type");
		
		graph.takeValueForKeyPath(
				new ERHighchartsFunction(
						"function() {return '<b>'+ this.series.name +'</b><br/>'+Highcharts.dateFormat('%e. %b %Y, %I:%M:%S', this.x) +': '+ this.y +' things';}"	
					), 
				"tooltip.formatter");

		return graph;
	}

    
    
}