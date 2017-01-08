package ev3.rubikcubesolver.control;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;

import ev3.rubikcubesolver.cube.Cube;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.Color;

public class ColorScanner {
	
	public static final int SCAN_POSITION_CENTER = 0;
	public static final int SCAN_POSITION_MIDDLE = 1;
	public static final int SCAN_POSITION_CORNER = 2;
	
	protected static final int RESET_ANGLE = -645;
	protected static final int RESET_FORCE_ANGLE = 650;
	protected static final int RESET_SPEED = 360;
	
	protected static final int WORKING_SPEED = 720;
	
	protected static final int[] SCAN_ANGLES = {0,170,225};
	
	protected static final int HOME_ANGLE = 555;
	
	protected BaseRegulatedMotor motor;
	protected EV3ColorSensor sensor;
	
	public Map<Integer, Cube.Color> colorMap;
	
	public ColorScanner(Port motorPort, Port sensorPort, Map<Integer, Cube.Color> colorMap) {
		motor = new EV3MediumRegulatedMotor(motorPort);
		sensor = new EV3ColorSensor(sensorPort);
		sensor.setCurrentMode("ColorID");
		
		if(colorMap == null) {
			colorMap = new HashMap<Integer, Cube.Color>();
			colorMap.put(Color.RED, Cube.Color.RED);
			colorMap.put(Color.GREEN, Cube.Color.GREEN);
			colorMap.put(Color.BLUE, Cube.Color.BLUE);
			colorMap.put(Color.YELLOW, Cube.Color.YELLOW);
			colorMap.put(Color.WHITE, Cube.Color.WHITE);
			colorMap.put(Color.BLACK, Cube.Color.ORANGE);
			colorMap.put(Color.BROWN, Cube.Color.RED);
			colorMap.put(Color.NONE, Cube.Color.NONE);
		}
		this.colorMap = colorMap;
	}
	
	public ColorScanner(Port motorPort, Port sensorPort) {
		this(motorPort, sensorPort, null);
	}

	public void reset() {
		motor.setSpeed(RESET_SPEED);
		motor.rotate(RESET_FORCE_ANGLE);
		motor.rotate(RESET_ANGLE);
		motor.resetTachoCount();
		motor.setSpeed(WORKING_SPEED);
		goHome();
	}
	
	public void setScanPosition(int scanPosition, boolean immediateReturn) {
		if(scanPosition < 0 || scanPosition >= SCAN_ANGLES.length) {
			throw new InvalidParameterException("Invalid scan position: " + Integer.toString(scanPosition));
		}
		motor.rotateTo(SCAN_ANGLES[scanPosition], immediateReturn);
	}
	
	public void setScanPosition(int scanPosition) {
		setScanPosition(scanPosition, false);
	}
	
	public void goHome(boolean immediateReturn) {
		motor.rotateTo(HOME_ANGLE);
	}
	
	public void goHome() {
		goHome(false);
	}
	
	public boolean isMoving() {
		return motor.isMoving();
	}
	
	public Cube.Color identifyColor() {
		int colorID = sensor.getColorID();
    	if(!colorMap.containsKey(colorID)) {
    		throw new InvalidParameterException("Color map does not contain " + Integer.toString(colorID));
    	}
    	return colorMap.get(colorID);
	}
	
}
