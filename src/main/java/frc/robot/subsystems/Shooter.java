/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.RemoteFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  Logger logger = Logger.getGlobal();

  private WPI_TalonSRX motor;
  private WPI_VictorSPX follower; // TODO - rename if there is a more contextually relevant name, ie motorLeft, motorRight
  private double SPEED = 1;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    motor = new WPI_TalonSRX(8);
    follower = new WPI_VictorSPX(4);

    follower.setSafetyEnabled(false);
    follower.follow(motor);

    //motor.getSelectedSensorVelocity()
  }

  @Override
  public void periodic() {
    //logger.info("shooter encoder: " + motor.getSelectedSensorPosition());
    SmartDashboard.putNumber("shooter encoder", motor.getSelectedSensorVelocity(0));

  }

  public void engage() {
    motor.set(ControlMode.PercentOutput, SPEED);
  }

  public void disengage() {
    motor.stopMotor();
  }
}
