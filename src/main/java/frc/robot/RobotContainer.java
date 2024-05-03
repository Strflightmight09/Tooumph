package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.OperatorConstants;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.intake.IntakeCommands;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.subsystems.tank.Tank;
import frc.robot.subsystems.tank.TankCommands;

public class RobotContainer {
    private final CommandXboxController driverController =
            new CommandXboxController(OperatorConstants.DRIVER_CONTROLLER_PORT);
    private final Tank tank = Tank.getInstance();
    private final Intake intake = Intake.getInstance();

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
        intake.setDefaultCommand(IntakeCommands.getSetIntakeStateCommand(IntakeConstants.IntakeState.STOP));
    }

    private void bindControllerCommands() {
        driverController.leftTrigger().whileTrue(IntakeCommands.getSetIntakeStateCommand(IntakeConstants.IntakeState.COLLECTION));
        driverController.rightBumper().whileTrue(IntakeCommands.getSetIntakeStateCommand(IntakeConstants.IntakeState.EJECTION));
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