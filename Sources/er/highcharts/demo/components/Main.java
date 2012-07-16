package er.highcharts.demo.components;

import org.apache.log4j.Logger;

import com.webobjects.appserver.WOActionResults;
import com.webobjects.appserver.WOComponent;
import com.webobjects.appserver.WOContext;

import er.extensions.components.ERXComponent;
import er.highcharts.demo.components.pages.AreaChartPage;
import er.highcharts.demo.components.pages.BarChartPage;
import er.highcharts.demo.components.pages.ColumnChartPage;
import er.highcharts.demo.components.pages.LineChartPage;
import er.highcharts.demo.components.pages.PieChartPage;
import er.highcharts.demo.components.pages.ScatterChartPage;
import er.highcharts.demo.components.pages.SplineChartPage;
import er.highcharts.demo.model.Company;

public class Main extends ERXComponent {

	/**
	 * Default serial version id
	 */
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(Main.class);

	private static Company _company = null;

	public Main(WOContext context) {
		super(context);
	}

	public Company company() {
		if (_company == null) {
			_company = Company.buildCompany();
			log.debug("Building company graph:\n"+_company);
		}
		return _company;
	}


	public WOComponent pieChartAction() {
		PieChartPage page = (PieChartPage)pageWithName(PieChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOComponent lineChartAction() {
		LineChartPage page = (LineChartPage)pageWithName(LineChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOComponent splineChartAction() {
		SplineChartPage page = (SplineChartPage)pageWithName(SplineChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOActionResults areaChartAction() {
		AreaChartPage page = (AreaChartPage)pageWithName(AreaChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOActionResults barChartAction() {
		BarChartPage page = (BarChartPage)pageWithName(BarChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOActionResults columnChartAction() {
		ColumnChartPage page = (ColumnChartPage)pageWithName(ColumnChartPage.class.getName());
		page.setCompany(company());
		return page;
	}

	public WOActionResults scatterChartAction() {
		ScatterChartPage page = (ScatterChartPage)pageWithName(ScatterChartPage.class.getName());
		page.setCompany(company());
		return page;
	}
	
	
}
