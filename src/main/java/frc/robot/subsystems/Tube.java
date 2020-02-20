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
  //private DistanceSensorGroup distanceSensorGroup;
  private double SPEED = 1; //0.3;

  /**
   * Creates a new Tube.
   */
  public Tube() {
    motor = new WPI_VictorSPX(5);
    //distanceSensorGroup = new DistanceSensorGroup(0, 1, 2); // TODO RoboRio only has 4 slots, it really seems like we only 2 looking at how the OptimizeTube command works
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void down() {
    motor.set(ControlMode.PercentOutput, SPEED);
  }

  public void up() {
    motor.set(ControlMode.PercentOutput, -SPEED);
  }

  public void stop() {
    motor.stopMotor();
  }
  public DistanceSensorGroup getDistanceSensorGroup() {
    return null;//distanceSensorGroup;
  }
}
