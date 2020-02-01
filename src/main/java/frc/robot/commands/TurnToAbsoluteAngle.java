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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.DriveLine;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToAbsoluteAngle extends PIDCommand {
  private int atSetpoint = 0;

  public TurnToAbsoluteAngle(final DriveLine driveLine, final DoubleSupplier angle, final DoubleSupplier p,
      final DoubleSupplier i, final DoubleSupplier d) {
    super(new PIDController(p.getAsDouble(), i.getAsDouble(), d.getAsDouble()),
        // This should return the measurement
        driveLine::getYaw,
        // This should return the setpoint (can also be a constant)
        angle,
        // This uses the output
        output -> {
          double clampedOutput = MathUtil.clamp(output, -.3, .3);
          driveLine.set(ControlMode.PercentOutput, clampedOutput, clampedOutput);
        });

    System.out.println("P: " + p + " I: " + i + " D: " + d);

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveLine);

    // Configure additional PID options by calling `getController` here.
    getController().enableContinuousInput(-180, 180);
    getController().setTolerance(2);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (getController().atSetpoint()) {
      atSetpoint++;
      if (atSetpoint > 4) {
        return true;
      }
      return false;
    } else {
      atSetpoint = 0;
      return false;
    }
  }
}
