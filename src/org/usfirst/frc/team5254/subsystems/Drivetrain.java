package org.usfirst.frc.team5254.subsystems;

import org.usfirst.frc.team5254.commands.DriveWithJoystick;
import org.usfirst.frc.team5254.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain extends Subsystem {
	public static WPI_TalonSRX driveControllerLeft = new WPI_TalonSRX(0);
	public static WPI_TalonSRX driveControllerRight = new WPI_TalonSRX(1);
	public static DifferentialDrive drivetrain = new DifferentialDrive(driveControllerLeft, driveControllerRight); 
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