/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Collector extends SubsystemBase {
  private WPI_VictorSPX motor;
  private DoubleSolenoid solenoid;
  private double SPEED = 1;
  /*
   * Creates a new Collector.
   */
  public Collector() {
    motor = new WPI_VictorSPX(1);
    solenoid = new DoubleSolenoid(0, 1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void in() {
    // TODO - gate based on sensor status, if both are active 
    motor.set(-SPEED);
  }

  public void out() {
    motor.set(SPEED);
  }


  public void stop() {
    motor.stopMotor();
    solenoid.set(Value.kOff);
  }

  public void stopSolenoid() {
    solenoid.set(Value.kOff);
  }

  public void deploy() {
    solenoid.set(Value.kForward);
  }

  public void retract() {
    solenoid.set(Value.kReverse);
  }
}
