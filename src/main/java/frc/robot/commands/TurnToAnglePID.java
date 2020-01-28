/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.DriveLine;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToAnglePID extends PIDCommand {
  /**
   * Creates a new TurnToAnglePID.
   */
  public TurnToAnglePID(final DriveLine driveLine, final DoubleSupplier angle) {
    super(
        new PIDController(0, 0, 0),
        // This should return the measurement
        driveLine::getYaw,
        // This should return the setpoint (can also be a constant)
        angle,
         // This uses the output
        output -> {
          driveLine.set(ControlMode.PercentOutput, output, output);
        });

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveLine);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(2); // todo - is this what we want?
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
