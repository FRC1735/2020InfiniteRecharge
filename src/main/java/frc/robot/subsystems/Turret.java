/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private WPI_TalonSRX motor;
  private AnalogPotentiometer pot;
  
  private double LEFT_LIMIT = .8;
  private double RIGHT_LIMIT = .3; 

  /**
   * Creates a new Turret.
   */
  public Turret() {
    motor = new WPI_TalonSRX(9);
    motor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
    motor.setSelectedSensorPosition(0);
    pot = new AnalogPotentiometer(2); 
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Turret Encoder", motor.getSelectedSensorPosition());
    SmartDashboard.putNumber("Turret Pot", pot.get());
  }
    
  public void set(double speed) {
    final double position = pot.get();
    
    // left is positive, right is negative
    if (speed > 0 && position <= LEFT_LIMIT) { // left
      motor.set(speed);
      return;
    }
    
    if (speed < 0 && position >= RIGHT_LIMIT) {
      motor.set(speed);
      return;
    }
    
    motor.stopMotor();
  }

  public double getEncoderValue() {
    return motor.getSelectedSensorPosition();
  }
  
  public void stop() { 
    motor.set(ControlMode.PercentOutput, 0);
  }
}
