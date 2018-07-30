package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorHold;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 * The elevator subsystem consists of a two stage elevator with a carriage. The elevator raises and lowers by being powered by
 * a mini CIM motor thats controlled by a talon. The talon is also hooked up to an encoder and uses a one way bang bang control
 * method so that the elevator can raise to set points accurately. A button at the bottom of the elevator tells the subsystem when 
 * it has reached the bottom which allows the bang bang to be a one way control. The main things this subsystem does is raise and
 * lower to set points/desired heights if you will.
 */
public class Elevator extends Subsystem {

	// Initializing auto Controllers
	public TalonSRX elevator = new TalonSRX(RobotMap.ELEVATOR);
	
	// Init Button
	public DigitalInput eleButton = new DigitalInput(RobotMap.ELE_BUTTON);
	
	// Define other variables
	public static Timer timer = new Timer();
	public double finalSpeed;
	public double remainingDistance;
	public double Speed;
	public double Distance;
	public int ticks;

	public void initElevator() {
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10); // from example code - don't remove things don't work without it
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);// sets the encoder to the mag encode on GB
	}
	
	// Default Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorHold());
	}

	/**
	 * Sets the elevator motor on to what ever speed is set by the parameter
	 * 
	 * <p>
	 * Note if the elevator is being powered down but its already pressing the button at the bottom
	 * the elevator wont power down more - this is a precaution setup for obvious reasons
	 * 
	 * @param Speed The power of the motor
	 */
	public void on(double Speed) {
		if (eleButton.get() == false) { // if the button is pressed
			if (Speed > 0) { // and if elevator is going down
				elevator.set(ControlMode.PercentOutput, 0.0);// stop
				elevator.setSelectedSensorPosition(0, 0, 10);// zero encoder
			} else { // if elevator is going up
				elevator.set(ControlMode.PercentOutput, Speed);// motor at set speed
			}
		} else {// if button isnt pressed
			elevator.set(ControlMode.PercentOutput, Speed);// motor at set speed
		}		
	}

	/**
	 * This is a one way bang bang controller I've been told is quite jank but frankly I'm proud of it.
	 * Once the elevator reaches/exceeds the tick parameter the command ends
	 * 
	 * @param ticks How high to raise elevator, 0 ticks is bottom and 1 inch is equivalent to ~1000 ticks
	 */
	public void setToHeight(int ticks) {
		this.ticks = ticks;
		
		// TODO test this are these okay to be commented out
//			if (ticks > Math.abs(elevator.getSelectedSensorPosition(0))) { // if im not at set position
				elevator.set(ControlMode.PercentOutput, -1);//elevator go up 100% power
//			} else { // at/past set position
//				off(); // elevator set power 0%
//		}
	}

	/**
	 * Ends the setHeight command
	 * 
	 * @return <code> true </code> when elevator reaches set point
	 */
	public boolean endSetHeight() { 
		return (Math.abs(elevator.getSelectedSensorPosition(0)) > ticks);
	}

	/**
	 * Ends the slowDown command
	 * 
	 * @return <code> true </code> when button is pressed
	 */
	public boolean endSlowDown() {
		return (eleButton.get() == false);
	}

	/**
	 * sets elevator motor to 0% power
	 */
	public void off() {
		elevator.set(ControlMode.PercentOutput, 0.0);
	}
	
	// fail safe code
	public boolean mightBreak() {
		// If this ever equals true I failed as a programmer
		return false;
	}

	public void dontBreak() {
		// This runs when we dont want the elevator to break - purely precautionary because it'll never break.. Right? 
	}

}