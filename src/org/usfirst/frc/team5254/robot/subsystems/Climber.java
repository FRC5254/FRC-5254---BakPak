package org.usfirst.frc.team5254.robot.subsystems;

import org.usfirst.frc.team5254.robot.RobotMap;
import org.usfirst.frc.team5254.robot.commands.ClimberOn;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The climber subsystem controls two 775 pros attached to a ratchet system that (at the end of a match) 
 * winches the robot's bumpers up above 12". Each motor is hooked up to a Victor motor controller.
 */
public class Climber extends Subsystem {
	
	public static Victor climberMotor = new Victor(RobotMap.CLIMBER);
	public static Victor climberMotor2 = new Victor(RobotMap.CLIMBER_2);
	
	public void initDefaultCommand() {
		setDefaultCommand(new ClimberOn());
	}
	
	/**
	 * @param speed The power at which the climber motors are set to (between -1 to 1)
	 */
	public void set(double speed) {
		climberMotor.set(speed);
		climberMotor2.set(speed);
	}
	
	/**
	 * @deprecated
	 * @param direction Boolean value determining the direction to set the motors full power to
	 */
	public void on(boolean direction) {
		if (direction) {
			climberMotor.set(1.0);
			climberMotor2.set(1.0);
		} else {
			climberMotor.set(-1.0);
			climberMotor2.set(-1.0);
		}
	}
	
	/**
	 * Turns climber motors off completely
	 */
	public void off() {
		climberMotor.set(0.0);
		climberMotor2.set(0.0);
	}
}
