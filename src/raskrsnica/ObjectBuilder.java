/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import static raskrsnica.Cns.*;

/**
 *
 * @author jovan
 */
public abstract class ObjectBuilder {
    
    private static final PhongMaterial postM = new PhongMaterial(Color.LIGHTSLATEGRAY);
    private static final PhongMaterial backM = new PhongMaterial(Color.LIGHTSLATEGRAY.brighter());
    private static final PhongMaterial wM = new PhongMaterial(Color.WHITE);
    private static final PhongMaterial rM = new PhongMaterial(Color.RED);
    private static final PhongMaterial yM = new PhongMaterial(Color.YELLOW);
    private static final PhongMaterial bM = new PhongMaterial(Color.BLACK);
    private static Cylinder cy;
    private static Box bo;
    private static Sphere sp;
    
    private static final double SEM_POST_R = 0.1;
    private static final double SEM_POST_H = 2;
    private static final double SEM_SEM_W = 0.3;
    private static final double SEM_SEM_H = 1;
    private static final double SEM_LIG_SPAC = SEM_SEM_H / 10;
    private static final double SEM_LIG_R = SEM_LIG_SPAC * 1.4;
    
    static class Semaphore extends Group {
        
        public enum COLOR { GREEN, YELLOW, RED };
        
        public Cylinder g = new Cylinder(SEM_LIG_R, D);
        public Cylinder y = new Cylinder(SEM_LIG_R, D);
        public Cylinder r = new Cylinder(SEM_LIG_R, D);
        
        public void select(int state) {
            ((PhongMaterial)g.getMaterial()).setDiffuseColor(Color.GREEN.darker().darker().darker());
            ((PhongMaterial)y.getMaterial()).setDiffuseColor(Color.YELLOW.darker().darker().darker());
            ((PhongMaterial)r.getMaterial()).setDiffuseColor(Color.RED.darker().darker().darker());
            switch(state) {
                case 0: case 4: case 5: case 6: case 7:
                    ((PhongMaterial)r.getMaterial()).setDiffuseColor(Color.RED.brighter().brighter().brighter()); break;
                case 1: case 3: 
                    ((PhongMaterial)y.getMaterial()).setDiffuseColor(Color.YELLOW.brighter().brighter().brighter()); break;
                case 2: 
                    ((PhongMaterial)g.getMaterial()).setDiffuseColor(Color.GREEN.brighter().brighter().brighter()); break;
            }
        }
        
    }
    
    public static Semaphore getSemaphore() {
        Semaphore sem = new Semaphore();
        PhongMaterial redM = new PhongMaterial(Color.RED.darker().darker().darker());
        PhongMaterial yellowM = new PhongMaterial(Color.YELLOW.darker().darker().darker());
        PhongMaterial greenM = new PhongMaterial(Color.GREEN.darker().darker().darker());
        
        cy = new Cylinder(SEM_POST_R, SEM_POST_H);
        cy.setTranslateY(-SEM_POST_H / 2);
        cy.setMaterial(postM);
        sem.getChildren().add(cy);
        bo = new Box(SEM_SEM_W, SEM_SEM_H, SEM_SEM_W);
        bo.setTranslateY(-SEM_POST_H - SEM_SEM_H / 2);
        bo.setMaterial(bM);
        sem.getChildren().add(bo);
        
        sem.g.setTranslateY(-SEM_POST_H - 2 * SEM_LIG_SPAC);
        sem.g.setTranslateZ(-SEM_SEM_W / 2);
        sem.g.setRotate(90);
        sem.g.setRotationAxis(Rotate.X_AXIS);
        sem.g.setMaterial(greenM);
        sem.getChildren().add(sem.g);
        
        sem.y.setTranslateY(-SEM_POST_H - 5 * SEM_LIG_SPAC);
        sem.y.setTranslateZ(-SEM_SEM_W / 2);
        sem.y.setRotate(90);
        sem.y.setRotationAxis(Rotate.X_AXIS);
        sem.y.setMaterial(yellowM);
        sem.getChildren().add(sem.y);
        
        sem.r.setTranslateY(-SEM_POST_H - 8 * SEM_LIG_SPAC);
        sem.r.setTranslateZ(-SEM_SEM_W / 2);
        sem.r.setRotate(90);
        sem.r.setRotationAxis(Rotate.X_AXIS);
        sem.r.setMaterial(redM);
        sem.getChildren().add(sem.r);
        
        sem.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
        return sem;
    }
    
    private static final double STOP_POST_R = 0.05;
    private static final double STOP_POST_H = 2;
    private static final double STOP_R = 0.3;
    
    public static Group getStop() {
        Group stop = new Group();
        
        cy = new Cylinder(STOP_POST_R, STOP_POST_H);
        cy.setTranslateY(-STOP_POST_H / 2);
        cy.setMaterial(postM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D, 6);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(backM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D, 6);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(wM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R * 0.8, D, 6);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(rM);
        stop.getChildren().add(cy);
        
        stop.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
        return stop;
    }
    
    public static Group getGo() {
        Group stop = new Group();
        
        cy = new Cylinder(STOP_POST_R, STOP_POST_H);
        cy.setTranslateY(-STOP_POST_H / 2);
        cy.setMaterial(postM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D, 4);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(backM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D, 4);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(wM);
        stop.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R * 0.8, D, 4);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(yM);
        stop.getChildren().add(cy);
        
        stop.getTransforms().add(new Rotate(90, Rotate.X_AXIS));
        return stop;
    }
    
    public static Group getSpeed() {
        Group speed = new Group();
        
        cy = new Cylinder(STOP_POST_R, STOP_POST_H);
        cy.setTranslateY(-STOP_POST_H / 2);
        cy.setMaterial(postM);
        speed.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(backM);
        speed.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R, D);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(rM);
        speed.getChildren().add(cy);
        
        cy = new Cylinder(STOP_R * 0.8, D);
        cy.setTranslateY(-STOP_POST_H);
        cy.setTranslateZ(-D);
        cy.setRotate(90);
        cy.setRotationAxis(Rotate.X_AXIS);
        cy.setMaterial(wM);
        speed.getChildren().add(cy);
        
        speed.getTransforms().addAll(
                new Rotate(180, Rotate.Z_AXIS),
                new Rotate(90, Rotate.X_AXIS));
        return speed;
    }
    
}
