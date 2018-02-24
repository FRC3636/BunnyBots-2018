package org.usfirst.frc.team3636.robot;

import edu.wpi.first.wpilibj.Timer;

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
	
	public static final double forwardDirect = 1;
	public static final double reverseDirect = -forwardDirect;
	
	
	public static void curve(){
		Robot.myRobot.drive(Robot.AUTO_SPEED, Robot.AUTO_CURVE);
		Timer.delay(.005);
		
	}
	
	public static void lift(boolean liftUp){
		if (liftUp){//Lift Up
			Robot.liftArm.set(forwardDirect);
			Robot.liftArm2.set(reverseDirect);
			Timer.delay(.005);
		}
		else if (!liftUp){//Lift down
			Robot.liftArm.set(reverseDirect);
			Robot.liftArm2.set(forwardDirect);
			Timer.delay(.005);
		}
	}
	
	public static void shoot(boolean shootOut){
		if(shootOut){//Flywheel spins outwards to release boxes.
			Robot.shooter.set(forwardDirect);
			Robot.shooter2.set(reverseDirect);
			Timer.delay(.005);
		}
		else if(!shootOut){//Flywheel spins inwards to take in boxes
			Robot.shooter.set(reverseDirect);
			Robot.shooter2.set(forwardDirect);
			Timer.delay(.005);
		}
		
	}
	
	public static void endAuto(){
		Robot.myRobot.tankDrive(0,0);
		Robot.liftArm.set(0);
		Robot.liftArm2.set(0);
		Robot.shooter.set(0);
		Robot.shooter2.set(0);
		Timer.delay(.005);
	}
}
