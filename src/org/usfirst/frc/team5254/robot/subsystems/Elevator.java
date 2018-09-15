package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ElevatorHold;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

public class Elevator extends Subsystem {

	// Initializing auto Controllers
	public TalonSRX elevator;
	
	// Init Button
	public DigitalInput eleButton;

	public Elevator() {
		
		elevator = new TalonSRX(RobotMap.ELEVATOR);
		eleButton = new DigitalInput(RobotMap.ELE_BUTTON);
		
		elevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10); // from example code
		elevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);// sets the encoder to the mag encode on GB
	}

	// TeleOp Method
	public void setSpeed(double speed) {
		elevator.set(ControlMode.PercentOutput, speed);
	}


	public boolean endSetHeight(double ticks) {
		return (Math.abs(elevator.getSelectedSensorPosition(0)) > ticks);// return true when elevator reaches set point
	}

	public boolean isAtBottom() {
		return (eleButton.get() == false); // returns true when button is pressed
	}

	public void resetEncoder() {
		elevator.setSelectedSensorPosition(0, 0, 10);
	}
	// Auto Methods
	
	// Defualt Command
	public void initDefaultCommand() {
		setDefaultCommand(new ElevatorHold());
	}

	public boolean mightBreak() {
		// If this ever equals true I've failed as a programmer
		return false;
	}
	
	public void dontBreak() {
		// This runs when we dont want the elevator to break - purely precautionary because itll never break.. Right?
	}

}
