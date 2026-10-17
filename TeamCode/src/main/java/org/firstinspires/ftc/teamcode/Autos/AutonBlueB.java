package org.firstinspires.ftc.teamcode.Autos;

import com.pedropathing.api.Paths;
import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import static com.pedropathing.api.Paths.*;
import com.pedropathing.paths.Path;
import org.firstinspires.ftc.teamcode.OpModeStorage;
import com.pedropathing.follower.Follower;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.ShooterPollen;
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.ivy.Scheduler;
import static com.pedropathing.ivy.Scheduler.schedule;
import static com.pedropathing.ivy.commands.Commands.instant;
import static com.pedropathing.ivy.commands.Commands.waitMs;
import static com.pedropathing.ivy.groups.Groups.sequential;
import static com.pedropathing.ivy.pedro.PedroCommands.follow;

@Autonomous
public class AutonBlueB extends OpMode {
    private Follower follower;
    private ShooterPollen shooter;
    private Intake intake;
    private ShooterPollen GatePollen;
    private ShooterPollen ShooterPollenFly;
    private final PoseFactory p = PoseFactory.degrees();

    private final Pose start = p.of(131.8701, 109.4704, 90);
    private final Pose path1 = p.of(74.153, 129.0921, 270);
    private final Pose point2 = p.of(106.4227, 96.7171, 270);
    private final PoseFactory poseFactory = PoseFactory.degrees();
    private final Pose point3 = poseFactory.of(125.1859, 25.9671, 270);
    private final Pose point3Control1 = poseFactory.of(134.9836, 74.687, 0);
    /* Creating a Line
Let's break down the parts of the path quickly:
line() creates a straight path from startPose to park.

The .linear() part is our Interpolation.
It changes the robot's heading from the heading of startPose to the heading of park
while it moves along the path.
You can play around with this on the visualizer.
 */
    public Path path1() {
    return Paths.line(start, path1). constant(path1);
    }
    public Path path2() {
        return Paths.line(path1, point2). constant(point2);
    }
    public Path path3() {
        return Paths.curve(point2, point3Control1, point3). constant(point3);
    }

/*
To make a Curve
The first and last Poses are the start and end of the curve.
Any Poses between them are control points that change the shape of the curve.
 */


    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                follow(follower, path1()),
                instant(()-> shooter.setMotorVelocity(2200)),
                instant(()-> intake.setMotorPower(1)),
                instant(() -> GatePollen.setGatePollenPos(0)),
                instant(()->waitMs(200)),
                instant(()-> shooter.setMotorVelocity(0)),
                instant(() -> GatePollen.setGatePollenPos(2850)),
                follow(follower, path2()),
                follow(follower, path3())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
        shooter = new ShooterPollen(hardwareMap);

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
