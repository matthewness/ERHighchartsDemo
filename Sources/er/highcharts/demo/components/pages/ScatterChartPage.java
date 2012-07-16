package er.highcharts.demo.components.pages;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.KVCAExtensionGraph;

public class ScatterChartPage extends ERHighchartsDemoComponent {


	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(ScatterChartPage.class);

	/**
	 * Standard constructor
	 * @param context
	 */
    public ScatterChartPage(WOContext context) {
        super(context);
    }
    
    
    /**
     * The graph mappings for the bound series List.
     * 
     * @return
     */
	public NSDictionary<String, String> mappings() {
		return new NSDictionary<String, String>("values", "data");
	}
	
	
	private KVCAExtensionGraph _graph = null;

	public KVCAExtensionGraph graph() {
		log.debug(company());

		if (_graph == null) {
			_graph = new KVCAExtensionGraph();			
			
			_graph.takeValueForKeyPath("Litres / 100 Km", "yAxis.title.text");
			_graph.takeValueForKeyPath("Km / Hour", "xAxis.title.text");
			_graph.takeValueForKeyPath("Fleet Vehicles Economy Recordings", "title.text");
			_graph.takeValueForKeyPath("Recordings averaged every week", "subtitle.text");
			

		}
		return _graph;
	}

}