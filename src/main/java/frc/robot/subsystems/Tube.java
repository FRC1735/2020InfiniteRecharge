/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.DistanceSensorGroup;

public class Tube extends SubsystemBase {
  Logger logger = Logger.getGlobal();

  private static double MANUAL_CONTROL_SPEED = 1.0;
  private static double OPTIMIZE_TUBE_SPEED = 0.6;

  private WPI_VictorSPX motor;
  private DistanceSensorGroup distanceSensorGroup;

  /**
   * Creates a new Tube.
   */
  public Tube() {
    motor = new WPI_VictorSPX(5);
    distanceSensorGroup = new DistanceSensorGroup(0, 1, 2, 3, 4);
  }

  public void downManual() {
    down(MANUAL_CONTROL_SPEED);
  }

  public void downHalfSpeed() {
    down(0.75);
  }

  public void upManual() {
    up(-MANUAL_CONTROL_SPEED);
  }

  public void downOptimize() {
    down(OPTIMIZE_TUBE_SPEED);
  }

  public void upOptimize() {
    up(-OPTIMIZE_TUBE_SPEED);
  }

  private void down(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  private void up(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void stop() {
    motor.stopMotor();
  }

  public DistanceSensorGroup getDistanceSensorGroup() {
    return distanceSensorGroup;
  }
}
