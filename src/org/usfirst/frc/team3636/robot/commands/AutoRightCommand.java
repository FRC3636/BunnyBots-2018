package org.usfirst.frc.team3636.robot.commands;

import org.usfirst.frc.team3636.robot.Robot;
import org.usfirst.frc.team3636.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoRightCommand extends Command {
	public AutoRightCommand() {
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
		Robot.gyro.reset();
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0){
        	if(gameData.charAt(0) == 'R'){//if alliance switch is on right
//        		while(Robot.timer.get()<5){
//        			RobotMap.lift(true);
//					Timer.delay(.005);
//					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
//					Timer.delay(.005);
//				}
//				Robot.myRobot.tankDrive(0,0);
//				Timer.delay(.005);
//				while(Robot.timer.get()>=5 && Robot.timer.get()<=7){//turn left during interval
//					Robot.myRobot.tankDrive(0,Robot.AUTO_SPEED);
//					Timer.delay(.005);
//				}
//					
////				while((Robot.timer.get()>=5 && Robot.timer.get()<=7)){//experimental gyro turning
////					Robot.myRobot.tankDrive(0,Robot.AUTO_SPEED);
////					Timer.delay(.005);
////				//Allowed margin of error to stop turning
////					if(Robot.gyro.getAngle()>=87.5 && Robot.gyro.getAngle()<=92.5){
////						break;
////					}
////				}	
//					
//				while(Robot.timer.get()>=8 && Robot.timer.get()<=10){
//					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
//					Timer.delay(.005);
//				}
//				Robot.myRobot.tankDrive(0,0);
//				Timer.delay(.005);
//				while(Robot.timer.get()>=11 && Robot.timer.get()<=14){//for 3 seconds the shooter executes
//					RobotMap.shoot(true);
//					Timer.delay(.005);
//				}
        		System.out.println("right score");
        	}	
			else{//if alliance switch is on left
//				while(Robot.timer.get()<14){
//		   			Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
//		   			}
				System.out.println("right straight");
			}
       } 
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
//		if(RobotState.isAutonomous()){
//			return false;
//		}
//		else{
//			return true;
//		}
		return true;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		RobotMap.endAuto();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
