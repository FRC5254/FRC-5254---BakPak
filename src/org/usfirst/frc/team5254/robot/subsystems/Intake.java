package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.IntakeClose;
import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Initializing controllers
	
	public Spark leftFlywheels;
	public Spark rightFlywheels;
	
	public DoubleSolenoid armPistons;
	public DoubleSolenoid wingPiston;
	
	public static final double autoSpeed = 0.65;

	public Intake() {
		leftFlywheels = new Spark(RobotMap.CUBE_MECH_LEFT_FLYWHEEL);
		rightFlywheels = new Spark(RobotMap.CUBE_MECH_RIGHT_FLYWHEEL);
		armPistons = new DoubleSolenoid(RobotMap.INTAKE_ARMS_IN, RobotMap.INTAKE_ARMS_OUT);
		wingPiston = new DoubleSolenoid(RobotMap.INTAKE_CLAMP, RobotMap.INTAKE_RELEASE);
	}
	
	public void setSpeed(double speed) {
		leftFlywheels.set(speed);//both .75
		rightFlywheels.set(speed);
	}

	// this is used in auto for intaking the cube as well as dropping the cube in teleop
	public void open() {
		armPistons.set(DoubleSolenoid.Value.kReverse);
		wingPiston.set(DoubleSolenoid.Value.kReverse);
	}
	// this is used to intake in teleop and to hold a 13" wide cube
	public void close() {
		armPistons.set(DoubleSolenoid.Value.kForward);
		wingPiston.set(DoubleSolenoid.Value.kReverse);
	}
	
	// this is used to hold a 11" wide cube
	public void clamp() {
		armPistons.set(DoubleSolenoid.Value.kForward);
		wingPiston.set(DoubleSolenoid.Value.kForward);
	}
	
	// Default command
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeClose());
	}
}
