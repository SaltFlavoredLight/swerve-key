package frc.team449.swerve

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger

class SwerveSubsystem(private val io: SwerveIO) : SubsystemBase() {
    private val inputs: SwerveIOInputsAutoLogged = SwerveIOInputsAutoLogged()
    private val controller: PIDController = PIDController(0.1, 0.09, 0.01)

    init {
        controller.enableContinuousInput(-Math.PI, Math.PI)
        controller.setTolerance(0.1) // in radians
    }

    override fun periodic() {
        // log inputs
        io.updateInputs(inputs)
        Logger.processInputs("Swerve", inputs)

        if (controller.atSetpoint()) {
            io.updateAppliedVoltageTurn(0.0)
        } else {
            controller.calculate(inputs.angleTurn.radians)
        }
    }

    fun setDriveVoltage(voltage: Double): Command =
        runOnce {
            io.updateAppliedVoltageDrive(voltage)
        }

    fun stopMotor(): Command =
        runOnce {
            io.updateAppliedVoltageDrive(0.0)
        }

    fun setAngle(angle: Rotation2d): Command =
        runOnce {
            controller.setSetpoint(angle.radians)
        }
}
