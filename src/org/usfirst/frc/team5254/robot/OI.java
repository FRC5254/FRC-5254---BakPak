package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	// Defining controllers
	public Joystick driver = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operator = new Joystick(RobotMap.OPERATOR_JOYSTICK);
	
	public OI() {
		
		// Defining driver buttons
		Button DriverButtonA = new JoystickButton(driver, 1);
		Button DriverButtonB = new JoystickButton(driver, 2);
		Button DriverButtonX = new JoystickButton(driver, 3);
		Button DriverButtonY = new JoystickButton(driver, 4);
		Button DriverButtonLB= new JoystickButton(driver, 5);
		Button DriverButtonRB = new JoystickButton(driver, 6);
		Button DriverButtonBack = new JoystickButton(driver, 7);
		Button DriverButtonStart = new JoystickButton(driver, 8);
		Button DriverButtonLJC = new JoystickButton(driver, 9);
		Button DriverButtonRJC = new JoystickButton(driver, 10);

		// Defining operator buttons
		Button OperatorButtonA = new JoystickButton(operator, 1);
		Button OperatorButtonB = new JoystickButton(operator, 2);
		Button OperatorButtonX = new JoystickButton(operator, 3);
		Button OperatorButtonY = new JoystickButton(operator, 4);
		Button OperatorButtonLB = new JoystickButton(operator, 5);
		Button OperatorButtonRB = new JoystickButton(operator, 6);
		Button OperatorButtonBack = new JoystickButton(operator, 7);
		Button OperatorButtonStart = new JoystickButton(operator, 8);
		Button OperatorButtonLJC = new JoystickButton(operator, 9);
		Button OperatorButtonRJC = new JoystickButton(operator, 10);
		
		// Driver subcommands
		DriverButtonRB.whenPressed(new DrivetrainShiftUp());
		DriverButtonRB.whenInactive(new DrivetrainShiftDown());
		DriverButtonLB.whenPressed(new DrivetrainShiftUp());
		DriverButtonLB.whenInactive(new DrivetrainShiftDown());
		DriverButtonStart.whenPressed(new DrivetrainSlowTurn());
		DriverButtonStart.whenInactive(new DrivetrainDriveWithJoystick());
		DriverButtonRJC.whenPressed(new DrivetrainSlowTurn());
		DriverButtonRJC.whenInactive(new DrivetrainDriveWithJoystick());
//		DriverButtonA.whenPressed(new CubeMechIntake());
//		DriverButtonX.whenPressed(new CubeMechOutake());
//		DriverButtonB.whenPressed(new CubeMechStopFlywheels());
		
		// Operator Subcommands
		OperatorButtonX.whenPressed(new CubeMechIntake());
		OperatorButtonA.whenPressed(new CubeMechOutake());
		OperatorButtonB.whenPressed(new CubeMechStopFlywheels());
		OperatorButtonBack.whenPressed(new ElevatorStop());
		OperatorButtonBack.whenReleased(new ElevatorJoystickControl());
		OperatorButtonRB.whenPressed(new ElevatorUnrachet());
		OperatorButtonRB.whenReleased(new ElevatorRachet());

	}
}
