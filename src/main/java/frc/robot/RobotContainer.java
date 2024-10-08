package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeCommands;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterCommands;
import frc.robot.subsystems.shooter.ShooterConstants;
import frc.robot.subsystems.tank.Tank;
import frc.robot.subsystems.tank.TankCommands;

public class RobotContainer {
    private final CommandXboxController driverController =
            new CommandXboxController(OperatorConstants.DRIVER_CONTROLLER_PORT);
    private final Tank tank = Tank.getInstance();
    private final Intake intake = Intake.getInstance();
    private final Shooter shooter = Shooter.getInstance();

    public RobotContainer() {
        configureBindings();
    }

    private void configureBindings() {
        bindDefaultCommands();
        bindControllerCommands();
    }

    private void bindDefaultCommands() {
        tank.setDefaultCommand(
                TankCommands.getSetArcadeDriveCommand(
                        () -> calculateDriveSpeed(-driverController.getLeftY()),
                        () -> calculateDriveSpeed(-driverController.getRightX())
                )
        );
        intake.setDefaultCommand(IntakeCommands.getSetTargetIntakeStateCommand(IntakeConstants.IntakeState.STOP));
        shooter.setDefaultCommand(ShooterCommands.getSetTargetShooterStateCommand(ShooterConstants.ShooterState.STOPPED));
    }

    private void bindControllerCommands() {
        driverController.leftTrigger().whileTrue(IntakeCommands.getSetTargetIntakeStateCommand(IntakeConstants.IntakeState.COLLECTION));
        driverController.rightBumper().whileTrue(IntakeCommands.getSetTargetIntakeStateCommand(IntakeConstants.IntakeState.EJECTION));
        driverController.y().whileTrue(ShooterCommands.getSetTargetShooterStateCommand(ShooterConstants.ShooterState.SHOOTING));
    }

    public Command getAutonomousCommand() {
        return null;
    }

    private double calculateDriveSpeed(double stickMovement) {
        final double squaredShiftModeValue = driverController.getRightTriggerAxis();
        final double minimumShiftValueCoefficient = -1;
        final double speed = 1 - squaredShiftModeValue * minimumShiftValueCoefficient;
        return stickMovement / speed;
    }
}