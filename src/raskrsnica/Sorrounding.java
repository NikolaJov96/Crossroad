/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica;

import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import static raskrsnica.Cns.*;
import raskrsnica.ObjectBuilder.Semaphore;

/**
 *
 * @author jovan
 */
class Sorrounding extends Group {
    
    public Semaphore[] sems = new Semaphore[4];

    public Sorrounding() {
        
        PhongMaterial roadMat = new PhongMaterial();
        roadMat.setDiffuseMap(new Image("asph.jpg"));
        PhongMaterial spacingMat = new PhongMaterial(Color.WHITE);
        PhongMaterial walkMat = new PhongMaterial();
        walkMat.setDiffuseMap(new Image("side.jpg"));
        PhongMaterial grassMat = new PhongMaterial();
        grassMat.setDiffuseMap(new Image("grass.jpg"));
        PhongMaterial stripeMat = new PhongMaterial(Color.WHITE);
        
        Box road;
        road = new Box(GND_LENGTH, LANE_WIDTH * 2, GND_DEPTH);
        road.setMaterial(roadMat);
        road.setTranslateX(GND_LENGTH / 2);
        road.setTranslateY(GND_LENGTH / 2);
        road.setTranslateZ(GND_DEPTH / 2);
        getChildren().add(road);
        
        road = new Box(LANE_WIDTH * 2, GND_LENGTH / 2 - LANE_WIDTH, GND_DEPTH);
        road.setMaterial(roadMat);
        road.setTranslateX(GND_LENGTH / 2);
        road.setTranslateY(GND_LENGTH / 4 - LANE_WIDTH / 2);
        road.setTranslateZ(GND_DEPTH / 2);
        getChildren().add(road);
        
        road = new Box(LANE_WIDTH * 2, GND_LENGTH / 2 - LANE_WIDTH, GND_DEPTH);
        road.setMaterial(roadMat);
        road.setTranslateX(GND_LENGTH / 2);
        road.setTranslateY(GND_LENGTH * 3 / 4 + LANE_WIDTH / 2);
        road.setTranslateZ(GND_DEPTH / 2);
        getChildren().add(road);
        
        Box box;
        
        // road sorrounding
        for (int i = -1; i <= 1; i += 2) {
            for (int j = -1; j <= 1; j += 2) {
                // road edges
                box = new Box(SPACING_LENGTH, SPACING_WIDTH, GND_DEPTH);
                box.setTranslateX(GND_LENGTH / 2 + i * (SPACING_LENGTH / 2 + LANE_WIDTH));
                box.setTranslateY(GND_LENGTH / 2 + j * (LANE_WIDTH + SPACING_WIDTH / 2));
                box.setTranslateZ(GND_DEPTH / 2 - SPACING_HEIGHT);
                box.setMaterial(spacingMat);
                getChildren().add(box);
                box = new Box(SPACING_WIDTH, SPACING_LENGTH, GND_DEPTH);
                box.setTranslateX(GND_LENGTH / 2 + i * (LANE_WIDTH + SPACING_WIDTH / 2));
                box.setTranslateY(GND_LENGTH / 2 + j * (SPACING_LENGTH / 2 + LANE_WIDTH));
                box.setTranslateZ(GND_DEPTH / 2 - SPACING_HEIGHT);
                box.setMaterial(spacingMat);
                getChildren().add(box);
                // sidewalks
                box = new Box(SIDEWALK_LENGTH, SIDEWALK_WIDTH, GND_DEPTH);
                box.setTranslateX(GND_LENGTH / 2 + i * (SIDEWALK_LENGTH / 2 + LANE_WIDTH + SPACING_WIDTH));
                box.setTranslateY(GND_LENGTH / 2 + j * (LANE_WIDTH + SPACING_WIDTH + SIDEWALK_WIDTH / 2));
                box.setTranslateZ(GND_DEPTH / 2 - SPACING_HEIGHT);
                box.setMaterial(walkMat);
                getChildren().add(box);
                box = new Box(SIDEWALK_WIDTH, SIDEWALK_LENGTH - SIDEWALK_WIDTH, GND_DEPTH);
                box.setTranslateX(GND_LENGTH / 2 + i * (LANE_WIDTH + SPACING_WIDTH + SIDEWALK_WIDTH / 2));
                box.setTranslateY(GND_LENGTH / 2 + j * ((SIDEWALK_LENGTH - SIDEWALK_WIDTH) / 2 + LANE_WIDTH + SPACING_WIDTH + SIDEWALK_WIDTH));
                box.setTranslateZ(GND_DEPTH / 2 - SPACING_HEIGHT);
                box.setMaterial(walkMat);
                getChildren().add(box);
                // grass
                box = new Box(GRASS_LENGTH, GRASS_LENGTH, GND_DEPTH);
                box.setTranslateX(GND_LENGTH / 2 + i * (GRASS_LENGTH / 2 + LANE_WIDTH + SPACING_WIDTH + SIDEWALK_WIDTH));
                box.setTranslateY(GND_LENGTH / 2 + j * (GRASS_LENGTH / 2 + LANE_WIDTH + SPACING_WIDTH + SIDEWALK_WIDTH));
                box.setTranslateZ(GND_DEPTH / 2 - SPACING_HEIGHT);
                box.setMaterial(grassMat);
                getChildren().add(box);
            }
        }
        
        // road signalization
        int semCo = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if ((i != 0 && j != 0) || (i == 0 && j == 0)) { continue; }
                Group stripeArr;
                // crosswalk stripes
                stripeArr = new Group();
                for (int k = 0; k < SIDE_SRIPTES; k++) {
                    box = new Box(SIDE_STRIPE_WID, SIDE_STRIPE_LEN, GND_DEPTH);
                    box.setTranslateX(-LANE_WIDTH + 1.5 * SIDE_STRIPE_WID + k * 2 * SIDE_STRIPE_WID);
                    box.setTranslateZ(GND_DEPTH / 2 - D);
                    box.setMaterial(stripeMat);
                    stripeArr.getChildren().add(box);
                }
                // stop stripe
                box = new Box(LANE_WIDTH, SIDE_STRIPE_WID, GND_DEPTH);
                box.setTranslateX(LANE_WIDTH / 2);
                box.setTranslateY(LANE_WIDTH / 2 + SIDE_STRIPE_WID);
                box.setTranslateZ(GND_DEPTH / 2 - D);
                box.setMaterial(stripeMat);
                stripeArr.getChildren().add(box);
                // semaphore
                sems[semCo] = ObjectBuilder.getSemaphore();
                sems[semCo].setTranslateX(LANE_WIDTH + SIDE_STRIPE_WID);
                sems[semCo].setTranslateY(-SIDE_STRIPE_LEN / 2 + SIDE_STRIPE_WID);
                stripeArr.getChildren().add(sems[semCo++]);
                // stop/go sign
                Group sign = null;
                if (i == 0) { sign = ObjectBuilder.getStop(); }
                else { sign = ObjectBuilder.getGo(); }
                sign.setTranslateX(LANE_WIDTH + SIDE_STRIPE_WID);
                sign.setTranslateY(SIDE_STRIPE_LEN / 2 + SIDE_STRIPE_WID);
                stripeArr.getChildren().add(sign);
                // speed sign
                sign = ObjectBuilder.getSpeed();
                sign.setTranslateX(-LANE_WIDTH - SIDE_STRIPE_WID);
                sign.setTranslateY(SIDE_STRIPE_LEN / 2 + SIDE_STRIPE_WID + Math.random() * GND_LENGTH / 3);
                stripeArr.getChildren().add(sign);
                stripeArr.getTransforms().add(new Translate(
                        GND_LENGTH / 2 + i * (LANE_WIDTH + SIDE_STRIPE_LEN / 2), 
                        GND_LENGTH / 2 + j * (LANE_WIDTH + SIDE_STRIPE_LEN / 2), 0));
                if (i != 0) { stripeArr.getTransforms().add(new Rotate(-i * 90, Rotate.Z_AXIS)); }
                if (j == -1) { stripeArr.getTransforms().add(new Rotate(180, Rotate.Z_AXIS)); }
                getChildren().add(stripeArr);
                // road middle stripes
                stripeArr = new Group();
                for (int k = 0; k < MID_STRIPES; k++) {
                    box = new Box(MID_STRIPE_LEN, MID_STRIPE_WID, GND_DEPTH);
                    box.setTranslateX(-(MID_STRIPES * 2 + 1) * MID_STRIPE_LEN / 2 + 1.5 * MID_STRIPE_LEN + k * 2 * MID_STRIPE_LEN);
                    box.setMaterial(stripeMat);
                    stripeArr.getChildren().add(box);
                }
                stripeArr.getTransforms().add(new Translate(
                        GND_LENGTH / 2 + i * (LANE_WIDTH + SIDE_STRIPE_LEN + (MID_STRIPES * 2 + 1) * MID_STRIPE_LEN / 2), 
                        GND_LENGTH / 2 + j * (LANE_WIDTH + SIDE_STRIPE_LEN + (MID_STRIPES * 2 + 1) * MID_STRIPE_LEN / 2),
                        GND_DEPTH / 2 - D));
                if (j != 0) { stripeArr.getTransforms().add(new Rotate(90, Rotate.Z_AXIS)); }
                getChildren().add(stripeArr);
            }
        }
        
        getChildren().add(new AmbientLight());
    }
    
}
