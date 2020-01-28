/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveLine extends SubsystemBase implements PIDOutput{
  private WPI_TalonSRX leftMotor;
  private WPI_TalonSRX rightMotor;
  private DifferentialDrive differentialDrive;
  private WPI_VictorSPX leftFollower;
  private WPI_VictorSPX rightFollower;
  private final AHRS gyro; 
  public PIDController turnController;

  private final double kP = 0;
  private final double kI = 0;
  private final double kD = 0;

  /**
   * Creates a new DriveLine.
   */
  public DriveLine() {
    leftMotor = new WPI_TalonSRX(1);
    rightMotor = new WPI_TalonSRX(2);
    leftFollower = new WPI_VictorSPX(3);
    rightFollower = new WPI_VictorSPX(4);
    gyro = new AHRS(SerialPort.Port.kMXP);

    leftFollower.follow(leftMotor);
    rightFollower.follow(rightMotor);
    leftFollower.setSafetyEnabled(false);
    rightFollower.setSafetyEnabled(false);

    differentialDrive = new DifferentialDrive(leftMotor, rightMotor);
    differentialDrive.setSafetyEnabled(true);
    differentialDrive.setExpiration(0.1);
    differentialDrive.setMaxOutput(1.0);

    turnController = new PIDController(kP, kI, kD, gyro, this);
    turnController.setInputRange(-180.0f, -180.0f);
    turnController.setOutputRange(-0.45, 0.45);
    turnController.setAbsoluteTolerance(2.0f);
    turnController.setContinuous();
  }

  @Override
  public void periodic() {

  }
  
  public WPI_TalonSRX getLeftMotor() {
    return leftMotor;
  }
  
  public WPI_TalonSRX getRightMotor() {
    return rightMotor;
  }

  public void arcadeDrive(final double joystickX, final double joystickY) {
    System.out.println("arcadeDrive: " + joystickX + ", " + joystickY);
    differentialDrive.arcadeDrive(joystickY, joystickX, true);
  }

  public void tankDrive(final double joystickAY, final double joystickBY) {
    differentialDrive.tankDrive(joystickAY, joystickBY);
  }

  public void set(ControlMode mode, double leftValue, double rightValue) 
  {
    leftMotor.set(mode, leftValue);
    rightMotor.set(mode, rightValue);
  }

  public void rotateDegrees(double angle) 
  {
    gyro.reset();
    turnController.reset();
    turnController.setPID(kP, kI, kD);
    turnController.setSetpoint(angle);
    turnController.enable();
   }
  public void stop() {
    differentialDrive.stopMotor();
  }

  @Override
  public void pidWrite(double output) 
  {
    set(ControlMode.PercentOutput, -output, output);
  }
}
