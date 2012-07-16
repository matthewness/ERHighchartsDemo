package er.highcharts.demo.components;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;
import er.highcharts.demo.model.Company;
import er.highcharts.demo.model.DemoDataCollection;
import er.highcharts.model.KVCAExtensionGraph;

public class ERHighchartsDemoComponent extends ERXComponent {
	
	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(ERHighchartsDemoComponent.class);
	
	private Company company;

	/**
	 * Standard constructor
	 * @param context
	 */
    public ERHighchartsDemoComponent(WOContext context) {
        super(context);
    }

	public Company company() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
    
	
	
	public KVCAExtensionGraph byoGraph() {
		KVCAExtensionGraph graph = new KVCAExtensionGraph();
		graph.takeValueForKeyPath("BYO Chart", "title.text");
		return graph;
	}

    public String byoName;
    public Object byoValue;
    
	public DemoDataCollection dataCollection = new DemoDataCollection("Example Chart!");

    public WOComponent addEntryAction(){
    	dataCollection.addData(new DateTime(), new BigDecimal(byoValue.toString()));
    	byoValue=null;
    	log.debug("demo data collection added values to make.["+dataCollection+"]");
    	return null;
    }
    
    public List<DemoDataCollection> byoSeries(){
    	List<DemoDataCollection> list = new ArrayList<DemoDataCollection>();
    	list.add(dataCollection);
    	log.debug("returning single item List of a demo data collection ["+dataCollection+"]");
    	return list;
    }
     
    public WOComponent buildChartAction(){
    	return null;
    }
}
