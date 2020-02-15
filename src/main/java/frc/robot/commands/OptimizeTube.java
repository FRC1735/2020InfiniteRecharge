/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.DistanceSensorGroup;
import frc.robot.subsystems.Tube;

public class OptimizeTube extends CommandBase {
  Logger logger = Logger.getGlobal();

  private Tube tube;
  /**
   * Creates a new OptimizeTube.
   */
  public OptimizeTube(Tube thatTube) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(thatTube);
    this.tube = thatTube;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DistanceSensorGroup sensors = tube.getDistanceSensorGroup();

    if (sensors.isPowerCellDetected(4)) {
      logger.info("Tube is full!");
      tube.stop();
      return;
    }

    if (sensors.isPowerCellDetected(0)) {
      tube.forward();
    } else {
      tube.stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    // TODO - if the command is interrupted we stop the any motors that may have been started
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
