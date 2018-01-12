package org.usfirst.frc.team5254.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team5254.commands.ShiftDown;
import org.usfirst.frc.team5254.commands.ShiftUp;

public class OI {
	
	//defining controllers
	public Joystick driverJoystick = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operatorJoystick = new Joystick(RobotMap.OPERATOR_JOYSTICK);
	
	public OI() {
		
		// Defining driver buttons
		Button DriverButtonRB = new JoystickButton(driverJoystick,5);
		
		// Defining operator buttons
		
		// Driver subcommands
		DriverButtonRB.whenPressed(new ShiftUp());
		DriverButtonRB.whenInactive(new ShiftDown());
	}
}
