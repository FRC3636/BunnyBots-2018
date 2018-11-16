package org.usfirst.frc.team3636.robot;

import java.util.Scanner;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotState;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team3636.robot.commands.AutoLeftCommand;
import org.usfirst.frc.team3636.robot.commands.AutoRightCommand;
import org.usfirst.frc.team3636.robot.commands.AutoMid;
import org.usfirst.frc.team3636.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
//	private static final boolean testCommit = true;

    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    DifferentialDrive myRobot = new DifferentialDrive(new Spark(0), new Spark(1));
    public Joystick leftStick = new Joystick(0);
    public Joystick rightStick = new Joystick(1);
    public Solenoid sol = new Solenoid(0,0); //device id 0, channel 0
    public DoubleSolenoid sol2 = new DoubleSolenoid(1,2); //device id 0, channel 1,2
    public Compressor com = new Compressor(0);
    public DigitalInput leftSwitch = new DigitalInput(0);
    public DigitalInput rightSwitch = new DigitalInput(1);
    public static ADXRS450_Gyro gyro = new ADXRS450_Gyro(SPI.Port.kOnboardCS0);
    public static Spark liftArm = new Spark(4);
    public static Spark liftArm2 = new Spark(5);
    public static Spark shooter = new Spark(6);
    public static Spark shooter2 = new Spark(7); 
    
    public static Timer timer = new Timer();

//    public final double autoleft = -.25; 
//    public final double autoright = -.25;
//    public final double timerdelay = .1;
    public final int motorspeed = 1; 
    public static final int TIME_AUTO = 15; //Change to autonomous time in seconds
    public static final double AUTO_SPEED = -.5; //This controls the speed of autonomous
    public double CURVE_CHANGE =-.01;
    public static double AUTO_CURVE= -.15;
    public final int BRIGHTNESS = 30;
    public final double Kp = 0.03;
    Command autonomousCommand;
    SendableChooser chooser = new SendableChooser();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        chooser.addObject("Left Auto", new AutoLeftCommand());
        chooser.addObject("Middle Auto", new AutoMid());
        chooser.addObject("Right Auto", new AutoRightCommand());
        SmartDashboard.putData("Auto mode chooser", chooser);
        sol2.set(DoubleSolenoid.Value.kOff);
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        timer.reset();
        timer.start();
        /*
         * String autoSelected = SmartDashboard.getString("Auto Selector",
         * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
         * = new MyAutoCommand(); break; case "Default Auto": default:
         * autonomousCommand = new ExampleCommand(); break; }
         */

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
        
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
////        boolean experimentOn = true;
////        if (experimentOn == false){
////	        System.out.println("time: " + timer.get());
////	        while (timer.get() < TIME_AUTO && RobotState.isAutonomous()){
////	        	//myRobot.drive(AUTO_SPEED,CURVE_CHANGE);
////	        	myRobot.tankDrive(AUTO_SPEED,AUTO_SPEED+.075);
////	        
////	        	
////	        }
////        }
////        //more experiments:   
////        else if (experimentOn == true){
////        	gyro.reset();
////	        while (timer.get() < TIME_AUTO && RobotState.isAutonomous()){
////	        	double angle = gyro.getAngle();
//////	    		myRobot.drive(-1.0, -angle * Kp);
//////	    		Timer.delay(RobotMap.timerDelay);
////	    		CURVE_CHANGE = .15; //turn right
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
////	        	CURVE_CHANGE = -.1; //turn left
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
////	        }
////        }
//        boolean leftOn=false;
//        boolean rightOn=false;
//        
//        while (timer.get() < TIME_AUTO && RobotState.isAutonomous()){
//        	leftOn = leftSwitch.get();
//            rightOn = rightSwitch.get();
//        	if(leftOn && rightOn){ //Left mode
//        		System.out.println(rightOn);
//        		System.out.println("right ");
////        		CURVE_CHANGE = .15; //turn right
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
////	        	CURVE_CHANGE = -.1; //turn left
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
//        	}
//        	else if(!leftOn && rightOn){ //Right mode
//        		System.out.println("right");
////        		CURVE_CHANGE = .15; //turn right
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
////	        	CURVE_CHANGE = -.1; //turn left
////	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////	        	Timer.delay(.1);
//        	}
//        	else{// Middle mode w/ box dump
//        		System.out.println(leftOn);
////        		while(timer.get() <4){
////        			CURVE_CHANGE = .15; //turn right
////    	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////    	        	Timer.delay(.1);
////    	        	CURVE_CHANGE = -.1; //turn left
////    	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
////    	        	Timer.delay(.1);
////        		}
//        		//Pneumatics code here
//        		 
//        	}
//        	timer.delay(RobotMap.timerDelay);
//        	
//        }
        
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
        myRobot.setSafetyEnabled(true);
        //sol.set(false);
        sol2.set(DoubleSolenoid.Value.kOff);
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        double leftval = leftStick.getY();
        double rightval= rightStick.getY();
        myRobot.tankDrive(leftval, rightval);
        Timer.delay(RobotMap.timerDelay); // wait for a motor update time
        boolean pressureSwitch = com.getPressureSwitchValue();

        
        double duration = 3.0;

