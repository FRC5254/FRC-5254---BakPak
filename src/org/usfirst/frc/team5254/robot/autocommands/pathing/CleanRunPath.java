
/**
 * This is an untested and fully commented out class thats a easier to read RunPath
 * than the RunPath or the RunPath2
 */

package org.usfirst.frc.team5254.robot.autocommands.pathing;

import java.util.function.Function;

import org.usfirst.frc.team5254.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * 
 * This is the same as run path just untested and commented
 * refer to this class for any clarification on any unclear
 * "Black box" code in runpath
 */

public class CleanRunPath extends Command {
    // arcDivisor is basically your P constant for a P loop, except inverted.
    // so on a normal PID loop, increasing P has the same effect as decreasing arcDivisor.
    // this is since we divide by it later in the code. You could change the code to fit a normal
    // P loop and it would behave fine. There's nothing special about this.
    private final static double arcDivisor = 15;

    // Store a path to run
    private Path path;

    // Store a speed function. The speed function is designated by a lambda, like so:
    //            p -> {
    //               if (p < 0.5) return 0.3;
    //               else return 0.7;
    //            }
    // The function takes in one parameter (a Double) that is equal to the percentage of the path completion (0 to 1).
    // It returns one value (a Double).
    // If you are unsure about how the Function class works, there are tons of resources on Java Functional Programming.
    private Function<Double, Double> speed;

    /**
     * Constructor
     *
     * @param path  the path to run
     * @param speed the speed function for the path
     */
    public CleanRunPath(Path path, Function<Double, Double> speed) {
        requires(Robot.drivetrain); // don't let another command use the drive train while this is running
        this.path = path;
        this.speed = speed;
    }

    /**
     * Alternative constructor. Used for short, small paths (e.g. drive backwards 12 inches at 30% speed after scoring 1
     * in the switch)
     *
     * @param path  the path to run
     * @param speed the constant speed to drive at throughout the entire path
     */
    public CleanRunPath(Path path, double speed) {
        this(path, x -> speed);
    }

    /**
     * Get the slope of the tangent line at our current distance.
     *
     * @param distance the current distance the robot has traveled
     * @return The slope of the tangent line at the current point
     */
    private double getDyDx(double distance) {
        // Get our current path segment (Paths are comprised of multiple PathSegments)
        PathSegment segment = path.getPathAtDistance(distance);

        // get the derivative we specified and pass in the distance along the current path we've traveled
        return segment.getDerivative().apply(
                (distance - path.getTotalOfCompletedPaths(distance)) /
                        // To be honest, I forget why we're dividing by the segment length here
                        // This is one of those voodoo tricks our programmer tried and it worked
                        // I'll investigate further once our practice bot is functional again
                        // I honestly believe this shouldn't work. But 35 matches say otherwise, I guess?
                        segment.getLength()
        );
    }

    /**
     * Initialize things. The IMU referenced is just a device with a gyro and stuff. Nothing special is going on here.
     */
    protected void initialize() {
        Robot.drivetrain.drive(speed.apply(0.0), speed.apply(0.0));
        Robot.drivetrain.initEncoder(true);
    }

    /**
     * Get the distance the robot has traveled, in inches.
     *
     * @return the distance the robot has traveled, in inches.
     */
    private double getDistance() {
        return Math.abs(
                // Take the average of the two encoder readings
                (Robot.drivetrain.getLeftDistance() + Robot.drivetrain.getRightDistance()) / 2.0
        		//Jank the code to take on encoder :D - Dont do this
//        		(Robot.drivetrain.getDistance())
        );
    }

    /**
     * Get how much we need to rotate by given our current angle and our current position.
     *
     * @param currentAngle the current angle of the robot
     * @return how far the robot needs to rotate. Positive amount is to the right, negative amount is to the left.
     * (gyro dependent)
     */
    private double getDeltaAngle(double currentAngle) {
        double currentSlope = Math.tan(currentAngle * Math.PI / 180.0);
        double nextSlope = getDyDx(getDistance());

        // The angle difference between two given slopes is:
        // arctan( (m1 - m2) / (1 + m1 * m2) )
        // Read more here: https://math.stackexchange.com/questions/1269050/
        return Math.atan(
                (nextSlope - currentSlope) /
                        (1 + currentSlope * nextSlope)
        ) * 180.0 / Math.PI; // and convert to degrees
    }

    /**
     * Get the current speed to travel at
     *
     * @return the current speed we need to travel at, from the speed function we specified.
     */
    public double getSpeed() {
        return -speed.apply(getDistance() / path.getTotalLength());
    }

    protected void execute() {
        double error = -getDeltaAngle(Robot.drivetrain.gyro.getAngle());
        double speed = getSpeed();

        // We check if its > 3 because in testing the robot as kinda panicking at the start of paths and not driving
        // correctly. Once practice bot is functional again I'll take this out and see what happens. It's another one
        // of those voodoo tricks that made it work better.
        if (Math.abs(getDistance()) > 3) {
            // Alright, here's the magic ingredient. This is some ugly, wonky P loop that our programmer accidentally
            // wrote last year. Normally, a P loop is (kP * value), but this one is (error * kP * value).
            // We took out multiplying by the error but it didn't work as well. The third voodoo trick that made it all
            // work. It's weird, I know.
            Robot.drivetrain.setLeftRightSpeeds(
                    speed + (error * Math.abs(speed) / arcDivisor),
                    speed - (error * Math.abs(speed) / arcDivisor)
            );
        } else {
//            Robot.Drivetrain.setBothDrive(speed);
        	Robot.drivetrain.setLeftRightSpeeds(speed, speed);
        }
    }

    protected boolean isFinished() {
        try {
            // Check if we've driven past the end of the path
        	return Math.abs(getDistance()) > (path.getTotalLength());
        } catch (Exception e) {
            // If anything goes wrong just finish the path, don't keep driving.
            System.err.println(e);
            return true;
        }
    }

    protected void end() {
        // Once the path is over, stop driving.
//        Robot.Drivetrain.setBothDrive(0.0);
        Robot.drivetrain.setLeftRightSpeeds(0, 0);
    }

    protected void interrupted() {
        end();
    }
}
