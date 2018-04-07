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
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10); // from example code
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);// sets the encoder to the mag encode on GB
	}
	
	

	// TeleOp Method
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
//		System.out.println(elevator.getSelectedSensorPosition(0));
//		System.out.println(((elevator.getSelectedSensorPosition(0)) / 256) * (1.273 * Math.PI));
	}

	public void setToHeight(int ticks) {
		this.ticks = ticks;
		
			if (ticks > Math.abs(elevator.getSelectedSensorPosition(0))) { // if im not at set position
				elevator.set(ControlMode.PercentOutput, -1);//elevator go up 100% power
			} else { // at/past set position
				off(); // elevator set power 0%
		}
	}

	public boolean endSetHeight() {
		return (Math.abs(elevator.getSelectedSensorPosition(0)) > ticks);// return true when elevator reaches set point
	}

	public boolean endSlowDown() {
		return (eleButton.get() == false); // returns true when button is pressed
	}

	public void off() {
		elevator.set(ControlMode.PercentOutput, 0.0);// sets elevator motor to 0% power
	}

	// Auto Methods
	
	// Defualt Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorHold());
	}

}