//        if (leftStick.getTrigger()){ //Extend piston
////        	if(com.getPressureSwitchValue()){
////        	System.out.println(com.getClosedLoopControl());
////        	}
//        	System.out.println(pressureSwitch);
////        	myRobot.tankDrive(.5, 0);
////        	com.setClosedLoopControl(true);
//        	sol2.set(DoubleSolenoid.Value.kForward);
//        	//sol2.set(false);//
////        	com.start();
////        	System.out.println(com.enabled());
//        	
//        	
//        	timer.delay(RobotMap.timerDelay);
//        	
//        
//        }
//        /*else{
//        	sol2.set(DoubleSolenoid.Value.kOff);
//        	timer.delay(RobotMap.timerDelay);
//        }*/
//        else if (rightStick.getTrigger()){//Retract piston
//        	
//        	//sol.set(false);
//        	sol2.set(DoubleSolenoid.Value.kReverse);
////        	sol.set(DoubleSolenoid.Value.kReverse);
////        	sol.set(false);
//        	//sol.set(DoubleSolenoid.Value.);
////        	System.out.println(sol.get());
////        	sol.set(false);
//        	//com.stop();
//        	timer.delay(RobotMap.timerDelay);
////            com.set(false);
////            com.setPulseDuration(duration);
////            com.startPulse();
//        }
//        else if(rightStick.getRawButton(4)){//Retract with single solenoid
//        	sol2.set(DoubleSolenoid.Value.kReverse);
//        	sol.set(true);
//        	timer.delay(RobotMap.timerDelay);
//        }
//        else if(leftStick.getRawButton(2)){
//        	shooter.set(1.0);
//        	shooter2.set(1.0);
//        	timer.delay(RobotMap.timerDelay);
//        }
//        else if(rightStick.getRawButton(2)){
//        	shooter.set(-1.0);
//        	shooter2.set(-1.0);
//        	timer.delay(RobotMap.timerDelay);
//        }
//        else if(leftStick.getRawButton(3)){
//        	liftArm.set(1.0);
//        	liftArm2.set(1.0);
//        	timer.delay(RobotMap.timerDelay);
//        }
//        else if(rightStick.getRawButton(3)){
//        	shooter.set(-1.0);
//        	shooter2.set(-1.0);
//        	timer.delay(RobotMap.timerDelay);
//        }
//        else{
//        	sol.set(false);
//        	sol2.set(DoubleSolenoid.Value.kOff);
//        	shooter.set(0);
//        	shooter2.set(0);
//        	liftArm.set(0);
//        	liftArm2.set(0);
//        	timer.delay(RobotMap.timerDelay);
//        }
        
        
        //new buttons and triggers: 
        if (leftStick.getTrigger()){//Flywheel shooter inwards
        	shooter.set(RobotMap.reverseDirect);
        	shooter2.set(RobotMap.forwardDirect);
        	Timer.delay(RobotMap.timerDelay);
        }
        else if (rightStick.getTrigger()){//Flywheel shooter outwards
        	shooter.set(RobotMap.forwardDirect);
        	shooter2.set(RobotMap.reverseDirect);
        	Timer.delay(RobotMap.timerDelay);
        }
        else{
        	shooter.set(0);
        	shooter2.set(0);
        	Timer.delay(RobotMap.timerDelay);
        }
//        if (leftStick.getRawButton(2)){//Flywheel shooter inwards
//        	shooter.set(RobotMap.reverseDirect);
//        	shooter2.set(RobotMap.forwardDirect);
//        	Timer.delay(RobotMap.timerDelay);
//        }
//        else if (rightStick.getRawButton(2)){//Flywheel shooter outwards
//        	shooter.set(RobotMap.forwardDirect);
//        	shooter2.set(RobotMap.reverseDirect);
//        	Timer.delay(RobotMap.timerDelay);
//        }
//        else{
//        	shooter.set(0);
//        	shooter2.set(0);
//        	Timer.delay(RobotMap.timerDelay);
//        }
        if (leftStick.getRawButton(2)){//Lower shooting arm
        	liftArm.set(RobotMap.reverseDirect);
        	liftArm2.set(RobotMap.forwardDirect);
        	Timer.delay(RobotMap.timerDelay);
        }
        else if (rightStick.getRawButton(2)){//Raise shooting arm
        	liftArm.set(RobotMap.forwardDirect);
        	liftArm2.set(RobotMap.reverseDirect);
        	Timer.delay(RobotMap.timerDelay);
        }
        else{
        	liftArm.set(0);
        	liftArm2.set(0);
        	Timer.delay(RobotMap.timerDelay);
        }
        
        if (leftStick.getRawButton(3)){//Emergency extend piston w/ double solenoid
        	sol.set(false);
        	sol2.set(DoubleSolenoid.Value.kForward);
        	Timer.delay(RobotMap.timerDelay);
        }
        else if (rightStick.getRawButton(3)){//Retract piston w/ double solenoid
        	sol2.set(DoubleSolenoid.Value.kReverse);
        	Timer.delay(RobotMap.timerDelay);
        }
        else{
        	sol2.set(DoubleSolenoid.Value.kOff);
        	Timer.delay(RobotMap.timerDelay);
        }
        
        if (rightStick.getRawButton(4)){//Primary extend piston w/ single solenoid
        	sol2.set(DoubleSolenoid.Value.kOff);
        	sol.set(true);
        	Timer.delay(RobotMap.timerDelay);
        }
        else{
        	sol.set(false);
        	Timer.delay(RobotMap.timerDelay);
        }
        
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        //LiveWindow.run();
    	System.out.println("random print statement");
    }
}