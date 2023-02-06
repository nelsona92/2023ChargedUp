// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {

  private final CANSparkMax m_zeroWheel = 
  new CANSparkMax(DriveConstants.kLeftMotor00CanBusID, MotorType.kBrushless);
private final CANSparkMax m_oneWheel = 
  new CANSparkMax(DriveConstants.kLeftMotor01CanBusID, MotorType.kBrushless);
private final CANSparkMax m_twoWheel = 
  new CANSparkMax(DriveConstants.kRightMotor02CanBusID, MotorType.kBrushless);
private final CANSparkMax m_threeWheel = 
  new CANSparkMax(DriveConstants.kRightMotor03CanBusID, MotorType.kBrushless);

private final MotorControllerGroup m_leftMotors =
  new MotorControllerGroup(m_zeroWheel, m_oneWheel);
private final MotorControllerGroup m_rightMotors =
  new MotorControllerGroup(m_twoWheel, m_threeWheel);

private final DifferentialDrive m_drive =
  new DifferentialDrive(m_leftMotors, m_rightMotors);
  /** Creates a new DriveSubsystem. */
  public DriveSubsystem() {

    //inverting oneside
    m_zeroWheel.setInverted(true);
    m_oneWheel.setInverted(true);

    //ramp rate
    m_zeroWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_oneWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_twoWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    m_threeWheel.setOpenLoopRampRate(DriveConstants.kRampRate);
    
    // pairing the motors
    m_oneWheel.follow(m_zeroWheel);
    m_twoWheel.follow(m_threeWheel);
    //m_ahrs.     need to set up gyro
    //
    m_drive.setDeadband(0.15);
    m_drive.setMaxOutput(DriveConstants.kMaxSpeed);


  }

  // 
  public void arcadeDrive(double fwd, double rot){
    m_drive.arcadeDrive(-fwd*Math.abs(fwd), rot);
  }

  // straight driving... needs gyro added
  public void simpleDrive(double kpower) {
    m_drive.arcadeDrive(kpower, 0.0);
  }

  // to drop maximum speed for delicate motion
  public void setMax(double maxOutput){
    m_drive.setMaxOutput(maxOutput);
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
