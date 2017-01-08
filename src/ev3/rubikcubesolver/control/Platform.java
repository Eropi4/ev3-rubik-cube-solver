package ev3.rubikcubesolver.control;

import java.security.InvalidParameterException;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Platform {
	
	protected final static int GEAR_RATIO = 3;
	
	protected final static int SPEED = 240 * GEAR_RATIO;
	
	protected final static int ROTATE_ANGLE = 90 * GEAR_RATIO;
	protected final static int FULL_ROTATE = 4;
	
	protected final static int SCAN_ANGLE = 45 * GEAR_RATIO;
	
	
	protected BaseRegulatedMotor motor;
	protected int currentScanPosition = 0;
	protected int currentPosition = 0;
	protected int lastScanTachoCount = 0;
	
	public Platform(Port port) {
		motor = new EV3LargeRegulatedMotor(port);
		motor.setSpeed(SPEED);
	}
	
	public void reset() {
		motor.rotateTo(0);
	}
	
	public void rotate(int direction, int scanPosition, boolean immediateReturn) {
		if(scanPosition < -1 || scanPosition > 1) {
			throw new InvalidParameterException("Scan position must be -1,0,1");
		}
		if(direction == 0 && scanPosition == currentScanPosition) {
			return;
		}
		else if (Math.abs(direction) > 2) {
			direction -= FULL_ROTATE * Math.round((float)direction / (float)FULL_ROTATE);
			if(direction == 0 && scanPosition == currentScanPosition) {
				return;
			}
		}
		motor.stop();
		currentPosition += direction;
		currentScanPosition = scanPosition;
		int toAngle = currentPosition * ROTATE_ANGLE + currentScanPosition * SCAN_ANGLE;
		motor.rotateTo(toAngle, immediateReturn);
		if(immediateReturn == false && currentScanPosition == 0) {
			motor.resetTachoCount();
			currentPosition = 0;
		}
	}
	
	public void rotate(int direction) {
		rotate(direction, currentScanPosition, false);
	}
	
	public void rotate(int direction, boolean immediateReturn) {
		rotate(direction, currentScanPosition, immediateReturn);
	}
	
	public void rotate(int direction, int scanPosition) {
		rotate(direction, scanPosition, false);
	}
	
	public int getPosition() {
		return currentPosition;
	}
	
	public int getScanPosition() {
		return currentScanPosition;
	}
	
	public void setScanPosition(int scanPosition, boolean immediateReturn) {
		rotate(0, scanPosition, immediateReturn);
	}
	
	public void setScanPosition(int scanPosition) {
		setScanPosition(scanPosition, false);
	}
	
	public boolean isMoving() {
		return motor.isMoving();
	}
	
}
