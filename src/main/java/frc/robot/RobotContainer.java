/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DriveDistance;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.GetSensorDistance;
import frc.robot.commands.ShootOne;
import frc.robot.commands.TurnToAngle;
import frc.robot.joysticks.AbstractJoystick;
import frc.robot.joysticks.JoystickFactory;
import frc.robot.joysticks.Role;
import frc.robot.joysticks.XBoxJoystick;
import frc.robot.sensors.DistanceSensor;
import frc.robot.subsystems.DriveLine;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Lighting;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    private Joystick joystickLeft = new Joystick(0);
    JoystickFactory joystickFactory = new JoystickFactory();
    private AbstractJoystick abstractJoystickLeft = joystickFactory.get(joystickLeft, Role.DRIVER_LEFT);

    // The robot's subsystems and commands are defined here...
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final DriveLine driveLine = new DriveLine();
    private final Lighting lighting = new Lighting();
    // private final Collector collector = new Collector();
    private final Shooter shooter = new Shooter();
    private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
    private final DriveWithJoystick driveWithJoystickCommand = new DriveWithJoystick(abstractJoystickLeft, driveLine);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
        driveLine.setDefaultCommand(driveWithJoystickCommand);

        intializeSmartDashBoard();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        new JoystickButton(joystickLeft, XBoxJoystick.BUTTON_X)
                .whenPressed(new InstantCommand(lighting::blue, lighting));

        new JoystickButton(joystickLeft, XBoxJoystick.BUTTON_Y)
                .whenPressed(new InstantCommand(lighting::yellow, lighting));

        new JoystickButton(joystickLeft, XBoxJoystick.BUTTON_A).whenPressed(new InstantCommand(lighting::on, lighting));

        new JoystickButton(joystickLeft, XBoxJoystick.BUTTON_B)
                .whenPressed(new InstantCommand(lighting::off, lighting));

        new JoystickButton(joystickLeft, XBoxJoystick.BUMPER_R)
                .whenPressed(new ShootOne(shooter).withTimeout(0.25));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return m_autoCommand;
    }

    private void intializeSmartDashBoard() {
        SmartDashboard.putNumber("Turn Angle", 90);

        SmartDashboard.putData("Turn",
                new TurnToAngle(driveLine, () -> SmartDashboard.getNumber("Turn Angle", 90),
                        () -> SmartDashboard.getNumber("Turn P", 0), () -> SmartDashboard.getNumber("Turn I", 0),
                        () -> SmartDashboard.getNumber("Turn D", 0)));

        SmartDashboard.putNumber("Turn P", 0.2);
        SmartDashboard.putNumber("Turn I", 0.1);
        SmartDashboard.putNumber("Turn D", 0.5);

        // this is an example and good use case for the "inline command" feature they
        // added this year
        SmartDashboard.putData("Reset Gyro", new InstantCommand(driveLine::zeroYaw, driveLine));

        SmartDashboard.putNumber("Drive Inches", 0);

        SmartDashboard.putData("Drive", new DriveDistance(driveLine, SmartDashboard.getNumber("Drive Inches", 0)));

        SmartDashboard.putData("reset encoders", new InstantCommand(driveLine::resetEncoders, driveLine));

        SmartDashboard.putData("get distance", new GetSensorDistance());
    }
}
