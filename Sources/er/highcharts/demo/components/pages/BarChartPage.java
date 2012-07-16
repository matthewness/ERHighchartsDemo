package er.highcharts.demo.components.pages;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOContext;
import com.webobjects.foundation.NSDictionary;

import er.highcharts.demo.components.ERHighchartsDemoComponent;
import er.highcharts.model.KVCAExtensionGraph;

public class BarChartPage extends ERHighchartsDemoComponent {


	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger
	 */
	private static Logger log = Logger.getLogger(BarChartPage.class);

	/**
	 * Standard constructor
	 * @param context
	 */
	public BarChartPage(WOContext context) {
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

	public KVCAExtensionGraph graph() {
		KVCAExtensionGraph graph = new KVCAExtensionGraph();
		graph.takeValueForKeyPath("Company Department Financial breakdown", "title.text");
		graph.takeValueForKeyPath("Last Three Years", "subtitle.text");
		graph.takeValueForKeyPath(0, "yAxis.min");
		graph.takeValueForKeyPath("Expenditure (gazillions)", "yAxis.title.text");
		graph.takeValueForKeyPath("high", "yAxis.title.align");
		graph.takeValueForKeyPath(true, "plotOptions.bar.dataLabels.enabled");
		
		graph.takeValueForKeyPath("vertical", "legend.layout");
		graph.takeValueForKeyPath("right", "legend.align");
		graph.takeValueForKeyPath("top", "legend.verticalAlign");
		graph.takeValueForKeyPath(-100, "legend.x");
		graph.takeValueForKeyPath(100, "legend.y");
		graph.takeValueForKeyPath(true, "legend.floating");
		graph.takeValueForKeyPath(1, "legend.borderWidth");
		graph.takeValueForKeyPath("#FFFFFF", "legend.backgroundColor");
		graph.takeValueForKeyPath(true, "legend.shadow");
				
		return graph;
	}

}