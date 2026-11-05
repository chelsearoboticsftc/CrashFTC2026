package org.firstinspires.ftc.teamcode.Autos;

import static com.pedropathing.ivy.Scheduler.schedule;
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
public class AutonHivePickup extends OpMode {
    private Follower follower;
    private ShooterPollen ShooterPollenFly;
    private ShooterPollen GatePollen;
    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(56, 8, 90);
    private final Pose path1 = poseFactory.of(52.3134, 7.8084, 90);
    private final Pose point2 = poseFactory.of(9.4764, 8.4932, 180);
    private final Pose point3 = poseFactory.of(9.3279, 94.806, 180);
    private final Pose point3Control1 = poseFactory.of(53.6694, 59.5792, 0);

    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).constant(point2);
    }

    public Path path3() {
        return Paths.curve(point2, point3Control1, point3).constant(point3);
    }


    private Command autoRoutine() {
        return sequential(
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
