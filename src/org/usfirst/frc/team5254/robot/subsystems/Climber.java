package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	Victor climberMotor = new Victor(RobotMap.CLIMBER);
	DoubleSolenoid climberPistion = new DoubleSolenoid(RobotMap.FIRE_CROSSBOW, RobotMap.NO_FIRE_CROSSBOW);
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
	
	public void on(boolean direction) {
		if (direction = true) {
			climberMotor.set(1.0);
		} else {
			climberMotor.set(-1.0);
		}
	}
	
	public void off() {
		climberMotor.set(0.0);
	}
	
	public void fireCrossbow() {
		climberPistion.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void dontFireCrossbow() {
		climberPistion.set(DoubleSolenoid.Value.kForward);
	}
}
