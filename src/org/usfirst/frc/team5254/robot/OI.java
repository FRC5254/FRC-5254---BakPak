/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.commands.DrivetrainDriveWithJoystick;
import org.usfirst.frc.team5254.robot.commands.DrivetrainShiftDown;
import org.usfirst.frc.team5254.robot.commands.DrivetrainShiftUp;
import org.usfirst.frc.team5254.robot.commands.DrivetrainSlowTurn;
import org.usfirst.frc.team5254.robot.commands.ElevatorDown;
import org.usfirst.frc.team5254.robot.commands.ElevatorSetHeight;
import org.usfirst.frc.team5254.robot.commands.IntakeOff;
import org.usfirst.frc.team5254.robot.commands.IntakeOn;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The operator interface of the robot.
 * 
 * It has been setup to allow control with two Xbox Controllers.
 * 
 * <p>
 * Note: The axis on the controllers used are not implemented here and instead
 * are used in commands
 * 
 * @see DrivetrainDriveWithJoystick
 */
public class OI {

	// Defining controllers
	public Joystick driver = new Joystick(RobotMap.DRIVER_JOYSTICK);
	public Joystick operator = new Joystick(RobotMap.OPERATOR_JOYSTICK);

	public OI() {

		// Defining driver buttons (all buttons - even unused buttons)
		Button DriverButtonA = new JoystickButton(driver, 1);
		Button DriverButtonB = new JoystickButton(driver, 2);
		Button DriverButtonX = new JoystickButton(driver, 3);
		Button DriverButtonY = new JoystickButton(driver, 4);
		Button DriverLB = new JoystickButton(driver, 5);
		Button DriverRB = new JoystickButton(driver, 6);
		Button DriverButtonBack = new JoystickButton(driver, 7);
		Button DriverButtonStart = new JoystickButton(driver, 8);
		Button DriverLJC = new JoystickButton(driver, 9);
		Button DriverRJC = new JoystickButton(driver, 10);

		// Defining operator buttons (all buttons - even unused buttons)
		Button OperatorButtonA = new JoystickButton(operator, 1);
		Button OperatorButtonB = new JoystickButton(operator, 2);
		Button OperatorButtonX = new JoystickButton(operator, 3);
		Button OperatorButtonY = new JoystickButton(operator, 4);
		Button OperatorButtonLB = new JoystickButton(operator, 5);
		Button OperatorButtonRB = new JoystickButton(operator, 6);
		Button OperatorButtonBack = new JoystickButton(operator, 7);
		Button OperatorButtonStart = new JoystickButton(operator, 8);
		Button OperatorLJC = new JoystickButton(operator, 9);
		Button OperatorRJC = new JoystickButton(operator, 10);

		/* Driver Sub-commands */

		// A button: nothing
		// B button: nothing
		// X button: nothing
		// Y button: nothing
		// Left and/or Right bumper: when held shifts drivetrain into high gear
		// (when released drivetrain shifts into low gear)
		// Start button: nothing
		// Back button: when held turning throttle is slowed to half of its reading
		// Left joystick click: nothing
		// Right joystick click: nothing

		// DriverButtonA.whenPressed();
		// DriverButtonB.whenPressed();
		// DriverButtonX.whenPressed();
		// DriverButtonY.whenPressed();
		DriverLB.whenPressed(new DrivetrainShiftUp());
		DriverLB.whenInactive(new DrivetrainShiftDown());
		DriverRB.whenPressed(new DrivetrainShiftUp());
		DriverRB.whenInactive(new DrivetrainShiftDown());
		// DriverButtonStart.whenPressed();
		DriverButtonBack.whenPressed(new DrivetrainSlowTurn());
		DriverButtonBack.whenInactive(new DrivetrainDriveWithJoystick());
		// DriverLJC.whenPressed();
		// DriverRJC.whenPressed();

		/* Operator Sub-commands */

		// A button: puts elevator all the way down
		// B button: elevator goes to the "owned scale" set point
		// X button: elevator goes to the "switch" set point
		// Y button: elevator goes to the "unowned scale" set point
		// Left bumper: when held outtakes (when released intake is off)
		// Right bumper: when held intakes (when released intake is off)
		// Start button: when held outtakes at a lower speed
		// (when released intake is off)
		// Back button: nothing
		// Left joystick click: nothing
		// Right joystick click: nothing

		OperatorButtonA.whenPressed(new ElevatorDown(RobotMap.ELE_DOWN_SPEED));
		OperatorButtonB.whenPressed(new ElevatorSetHeight(RobotMap.OWNED_SCALE_HEIGHT));
		OperatorButtonX.whenPressed(new ElevatorSetHeight(RobotMap.SWITCH_HEIGHT));
		OperatorButtonY.whenPressed(new ElevatorSetHeight(RobotMap.UNOWNED_SCALE_HEIGHT));
		OperatorButtonRB.whenPressed(new IntakeOn(true, 1)); 
		OperatorButtonRB.whenReleased(new IntakeOff());
		OperatorButtonLB.whenPressed(new IntakeOn(false, 0.75));
		OperatorButtonLB.whenReleased(new IntakeOff());
		OperatorButtonStart.whenPressed(new IntakeOn(false, 0.5));
		OperatorButtonStart.whenReleased(new IntakeOff());
		// OperatorButtonBack.whenPressed();
		// OperatorLJC.whenPressed();
		// OperatorRJC.whenPressed();
	}
}
