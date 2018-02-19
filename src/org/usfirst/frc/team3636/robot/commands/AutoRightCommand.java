package org.usfirst.frc.team3636.robot.commands;

import org.usfirst.frc.team3636.robot.Robot;

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
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0){
        	if(gameData.charAt(0) == 'R'){
        		while(Robot.timer.get()<5){
					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
				}
				Robot.myRobot.tankDrive(0,0);
				Timer.delay(1);
				Robot.myRobot.tankDrive(0,Robot.AUTO_SPEED); //turn left towards switch
				while(Robot.timer.get()<9){//for 3 seconds the fly wheel will spin
					Robot.flywheel.set(1);
					Robot.flywheel2.set(1);
					Timer.delay(.005);
				}
				while(Robot.timer.get()<12){//for 3 seconds the shooter executes
					Robot.shooter.set(1);
					Robot.shooter2.set(1);
					Timer.delay(.005);
				}
        	}	
			else{
				if(Robot.timer.get()<10){
					Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
					Timer.delay(.005);
				}
			}
        } 
       else {
    	   if(Robot.timer.get()<10){
    		   Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
    		   Timer.delay(.005);
    	   }
       }
       System.out.println("right ");
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
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
