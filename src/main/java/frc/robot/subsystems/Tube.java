/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.sensors.DistanceSensorGroup;

public class Tube extends SubsystemBase {
  private WPI_VictorSPX motor;
  private DistanceSensorGroup distanceSensorGroup;
  private double SPEED = 0.3;

  /**
   * Creates a new Tube.
   */
  public Tube() {
    motor = new WPI_VictorSPX(1); // TODO This is the left motor of the practice bot
    distanceSensorGroup = new DistanceSensorGroup(0, 1, 2, 3, 4);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void forward() {
    motor.set(ControlMode.PercentOutput, SPEED);
  }

  public void backward() {
    motor.set(ControlMode.PercentOutput, -SPEED);
  }
}
