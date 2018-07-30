package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;

import org.usfirst.frc.team5254.robot.commands.IntakeOff;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The intake consists of two arms used to intake and hold cubes with. The arms are powered by 2 775 pros
 * hooked up to two sparks. The main things this subsystem does is intake and outtake (sometimes varying speeds)
 */
public class Intake extends Subsystem {

	// Initializing controllers
	public Spark intakeLeftFlywheels = new Spark(RobotMap.CUBE_MECH_LEFT_FLYWHEEL);
	public Spark intakeRightFlywheels = new Spark(RobotMap.CUBE_MECH_RIGHT_FLYWHEEL);

	public Intake() {
	}

	/**
	 * Turns on the flywheel motors in the direction and the speed set by the params
	 * 
	 * @param direction Boolean value to determine intaking (true) or outtaking (false)
	 * @param speed The speed at which the intake will outtake
	 */
	public void on(boolean direction, double speed) {
		if (direction == true) {
			intakeLeftFlywheels.set(-speed);
			intakeRightFlywheels.set(-speed + 0.1);
		} else {
			intakeLeftFlywheels.set(speed);//both .75
			intakeRightFlywheels.set(speed);
		}
	}

	public void off() {
		intakeLeftFlywheels.set(0);
		intakeRightFlywheels.set(0);
	}
	
	/** 
	 * used in RunPath2
	 * 
	 * @param direction <code>true</code> is intaking, <code>false</code> is outtaking
	 */
	public void autoOn(boolean direction) {
		if (direction == true) {
			intakeLeftFlywheels.set(-1);
			intakeRightFlywheels.set(-0.9);
		} else {
			intakeLeftFlywheels.set(0.65);
			intakeRightFlywheels.set(0.65);
		}
	}

	// Default command
	public void initDefaultCommand() {
		setDefaultCommand(new IntakeOff());
	}
}
