package org.usfirst.frc.team3636.robot.commands;

import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3636.robot.Robot;

/**
 *
 */
public class AutoMid extends Command {
	public AutoMid() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.exampleSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
		System.out.println("mid");
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(RobotState.isAutonomous()){
			return false;
		}
		else{
			return true;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.myRobot.tankDrive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
