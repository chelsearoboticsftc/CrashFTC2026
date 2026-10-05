package org.firstinspires.ftc.teamcode.TeleOps;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter_Nectar;
import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter_Nectar;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterConstant_Nectar;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterConstant_Pollen;

@TeleOp
public class SampleFlywheel extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        Shooter_Nectar FlywheelNectar = new Shooter_Nectar(hardwareMap);
        waitForStart();
        double start;
        double ET;

        double Vel;

        Vel = 0;

        while (opModeIsActive()) {

            FlywheelNectar.setMotorVelocity(Vel);

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
                FlywheelNectar.setMotorVelocity(0);
            }

            telemetry.addData("speed", FlywheelNectar.getVelocity());
                    telemetry.update();
        }
    }
}
