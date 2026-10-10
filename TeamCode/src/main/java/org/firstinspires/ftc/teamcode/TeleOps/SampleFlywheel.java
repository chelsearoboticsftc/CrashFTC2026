package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterNectar;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;


@TeleOp
public class SampleFlywheel extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ShooterPollen FlywheelPollen = new ShooterPollen(hardwareMap);
        waitForStart();
        double start;
        double ET;

        double Vel;

        Vel = 0;

        while (opModeIsActive()) {

            FlywheelPollen.setMotorVelocity(Vel);

//            if(gamepad1.aWasPressed()){
//
//            Flywheel.setMotorVelocity(2850);
//            }
//
//            if(gamepad1.yWasPressed()) {
//                Flywheel.setMotorVelocity(2000);
//            }
//
//            if(gamepad1.bWasPressed()) {
//                Flywheel.setMotorVelocity(1520);
//            }

            if(gamepad1.bWasPressed()) {
                Vel = Vel+1200;

            }

            if(gamepad1.yWasPressed()) {
                Vel = Vel-100;
                sleep(500);
            }

            if(gamepad1.xWasPressed()){
                FlywheelPollen.setMotorVelocity(0);
            }

            telemetry.addData("speed", FlywheelPollen.getVelocity());
                    telemetry.update();
        }
    }
}
