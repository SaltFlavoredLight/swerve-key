package frc.team449.swerve

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.system.plant.LinearSystemId
import edu.wpi.first.wpilibj.simulation.DCMotorSim
import frc.team449.swerve.SwerveIO.ExampleIOInputs


class SwerveIOSim : SwerveIO {
    // this is an example of the DC moter Sim
    val turnMotor: DCMotorSim =
        DCMotorSim(
            LinearSystemId.createDCMotorSystem( // makes a LinearSystem
                DCMotor.getKrakenX60(1), // gearbox - 1 Kraken x60
                1.0, // moment of inertia - find in CAD
                1.0 // gearing - find in CAD
            ),
            DCMotor.getKrakenX60(1) // gearbox - 1 Kraken x60
        )

    val speedMotor: DCMotorSim =
        DCMotorSim(
            LinearSystemId.createDCMotorSystem( // makes a LinearSystem
                DCMotor.getKrakenX60(1), // gearbox - 1 Kraken x60
                1.0, // moment of inertia - find in CAD
                1.0 // gearing - find in CAD
            ),
            DCMotor.getKrakenX60(1) // gearbox - 1 Kraken x60
        )

    //
    override fun updateVelocityTurn(velocity: Double) {
        turnMotor.setAngularVelocity(velocity)
    }
    override fun updateAppliedVoltageTurn(voltage: Double) {
        turnMotor.setInputVoltage(voltage)
    }
    override fun updateAngleTurn(angle: Rotation2d) {
        turnMotor.setAngle(angle.radians)
    }
    override fun updateVelocityDrive(velocity: Double) {
        speedMotor.setAngularVelocity(velocity)
    }
    override fun updateAppliedVoltageDrive(voltage: Double) {
        speedMotor.setInputVoltage(voltage)
    }

    override fun updateInputs(inputs: ExampleIOInputs) {
        inputs.velocityTurn = turnMotor.angularVelocityRadPerSec
        inputs.appliedVoltageTurn = turnMotor.inputVoltage
        inputs.angleTurn = Rotation2d(turnMotor.angularPositionRad)
        inputs.velocityDrive = speedMotor.angularVelocityRadPerSec
        inputs.appliedVoltageDrive = speedMotor.inputVoltage

        speedMotor.update(0.02)
        turnMotor.update(0.02)
    }
}