// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.AutoConstants;
import frc.robot.Constants.OperatorConstants;
<<<<<<< HEAD
import frc.robot.commands.Autos;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
=======
import frc.robot.commands.BalnceCommand;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSink;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
>>>>>>> 48e31f4ec5b40a5d23036166075c6a29489f2a71
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {

   // drn -- drive & intake & arm subsystem declarations
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();

   // drn -- A chooser for autonomous commands
   private final SendableChooser<Command> m_chooser = new SendableChooser<>();

  //calling robot for navX
  //private Robot m_robot;

  //pneumatics
  Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  
  boolean enabled = pcmCompressor.isEnabled();
  boolean pressureSwitch = pcmCompressor.getPressureSwitchValue();
  double current = pcmCompressor.getCurrent();

  //Solenoid pcmSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 0);
  DoubleSolenoid pcmDoubleSolenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);

  //balance subsytem
  private final BalnceCommand m_balance = new BalnceCommand(m_robotDrive);

  
  private final ShuffleboardTab sbCamera = Shuffleboard.getTab("Camera");
  
  //camera
  private UsbCamera camera01;
  private VideoSink videoServer;

  // Replace with CommandPS4Controller or CommandJoystick if needed
  public final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);

  //auto command
  private final Command m_simpleDriveReverse = new StartEndCommand(() -> m_robotDrive.arcadeDrive(AutoConstants.kPower, 0.0), () -> m_robotDrive.arcadeDrive(0.0, 0.0),
      m_robotDrive).withTimeout(AutoConstants.kTimeOut);
  private final Command m_simpleAuto = new SequentialCommandGroup(
    new RunCommand(() -> pcmDoubleSolenoid.set(Value.kReverse)).withTimeout(1.5),
    new RunCommand(() -> pcmDoubleSolenoid.set(Value.kForward)).withTimeout(0.2),
    //reverse
    new StartEndCommand(() -> m_robotDrive.arcadeDrive(-AutoConstants.kPower, 0.0), () -> m_robotDrive.arcadeDrive(0.0, 0.0), m_robotDrive).withTimeout(AutoConstants.kTimeOut), 
    //turn(doesnt work)
    //new StartEndCommand(() -> m_robotDrive.arcadeDrive(0.0, 1), () -> m_robotDrive.arcadeDrive(0.0, 0.0), m_robotDrive).withTimeout(.7),
    //new StartEndCommand(() -> m_robotDrive.arcadeDrive(-1, 0.0), () -> m_robotDrive.arcadeDrive(0.0, 0.0), m_robotDrive).withTimeout(.5),
    (m_balance));
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    cameraInit();
    // Configure the trigger bindings
    configureBindings();
    System.out.println("out of configure bindings");

    m_robotDrive
        .setDefaultCommand(new RunCommand(() -> m_robotDrive.arcadeDrive(-m_driverController.getLeftY(),
            m_driverController.getRightX()), m_robotDrive));
    
    m_chooser.setDefaultOption("Auto", m_simpleAuto);

    sbCamera.add(camera01)
    .withSize(6, 4).withPosition(2, 0);
    
    m_robotDrive.m_drive.setSafetyEnabled(false);
  }

  private void cameraInit() {
    camera01 = CameraServer.startAutomaticCapture(0);
    videoServer = CameraServer.getServer();
    camera01.setResolution(320, 240);
    camera01.setFPS(15);
    videoServer.setSource(camera01);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    //pcmDoubleSolenoid.set(Value.kForward);
  
    /* 
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));
    */
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    //m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    //half speed
    m_driverController.leftBumper()
    //.toggleOnTrue(m_half);
      .onTrue(Commands.runOnce(() -> m_robotDrive.setMax(Constants.DriveConstants.kHalfSpeed)))
        .onFalse(Commands.runOnce(() -> m_robotDrive.setMax(Constants.DriveConstants.kMaxSpeed)));

    //balance
    //m_driverController.a().toggleOnTrue(m_balance);

    //solenoid
    pcmDoubleSolenoid.set(Value.kForward);
    m_driverController.povUp().onTrue(Commands.runOnce(() -> pcmDoubleSolenoid.toggle()));
    /* 
    m_driverController.povUp()
      .onTrue(Commands.runOnce(() -> System.out.println("piston")))
      .toggleOnTrue(Commands.runOnce(() -> pcmDoubleSolenoid.toggle()));
*/

    
    //m_driverController.povUp().toggleOnTrue(pcmDoubleSolenoid.set(Value.kForward));
    //m_driverController.povDown().toggleOnTrue(pcmDoubleSolenoid.set(Value.kReverse));

    }
      
  
  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return m_chooser.getSelected();
  }
}
