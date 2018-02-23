package org.usfirst.frc.team3636.robot.commands;

import org.usfirst.frc.team3636.robot.Robot;
import org.usfirst.frc.team3636.robot.RobotMap;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AutoLeftCommand extends Command {
	public AutoLeftCommand() {
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
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0){
        	if(gameData.charAt(0) == 'L'){//if alliance switch is on left
        		while(Robot.timer.get()<5){
        			RobotMap.lift(true);
					Timer.delay(.005);
					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
					Timer.delay(.005);
				}
				Robot.myRobot.tankDrive(0,0);
				Timer.delay(1);
				
				Robot.myRobot.tankDrive(Robot.AUTO_SPEED,0);
				Timer.delay(1);//turn right towards switch
				while(Robot.timer.get()<2){
					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
					Timer.delay(.005);
				}
				Robot.myRobot.tankDrive(0,0);
				Timer.delay(.005);
				while(Robot.timer.get()>=9 && Robot.timer.get()<=12){//for 3 seconds the shooter executes
					RobotMap.shoot();
					Timer.delay(.005);
				}
//        		System.out.println("box time");
        	}	
			else{//if alliance switch is on right
				if(Robot.timer.get()<10){
					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
					Timer.delay(.005);
				}
//				System.out.println("straight");
			}
       } 
       else {
    	   if(Robot.timer.get()<10){
    		   Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
    		   Timer.delay(.005);
    	   }
//    	   System.out.println("straight ");
       }
          
	   
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

	// Called once after isFinished returns true.
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
