/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveLine;

public class TurnToAngle extends CommandBase {
  DriveLine driveLine;
  double angle;
  boolean isFinished = false;
  boolean inErrorZone = false;
  int count = 0;

  /**
   * Creates a new TurnToAngle.
   */
  public TurnToAngle(DriveLine thatdriveLine, double thatangle) {
    addRequirements(thatdriveLine);
    this.driveLine = thatdriveLine;
    this.angle = thatangle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    driveLine.rotateDegrees(angle);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double error = driveLine.turnController.getError();
    inErrorZone = Math.abs(error) < 2;

    SmartDashboard.putNumber("error", error);
    SmartDashboard.putNumber("angle", driveLine.gyro.getYaw());

    if (inErrorZone) {
      count++;
      isFinished = count >= 5;
    } else {
      count = 0;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveLine.turnController.disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return isFinished;
  }
}
