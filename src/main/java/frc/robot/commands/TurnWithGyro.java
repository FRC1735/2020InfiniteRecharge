/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveLine;

public class TurnWithGyro extends CommandBase {
  /**
   * Creates a new TurnWithGyro.
   */
  double targetDegrees;
  DriveLine driveLine;
  AHRS gyro;
  double startingDegrees;

  public TurnWithGyro(double thatdegrees, DriveLine thatdriveLine, AHRS thatgyro) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.targetDegrees = thatdegrees;
    this.driveLine = thatdriveLine;
    this.gyro = thatgyro;
    this.startingDegrees = 0;
    addRequirements(driveLine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.startingDegrees = gyro.getAngle();
    this.targetDegrees = startingDegrees + targetDegrees;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speed = .5;
    double currentDegrees = gyro.getAngle();
    SmartDashboard.putNumber("Gyro", currentDegrees);
    if (currentDegrees > targetDegrees) {
      System.out.println("execute A");
     driveLine.arcadeDrive(-.51, .14);
    } else {
      driveLine.arcadeDrive(.51, .14);
      System.out.println("execute B");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    System.out.println("end");
    driveLine.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return hasReachedTargetAngle();
  }

  private boolean hasReachedTargetAngle() {
    double currentAngle = gyro.getAngle();
    double thatTargetDegreesAnd2 = targetDegrees + 2;
    double thatTargetDegreesNot2 = targetDegrees - 2;
    // > < >= <= ==
    // &&
    // ||
    if (thatTargetDegreesNot2 < currentAngle && thatTargetDegreesAnd2 > currentAngle) {
      System.out.println("hasReachedTargetAngle: true");
      return true;
    }
    System.out.println("hasReachedTargetAngle: false");
    return false;
  }

}
