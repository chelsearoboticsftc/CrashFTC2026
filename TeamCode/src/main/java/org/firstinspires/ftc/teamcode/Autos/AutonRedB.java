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

    private final Pose start = poseFactory.of(8.523, 34.9967, 90);
    private final Pose path1 = poseFactory.of(60.6546, 9.0033, 90);
    private final Pose path2 = poseFactory.of(30.472, 45.4836, 90);
    private final Pose path3 = poseFactory.of(13.4062, 97.648, 90);
    private final Pose point3Control1 = poseFactory.of(5.0066, 72.9523, 0);

    // Autonomous routine
    public Command autoRoutine() {
        return sequential(
                follow(follower, path1()),
                follow(follower, path2()),
                follow(follower, path3())
        );
    }


        public Path path1() {
            return Paths.line(start, path1).constant(path1);
        }

        public Path path2() {
            return Paths.line(path1, path2).constant(path2);
        }

        public Path path3() {
            return Paths.curve(path2, point3Control1, path3).constant(path3);
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




