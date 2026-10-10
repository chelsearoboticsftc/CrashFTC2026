package org.firstinspires.ftc.teamcode.TeleOps;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter_Nectar;
import org.firstinspires.ftc.teamcode.Subsystems.Shooter_Pollen;

@TeleOp(name = "PFC Drive")
public class PedroFieldCentricDrive extends OpMode {

    private Intake intake;
    private Shooter_Nectar Flywheel;

    private Follower follower;

    @Override
    public void start() {
        follower.setPose(OpModeStorage.autoEndPose);
        follower.update();
    }

    @Override
    public void init() {
        follower = PedroConstants.create(hardwareMap);
        intake = new Intake(hardwareMap);
    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                gamepad1.left_stick_y,
                gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        follower.manual(powers);
        follower.update();
        // relocalise button
        if (gamepad1.startWasPressed()) {
            Pose cornerPose = new Pose(10.5, 10.5, Math.toRadians(90));
            // On the fly Pose creation, Only accepts radians for heading
            follower.setPose(cornerPose); // overrides our pose

            Pose robotPose = follower.pose(); // returns a Pose object
            telemetry.addData("Robot X", robotPose.x());
            telemetry.addData("Robot Y", robotPose.y());
            telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));

            // Intake stuff

            boolean Intaked;
            if (gamepad2.aWasPressed()) {
                Intaked = true;
            }
            if (gamepad2.aWasReleased()) {
                Intaked = false;
            }
            if (Intaked = true) {
                intake.setMotorPower(1);
            }

            if (Intaked = false) {
                intake.setMotorPower(0);


            }
        }
    }
}