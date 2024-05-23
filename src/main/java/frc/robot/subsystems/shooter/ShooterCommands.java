package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;

public class ShooterCommands {
    private static final Shooter SHOOTER = Shooter.getInstance();

    public static Command getSetTargetShooterStateCommand(ShooterConstants.ShooterState targetState) {
        return new FunctionalCommand(
                () -> {},
                () -> SHOOTER.setMotorState(targetState),
                (interrupted) -> {},
                () -> false,
                SHOOTER
        );
    }
}
