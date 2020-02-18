/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.sensors.DistanceSensor;

/**
 * Add your docs here.
 */
public class GetSensorDistance extends CommandBase {
  Logger logger = Logger.getGlobal();
  DistanceSensor distanceSensor = new DistanceSensor(0);

  /**
   * Add your docs here.
   */
  public GetSensorDistance() {
    super();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    logger.info("distance: " + distanceSensor.getDistance());
  }


}
