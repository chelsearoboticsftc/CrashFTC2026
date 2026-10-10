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
import org.firstinspires.ftc.teamcode.pedro.PedroConstants;

@Autonomous
public class AutonRedB extends OpMode {
    private Follower follower;


    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(111.7105, 4.0922, 90);
    private final Pose path1 = poseFactory.of(109.0625, 15.5197, 90);
    private final Pose point2 = poseFactory.of(55.4293, 15.9013, 90);
    private final Pose point3 = poseFactory.of(56.7993, 33.5197, 90);
    private final Pose point4 = poseFactory.of(4.9737, 103.7566, -86.2324);
    private final Pose point4Control1 = poseFactory.of(9.2796, 34.7484, 0);



    public Path path1() {
        return Paths.line(start, path1).constant(path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).constant(point2);
    }

    public Path path3() {
        return Paths.curve(point2, point3).constant(point3);
    }
    public Path path4() {
        return Paths.curve(point3, point4Control1, point4).reverseTangent();
    }
    private Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3()),
                follow(follower, path4())
                // Add mechanism commands here.

        );
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
