/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.DriveLine;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnToRelativeAngle extends SequentialCommandGroup {
  /**
   * Creates a new TurnToRelativeAngle.
   */
  public TurnToRelativeAngle(DriveLine driveLine, final DoubleSupplier angle, final DoubleSupplier p,
      final DoubleSupplier i, final DoubleSupplier d) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new InstantCommand(driveLine::zeroYaw, driveLine), new TurnToAbsoluteAngle(driveLine, angle, p, i, d) );
  }
}
