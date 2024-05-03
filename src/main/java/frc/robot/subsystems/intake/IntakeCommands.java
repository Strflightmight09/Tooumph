package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;

public class IntakeCommands {
    private static final Intake INTAKE = Intake.getInstance();
    public static Command getSetIntakeStateCommand(IntakeConstants.IntakeState targetState) {
        return new FunctionalCommand(
                () -> {},
                () -> INTAKE.setMotorState(targetState),
                (interrupted) -> INTAKE.stop(),
                () -> false,
                INTAKE
        );
    }
}
