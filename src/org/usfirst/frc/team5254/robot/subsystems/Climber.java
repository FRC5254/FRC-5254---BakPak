package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ClimberFire;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	public static Victor climberMotor = new Victor(RobotMap.CLIMBER);
	public static Victor climberMotor2 = new Victor(RobotMap.CLIMBER_2);
	
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberFire());
	}
	
	public void set(double speed) {
		climberMotor.set(speed);
		climberMotor2.set(speed);
	}
	
	public void on(boolean direction) {
		if (direction = true) {
			climberMotor.set(1.0);
			climberMotor2.set(1.0);
		} else {
			climberMotor.set(-1.0);
			climberMotor2.set(-1.0);
		}
	}
	
	public void off() {
		climberMotor.set(0.0);
		climberMotor2.set(0.0);
	}
}
