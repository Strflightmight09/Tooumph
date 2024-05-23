package frc.robot.subsystems.intake;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

public class IntakeCommands {
    private static final Intake INTAKE = Intake.getInstance();

    public static Command getSetTargetIntakeStateCommand(IntakeConstants.IntakeState intakeState) {
        return new ParallelCommandGroup(
                getSetCollectionMotorStateCommand(intakeState.voltage),
                getSetTargetAngleFromProfileCommand(Rotation2d.fromDegrees(intakeState.targetPosition))
        );
    }

    private static Command getSetCollectionMotorStateCommand(double voltage) {
        return withoutRequirements(new FunctionalCommand(
                        () -> {
                        },
                        () -> INTAKE.setCollectionMotorState(voltage),
                        (interrupted) -> INTAKE.stopCollectionMotor(),
                        () -> false,
                        INTAKE
                )
        );
    }

    private static Command getSetTargetAngleFromProfileCommand(Rotation2d targetAngle) {
        return withoutRequirements(new FunctionalCommand(
                        () -> INTAKE.generateAngleMotorProfile(targetAngle),
                        INTAKE::setTargetAngleFromProfile,
                        (interrupted) -> {
                        },
                        () -> false,
                        INTAKE
                )
        );
    }

    private static Command withoutRequirements(Command command) {
        return new FunctionalCommand(
                command::initialize,
                command::execute,
                command::end,
                command::isFinished
        );
    }
}
