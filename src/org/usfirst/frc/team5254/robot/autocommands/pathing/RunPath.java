package org.usfirst.frc.team5254.robot.autocommands.pathing;


import java.util.function.Function;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Refer to CleanRunPath for comments
 * 
 * @see CleanRunPath
 */
public class RunPath extends Command {
	private final double arcDivisor = 19;

	private double leftSpeed = 0;
	private double rightSpeed = 0;
	
	private double length = -1;
	
	private double shift; // What percent of the path should be in high gear 
	
	private boolean reset = true;
	
	private Path path;
	
	private Function<Double, Double> speed;
	
	/**
	 * @param path
	 * @param speed
	 * @param shift
	 */
    public RunPath(Path path, double speed, double shift) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.path = path;
    	this.leftSpeed = -speed;
    	this.rightSpeed = -speed;
    	this.speed = x -> speed;
    	this.shift = shift;
    }
    
    /**
     * @param path
     * @param speed
     * @param shift
     * @param reset
     */
    public RunPath(Path path, double speed, double shift, boolean reset) {
    	this(path, speed, shift);
    	this.reset = reset;
    }
    
    /**
     * @param path
     * @param speed
     * @param shift
     */
    public RunPath(Path path, Function<Double, Double> speed, double shift) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.path = path;
    	this.speed = speed;
    	this.shift = shift;
    	this.leftSpeed = speed.apply(0.0);
    	this.rightSpeed = speed.apply(0.0);
    }
    
    /**
     * @param path
     * @param speed
     * @param shift
     * @param reset
     */
    public RunPath(Path path, Function<Double, Double> speed, double shift, boolean reset) {
    	this(path, speed, shift);
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
    	Robot.drivetrain.encoderLeft.reset();
    	Robot.drivetrain.encoderRight.reset();
    	Robot.drivetrain.gyro.reset();
    	Robot.drivetrain.setLeftRightSpeeds(0, 0);
    	System.out.println("RUNPATH INIT");
    }
    
    private double getDistance() {
    	return ( Math.abs( Robot.drivetrain.getRightDistance() ) + Math.abs( Robot.drivetrain.getLeftDistance() ) )/2;
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
    	double error = -deltaAngle(Robot.drivetrain.gyro.getAngle());
    	
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
        		Robot.drivetrain.shiftUp();
        	} else {
        		Robot.drivetrain.shiftDown();
        	}
//    	}
    	
    	System.out.println("error: " + error);
    	if(Math.abs(getDistance()) > 3) {
    		double speed = leftSpeed;
    		Robot.drivetrain.setLeftRightSpeeds(
        			(leftSpeed+((error)/(arcDivisor/Math.abs(speed)))), 
        			(rightSpeed-(((error)/(arcDivisor/Math.abs(speed))))));
    	} else {
    		Robot.drivetrain.setLeftRightSpeeds(leftSpeed, rightSpeed);
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
    	Robot.drivetrain.setLeftRightSpeeds(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}