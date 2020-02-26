/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.util.logging.Logger;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
  Logger logger = Logger.getGlobal();

  private WPI_TalonSRX motor;
  private WPI_VictorSPX follower;
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

    motor.configClosedloopRamp(0.25);
    motor.config_kP(0, 0.071); 
    motor.config_kI(0, 0.0); 
    motor.config_kD(0, 0.0); 
    motor.config_kF(0, 0.065);
    motor.setSensorPhase(false);

    motor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("ErroR", motor.getClosedLoopError());
  }

  public double getVelocity() {
    return motor.getSelectedSensorVelocity();
  }

  public void setVelocity(double speed) {
    motor.set(ControlMode.Velocity, speed);
  }

  public void set(double speed) {
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void engage() {
    motor.set(ControlMode.PercentOutput, 1);
  }

  public void disengage() {
    motor.stopMotor();
  }
}
