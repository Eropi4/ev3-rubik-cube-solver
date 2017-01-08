package ev3.rubikcubesolver.control;

import java.security.InvalidParameterException;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;

public class Gate {
	
	protected static final int RESET_ANGLE = -270;
	protected static final int RESET_SPEED = 120;
	
	protected static final int WORKING_SPEED = 230;
	
	protected static final int OPEN_ANGLE = 5;
	protected static final int HOLD_ANGLE = 115;
	
	protected static final int FLIP_SPEED = 410;
	protected static final int FLIP_ANGLE = 220;
	
	
	protected BaseRegulatedMotor motor;
	
	public Gate(Port port) {
		this.motor = new EV3LargeRegulatedMotor(port);
	}
	
	public void reset() {
		motor.setSpeed(RESET_SPEED);
		motor.rotate(RESET_ANGLE);
		motor.resetTachoCount();
		motor.setSpeed(WORKING_SPEED);
		open();
	}
	
	public void open() {
		motor.rotateTo(OPEN_ANGLE);
	}
	
	public void hold() {
		motor.rotateTo(HOLD_ANGLE);
	}
	
	public void flip(boolean thenOpen) {
		motor.setSpeed(FLIP_SPEED);
		motor.rotateTo(FLIP_ANGLE);
		motor.setSpeed(WORKING_SPEED);
		if(thenOpen) {
			open();
		}
		else {
			hold();
		}
	}
	
	public void flip() {
		flip(false);
	}
	
	public void multiFlip(int count, boolean thenOpen) {
		if(count < 1) {
			throw new InvalidParameterException("Count must be equal or greater than 1");
		}
		while(--count > 0) {
			flip(false);
		}
		flip(thenOpen);
	}
	
	public void multiFlip(int count) {
		multiFlip(count);
	}
}
