package org.usfirst.frc.team3636.robot;

import java.util.Scanner;

import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotState;
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


import org.usfirst.frc.team3636.robot.commands.ExampleCommand;
import org.usfirst.frc.team3636.robot.subsystems.ExampleSubsystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	private static final boolean testCommit = true;

    public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    public static OI oi;
    
    RobotDrive myRobot = new RobotDrive(0, 1); // class that handles basic drive
    // operations
//    Spark m_frontLeft = new Spark(4);
//    Spark m_rearLeft = new Spark(2);
//    SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
//
//    Spark m_frontRight = new Spark(5);
//    Spark m_rearRight = new Spark(3);
//    SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
//
//    DifferentialDrive myRobot = new DifferentialDrive(m_left, m_right);
    public Joystick leftStick = new Joystick(0);
    public Joystick rightStick = new Joystick(1);
//    public Solenoid sol = new Solenoid(0,0); //device id 1, channel 0
    public DoubleSolenoid sol = new DoubleSolenoid(0,1,2); //device id 1, channel 0
    public Compressor com = new Compressor(0);
    
    /*public Button leftButton = new JoystickButton(leftStick, 0);
    public Button rightButton = new JoystickButton(rightStick, 1);
    public Spark s = new Spark(2); */
    Timer timer = new Timer();

//    public final double autoleft = -.25; 
//    public final double autoright = -.25;
//    public final double timerdelay = .1;
    public final int motorspeed = 1; 
    public final int TIME_AUTO = 15; //Change to autonomous time in seconds
    public final double AUTO_SPEED = .5; //This controls the speed of autonomous
    public double CURVE_CHANGE =-.01;
    public final int BRIGHTNESS = 30;    
    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
    	//this comment lamo
        oi = new OI();
        chooser.addDefault("Default Auto", new ExampleCommand());
        // chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
        new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            camera.setFPS(60);
            camera.setBrightness(BRIGHTNESS);
//            CvSink cvSink = CameraServer.getInstance().getVideo();
//            CvSource outputStream = CameraServer.getInstance().putVideo("Blur", 640, 480);
//            
//            Mat source = new Mat();
//            Mat output = new Mat();
//            
//            while(!Thread.interrupted()) {
//                cvSink.grabFrame(source);
//                Imgproc.cvtColor(source, output, Imgproc.COLOR_BGR2GRAY);
//                outputStream.putFrame(output);
//            }
        }).start();
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
        autonomousCommand = chooser.getSelected();
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
        boolean experimentOn = true;
        if (experimentOn == false){
	        System.out.println("time: " + timer.get());
	        while (timer.get() < TIME_AUTO && RobotState.isAutonomous()){
	        	//myRobot.drive(AUTO_SPEED,CURVE_CHANGE);
	        	myRobot.tankDrive(AUTO_SPEED,AUTO_SPEED-.1);
	        
	        	
	        }
        }
        //more experiments:
        else if (experimentOn == true){
	        while (timer.get() < TIME_AUTO && RobotState.isAutonomous()){
	        	CURVE_CHANGE = .15; //turn right
	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
	        	Timer.delay(.1);
	        	CURVE_CHANGE = -.1; //turn left
	        	myRobot.arcadeDrive(AUTO_SPEED, CURVE_CHANGE);
	        	Timer.delay(.1);
	        }
        }
        
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
        
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        double leftval = -leftStick.getY();
        double rightval= -rightStick.getY();
        myRobot.tankDrive(leftval, rightval);
        Timer.delay(0.005); // wait for a motor update time
       
//        boolean enabled = com.enabled();
        boolean pressureSwitch = com.getPressureSwitchValue();
//        double current = com.getCompressorCurrent();
        /*if (leftStick.getRawButton(3)) {
            //leftB`utton.whileHeld(new TestCommand());
        }*/
        
        double duration = 3.0;

        if(leftStick.getRawButton(3)){
        	com.setClosedLoopControl(true);
        	timer.delay(.005);
        }
        else{
        	com.setClosedLoopControl(false);
        	timer.delay(.005);
        }
        if (leftStick.getTrigger()){
//        	if(com.getPressureSwitchValue()){
//        	System.out.println(com.getClosedLoopControl());
//        	}
        	System.out.println(pressureSwitch);
//        	myRobot.tankDrive(.5, 0);
//        	com.setClosedLoopControl(true);

        	sol.set(DoubleSolenoid.Value.kForward);
//        	com.start();
//        	System.out.println(com.enabled());
        	
        	
        	timer.delay(0.005);
        	
        
        }
//        else{
////        	com.setClosedLoopControl(false);
//        	System.out.println(pressureSwitch);
////        	timer.delay(.005);
//        	/*if(com.enabled()){
//        		com.stop();
//        	}*/
//        }
        if (rightStick.getTrigger()){
        	//sol.set(DoubleSolenoid.Value.kReverse);
        	sol.set(DoubleSolenoid.Value.kReverse);
        	//sol.set(DoubleSolenoid.Value.);
//        	System.out.println(sol.get());
//        	sol.set(false);
        	//com.stop();
        	timer.delay(0.005);
//            com.set(false);
//            com.setPulseDuration(duration);
//            com.startPulse();
        }
//        else{
////        	sol.set(DoubleSolenoid.Value.kOff);
//        
//        	
//        }
        
//        else{
//            s.set(0);
//        }
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        //LiveWindow.run();
    }
}