package org.firstinspires.ftc.teamcode.Autos;

import com.pedropathing.api.Paths;
import com.pedropathing.ivy.Command;

import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.pedro.PedroCommands.*;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.pedropathing.paths.Path;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;

@Autonomous
public class AutonRedA extends OpMode {
    private Follower follower;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(59.7237, 8.9309, 270);
    private final Pose path1 = poseFactory.of(10.8503, 116.0592, 0);
    private final Pose path1Control1 = poseFactory.of(3.6464, 34.4178, 180);

    public Path path1() {
        return Paths.curve(start, path1Control1, path1).linear(start, path1);
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
