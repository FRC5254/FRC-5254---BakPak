package org.usfirst.frc.team5254.subsystems;

import org.usfirst.frc.team5254.commands.DriveWithJoystick;
import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	public static RobotDrive drivetrain = new RobotDrive(RobotMap.DRIVETRAIN_LEFT1_MOTOR,RobotMap.DRIVETRAIN_LEFT2_MOTOR,RobotMap.DRIVETRAIN_RIGHT1_MOTOR,RobotMap.DRIVETRAIN_RIGHT2_MOTOR); 
	public static Solenoid shiftingPiston = new Solenoid(RobotMap.SHIFTING_PISTON);
	
	public Drivetrain() {
		
		
	}
	public void drive(double Throttle,double Turn) {
		drivetrain.arcadeDrive(Throttle, Turn);
	}

	public void stop() {
		drivetrain.arcadeDrive(0.0,0.0);
	}
	public void shiftDown () {
		shiftingPiston.set(true);
	}
	public void shiftUp () {
		shiftingPiston.set(false);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
		
	}
}