// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class BalnceCommand extends CommandBase {

  //drive
  private final DriveSubsystem m_robotdrive = new DriveSubsystem();
  //navX
  private final AHRS navx = new AHRS(SPI.Port.kMXP);

  /** Creates a new BalnceCommand. */
  public BalnceCommand() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (navx.getRoll() < 2){
        m_robotdrive.arcadeDrive(0, 0);
    }

    if (navx.getRoll() > -2){
        m_robotdrive.arcadeDrive(0, 0);
    }
    
    if (navx.getRoll() <= -2){
        m_robotdrive.arcadeDrive(1, 0);
    }

    if (navx.getRoll() >= 2){
        m_robotdrive.arcadeDrive(1, 0);
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
