package ev3.rubikcubesolver;

import ev3.rubikcubesolver.control.ColorScanner;
import ev3.rubikcubesolver.control.Platform;
import lejos.hardware.Button;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class RubikCubeSolver {

	protected static void test(ColorScanner cs) {
		Button.waitForAnyPress();
		if(Button.DOWN.isDown()) {
			System.out.println(cs.identifyColor());
			test(cs);
		}
	}
	
	public static void main(String[] args) {
		/*EV3ColorSensor color = new EV3ColorSensor(SensorPort.S1);
		
		GraphicsLCD lcd = LocalEV3.get().getGraphicsLCD();
		final int SW = lcd.getWidth();
        final int SH = lcd.getHeight();
		
		LED led = LocalEV3.get().getLED();
		
		lcd.clear();
		lcd.setFont(Font.getLargeFont());
		led.setPattern(LEDPattern.LED_GREEN_BLINK);
        
		MotorRotater mr = new MotorRotater(new EV3LargeRegulatedMotor(MotorPort.A));
		mr.start();
		
        while(Button.ESCAPE.isUp()) {
        	lcd.clear();
        	lcd.drawString(Color.identify(color).getName(), SW/2, SH/2, GraphicsLCD.BASELINE|GraphicsLCD.HCENTER);
        	Delay.msDelay(250);
        }
        
        lcd.clear();
        lcd.refresh();
        
        led.setPattern(LEDPattern.LED_RED);
        
        Delay.msDelay(500);*/
		
		
		//Gate gate = new Gate(MotorPort.B/);
		Platform platform = new Platform(MotorPort.A);
		
		
		/*gate.reset();
		
		Delay.msDelay(1000);
		
		//gate.multiFlip(3, true);
		//platform.rotate(3);
		int c = 5;
		gate.hold();
		
		while(c-- > 0 && Button.ESCAPE.isUp()) {
			gate.flip();
			platform.rotate(1);
			gate.flip();
			platform.rotate(-1);
		}*/
		
		//EV3LargeRegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.A);
		
		/*while(Button.ESCAPE.isUp()) {
			if(Button.DOWN.isDown()) {
				m.rotate(-30);
			}
			if(Button.UP.isDown()) {
				m.rotate(30);
			}
		}*/
		ColorScanner cs = new ColorScanner(MotorPort.C, SensorPort.S2);
		cs.reset();
		
		cs.setScanPosition(ColorScanner.SCAN_POSITION_CENTER);
		Delay.msDelay(2000);
		System.out.println(cs.identifyColor());
		test(cs);
		
		cs.setScanPosition(ColorScanner.SCAN_POSITION_MIDDLE);
		Delay.msDelay(2000);
		System.out.println(cs.identifyColor());
		test(cs);
		
		cs.setScanPosition(ColorScanner.SCAN_POSITION_CORNER, true);
		platform.setScanPosition(-1, true);
		Delay.msDelay(2000);
		System.out.println(cs.identifyColor());
		test(cs);
		
		platform.setScanPosition(1);
		Delay.msDelay(2000);
		System.out.println(cs.identifyColor());
		test(cs);
		
		platform.setScanPosition(0, true);
		cs.goHome(true);
		
		
		/*platform.setScanPosition(1);
		Button.waitForAnyPress();
		platform.setScanPosition(-1);
		Button.waitForAnyPress();
		platform.setScanPosition(0);*/
		
		Button.waitForAnyPress();
		
		
		//gate.open();
		
        System.exit(0);
	}

}
