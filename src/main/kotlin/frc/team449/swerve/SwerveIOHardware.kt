package frc.team449.swerve

import com.ctre.phoenix6.controls.VelocityVoltage
import com.ctre.phoenix6.hardware.TalonFX
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.units.Units
import frc.team449.swerve.SwerveIO.ExampleIOInputs

class SwerveIOHardware(turnPort:Int, speedPort:Int) : SwerveIO {
    private val turnMotor = TalonFX(turnPort)
    private val driveMotor = TalonFX(speedPort)

    override fun updateVelocityTurn(velocity: Double) {
        turnMotor.setControl(VelocityVoltage(velocity))
    }
    override fun updateAppliedVoltageTurn(voltage: Double) {
        turnMotor.setVoltage(voltage)
    }
    override fun updateAngleTurn(angle: Rotation2d) {
        turnMotor.setPosition(angle.rotations)
    }
    override fun updateVelocityDrive(velocity: Double) {
        driveMotor.setControl(VelocityVoltage(velocity))
    }
    override fun updateAppliedVoltageDrive(voltage: Double) {
        driveMotor.setVoltage(voltage)
    }

    override fun updateInputs(inputs: ExampleIOInputs) {
        inputs.velocityTurn = turnMotor.velocity.value.`in`(Units.RadiansPerSecond)
        inputs.appliedVoltageTurn = turnMotor.motorVoltage.value.`in`(Units.Volts)
        inputs.angleTurn = Rotation2d(turnMotor.position.value.`in`(Units.Radians))
        inputs.velocityDrive = driveMotor.velocity.value.`in`(Units.RadiansPerSecond)
        inputs.appliedVoltageDrive = driveMotor.motorVoltage.value.`in`(Units.Volts)
    }
}