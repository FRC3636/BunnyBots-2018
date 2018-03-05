package org.usfirst.frc.team3636.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team3636.robot.Robot;
import org.usfirst.frc.team3636.robot.RobotMap;

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
		String gameData;
		gameData = DriverStation.getInstance().getGameSpecificMessage();
        if(gameData.length() > 0){
        	if(gameData.charAt(0) == 'R'){ //If alliance switch is on right, curve to score
//        		while(Robot.timer.get()<5){//lift arm while curving
//        			RobotMap.lift(true);
//					Timer.delay(.005);
//					RobotMap.curve();
//					Timer.delay(.005);				
//				}
//        		Robot.liftArm.set(0);
//				Timer.delay(.005);
//        		Robot.myRobot.tankDrive(0,0);
//				Timer.delay(.005);
//				while(Robot.timer.get()>=5 && Robot.timer.get()<=8){//for 3 seconds the shooter executes
//					RobotMap.shoot(true);
//					Timer.delay(.005);
//				}
				System.out.println("mid score");
        	}
        	else{//Drive straight if switch is on left
//        		while(Robot.timer.get()<14){
//        			Robot.myRobot.tankDrive(Robot.AUTO_SPEED,Robot.AUTO_SPEED+.075);
//        		}
        		System.out.println("mid straight");
        		
        	}
        	
        }
        	
        
        	
		
		System.out.println("mid");
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
