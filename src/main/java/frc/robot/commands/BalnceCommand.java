// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.DriveSubsystem;

public class BalnceCommand extends CommandBase {
  
  private DriveSubsystem driveSubsystem;
  //drive
  //private final DriveSubsystem m_robotdrive = DriveSubsystem();
  //navX
  private final AHRS navx = new AHRS(SPI.Port.kMXP);

  /** Creates a new BalnceCommand. */
  public BalnceCommand(DriveSubsystem driveSubsystem) {  
    // Use addRequirements() here to declare subsystem dependencies.
    this.driveSubsystem = driveSubsystem;
    addRequirements(driveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    navx.reset();
    System.out.println("Balance Active");

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (navx.getRoll() < 2){
      driveSubsystem.arcadeDrive(0, 0);
    }

    if (navx.getRoll() > -2){
        driveSubsystem.arcadeDrive(0, 0);
    }
    
    if (navx.getRoll() <= -2){
        driveSubsystem.arcadeDrive(Constants.DriveConstants.kMaxSpeed, 0);
    }

    if (navx.getRoll() >= 2){
        driveSubsystem.arcadeDrive(Constants.DriveConstants.kMaxSpeed, 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
