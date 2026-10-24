package frc.team449.swerve

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger

class SwevreSubsystem(private val io: SwerveIO): SubsystemBase() {
    private val inputs: swerveIOInputsAutoLogged = swerveIOInputsAutoLogged()
    private val controller: PIDController = PIDController(0.1, 0.09, 0.01)

    init {
        controller.enableContinuousInput(-Math.PI, Math.PI)
        controller.setTolerance(0.1) // in radians
    }

    override fun periodic() {
        if ( controller.atSetpoint() ) {
            io.updateAppliedVoltageTurn(0.0)
        } else {
            controller.calculate(position: Double)
        }

        // log inputs
        io.updateInputs(inputs)
        Logger.processInputs("Swerve", inputs)
    }


    fun setDriveVoltage(voltage: Double): Command =
        runOnce { // or run
            io.updateAppliedVoltageDrive(voltage)
        }

    fun stopMotor(): Command =
        runOnce { // or run
            io.updateAppliedVoltageDrive(0.0)
        }

    fun setAngle(angle: Rotation2d): Command =
        runOnce {
            controller.setSetpoint(angle.radians)

        }
}
