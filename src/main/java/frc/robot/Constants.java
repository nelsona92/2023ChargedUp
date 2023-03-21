// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

  public static int kHalfPower = XboxController.Button.kLeftBumper.value;
  public static boolean powerState = false;
  public static boolean speedState = true; //true max, false half
  public static boolean kspeedSlowState = false;
  public static final double kspeedSlowFraction = 0.50;
  
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }
  public final class DriveConstants{
    public static final int kLeftMotor00CanBusID = 11;
    public static final int kLeftMotor01CanBusID = 12;
    public static final int kRightMotor02CanBusID = 10;
    public static final int kRightMotor03CanBusID = 13;
    public static final double kMaxSpeed = 0.9;
    public static final double kRampRate = 0.65;
    public static final double kHalfSpeed = 0.5;
    public static final double kBalanceSpeed = 0.6;
    
  }
}
