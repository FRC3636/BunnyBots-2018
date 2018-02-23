package org.usfirst.frc.team3636.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	
	public static final double setLiftUp = 1;
	public static final double setLiftDown = -setLiftUp;
	
	
	public static void curve(){
		Robot.myRobot.drive(Robot.AUTO_SPEED, Robot.AUTO_CURVE);
		
		
	}
	
	public static void lift(boolean upDown){
		if (upDown){
			Robot.liftArm.set(setLiftUp);
			Robot.liftArm2.set(setLiftUp);
		}
		else if (!upDown){
			Robot.liftArm.set(setLiftDown);
			Robot.liftArm2.set(setLiftDown);
		}
	}
	
	public static void shoot(){
		Robot.shooter.set(1);
		Robot.shooter2.set(1);
	}
	
	public static void endAuto(){
		Robot.myRobot.tankDrive(0,0);
		Robot.liftArm.set(0);
		Robot.liftArm2.set(0);
		Robot.shooter.set(0);
		Robot.shooter2.set(0);
	}
}
