package org.firstinspires.ftc.teamcode.Autos;

import com.pedropathing.api.Paths;
import com.pedropathing.api.PoseFactory;
import com.pedropathing.math.Pose;
import com.pedropathing.paths.Path;

public class benjaminAuto {    private final PoseFactory poseFactory = PoseFactory.degrees();

    private final Pose start = poseFactory.of(51.2728, 31.6359, 90);
    private final Pose path1 = poseFactory.of(109.5128, 30.2381, 180);
    private final Pose point2 = poseFactory.of(108.853, 48.892, -87.9743);
    private final Pose point3 = poseFactory.of(87.4527, 48.8317, 0.1614);
    private final Pose point4 = poseFactory.of(108.2522, 47.8862, 177.3974);
    private final Pose point5 = poseFactory.of(108.9744, 67.9699, -92.0593);
    private final Pose point6 = poseFactory.of(87.1375, 67.7404, 0.6023);
    private final Pose point7 = poseFactory.of(109.6481, 65.6303, 174.6449);
    private final Pose point8 = poseFactory.of(108.618, 86.0356, -87.1101);
    private final Pose point9 = poseFactory.of(77.6832, 86.9642, -1.7193);
    private final Pose point10 = poseFactory.of(77.0724, 115.0045, -88.7521);
    private final Pose point11 = poseFactory.of(49.7884, 115.3085, -0.6384);
    private final Pose point12 = poseFactory.of(51.0735, 32.2862, 90.8868);

    public Path path1() {
        return Paths.line(start, path1).linear(start, path1);
    }

    public Path path2() {
        return Paths.line(path1, point2).reverseTangent();
    }

    public Path path3() {
        return Paths.line(point2, point3).reverseTangent();
    }

    public Path path4() {
        return Paths.line(point3, point4).reverseTangent();
    }

    public Path path5() {
        return Paths.line(point4, point5).reverseTangent();
    }

    public Path path6() {
        return Paths.line(point5, point6).reverseTangent();
    }

    public Path path7() {
        return Paths.line(point6, point7).reverseTangent();
    }

    public Path path8() {
        return Paths.line(point7, point8).reverseTangent();
    }

    public Path path9() {
        return Paths.line(point8, point9).reverseTangent();
    }

    public Path path10() {
        return Paths.line(point9, point10).reverseTangent();
    }

    public Path path11() {
        return Paths.line(point10, point11).reverseTangent();
    }

    public Path path12() {
        return Paths.line(point11, point12).reverseTangent();
    }
}
