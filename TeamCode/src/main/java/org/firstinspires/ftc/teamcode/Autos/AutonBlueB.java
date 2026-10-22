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
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(131.9499, 108.8463, 90);
    private final Pose BlueBPath = poseFactory.of(130.059, 29.6971, 180);
    private final Pose path1Control1 = poseFactory.of(103.6169, 105.3452, 0);

    public Path BlueBPath() {
        return Paths.curve(start, path1Control1, BlueBPath).linear(start, BlueBPath);
    }

    /* Creating a Line
Let's break down the parts of the path quickly:
line() creates a straight path from startPose to park.

The .linear() part is our Interpolation.
It changes the robot's heading from the heading of startPose to the heading of park
while it moves along the path.
You can play around with this on the visualizer.
 */
    public Path path1() {
    return Paths.line(start, BlueBPath). constant(BlueBPath);
    }


/*
To make a Curve
The first and last Poses are the start and end of the curve.
Any Poses between them are control points that change the shape of the curve.
 */


    private Command autoRoutine() {
        return sequential(
                // Add mechanism commands here.
                follow(follower, path1())
                //instant(()-> shooter.setMotorVelocity(2200)),
                //instant(()-> intake.setMotorPower(1)),
                //instant(() -> GatePollen.setGatePollenPos(0)),
                //instant(()->waitMs(200)),
                //instant(()-> shooter.setMotorVelocity(0)),
                //instant(() -> GatePollen.setGatePollenPos(2850)),
                //follow(follower, path2()),
               // follow(follower, path3())
        );
    }

    @Override
    public void init() {
        Scheduler.reset();
        follower = PedroConstants.create(hardwareMap);
        follower.setPose(start);//sets the starting point of your Robot
        follower.update();
        shooter = new ShooterPollen(hardwareMap);
        intake = new Intake(hardwareMap);
        GatePollen = new ShooterPollen(hardwareMap);

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
