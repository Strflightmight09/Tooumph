package frc.robot.subsystems.tank;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Tank extends SubsystemBase {
    private static final Tank INSTANCE = new Tank();
    private final DifferentialDrive differentialDrive = TankConstants.DIFFERENTIAL_DRIVE;

    public static Tank getInstance() {
        return INSTANCE;
    }

    void setArcadeDrive(double driveSpeed, double rotationSpeed) {
        differentialDrive.arcadeDrive(driveSpeed, rotationSpeed);
    }

    void stop() {
        differentialDrive.stopMotor();
    }
}