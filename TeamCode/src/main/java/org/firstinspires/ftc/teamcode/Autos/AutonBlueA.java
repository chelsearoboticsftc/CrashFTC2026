package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.follower.Follower;
import com.pedropathing.ivy.Command;
import com.pedropathing.ivy.Scheduler;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.OpModeStorage;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;

@Autonomous
public class AutonBlueA extends OpMode {
    private Follower follower;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(79.9454, 131.9512, 90);
    private final Pose BlueAPath = poseFactory.of(129.9972, 28.3473, 180);
    private final Pose path1Control1 = poseFactory.of(120.9143, 109.7572, 0);

    public Path BlueAPath() {
        return Paths.curve(start, path1Control1, BlueAPath).linear(start, BlueAPath);
    }

    public Path path1() {
        return Paths.curve(start, path1Control1, BlueAPath).linear(start, BlueAPath);
    }


    private Command autoRoutine() {
        return sequential(
                instant(() -> ShooterPollenFly.setMotorVelocity(2000)),
                instant(() -> GatePollen.setGatePollenPos(0)),
                waitMs(200),
                instant(() -> ShooterPollenFly.setMotorVelocity(0)),
                instant(() -> GatePollen.setGatePollenPos(2850)),
                follow(follower, path1()));
        // Add mechanism commands here.
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
    }
    @Override
    public void start() {
        schedule(autoRoutine());
    }
    @Override
    public void loop() {
        follower.update();
        Scheduler.execute();
    }

    @Override
    public void stop() {
        OpModeStorage.autoEndPose = follower.pose(); //saves your position in that file
    }



}