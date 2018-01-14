package org.usfirst.frc.team5254.subsystems;

import org.usfirst.frc.team5254.commands.DrivetrainDriveWithJoystick;
import org.usfirst.frc.team5254.robot.RobotMap;


import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {
	
	public static VictorSP driveControllerLeft1 = new VictorSP(RobotMap.DRIVETRAIN_LEFT_TALON);
	public static VictorSP driveControllerLeft2 = new VictorSP(RobotMap.DRIVETRAIN_LEFT_TALON2);
	public static SpeedControllerGroup driveControllersLeft = new SpeedControllerGroup(driveControllerLeft1, driveControllerLeft2);
	
	public static VictorSP driveControllerRight1 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_TALON);
	public static VictorSP driveControllerRight2 = new VictorSP(RobotMap.DRIVETRAIN_RIGHT_TALON2);
	public static SpeedControllerGroup driveControllersRight = new SpeedControllerGroup(driveControllerRight1, driveControllerRight2);
	
	public static DifferentialDrive drivetrain = new DifferentialDrive(driveControllersLeft, driveControllersRight); 
	public static Solenoid shiftingPiston = new Solenoid(RobotMap.SHIFTING_PISTON);
	
	public Drivetrain() {	
	}
	public void drive(double Throttle,double Turn) {
		drivetrain.arcadeDrive(Throttle, Turn);
	}

	public void stop() {
		drivetrain.arcadeDrive(0.0,0.0);
	}
	public void shiftDown() {
		shiftingPiston.set(true);
	}
	public void shiftUp() {
		shiftingPiston.set(false);
	}
	public void slowTurn(double Throttle, double Turn) {
		drivetrain.arcadeDrive(Throttle, 0.5 * Turn);
	}
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new DrivetrainDriveWithJoystick());
		
	}
}