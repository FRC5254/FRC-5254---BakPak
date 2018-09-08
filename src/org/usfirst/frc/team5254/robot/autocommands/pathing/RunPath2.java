package org.usfirst.frc.team5254.robot.autocommands.pathing;


import java.util.function.Function;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Refer to CleanRunPath for comments
 */
public class RunPath2 extends Command {
	private final double arcDivisor = 19;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	private double length = -1;
	
	private double shift; // What percent of the path should be in high gear 
	private double outtake;// What percent of the path that you want to start outaking at
	
	private boolean reset = true;
	
	private Path path;
	
	private Function<Double, Double> speed;
	
    public RunPath2(Path path, double speed, double shift, double outtake) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Drivetrain);
    	this.path = path;
    	this.leftSpeed = -speed;
    	this.rightSpeed = -speed;
    	this.speed = x -> speed;
    	this.shift = shift;
    	this.outtake = outtake;
    }
    
    public RunPath2(Path path, double speed, double shift, double outtake, boolean reset) {
    	this(path, speed, shift, outtake);
    	this.reset = reset;
    }
    
    public RunPath2(Path path, Function<Double, Double> speed, double shift, double outtake) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.Drivetrain);
    	this.path = path;
    	this.speed = speed;
    	this.shift = shift;
    	this.outtake = outtake;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    }
    
    public RunPath2(Path path, Function<Double, Double> speed, double shift, double outtake, boolean reset) {
    	this(path, speed, shift, outtake);
    	this.reset = reset;
    }
    
	public double dydx(double s) {
		PathSegment segment = path.getPathAtDistance(s);
		return segment.getDerivative().apply((s - path.getTotalOfCompletedPaths(s))/segment.getLength());
	}

    // Called just before this Command runs the first time
    protected void initialize() {	
//    	Robot.drive.setBothDrive(leftSpeed, rightSpeed);
//    	Robot.drive.resetBothEncoders();
//    	Robot.drive.resetIMU();
    	Robot.Drivetrain.encoderLeft.reset();
    	Robot.Drivetrain.encoderRight.reset();
    	Robot.Drivetrain.gyro.reset();
    	Robot.Drivetrain.setLeftRightSpeeds(0, 0);
    	System.out.println("RUNPATH INIT");
    }
    
    private double getDistance() {
    	return ( Math.abs( Robot.Drivetrain.getRightDistance() ) + Math.abs( Robot.Drivetrain.getLeftDistance() ) )/2;
//    	return Math.abs(Robot.Drivetrain.getRightDistance()); // change this to average once you get two encoders -- done
    	//return (Math.abs(Robot.Drivetrain.getLeftDistance())) + (Math.abs(Robot.Drivetrain.getRightDistance()));
    }
    
    private double deltaAngle(double currentAngle) {
    	double currentSlope = Math.tan(currentAngle * Math.PI / 180);
    	double nextSlope = dydx(getDistance());
    	
    	double angle = Math.atan((nextSlope - currentSlope)/(1 + currentSlope * nextSlope))*180/Math.PI;
    	
//    	System.out.println("m1: " + currentSlope + " m2: " + nextSlope + " dTheta: " + angle);
//    	System.out.println("Encoder: " + getDistance() + " dydx: " + dydx(getDistance()));
    	return angle;
    }
    
    public double speed() {
//    	System.out.println(-speed.apply(getDistance()/path.getTotalLength()));
    	return -speed.apply(getDistance()/path.getTotalLength());
    }
    
    public double pathPercentage() {
    	return getDistance()/path.getTotalLength();
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double error = -deltaAngle(Robot.Drivetrain.gyro.getAngle());
    	
    	SmartDashboard.putNumber("getDistance", getDistance());
    	SmartDashboard.putNumber("getTotalLength", path.getTotalLength());
    	
    	leftSpeed = speed();
    	rightSpeed = speed();
//    	if (shift == 1.0) {
//    		Robot.Drivetrain.shiftUp();
//    	} else if (shift == 0.0) {
//    		Robot.Drivetrain.shiftDown();
//    	} else {
    		if (getDistance()/path.getTotalLength() <= shift) { // 0 is just low gear, 1 is just high gear
        		Robot.Drivetrain.shiftUp();
        	} else {
        		Robot.Drivetrain.shiftDown();
        	}
//    	}
    		
    	if (getDistance()/path.getTotalLength() >= outtake) {
    		Robot.Intake.setSpeed(Robot.Intake.autoSpeed);
    	} else {
    		Robot.Intake.setSpeed(0);
    	}
    	
    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
    		double speed = leftSpeed;
    		Robot.Drivetrain.setLeftRightSpeeds(
        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
    	} else {
    		Robot.Drivetrain.setLeftRightSpeeds(leftSpeed, rightSpeed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	try {
//        	System.out.println(path.getPathAtDistance(Robot.drive.getRightDistance()).getLength());
            return Math.abs(getDistance()) > (path.getTotalLength());
    	} catch (Exception e) {
    		System.err.println(e);
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.Drivetrain.setLeftRightSpeeds(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}