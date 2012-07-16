package er.highcharts.demo.components.pages;

import org.joda.time.DateTime;

import com.webobjects.appserver.WOContext;
import com.webobjects.appserver.WOResponse;
import com.webobjects.foundation.NSArray;
import com.webobjects.foundation.NSMutableArray;

import er.ajax.AjaxUtils;
import er.extensions.components.ERXComponent;
import er.highcharts.control.ERHighchartsRequestHandler;
import er.highcharts.demo.model.TriageDiagnosis;

public class RawHighchartPage extends ERXComponent {
  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RawHighchartPage(WOContext context) {
        super(context);
    }
	
	private NSMutableArray<TriageDiagnosis> _diagArray = null;
	public TriageDiagnosis diagIter;
	public int diagIndex;
	public NSArray<TriageDiagnosis> diagArray(){
		if(_diagArray==null){
			NSMutableArray<TriageDiagnosis> list = new NSMutableArray<TriageDiagnosis>();
			
			TriageDiagnosis med = new TriageDiagnosis("Medical", 1);
			med.addDivision("Cardiac", 3);
			med.addDivision("Irilogical", 15);
			med.addDivision("Gastrointerology", 32);
			
			list.add(med);
			
			TriageDiagnosis sur = new TriageDiagnosis("Surgical", 2);
			sur.addDivision("Orthopaedics", 20);
			sur.addDivision("Head and Neck", 14);
			sur.addDivision("ENT", 32);
			
			list.add(sur);
			
			TriageDiagnosis psy = new TriageDiagnosis("Psychiatric", 3);
			psy.addDivision("Depression", 3);
			list.add(psy);
			
			_diagArray = list;
			
		}
		
		return _diagArray;
	}

	public NSArray<String> innerSeries(){
		return diagIter.divisions.allKeys();
	}
	public String innerSeriesIter;
	public int innerSeriesIndex;
	
	public Integer innerSeriesIterAmount(){
		return diagIter.divisions.objectForKey(innerSeriesIter);
	}
	
    /**
     * Generated id for this chart's html element container
     * @return
     */
    public String chartContainerID(){
    	return "erhighcharts_"+this.hashCode();
    }
    
    
    public String title = "Emergency Ward - Triage Breakdown";
    public String exportFilename= "Emergency Ward - Request Breakdown - "+new DateTime();
    
    public String exportURL(){
		return ERHighchartsRequestHandler.transcodeUrl(context(), null);
    }
    
	/**
	 * Ensure the JavaScript resources are present in the HTML document.
	 */
	public void appendToResponse(WOResponse response, WOContext context){
		AjaxUtils.addScriptResourceInHead(context, response, "Ajax", "prototype.js");
		AjaxUtils.addScriptResourceInHead(context, response, "Ajax", "effects.js");
		AjaxUtils.addScriptResourceInHead(context, response, "Ajax", "wonder.js");

		AjaxUtils.addScriptResourceInHead(context, response, "ERHighcharts", "prototype-adapter.js");
		AjaxUtils.addScriptResourceInHead(context, response, "ERHighcharts", "highcharts.js");
		AjaxUtils.addScriptResourceInHead(context, response, "ERHighcharts", "exporting.js");
		super.appendToResponse(response, context);
	}

    
}