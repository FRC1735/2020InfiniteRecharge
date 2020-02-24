/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.DistanceSensorGroup;
import frc.robot.subsystems.Lighting;
import frc.robot.subsystems.Tube;

public class OptimizeTube extends CommandBase {
  Logger logger = Logger.getGlobal();

  private Tube tube;
  private Lighting lighting;
  /**
   * Creates a new OptimizeTube.
   */
  public OptimizeTube(Tube thatTube, Lighting lighting) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(thatTube);
    addRequirements(lighting);
    this.tube = thatTube;
    this.lighting = lighting;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DistanceSensorGroup sensors = tube.getDistanceSensorGroup();

    SmartDashboard.putNumber("distance sensor 1", sensors.getDistances().get(1));
    
    /*
    if (sensors.isPowerCellDetected(1)) {
      logger.info("Tube is full!");
      tube.stop();
      return;
    }
    */
    

    if (sensors.isPowerCellDetected(0)) {
      tube.up();
      lighting.blue();
    } else {
      tube.stop();
      lighting.yellow();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    tube.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
