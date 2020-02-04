/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
  private NetworkTable limelight;
  private NetworkTableEntry tv;
  private NetworkTableEntry tx;
  private NetworkTableEntry ty;
  private NetworkTableEntry ta;
  private NetworkTableEntry tx0;
  private NetworkTableEntry tx1;
  private NetworkTableEntry tx2;  

  /**
   * Creates a new LimeLight.
   */
  public LimeLight() {
    limelight = NetworkTableInstance.getDefault().getTable("limelight");
    
    tv = limelight.getEntry("tv"); // target detected
    tx = limelight.getEntry("tx"); // Horizontal angle to target in degrees
    ty = limelight.getEntry("ty"); // Vertical angle to target in degrees
    ta = limelight.getEntry("ta"); // Area of target relative to full screen image in percent 
    tx0 = limelight.getEntry("tx0"); // one of the three raw tx values
    tx1 = limelight.getEntry("tx1"); // one of the three raw tx values
    tx2 = limelight.getEntry("tx2"); //one of the three raw tx values
    }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getTv() {
    return tv.getDouble(0);

  }

  public double getTx() {
    return tx.getDouble(0);
  }

  public double getTx0() {
    return tx0.getDouble(0);
  }

  public double getTx1() {
    return tx1.getDouble(0);
  }

  public double getTx2() {
    return tx2.getDouble(0);
  }

  public double getTy() {
    return ty.getDouble(0);
  }

  public void setLedMode(final int ledMode) {
    limelight.getEntry("ledMode").setNumber(ledMode);
  }

  public void setCameraMode(final int cameraMode) {
    limelight.getEntry("camMode").setNumber(cameraMode);
  }

  public interface LedMode {
    public static int USE_CURRENT_PIPELINE = 0;
    public static int FORCE_OFF = 1;
    public static int FORCE_BLINK = 2;
    public static int FORCE_ON = 3;
  }

  public interface CameraMode {
    public static int VISION_PROCESSING = 0;
    public static int DRIVING = 1;
  }
}