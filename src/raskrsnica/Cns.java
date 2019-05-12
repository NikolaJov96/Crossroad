/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica;

/**
 *
 * @author jovan
 */
public abstract class Cns {
    
    public enum DIRECTION { UD, DU, LR, RL };
    
    public static final int SCREEN_WIDTH = 960;
    public static final int SCREEN_HEIGHT = 960;
    
    public static final double GND_LENGTH = 200;
    public static final double GND_DEPTH = 1;
    public static final double D = 0.01;
    
    public static final double LANE_WIDTH = 3;
    public static final double SPACING_WIDTH = 0.1;
    public static final double SPACING_HEIGHT = 0.1;
    public static final double SPACING_LENGTH = GND_LENGTH / 2 - LANE_WIDTH;
    public static final double SIDEWALK_WIDTH = 2;
    public static final double SIDEWALK_LENGTH = GND_LENGTH / 2 - LANE_WIDTH - SPACING_WIDTH;
    public static final double GRASS_LENGTH = GND_LENGTH / 2 - LANE_WIDTH - SPACING_WIDTH - SIDEWALK_WIDTH;
    
    public static final int SIDE_SRIPTES = 7;
    public static final double SIDE_STRIPE_LEN = 2.5;
    public static final double SIDE_STRIPE_WID = LANE_WIDTH * 2 / (SIDE_SRIPTES * 2 + 1);
    public static final int MID_STRIPES = 10;
    public static final double MID_STRIPE_LEN = (GND_LENGTH / 2 - LANE_WIDTH - SIDE_STRIPE_LEN) / (MID_STRIPES * 2 + 1);
    public static final double MID_STRIPE_WID = 0.3;
            
    public static final double CAM_FIELD_OF_VIEW = 60;
    public static final double CAM_MAX_HEIGHT = GND_LENGTH / 2 / Math.tan(CAM_FIELD_OF_VIEW / 2 * Math.PI / 180.0); // ? function of gnd length and aspect ratio
    public static final double CAM_MIN_HEIGHT = 5;
    public static final double CAM_NEAR_CLIP = 0.1;
    public static final double CAM_FAR_CLIP = GND_LENGTH * 2;
    public static final double CAM_UP_DOWN_STEP = 1;
    public static final double CAM_LEFT_RIGHT_STEP = 1;
    
    public static final double SPAWN_DELAY = 1000;
    public static final double SPAWN_DELAY_VARIATION = SPAWN_DELAY * 0.6;
    public static final double SPAWN_NEEDED_SPACE = 6;
    public static final double SPAWN_POSITION = 3;
    
    public static final double DT_COEF = 60.0 / 1000;
    
}
