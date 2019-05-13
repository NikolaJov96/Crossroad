/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import static raskrsnica.Cns.*;
import raskrsnica.vehicle.*;

/**
 *
 * @author jovan
 */
public class Raskrsnica extends Application {
    
    private final CameraManager cameraManager = new CameraManager();
    private final Sorrounding sorrounding = new Sorrounding();
    Group root = new Group();
    Scene scene = new Scene(root, SCREEN_WIDTH, SCREEN_HEIGHT, true);
    Random rnd = new Random();
    
    private boolean upDown = false;
    private boolean downDown = false;
    private boolean leftDown = false;
    private boolean rightDown = false;
    
    private final Map<DIRECTION, List<Vehicle>> vehicles = new HashMap();
    {
        vehicles.put(DIRECTION.UD, new ArrayList());
        vehicles.put(DIRECTION.DU, new ArrayList());
        vehicles.put(DIRECTION.LR, new ArrayList());
        vehicles.put(DIRECTION.RL, new ArrayList());
    }
    
    @Override
    public void start(Stage primaryStage) {
        root.getChildren().add(sorrounding);
        
        scene.setCamera(cameraManager.getTopDownCamera());
        scene.setFill(Color.SILVER);
        scene.setOnKeyPressed(e -> {
            switch(e.getCode()) {
                case UP: upDown = true; break;
                case DOWN: downDown = true; break;
                case LEFT: leftDown = true; break;
                case RIGHT: rightDown = true; break;
                case ESCAPE: primaryStage.fireEvent(
                        new WindowEvent(
                                primaryStage,
                                WindowEvent.WINDOW_CLOSE_REQUEST
                        ));
                    break;
            }
        });
        scene.setOnKeyReleased(e -> {
            switch(e.getCode()) {
                case UP: upDown = false; break;
                case DOWN: downDown = false; break;
                case LEFT: leftDown = false; break;
                case RIGHT: rightDown = false; break;
            }
        });
        scene.setOnKeyTyped(e -> {
            switch(e.getCharacter()) {
                case "1": scene.setCamera(cameraManager.getTopDownCamera()); break;
                case "2": scene.setCamera(cameraManager.getCenterCamera()); break;
            }
        });
        
        new AnimationTimer() {
            
            private long lastStateChangeTime = -1;
            private long lastSpawnTime = -1;
            private long lastTimeStep = -1;
            
            private int stateId = -1;
            private long spawnWait = 0;
            
            @Override
            public void handle(long now) {
                now = now / 1000 / 1000;
                
                if (lastStateChangeTime < 0) { lastStateChangeTime = now; }
                if (lastSpawnTime < 0) { lastSpawnTime = now; }
                if (lastTimeStep < 0) { lastTimeStep = now; }
                
                if (upDown) { cameraManager.goUp(); }
                if (downDown) { cameraManager.goDown(); }
                if (leftDown) { cameraManager.goLeft(); }
                if (rightDown) { cameraManager.goGoRight(); }
                
                if (now - lastSpawnTime > spawnWait) {
                    if (trySpawn()) {
                        System.out.println("Spawn");
                    }
                    lastSpawnTime = now;
                    spawnWait = (long) (SPAWN_DELAY + rnd.nextDouble() * SPAWN_DELAY_VARIATION);
                }
                
                boolean nextState = false;
                switch(stateId) {
                    case 0: if (now - lastStateChangeTime > 1000) { nextState = true; } break;
                    case 1: if (now - lastStateChangeTime > 3000) { nextState = true; } break;
                    case 2: if (now - lastStateChangeTime > 12000) { nextState = true; } break;
                    case 3: if (now - lastStateChangeTime > 3000) { nextState = true; } break;
                    case 4: if (now - lastStateChangeTime > 1000) { nextState = true; } break;
                    case 5: if (now - lastStateChangeTime > 3000) { nextState = true; } break;
                    case 6: if (now - lastStateChangeTime > 12000) { nextState = true; } break;
                    case 7: if (now - lastStateChangeTime > 3000) { nextState = true; } break;
                    case -1: nextState = true; break;
                }
                if (nextState) {
                    stateId = (stateId + 1) % 8; 
                    lastStateChangeTime = now;
                    System.out.println("New state: " + stateId);
                    sorrounding.sems[0].select(stateId);
                    sorrounding.sems[1].select((stateId + 4) % 8);
                    sorrounding.sems[2].select((stateId + 4) % 8);
                    sorrounding.sems[3].select(stateId);
                }
                
                double dt = (double)(now - lastTimeStep) * DT_COEF;
                vehicles.values().forEach((vehs) -> {
                    Vehicle inFront = null;
                    for (int i = 0; i < vehs.size();) {
                        Vehicle veh = vehs.get(i);
                        veh.step(stateId, inFront, dt);
                        Translate t = veh.getTran();
                        inFront = veh;
                        if (t.getX() < 0 || t.getX() > GND_LENGTH ||
                                t.getY() < 0 || t.getY() > GND_LENGTH) {
                            if (veh.getCam() == scene.getCamera()) {
                                scene.setCamera(cameraManager.getTopDownCamera());
                            }
                            vehs.remove(veh);
                            root.getChildren().remove(veh);
                            inFront = null;
                            System.out.println("Vehicle removed");
                        } else {
                            i++;
                        }
                    }
                });
                
                lastTimeStep = now;
            }
        }.start();
        
        primaryStage.setTitle("Raskrsnica");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private boolean trySpawn() {
        int initPick = rnd.nextInt(4);
        for (int i = 0; i < 4; i++) {
            int pick = (initPick + i) % 4;
            DIRECTION dir = DIRECTION.values()[pick];
            if (vehicles.get(dir).isEmpty() ||
                    enoughDist(vehicles.get(dir).get(vehicles.get(dir).size() - 1), dir)) {
                int vehicleType = rnd.nextInt(6);
                Vehicle veh = null;
                switch(vehicleType) {
                    case 0: veh = (Vehicle) new Car1(dir, this); break;
                    case 1: veh = (Vehicle) new Car2(dir, this); break;
                    case 2: veh = (Vehicle) new Car3(dir, this); break;
                    case 3: veh = (Vehicle) new Bus(dir, this); break;
                    case 4: veh = (Vehicle) new Truck(dir, this); break;
                    case 5: veh = (Vehicle) new TrailerTruck(dir, this); break;
                }
                vehicles.get(dir).add(veh);
                root.getChildren().add(veh);
                return true;
            }
        }
        return false;
    }
    
    private boolean enoughDist(Vehicle v, DIRECTION d) {
        switch(d) {
            case UD:
                if (v.getTran().getY() > SPAWN_NEEDED_SPACE + SPAWN_POSITION) {
                    return true;
                }
                break;
            case DU:
                if (v.getTran().getY() < GND_LENGTH - SPAWN_NEEDED_SPACE - SPAWN_POSITION) {
                    return true;
                }
                break;
            case LR:
                if (v.getTran().getX() > SPAWN_NEEDED_SPACE + SPAWN_POSITION) {
                    return true;
                }
                break;
            case RL:
                if (v.getTran().getX() < GND_LENGTH - SPAWN_NEEDED_SPACE - SPAWN_POSITION) {
                    return true;
                }
                break;
        }
        System.out.println("b");
        return false;
    }
    
    public void vehicleSelected(Vehicle vehicle) {
        System.out.println("Vehicle selected");
        cameraManager.setVehicleCamera(vehicle);
        scene.setCamera(vehicle.getCam());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
