package org.firstinspires.ftc.teamcode.TeleOps;

import com.pedropathing.drivetrain.DrivePowers;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.pedropathing.math.Pose;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterNectar;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.Subsystems.IntakeB;

@TeleOp(name = "PFC Drive")
public class PedroFieldCentricDrive extends OpMode {

    private Intake intake;
    private IntakeB intakeb;
    private ShooterNectar Flywheel;
    private ShooterNectar ShooterNectarFly;
    private ShooterNectar GateNectar;
    private ShooterPollen ShooterPollenFly;

   double GatePollenPos;
    private Follower follower;

    @Override
    public void start() {
        follower.setPose(OpModeStorage.autoEndPose);
        follower.update();
        GatePollenPos = 0;
    }

    @Override
    public void init() {
        follower = PedroConstants.create(hardwareMap);
        intake = new Intake(hardwareMap);
        intakeb = new IntakeB(hardwareMap);
        ShooterPollenFly = new ShooterPollen(hardwareMap);

    }

    @Override
    public void loop() {
        DrivePowers powers = ManualDrive.fieldCentric(
                -gamepad1.left_stick_y,
                -gamepad1.left_stick_x,
                gamepad1.right_stick_x,
                follower.pose().heading()
        );
        follower.manual(powers);
        follower.update();
        // relocalise button
        //if (gamepad1.startWasPressed()) {
            //Pose cornerPose = new Pose(0, 0, Math.toRadians(90));
            // On the fly Pose creation, Only accepts radians for heading
        //follower.setPose(cornerPose); // overrides our pose

            Pose robotPose = follower.pose(); // returns a Pose object
            telemetry.addData("Robot X", robotPose.x());
            telemetry.addData("Robot Y", robotPose.y());
            telemetry.addData("Robot Heading", Math.toDegrees(robotPose.heading()));
            telemetry.addData("Servo Position", ShooterPollenFly.getGatePollenPos());
            // Intake stuff


            if (gamepad2.aWasPressed()) {
                intake.setMotorPower(-1);
                intakeb.setMotorPower(-1);
            }

            if (gamepad2.aWasReleased()) {
                intake.setMotorPower(0);
                intakeb.setMotorPower(0);
            }
            // Shooter stuff


            if (gamepad2.yWasPressed()) {
                ShooterPollenFly.setMotorVelocity(2200);

            }

            if (gamepad2.yWasReleased()) {
                ShooterPollenFly.setMotorVelocity(0);
            }
            if (gamepad2.dpadRightWasPressed()) {
                ShooterPollenFly.setGatePollenPos(0.8);
            }
//            if (gamepad2.dpadUpWasPressed()) {
//            ShooterPollenFly.setGatePollenPos(0.2);
//            }
            if (gamepad2.dpadLeftWasPressed()) {
            ShooterPollenFly.setGatePollenPos(0.5);
        }
//            if (gamepad2.dpadDownWasPressed()) {
//            ShooterPollenFly.setGatePollenPos(0.7);
//        }

            //ShooterPollenFly.setGatePollenPos();
//            if (gamepad2.xWasPressed()) {
//                //ShooterPollenFly.setMotorVelocity(200);
//                GatePollen.setGatePollenPos(2850);
//                //setGatePollenPos(0);
//            }
//

            // Stuff for later stuff


        }
    }
