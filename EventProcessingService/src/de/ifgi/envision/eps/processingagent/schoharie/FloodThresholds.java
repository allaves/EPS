package de.ifgi.envision.eps.processingagent.schoharie;

/**
 * Contains the constant variables for the gauge height thresholds (in feet) of the various monitoring stations
 * See http://water.weather.gov/ahps/
 * @author a_llav02
 *
 */
public final class FloodThresholds {

	/**
	 * SCHOHARIE CREEK AT BREAKABEEN - http://water.weather.gov/ahps2/hydrograph.php?wfo=aly&gage=bkbn6
	 */
	public static final double BREAKABEEN_ACTION_STAGE = 8;
	public static final double BREAKABEEN_MINOR_FLOOD_STAGE = 11;
	public static final double BREAKABEEN_MODERATE_FLOOD_STAGE = 16;
	public static final double BREAKABEEN_MAJOR_FLOOD_STAGE = 18;
	// Site id
	public static final String BREAKABEEN_SITE_CODE = "01350355";
	
	
	/**
	 * Schoharie Reservoir near Grand Gorge (Gilboa Dam) - http://water.weather.gov/ahps2/hydrograph.php?wfo=aly&gage=giln6
	 */
	// Flood categories
	public static final double GILBOA_DAM_ACTION_STAGE = 1124.82;
	public static final double GILBOA_DAM_MINOR_FLOOD_STAGE = 1130.5;
	public static final double GILBOA_DAM_MODERATE_FLOOD_STAGE = 1131.5;
	public static final double GILBOA_DAM_MAJOR_FLOOD_STAGE = 1133.5;
	// Site id
	public static final String GILBOA_DAM_SITE_CODE = "01350100";

	
	/**
	 * Schoharie Creek at Gilboa Bridge - http://water.weather.gov/ahps2/hydrograph.php?wfo=aly&gage=gbrn6
	 */
	// Flood categories
	public static final double GILBOA_BRIDGE_ACTION_STAGE = 18;
	public static final double GILBOA_BRIDGE_MINOR_FLOOD_STAGE = 20;
	// Site id
	public static final String GILBOA_BRIDGE_SITE_CODE = "01350101";
	
	
	/**
	 * Schoharie Creek at Prattsville - http://water.weather.gov/ahps2/hydrograph.php?wfo=aly&gage=ptvn6
	 */
	// Flood categories
	public static final double PRATTSVILLE_ACTION_STAGE = 9;
	public static final double PRATTSVILLE_MINOR_FLOOD_STAGE = 12;
	public static final double PRATTSVILLE_MODERATE_FLOOD_STAGE = 14;
	public static final double PRATTSVILLE_MAJOR_FLOOD_STAGE = 16;
	// Site id
	public static final String PRATTSVILLE_SITE_CODE = "01350000";
	public static final String PRATTSVILLE_SITE_URL = "http://waterdata.usgs.gov/ny/nwis/uv/?site_no=01350000";
	// Site id
	public static final String PRATTSVILLE_FOI = "http://geonames.usgs.gov/pls/gnispublic/f?p=129:3:3551858265178114::NO::P3_FID,P3_TITLE:964599%2CSchoharie%20Creek";
	
	
	/**
	 * Schoharie Creek at Burtonsville - http://water.weather.gov/ahps2/hydrograph.php?wfo=aly&gage=brtn6
	 */
	// Flood categories
	public static final double BURTONSVILLE_ACTION_STAGE = 4.5;
	public static final double BURTONSVILLE_MINOR_FLOOD_STAGE = 6;
	public static final double BURTONSVILLE_MODERATE_FLOOD_STAGE = 8;
	public static final double BURTONSVILLE_MAJOR_FLOOD_STAGE = 10;
	// Site id
	public static final String BURTONSVILLE_SITE_CODE = "01351500";
	
	
	/**
	 * Avoids external and internal constructor calls
	 */
	private FloodThresholds() {
		throw new AssertionError(); 
	}
}
