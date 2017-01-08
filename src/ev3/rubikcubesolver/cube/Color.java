package ev3.rubikcubesolver.cube;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;


public class Color {
	
	public static final int RED = 0;
	public static final int GREEN = 1;
	public static final int BLUE = 2;
	public static final int YELLOW = 3;
	public static final int ORANGE = 5;
	public static final int WHITE = 6;
    public static final int NONE = -1;
    
    @SuppressWarnings("serial")
    protected static Map<Integer, String> nameMap = new HashMap<Integer, String>() {{
    	put(RED, "RED");
    	put(GREEN, "GREEN");
    	put(BLUE, "BLUE");
    	put(YELLOW, "YELLOW");
    	put(ORANGE, "ORANGE");
    	put(WHITE, "WHITE");
    	put(NONE, "NONE");
    }};
    
    @SuppressWarnings("serial")
	protected static Map<Integer, Integer> identifyMap = new HashMap<Integer, Integer>() {{
    	put(lejos.robotics.Color.RED, RED);
    	put(lejos.robotics.Color.GREEN, GREEN);
    	put(lejos.robotics.Color.BLUE, BLUE);
    	put(lejos.robotics.Color.YELLOW, YELLOW);
    	put(lejos.robotics.Color.WHITE, WHITE);
    	put(lejos.robotics.Color.BLACK, ORANGE);
    	put(lejos.robotics.Color.BROWN, RED);
    	put(lejos.robotics.Color.NONE, NONE);
    }};
    
    public static String getColorNameByID(int colorID) {
    	return nameMap.get(colorID);
    }
    
    /*public static Color identify(ColorIdentifier identifier) {
    	int colorID = identifier.getColorID();
    	return new Color(identifyMap.get(colorID), colorID);
    }*/
    
    protected int colorID;
    protected String customInfo = null;
    public Color(int colorID) {
    	if(!nameMap.containsKey(colorID)) {
    		throw new InvalidParameterException("Invalid colorID: + " + Integer.toString(colorID));
    	}
    	this.colorID = colorID;
    }
    
    public Color(int colorID, String customInfo) {
    	this.customInfo = customInfo;
    	this.colorID = colorID;
    }
    
    public String getName() {
    	return getColorNameByID(colorID);
    }
    
    public int getID() {
    	return colorID;
    }
    
    public String getCustomInfo() {
    	return customInfo;
    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder(getName());
    	sb.append("(");
    	sb.append(getID());
    	sb.append("/");
    	sb.append(getCustomInfo());
    	sb.append(")");
    	return sb.toString();
    }
    
}
