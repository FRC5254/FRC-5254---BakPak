package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.Robot;
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
	public TalonSRX elevatorTalon;
	
	// Init Button
	public DigitalInput eleButton;

	public Elevator() {
		
		elevatorTalon = new TalonSRX(RobotMap.ELEVATOR);
		eleButton = new DigitalInput(RobotMap.ELE_BUTTON);
		
		//TODO put like a modeConfig here (brake vs coast) i forgot which one - ask Rory
		elevatorTalon.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10); // from example code
		elevatorTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);// sets the encoder to the mag encode on GB
	}

	// TeleOp Method
	public void setSpeed(double speed) {
		elevatorTalon.set(ControlMode.PercentOutput, speed);
	}
	
	public int getHeight() {
		return  Math.abs(Robot.Elevator.elevatorTalon.getSelectedSensorPosition(0));
	}

	public boolean isAboveHeight(double ticks) {
		return (Math.abs(elevatorTalon.getSelectedSensorPosition(0)) > ticks);// return true when elevator reaches set point
	}

	public boolean isAtBottom() {
		return (eleButton.get() == false); // returns true when button is pressed
	}

	public void resetEncoder() {
		elevatorTalon.setSelectedSensorPosition(0, 0, 10);
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
