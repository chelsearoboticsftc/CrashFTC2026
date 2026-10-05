package org.firstinspires.ftc.teamcode.TeleOps;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterSmart;

@TeleOp
public class SampleFlywheel extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ShooterSmart Flywheel = new ShooterSmart(hardwareMap);
        waitForStart();
        double start;
        double ET;

        double Vel;

        Vel = 0;

        while (opModeIsActive()) {

            Flywheel.setMotorVelocity(Vel);

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

            if(gamepad1.right_trigger_pressed) {
                Vel = Vel+100;
                sleep(500);
            }

            if(gamepad1.left_trigger_pressed) {
                Vel = Vel-100;
                sleep(500);
            }

            if(gamepad1.xWasPressed()){
                Flywheel.setMotorVelocity(0);
            }

            telemetry.addData("speed", Flywheel.getVelocity());
                    telemetry.update();
        }
    }
}
