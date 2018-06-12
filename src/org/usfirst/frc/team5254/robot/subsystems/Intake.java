package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;

import org.usfirst.frc.team5254.robot.commands.IntakeOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

	// Initializing controllers
	public Spark intakeLeftFlywheels = new Spark(RobotMap.CUBE_MECH_LEFT_FLYWHEEL);
	public Spark intakeRightFlywheels = new Spark(RobotMap.CUBE_MECH_RIGHT_FLYWHEEL);

	public Intake() {
	}

	// TeleOp Methods
	public void on(boolean direction) {
		if (direction == true) {
			intakeLeftFlywheels.set(-1);
			intakeRightFlywheels.set(-0.9);
		} else {
			intakeLeftFlywheels.set(0.75);//both .75
			intakeRightFlywheels.set(0.75);
		}
	}

	public void off() {
		intakeLeftFlywheels.set(0);
		intakeRightFlywheels.set(0);
	}
	
	public void autoOn(boolean direction) {
		if (direction == true) {
			intakeLeftFlywheels.set(-1);
			intakeRightFlywheels.set(-0.9);
		} else {
			intakeLeftFlywheels.set(1);
			intakeRightFlywheels.set(1);
		}
	}

	// Default command
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeOff());
	}
}
