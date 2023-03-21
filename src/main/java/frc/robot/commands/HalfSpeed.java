// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
<<<<<<< HEAD
=======
import frc.robot.Constants;
>>>>>>> 48e31f4ec5b40a5d23036166075c6a29489f2a71
import frc.robot.subsystems.DriveSubsystem;

public class HalfSpeed extends CommandBase {
  DriveSubsystem driveSubsystem;
  /** Creates a new HalfSpeed. */
  public HalfSpeed(DriveSubsystem driveSubsystem) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driveSubsystem);
    this.driveSubsystem = driveSubsystem;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveSubsystem.setMax(0.1);
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
