/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ControlTurretWithJoystick;
import frc.robot.commands.DeployCollector;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.OptimizeTube;
import frc.robot.commands.ShootOne;
import frc.robot.commands.TubeUp;
import frc.robot.joysticks.AbstractJoystick;
import frc.robot.joysticks.Attack3Joystick;
import frc.robot.joysticks.JoystickFactory;
import frc.robot.joysticks.Role;
import frc.robot.joysticks.XBoxJoystick;
import frc.robot.sensors.DistanceSensorGroup;
import frc.robot.subsystems.Collector;
import frc.robot.subsystems.DriveLine;
import frc.robot.subsystems.Lighting;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Tube;
import frc.robot.subsystems.Turret;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private SendableChooser<Integer> autoShootTimeChooser = new SendableChooser<Integer>();
    private SendableChooser<Integer> autoDoNothingTimeChooser = new SendableChooser<Integer>();
    private SendableChooser<Integer> autoDriveTimeChooser = new SendableChooser<Integer>();

        private Joystick xBoxJoystick = new Joystick(0);
    private Joystick attack3Joystick = new Joystick(1);       

    JoystickFactory joystickFactory = new JoystickFactory();
    private AbstractJoystick abstractJoystickLeft = joystickFactory.get(xBoxJoystick, Role.DRIVER_LEFT);
    private AbstractJoystick abstractJoystickRight = joystickFactory.get(attack3Joystick, Role.OPERATOR);

        // The robot's subsystems and commands are defined here...
    private final DistanceSensorGroup distanceSensors = new DistanceSensorGroup(0, 1, 2, 3, 4);
    private final DriveLine driveLine = new DriveLine();
    private final Lighting lighting = new Lighting();
    private final Collector collector = new Collector(distanceSensors);
    private final Shooter shooter = new Shooter();
    private final LimeLight limelight = new LimeLight();
    private final Tube tube = new Tube(distanceSensors);
    private final Turret turret = new Turret();
    private final DriveWithJoystick driveWithJoystickCommand = new DriveWithJoystick(abstractJoystickLeft, driveLine);
        //private final ControlTurretWithLimelight controlTurretWithLimelightCommand = new ControlTurretWithLimelight(turret, limelight);
    
    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        driveLine.setDefaultCommand(driveWithJoystickCommand);
        tube.setDefaultCommand(new OptimizeTube(tube, lighting));
        turret.setDefaultCommand(new ControlTurretWithJoystick(turret, abstractJoystickRight));
        //lighting.setDefaultCommand(new InstantCommand(lighting::green, lighting));

        intializeSmartDashBoard();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        initializeXBoxController();
        initializeAttack3();
    }

    private void initializeXBoxController() {

        // collector I
        // when pressed: deploy collector, suck in and optimize tue
        // when released: stop collector and pull in
        new JoystickButton(xBoxJoystick, XBoxJoystick.BUTTON_A)
                .whenPressed(
                        new SequentialCommandGroup(
                                new DeployCollector(collector, Value.kForward).withTimeout(0.08),
                                new InstantCommand(collector::in, collector))
                )
                .whenReleased(new SequentialCommandGroup(
                        new InstantCommand(collector::stop, collector),
                        new DeployCollector(collector, Value.kReverse).withTimeout(0.08)      
                ));
             
        // collector II
        // when pressed: suck in, optimize tube
        new JoystickButton(xBoxJoystick, XBoxJoystick.BUTTON_X)
                .whenPressed(new ParallelCommandGroup(
                        new InstantCommand(collector::in, collector)
                ))
                .whenReleased(new InstantCommand(collector::stop, collector));

        // reverse collector
        new JoystickButton(xBoxJoystick, XBoxJoystick.BUTTON_B)
                .whenPressed(new InstantCommand(collector::out, collector))
                                .whenReleased(new InstantCommand(collector::stop, collector));
                
        new JoystickButton(xBoxJoystick, XBoxJoystick.BUTTON_START)
                .whenPressed(new InstantCommand(limelight::ledOff, limelight));

                /*
        new JoystickButton(xBoxJoystick, XBoxJoystick.BUTTON_B)
                .whenPressed(new InstantCommand(collector::in, collector))
                .whenReleased(new InstantCommand(collector::stop, collector));*/
    }

    private void initializeAttack3() {
        // shooter
        new JoystickButton(attack3Joystick, Attack3Joystick.BUTTON_3).whenPressed(new ShootOne(shooter))
                                .whenReleased(new InstantCommand(shooter::disengage, shooter));
        
        // "shoot" - move the tube up, the shooter should already be spinning
        new JoystickButton(attack3Joystick, Attack3Joystick.BUTTON_1)
                                .whileHeld(tube::upManual, tube)
                                .whenReleased(new InstantCommand(tube::stop, tube));

        // "shoot with limelight turret control"
        /*
        new JoystickButton(attack3Joystick, Attack3Joystick.BUTTON_2)
                        .whenPressed(new ParallelCommandGroup(
                                controlTurretWithLimelightCommand,
                                new InstantCommand(shooter::engage, shooter)
                                )).cancelWhenRelease(controlTurretWithLimelightCommand);
                                 */

        // "unshoot"
        new JoystickButton(attack3Joystick, Attack3Joystick.BUTTON_4)
                .whileHeld(new ParallelCommandGroup(
                        new InstantCommand(shooter::engageReversed, shooter),
                        new InstantCommand(tube::downHalfSpeed, tube),
                        new InstantCommand(collector::out, collector)                        
                        ))
                .whenReleased(new ParallelCommandGroup(
                        new InstantCommand(shooter::disengage, shooter),
                        new InstantCommand(tube::stop, tube),
                        new InstantCommand(collector::stop, collector)
                ));
                
        
        // manual tube control
        new JoystickButton(attack3Joystick, Attack3Joystick.BUTTON_5)
             .whileHeld(new ParallelCommandGroup(
                new InstantCommand(tube::downManual, tube),
                new InstantCommand(collector::out, collector)
             ))       
            .whenReleased(new ParallelCommandGroup(
                    new InstantCommand(tube::stop, tube),
                     new InstantCommand(collector::stop, collector)));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
        public Command getAutonomousCommand() {
                // An ExampleCommand will run in autonomous
                // would need to have controlTurretWithLimelight running
                return new SequentialCommandGroup(
                                new ParallelCommandGroup(new ShootOne(shooter),
                                                new SequentialCommandGroup(new WaitCommand(1), new TubeUp(tube))).withTimeout(autoShootTimeChooser.getSelected()),
                                new WaitCommand(autoDoNothingTimeChooser.getSelected()), 
                                new DriveDistance(driveLine, -48).withTimeout(autoDriveTimeChooser.getSelected())
                                );
                   
    }

    private void intializeSmartDashBoard() {
        autoShootTimeChooser.setDefaultOption("1", 1);
        autoShootTimeChooser.addOption("2", 2);
        autoShootTimeChooser.addOption("3", 3);
        autoShootTimeChooser.addOption("4", 4);
        autoShootTimeChooser.addOption("5", 5);
        autoShootTimeChooser.addOption("6", 6);
        SmartDashboard.putData("Auto Shoot Time", autoShootTimeChooser);
        
        autoDoNothingTimeChooser.setDefaultOption("1", 1);
        autoDoNothingTimeChooser.addOption("2", 2);
        autoDoNothingTimeChooser.addOption("3", 3);
        autoDoNothingTimeChooser.addOption("4", 4);
        autoDoNothingTimeChooser.addOption("5", 5);
        autoDoNothingTimeChooser.addOption("6", 6);
        SmartDashboard.putData("Auto Do Nothing Time", autoDoNothingTimeChooser);
                
        autoDriveTimeChooser.setDefaultOption("1", 1);
        autoDriveTimeChooser.addOption("2", 2);
        autoDriveTimeChooser.addOption("3", 3);
        autoDriveTimeChooser.addOption("4", 4);
        autoDriveTimeChooser.addOption("5", 5);
        autoDriveTimeChooser.addOption("6", 6);
        SmartDashboard.putData("Auto Drive Time", autoDriveTimeChooser);
             
        //
        // !!! this is copied from the real auto command above, if you change this change that!!!!
        //
        SmartDashboard.putData("AUTO TEST", new SequentialCommandGroup(
                                new ParallelCommandGroup(new ShootOne(shooter),
                                                new SequentialCommandGroup(new WaitCommand(1), new TubeUp(tube))).withTimeout(3),
                                new WaitCommand(autoDoNothingTimeChooser.getSelected()), 
                                new DriveDistance(driveLine, -48).withTimeout(autoDriveTimeChooser.getSelected())
                                ));
        
                /*
                SmartDashboard.putNumber("Turn Angle", 90);

        SmartDashboard.putData("Turn",
                new TurnToAngle(driveLine, () -> SmartDashboard.getNum
                
                                () -> SmartDashboard.get
                
                SmartDashboard.putNumber("Turn P", 0.2);
                SmartDashboard.putNumber("Turn I", 0.1);
        SmartDashboard.putNumber("Turn D", 0.5);
        */
        // this is an example and good use case for the "inline command" feature they
                // added this year
        /*
        SmartDashboard.putData("Reset Gyro", new InstantCommand(driveLine::zeroYaw, driveLine));

        SmartDashboard.putNumber("Drive Inches", 0);

        SmartDashboard.putData("Drive", new DriveDistance(driveLine, SmartDashboard.getNumber("Drive Inches", 0)));

        SmartDashboard.putData("reset encoders", new InstantCommand(driveLine::resetEncoders, driveLine));
        */
        // SmartDashboard.putData("get distance", new GetSensorDistance());
        /*
        SmartDashboard.putData("shoot (no PID)", new ShootOne(shooter));
        SmartDashboard.putData("shoot (PID)", new Shoot(shooter));
        */
    }
}
