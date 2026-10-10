package org.firstinspires.ftc.teamcode.Subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoControllerEx;

import org.firstinspires.ftc.teamcode.utils.LookupTable;

public class ShooterPollen {

    //Example declare a DcMotorEx object as part of this class called 'motorName'
    DcMotorEx ShooterPollenFly;
    Servo GatePollen;

    //Declare any other global variables for this class here
<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Subsystems/Shooter_Pollen.java
    private final LookupTable distanceToVelocity = new LookupTable(ShooterConstant_Pollen.LOOKUP_TABLE);

    public Shooter_Pollen(HardwareMap hardwareMap){
        this.Flywheel = hardwareMap.get(DcMotorEx.class, ShooterConstant_Pollen.MOTOR_NAME);
        this.Gate = hardwareMap.get(ServoControllerEx.class, ShooterConstant_Pollen.SERVO_NAME);
=======
    private final LookupTable distanceToVelocity = new LookupTable(ShooterConstantPollen.LOOKUP_TABLE);
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Subsystems/ShooterPollen.java

    public ShooterPollen(HardwareMap hardwareMap){
        this.ShooterPollenFly = hardwareMap.get(DcMotorEx.class, ShooterConstantPollen.MOTOR_NAME);
        this.GatePollen = hardwareMap.get(Servo.class, ShooterConstantNectar.SERVO_NAME);
        //This defines the behavior at zero power (brake or coast)
        ShooterPollenFly.setZeroPowerBehavior(ShooterConstantPollen.ZERO_POWER_BEHAVIOR);

        //This defines the motor direction (forward or reversed)
        ShooterPollenFly.setDirection(ShooterConstantPollen.MOTOR_DIRECTION);

        /* This defines the motor velocity PIDF gains.  Velocity PIDF values determine control    *
         * around a target velocity (setTargetVelocity) OR how fast the system responds to a      *
         * change in set position (setTargetPosition).                                            */
        ShooterPollenFly.setVelocityPIDFCoefficients(
                ShooterConstantPollen.VELOCITY_P, //Proportional Gain
                ShooterConstantPollen.VELOCITY_I, //Integral Gain
                ShooterConstantPollen.VELOCITY_D, //Derivative Gain
                ShooterConstantPollen.VELOCITY_F);//Feed Forward Gain

        /* This defines the motor position PID P gain. Position control only needs P gain since   *
         * once the system reaches the target position since once at position you're only         *
         * disturbances in the system                                                             */
        ShooterPollenFly.setPositionPIDFCoefficients(
                ShooterConstantPollen.POSITION_P);//Proportional Gain

        //motorName.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }
    /* Standard functions.  All Chelsea Robotics subsystems shall have init() and update() these  *
     * methods defined. Leave empty if not needed!                                                */
    public void init(){
        /* Call this method at the start of your opmode logic once to execute any logic you       *
         * want to be called on initialization. If none, leave empty!                             */
    }

    public void update(){
        //Call this method each time your opmode logic loops (i.e. inside while(opModeIsActive()){}
        //to execute any logic you want to be called periodically. If none, leave empty!

        //setTargetPosition needs to be called once per loop to keep the REV watchdog happy
        //motorName.setTargetPosition(motorSetPosition);
    }

    public void shoot(double distance) {
        double velocity = distanceToVelocity.interpolate(distance);
        this.setMotorVelocity(velocity);
        // TODO - is ball already engaged, or does it need to be dropped,
        // maybe after a short delay to allow the motor to spin up?
    }

    public double getVelocity() {
      return ShooterPollenFly.getVelocity();
    }

    public void setMotorVelocity(double angularRate) {
        this.ShooterPollenFly.setVelocity(angularRate);
    }

    public void setGatePollenPos(double pos) {
        GatePollen.setPosition(pos);
    }
}
