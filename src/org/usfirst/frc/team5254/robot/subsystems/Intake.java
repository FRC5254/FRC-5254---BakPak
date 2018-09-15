package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;

import org.usfirst.frc.team5254.robot.commands.IntakeSetSpeed;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Initializing controllers
	
	public Spark intakeLeftFlywheels;
	public Spark intakeRightFlywheels;
	
	public static final double autoSpeed = 0.65;

	public Intake() {
		intakeLeftFlywheels = new Spark(RobotMap.CUBE_MECH_LEFT_FLYWHEEL);
		intakeRightFlywheels = new Spark(RobotMap.CUBE_MECH_RIGHT_FLYWHEEL);
	}
	
	public void setSpeed(double speed) {
		intakeLeftFlywheels.set(speed);//both .75
		intakeRightFlywheels.set(speed);
	}

	// Default command
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeSetSpeed(0));
	}
}
