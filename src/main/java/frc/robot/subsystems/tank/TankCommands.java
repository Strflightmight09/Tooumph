package frc.robot.subsystems.tank;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;

import java.util.function.Supplier;

public class TankCommands {
    private static final Tank TANK = Tank.getInstance();

    public static Command getSetArcadeDriveCommand(Supplier<Double> driveSpeed, Supplier<Double> rotationSpeed) {
        return new FunctionalCommand(
                () -> {
                },
                () -> TANK.setArcadeDrive(driveSpeed.get(), rotationSpeed.get()),
                (interrupted) -> TANK.stop(),
                () -> false,
                TANK
        );
    }
}