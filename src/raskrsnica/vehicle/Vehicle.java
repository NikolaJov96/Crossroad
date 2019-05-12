/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica.vehicle;

import java.util.Random;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import raskrsnica.CameraManager;
import raskrsnica.Cns.*;
import static raskrsnica.Cns.*;
import raskrsnica.Raskrsnica;

/**
 *
 * @author jovan
 */
public abstract class Vehicle extends Group {
    
    protected final PhongMaterial WHEEL_MAT = new PhongMaterial(Color.BLACK);
    protected final PhongMaterial ACC_MAT = new PhongMaterial(Color.RED.darker().darker().darker());
    protected final PhongMaterial DEC_MAT = new PhongMaterial(Color.RED.brighter().brighter().brighter());
    protected final Image CLS = new Image("cls.bmp");
    protected final int WINDOW_CLR = 2;
    
    protected DIRECTION dir;
    protected int dx = 0;
    protected int dy = 0;
    protected double spd = 0;
    protected Translate translate = new Translate();
    protected Rotate rotate = new Rotate(0, Rotate.Z_AXIS);
    protected Box breakLight1;
    protected Box breakLight2;
    
    protected PerspectiveCamera cam;
    private Rotate camHor = new Rotate(90, Rotate.Z_AXIS);
    private Rotate camVert = new Rotate(70, Rotate.X_AXIS);
    
    private Raskrsnica raskrsnica;
    private boolean passedCenter = false;
    private boolean decidedToRun = false;
    private int lastStateId = -1;
    private double stoppingDist = 0;
    private boolean isSlowingDown = false;

    public Vehicle(DIRECTION dir, Raskrsnica raskrsnica) {
        this.dir = dir;
        this.raskrsnica = raskrsnica;
        double drift = (-0.5 + (new Random()).nextDouble()) * 0.2 * LANE_WIDTH;
        switch(dir) {
            case UD: 
                this.dy = 1;
                translate.setX((GND_LENGTH - LANE_WIDTH) / 2 + drift);
                translate.setY(SPAWN_POSITION);
                rotate.setAngle(90);
                break;
            case DU: 
                this.dy = -1; 
                translate.setX((GND_LENGTH + LANE_WIDTH) / 2 + drift);
                translate.setY(GND_LENGTH - SPAWN_POSITION);
                rotate.setAngle(-90);
                break;
            case LR: 
                this.dx = 1; 
                translate.setX(SPAWN_POSITION);
                translate.setY((GND_LENGTH + LANE_WIDTH) / 2 + drift);
                rotate.setAngle(0);
                break;
            case RL: 
                this.dx = -1; 
                translate.setX(GND_LENGTH - SPAWN_POSITION);
                translate.setY((GND_LENGTH - LANE_WIDTH) / 2 + drift);
                rotate.setAngle(180);
                break;
        }
        getTransforms().addAll(translate, rotate);
        stoppingDist = (getMaxSpd() / getAccelRate()) * getMaxSpd();
        
        cam = CameraManager.createFollowCam();
        cam.setTranslateX(-getLen() * 1.5);
        cam.setTranslateZ(-3);
        cam.getTransforms().addAll(camHor, camVert);
        getChildren().add(cam);
        
        setOnMouseClicked(e -> { raskrsnica.vehicleSelected(this); });
    }
    
    public void step(int stateId, Vehicle veh, double dt) {
        if (dir.equals(DIRECTION.UD) || dir.equals(DIRECTION.DU)) {
            stateId = (stateId + 4) % 8;
        }
        if (translate.getX() - GND_LENGTH / 2 > - LANE_WIDTH && 
                translate.getX() - GND_LENGTH / 2 < LANE_WIDTH && 
                translate.getY() - GND_LENGTH / 2 > - LANE_WIDTH && 
                translate.getY() - GND_LENGTH / 2 < LANE_WIDTH) {
            passedCenter = true;
        }
        boolean shouldBreak = false;
        switch(stateId) {
            case 0: case 1: case 3: case 4: case 5: case 6: case 7: 
                if (!passedCenter && !decidedToRun) {
                    double semPosOffset = LANE_WIDTH + SIDE_STRIPE_LEN + SIDE_STRIPE_WID * 3;
                    double dist = 0;
                    switch(dir) {
                        case UD: dist = GND_LENGTH / 2 - semPosOffset - translate.getY(); break;
                        case DU: dist = translate.getY() - GND_LENGTH / 2 - semPosOffset; break;
                        case LR: dist = GND_LENGTH / 2 - semPosOffset - translate.getX(); break;
                        case RL: dist = translate.getX() - GND_LENGTH / 2 - semPosOffset; break;
                    }
                    if (dist < getLen() || 
                        (dist < stoppingDist && dist / stoppingDist < spd / getMaxSpd())) {
                        if (stateId == 3 && lastStateId == 2) {
                            decidedToRun = true;
                        }
                        shouldBreak = true; 
                    }
                }
        }
        
        if (!shouldBreak && veh != null) {
            double dist = 0;
            switch(dir) {
                case UD: dist = veh.getTran().getY() - translate.getY(); break;
                case DU: dist = translate.getY() - veh.getTran().getY(); break;
                case LR: dist = veh.getTran().getX() - translate.getX(); break;
                case RL: dist = translate.getX() - veh.getTran().getX(); break;
            }
            if (dist < getLen() + veh.getLen() || 
                    (dist < stoppingDist && dist / stoppingDist < spd / getMaxSpd())) {
                shouldBreak = true; 
            }
        }
                
        if (shouldBreak) {
            this.spd -= getAccelRate() * dt;
            if (this.spd < 0) { this.spd = 0; }
            breakLight1.setMaterial(DEC_MAT);
            breakLight2.setMaterial(DEC_MAT);
            isSlowingDown = true;
        } else {
            this.spd += getAccelRate() * dt;
            if (this.spd > getMaxSpd()) { this.spd = this.getMaxSpd(); } 
            breakLight1.setMaterial(ACC_MAT);
            breakLight2.setMaterial(ACC_MAT);
            isSlowingDown = false;
        }
        
        translate.setX(translate.getX() + spd * dx * dt);
        translate.setY(translate.getY() + spd * dy * dt);
        lastStateId = stateId;
    }
    
    public double getDX() {
        return this.dx * this.spd;
    }
    
    public double getDY() {
        return this.dy * this.spd;
    }

    public Rotate getCamHor() {
        return camHor;
    }

    public void setCamHor(Rotate camHor) {
        this.camHor = camHor;
    }

    public Rotate getCamVert() {
        return camVert;
    }

    public void setCamVert(Rotate camVert) {
        this.camVert = camVert;
    }
    
    public Translate getTran() { return translate; }
    
    protected abstract double getMaxSpd();
    protected abstract double getAccelRate();
    protected abstract double getLen();

    public PerspectiveCamera getCam() {
        return cam;
    }
    
}
