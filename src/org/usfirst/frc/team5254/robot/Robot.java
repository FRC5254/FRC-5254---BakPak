package org.usfirst.frc.team5254.robot;

import org.usfirst.frc.team5254.robot.subsystems.*;
import org.usfirst.frc.team5254.robot.autos.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends IterativeRobot {

	NetworkTable table;
	String gameData;
	Boolean allianceColorRed;
	int allianceNumber;

	public static OI oi;
	public static Drivetrain Drivetrain = new Drivetrain();
	public static Intake Intake = new Intake();
	public static Elevator Elevator = new Elevator();
	public static Climber Climber = new Climber();

	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	// Auto modes
	private final String NothingAuto = "Do Nothing";
	private final String CrossAutoLine = "Cross Autoline";
	private final String SwitchAuto = "Switch Auto";
	private final String ScaleAutoL = "Scale Auto Left";
	private final String ScaleAutoR = "Scale Auto Right";
	private final String TestAuto = "Test Auto";
	private final String LeftScaleOrSwitch = "Scale or Switch on Left Side";
	private final String RightScaleOrSwitch = "Scale or Switch on Right Side";
	private final String TwoCubeSwitch = "Two Cube Switch";
	private final String SwitchCubeDrive = "Switch Cube Drive";
	
//	public static boolean Auto;
	
	private final String[] AutoModes = { NothingAuto, CrossAutoLine, SwitchAuto, ScaleAutoL, ScaleAutoR,
			TestAuto, LeftScaleOrSwitch, RightScaleOrSwitch, TwoCubeSwitch, SwitchCubeDrive};

	Command autonomousCommand;
	// Defining the autonomous commands into a string to be listed on the dashboard

	@Override
	public void robotInit() {
		oi = new OI();

		// Send auto modes
		SmartDashboard.putStringArray("Auto List", AutoModes);
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
		NetworkTable table = inst.getTable("SmartDashboard");

		// Initializing cameras
		CameraServer.getInstance().startAutomaticCapture(1);
		CameraServer.getInstance().startAutomaticCapture(0);
	}

	@Override
	public void autonomousInit() {
		
//		Auto = true;

		String autoSelected = SmartDashboard.getString("Auto Selector", SwitchAuto);
		// TODO do the scale auto outline and make more logic in here to make it cooler
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		allianceColorRed = DriverStation.getInstance().getAlliance() == DriverStation.Alliance.Red;
		allianceNumber = DriverStation.getInstance().getLocation();

		if (gameData.charAt(0) == 'L') {
            
            
            if (gameData.charAt(1)== 'L') {// when switch and scale are on left side
                
                switch (autoSelected) {

                case CrossAutoLine:
                    autonomousCommand = new CrossBaselineAuto();
                    break;

                case SwitchAuto:
                    autonomousCommand = new CenterSwitchAutoLeft();
                    break;
                    
                case ScaleAutoL:
                    autonomousCommand = new LeftScaleAutoLeft();
                    break;

                case LeftScaleOrSwitch: 
                    autonomousCommand = new LeftScaleAutoLeft();
                    break;
                    
                case RightScaleOrSwitch: 
                    autonomousCommand = new CrossBaselineAuto(); 
                    break;
                    
                case TestAuto:
                    autonomousCommand = new TestAuto();
                    break;
                    
                case TwoCubeSwitch:
                	autonomousCommand = new CenterSwitchAutoLeftTwoCube();
                	break;
                	
                case SwitchCubeDrive:
                	autonomousCommand = new CenterSwitchAutoLeftDriveLeft();
                	break;
                
                default:
                    autonomousCommand = new NothingAuto();
                    break;
                }    
            } else { // switch is on left, scale is on right
                
                switch (autoSelected) {

                case CrossAutoLine:
                    autonomousCommand = new CrossBaselineAuto();
                    break;

                case SwitchAuto:
                    autonomousCommand = new CenterSwitchAutoLeft();
                    break;

                case ScaleAutoL:
                    autonomousCommand = new LeftScaleAutoRight();
                    break;
                    
                case LeftScaleOrSwitch: 
                    autonomousCommand = new LeftSwitchAutoLeft(); 
                    break;
                    
                case RightScaleOrSwitch: 
                    autonomousCommand = new RightScaleAutoRight(); 
                    break;
                    
                case TestAuto:
                    autonomousCommand = new TestAuto();
                    break;
                    
                case TwoCubeSwitch:
                	autonomousCommand = new CenterSwitchAutoLeftTwoCube();
                	break;
                	
                case SwitchCubeDrive:
                	autonomousCommand = new CenterSwitchAutoLeftDriveRight();
                	break;
                    
                default:
                    autonomousCommand = new NothingAuto();
                    break;
                }    
            }
            
        } else {
                
            if (gameData.charAt(1) == 'L') { // switch is on right, scale is on left
                switch (autoSelected) {
                
                case CrossAutoLine:
                    autonomousCommand = new CrossBaselineAuto();
                    break;
    
                case SwitchAuto:
                    autonomousCommand = new CenterSwitchAutoRight();
                    break;
    
                case ScaleAutoL:
                    autonomousCommand = new LeftScaleAutoLeft();
                    break;
                    
                case LeftScaleOrSwitch: 
                    autonomousCommand = new LeftScaleAutoLeft(); 
                    break;
                    
                case RightScaleOrSwitch: 
                    autonomousCommand = new RightSwitchAutoRight(); 
                    break;

                case TestAuto:
                    autonomousCommand = new TestAuto();
                    break;
                    
                case TwoCubeSwitch:
                	autonomousCommand = new CenterSwitchAutoRightTwoCube();
                	break;
                	
                case SwitchCubeDrive:
                	autonomousCommand = new CenterSwitchAutoRightDriveLeft();
                	break;
                    
                default:
                    autonomousCommand = new NothingAuto();
                    break;
                }
            
            } else { // switch is on right, scale is on right
                switch (autoSelected) {
                
                case CrossAutoLine:
                    autonomousCommand = new CrossBaselineAuto();
                    break;
    
                case SwitchAuto:
                    autonomousCommand = new CenterSwitchAutoRight();
                    break;
    
                case ScaleAutoL:
                    autonomousCommand = new LeftScaleAutoRight();
                    break;
                    
                case LeftScaleOrSwitch: 
                    autonomousCommand = new CrossBaselineAuto(); 
                    break;
                    
                case RightScaleOrSwitch: 
                    autonomousCommand = new RightScaleAutoRight(); 
                    break;
                    
                case TestAuto:
                    autonomousCommand = new TestAuto();
                    break;
                    
                case TwoCubeSwitch:
                	autonomousCommand = new CenterSwitchAutoRightTwoCube();
                	break;
                	
                case SwitchCubeDrive:
                	autonomousCommand = new CenterSwitchAutoRightDriveRight();
                	break;
    
                default:
                    autonomousCommand = new NothingAuto();
                    break;
                }
            }
        }
	
		
		System.out.format("Auto: %s '%s' %n", m_ds.getAlliance(), autoSelected);

		// Schedule the autonomous command (example)
		if (autonomousCommand != null) {
			SmartDashboard.putString("DB/String 0", autoSelected);
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
//		SmartDashboard.putNumber("Encoder Adverage",((Drivetrain.encoderLeft.getDistance() + Drivetrain.encoderRight.getDistance())/2));
//		SmartDashboard.putNumber("left", Drivetrain.encoderLeft.getDistance());
//		SmartDashboard.getNumber("right", Drivetrain.encoderRight.getDistance());
//		SmartDashboard.getNumber("Gyro", Drivetrain.gyro.getAngle());
//		System.out.println(Robot.Drivetrain.encoderRight.getDistance());
//		System.out.println(Robot.Drivetrain.encoderLeft.getDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
	}

	@Override
	public void teleopInit() {
//		Auto = false;
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
//		System.out.println(Robot.oi.driver.getRawAxis(RobotMap.OPERATOR_THROTTLE_AXIS));
//		System.out.println(Robot.Elevator.elevator.getMotorOutputPercent());
//		SmartDashboard.putNumber("Distance", Robot.Drivetrain.getDistance());
//		System.out.println(Robot.Elevator.elevator.getSelectedSensorPosition(0));
	}

	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}