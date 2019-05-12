/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica;

import raskrsnica.vehicle.Vehicle;
import javafx.geometry.Point3D;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import static raskrsnica.Cns.*;

/**
 *
 * @author jovan
 */
public class CameraManager {
    
    private PerspectiveCamera topDownCam = new PerspectiveCamera(true);
    private PerspectiveCamera centerCam = new PerspectiveCamera(true);
    private Vehicle followVeh = null;
    
    private enum SELECTED { TOP_DOWN, CENTER, FOLLOW };
    private SELECTED selected = SELECTED.TOP_DOWN;
    
    private Rotate centerHor = new Rotate(0, Rotate.Z_AXIS);
    private Rotate centerVert = new Rotate(45, Rotate.X_AXIS);
    
    private Translate translate = new Translate(0, 0, 0);
    private Rotate rotate = new Rotate(0, Rotate.X_AXIS);
    
    public CameraManager() {
        
        topDownCam.setNearClip(CAM_NEAR_CLIP);
        topDownCam.setFarClip(CAM_FAR_CLIP);
        topDownCam.setTranslateX(GND_LENGTH / 2);
        topDownCam.setTranslateY(GND_LENGTH / 2);
        topDownCam.setTranslateZ(-CAM_MAX_HEIGHT);
        topDownCam.setFieldOfView(CAM_FIELD_OF_VIEW);
        
        centerCam.setNearClip(CAM_NEAR_CLIP);
        centerCam.setFarClip(CAM_FAR_CLIP);
        centerCam.setTranslateX(GND_LENGTH / 2);
        centerCam.setTranslateY(GND_LENGTH / 2);
        centerCam.setTranslateZ(-CAM_MIN_HEIGHT);
        centerCam.getTransforms().addAll(centerHor, centerVert);
        centerCam.setFieldOfView(CAM_FIELD_OF_VIEW * 2);
        
    }
    
    public PerspectiveCamera getTopDownCamera() { 
        selected = SELECTED.TOP_DOWN;
        return topDownCam; 
    }
    
    public PerspectiveCamera getCenterCamera() { 
        selected = SELECTED.CENTER;
        return centerCam; 
    }
    
    public static PerspectiveCamera createFollowCam() {
        PerspectiveCamera perspectiveCamera = new PerspectiveCamera(true);
        perspectiveCamera.setNearClip(CAM_NEAR_CLIP);
        perspectiveCamera.setFarClip(CAM_FAR_CLIP);
        perspectiveCamera.setFieldOfView(CAM_FIELD_OF_VIEW);
        return perspectiveCamera;
    }
    
    public void setVehicleCamera(Vehicle vehicle) { 
        selected = SELECTED.FOLLOW;
        followVeh = vehicle;
    }

    void goDown() {
        if (selected == SELECTED.TOP_DOWN) {
            topDownCam.setTranslateZ(topDownCam.getTranslateZ() + CAM_UP_DOWN_STEP);
            if (topDownCam.getTranslateZ() > -CAM_MIN_HEIGHT) {
                topDownCam.setTranslateZ(-CAM_MIN_HEIGHT);
            }
        } else if (selected == SELECTED.CENTER) {
            centerVert.setAngle(centerVert.getAngle() - CAM_LEFT_RIGHT_STEP);
            if (centerVert.getAngle() < 15) {
                centerVert.setAngle(15);
            }
        } else if (selected == SELECTED.FOLLOW) {
            Rotate r = followVeh.getCamVert();
            r.setAngle(r.getAngle() - CAM_LEFT_RIGHT_STEP);
            if (r.getAngle() < 15) {
                r.setAngle(15);
            }
        }
    }

    void goUp() {
        if (selected == SELECTED.TOP_DOWN) {
            topDownCam.setTranslateZ(topDownCam.getTranslateZ() - CAM_UP_DOWN_STEP);
            if (topDownCam.getTranslateZ() < -CAM_MAX_HEIGHT) {
                topDownCam.setTranslateZ(-CAM_MAX_HEIGHT);
            }
        } else if (selected == SELECTED.CENTER) {
            centerVert.setAngle(centerVert.getAngle() + CAM_LEFT_RIGHT_STEP);
            if (centerVert.getAngle() > 90) {
                centerVert.setAngle(90);
            }
        } else if (selected == SELECTED.FOLLOW) {
            Rotate r = followVeh.getCamVert();
            r.setAngle(r.getAngle() + CAM_LEFT_RIGHT_STEP);
            if (r.getAngle() > 90) {
                r.setAngle(90);
            }
        }
    }
    
    void goLeft() {
        if (selected == SELECTED.CENTER) {
            centerHor.setAngle((centerHor.getAngle() + 360 - CAM_LEFT_RIGHT_STEP) % 360);
        } else if (selected == SELECTED.FOLLOW) {
            Rotate r = followVeh.getCamHor();
            r.setAngle((r.getAngle() + 360 - CAM_LEFT_RIGHT_STEP) % 360);
        }
    }

    void goGoRight() {
        if (selected == SELECTED.CENTER) {
            centerHor.setAngle((centerHor.getAngle() + CAM_LEFT_RIGHT_STEP) % 360);
        } else if (selected == SELECTED.FOLLOW) {
            Rotate r = followVeh.getCamHor();
            r.setAngle((r.getAngle() + CAM_LEFT_RIGHT_STEP) % 360);
        }
    }
    
}
