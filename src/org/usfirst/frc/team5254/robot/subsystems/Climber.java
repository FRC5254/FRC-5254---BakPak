package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ClimberFire;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	
	public static Victor climberMotor;
	public static Victor climberMotor2;
	
	public Climber () {
		climberMotor = new Victor(RobotMap.CLIMBER);
		climberMotor2 = new Victor(RobotMap.CLIMBER_2);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberFire());
	}
	
	public void set(double speed) {
		climberMotor.set(speed);
		climberMotor2.set(speed);
	}
}
