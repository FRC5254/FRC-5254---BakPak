package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;

import org.usfirst.frc.team5254.robot.commands.CubeMechStopFlywheels;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeMech extends Subsystem {
	
	//Initializing controllers
	public static Spark cubeMechLeftFlywheels = new Spark(RobotMap.CUBE_MECH_LEFT_FLYWHEEL);
	public static Spark cubeMechRightFlywheels = new Spark(RobotMap.CUBE_MECH_RIGHT_FLYWHEEL);
	
	//Initializing solenoids
	public static Solenoid cubeMechArms = new Solenoid(RobotMap.CUBE_MECH_ARMS);
	public static DoubleSolenoid cubeMechHinge = new DoubleSolenoid(RobotMap.CUBE_MECH_HINGE_UP, RobotMap.CUBE_MECH_HINGE_DOWN);
	
	public CubeMech() {
	}
	
	//TeleOp Methods
	public void ArmsDown() {
		cubeMechHinge.set(DoubleSolenoid.Value.kReverse);
	}
	public void ArmsUp() {
		cubeMechHinge.set(DoubleSolenoid.Value.kForward);
	}
	public void Clamp() {
		cubeMechArms.set(true);
	}
	public void Release() {
		cubeMechArms.set(false);
	}
	public void Intake() {
		cubeMechLeftFlywheels.set(1);
		cubeMechRightFlywheels.set(1);
	}
	public void Outake() {
		cubeMechLeftFlywheels.set(-1);
		cubeMechRightFlywheels.set(-1);
	}
	public void StopFlywheels() {
		cubeMechLeftFlywheels.set(0);
		cubeMechRightFlywheels.set(0);
	}

	//Default command
    public void initDefaultCommand() {
    	setDefaultCommand(new CubeMechStopFlywheels());
    }
}

