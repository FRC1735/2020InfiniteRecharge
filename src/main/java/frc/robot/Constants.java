/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static boolean TUNING_MODE = false;

    public static String SD_SHOOTER_PID_P = "Shooter P";
    public static String SD_SHOOTER_PID_I = "Shooter I";
    public static String SD_SHOOTER_PID_D = "Shooter D";
    public static String SD_TURRET_PID_P = "Turret P";
    public static String SD_TURRET_PID_I = "Turret I";
    public static String SD_TURRET_PID_D = "Turret D";
}
