/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raskrsnica.vehicle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;
import raskrsnica.Cns;
import static raskrsnica.Cns.D;
import raskrsnica.Raskrsnica;

/**
 *
 * @author jovan
 */
public class Bus extends Vehicle {
    
    private static final float SCALE = 3.5f;

    private static final float X1 = -8.0f;
    private static final float X2 = -7.0f;
    private static final float X3 = -6.0f;
    private static final float X4 = -4.0f;
    private static final float X5 = 4.0f;
    private static final float X6 = 5.0f;
    private static final float X7 = 6.0f;
    private static final float X8 = 7.0f;
    private static final float X9 = 8.0f;
    
    private static final float Y1 = -3.0f;
    
    private static final float Z1 = -1.0f;
    private static final float Z2 = -2.0f;
    private static final float Z3 = -3.3f;
    private static final float Z4 = -4.5f;
    private static final float Z5 = -6.0f;
    
    private static final float WX1 = -5.0f / SCALE;
    private static final float WX2 = 5.0f / SCALE;
    private static final float WY1 = -2.0f / SCALE;
    private static final float WY2 = 2.0f / SCALE;
    private static final float WZ = -1.0f / SCALE;
    
    public Bus(Cns.DIRECTION dir, Raskrsnica raskrsnica) {
        super(dir, raskrsnica);
        int rc = 3 + (new Random()).nextInt(7);
        
        // One side points
        ArrayList<Float> pointsList = new ArrayList();
        Collections.addAll(pointsList, X1, Y1, Z1);
        Collections.addAll(pointsList, X1, Y1, Z4);
        Collections.addAll(pointsList, X1, Y1, Z5);
        Collections.addAll(pointsList, X2, Y1, Z3);
        Collections.addAll(pointsList, X3, Y1, Z1);
        Collections.addAll(pointsList, X3, Y1, Z2);
        Collections.addAll(pointsList, X4, Y1, Z1);
        Collections.addAll(pointsList, X4, Y1, Z2);
        Collections.addAll(pointsList, X5, Y1, Z1);
        Collections.addAll(pointsList, X5, Y1, Z2);
        Collections.addAll(pointsList, X6, Y1, Z5);
        Collections.addAll(pointsList, X7, Y1, Z1);
        Collections.addAll(pointsList, X7, Y1, Z2);
        Collections.addAll(pointsList, X8, Y1, Z3);
        Collections.addAll(pointsList, X9, Y1, Z1);
        Collections.addAll(pointsList, X9, Y1, Z2);
        Collections.addAll(pointsList, X9, Y1, Z5);
        int oneSidePoints = 17;
        // Other side points
        for (int i = 0; i < oneSidePoints; i++) {
            Collections.addAll(pointsList, 
                    pointsList.get(i * 3), 
                    -pointsList.get(i * 3 + 1), 
                    pointsList.get(i * 3 + 2));
        }
        float[] points = new float[pointsList.size()];
        int i = 0;
        for (Float f : pointsList) { points[i++] = f / SCALE; }
        
        // One side faces
        ArrayList<Integer> facesChrList = new ArrayList();
        ArrayList<Integer> facesTexList = new ArrayList();
        Collections.addAll(facesChrList, 0, 4, 5);
        Collections.addAll(facesChrList, 0, 5, 1);
        for (i = 0; i < 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        Collections.addAll(facesChrList, 3, 10, 13);
        Collections.addAll(facesChrList, 1, 3, 10);
        Collections.addAll(facesChrList, 1, 2, 10);
        Collections.addAll(facesChrList, 10, 15, 16);
        for (i = 0; i < 4; i++) { Collections.addAll(facesTexList, WINDOW_CLR, WINDOW_CLR, WINDOW_CLR); }
        Collections.addAll(facesChrList, 3, 5, 7);
        Collections.addAll(facesChrList, 6, 8, 9);
        Collections.addAll(facesChrList, 6, 7, 9);
        Collections.addAll(facesChrList, 7, 15, 13);
        Collections.addAll(facesChrList, 3, 7, 13);
        Collections.addAll(facesChrList, 11, 14, 15);
        Collections.addAll(facesChrList, 11, 12, 15);
        for (i = 0; i < 7; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        int oneSideFacesNum = facesChrList.size() / 3;
        // Faces on the other side
        for (i = 0; i < oneSideFacesNum; i++) {
            Collections.addAll(facesChrList, 
                    facesChrList.get(i * 3) + oneSidePoints, 
                    facesChrList.get(i * 3 + 1) + oneSidePoints, 
                    facesChrList.get(i * 3 + 2) + oneSidePoints
            );
            Collections.addAll(facesTexList, 
                    facesTexList.get(i * 3), 
                    facesTexList.get(i * 3 + 1), 
                    facesTexList.get(i * 3 + 2)
            );
        }
        // back, front and top faces
        addFace(facesChrList, 0, 1, oneSidePoints);
        for (i = 0; i < 1 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        addFace(facesChrList, 1, 2, oneSidePoints);
        for (i = 0; i < 1 * 2; i++) { Collections.addAll(facesTexList, WINDOW_CLR, WINDOW_CLR, WINDOW_CLR); }
        addFace(facesChrList, 2, 10, oneSidePoints);
        addFace(facesChrList, 10, 16, oneSidePoints);
        for (i = 0; i < 2 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        addFace(facesChrList, 16, 15, oneSidePoints);
        for (i = 0; i < 1 * 2; i++) { Collections.addAll(facesTexList, WINDOW_CLR, WINDOW_CLR, WINDOW_CLR); }
        addFace(facesChrList, 15, 14, oneSidePoints);
        addFace(facesChrList, 14, 11, oneSidePoints);
        addFace(facesChrList, 11, 12, oneSidePoints);
        addFace(facesChrList, 12, 9, oneSidePoints);
        addFace(facesChrList, 9, 8, oneSidePoints);
        addFace(facesChrList, 8, 6, oneSidePoints);
        addFace(facesChrList, 6, 7, oneSidePoints);
        addFace(facesChrList, 7, 5, oneSidePoints);
        addFace(facesChrList, 5, 4, oneSidePoints);
        addFace(facesChrList, 4, 0, oneSidePoints);
        for (i = 0; i < 10 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        // Reversefaces
        int totalNonReversedFaces = facesChrList.size();
        for (i = 0; i < totalNonReversedFaces; i++) {
            Collections.addAll(facesChrList, 
                    facesChrList.get(i * 3), 
                    facesChrList.get(i * 3 + 2), 
                    facesChrList.get(i * 3 + 1)
            );
            Collections.addAll(facesTexList, 
                    facesTexList.get(i * 3), 
                    facesTexList.get(i * 3 + 1), 
                    facesTexList.get(i * 3 + 2)
            );
        }
        int[] faces = new int[facesChrList.size() * 2];
        for (i = 0; i < facesChrList.size(); i++) {
            faces[i * 2] = facesChrList.get(i);
            faces[i * 2 + 1] = facesTexList.get(i);
        }
        
        TriangleMesh mesh = new TriangleMesh();
        mesh.getPoints().addAll(points);
        mesh.getTexCoords().addAll(texCoords);
        mesh.getFaces().addAll(faces);
         
        MeshView meshView = new MeshView(mesh);
        PhongMaterial pm = new PhongMaterial(Color.BLACK);
        pm.setSpecularPower(100);
        meshView.setMaterial(pm);
        meshView.setDrawMode(DrawMode.LINE);
        
        MeshView meshView1 = new MeshView(mesh);
        PhongMaterial pm1 = new PhongMaterial();
        pm1.setDiffuseMap(CLS);
        pm1.setSpecularPower(150);
        //pm1.setSpecularPower(100);
        meshView1.setMaterial(pm1);
        meshView1.setDrawMode(DrawMode.FILL);
        
        // Wheels
        Cylinder w1 = new Cylinder(1 / SCALE, 1 / SCALE);
        w1.setMaterial(WHEEL_MAT);
        w1.setTranslateX(WX1);
        w1.setTranslateY(WY1);
        w1.setTranslateZ(WZ);
        Cylinder w2 = new Cylinder(1 / SCALE, 1 / SCALE);
        w2.setMaterial(WHEEL_MAT);
        w2.setTranslateX(WX1);
        w2.setTranslateY(WY2);
        w2.setTranslateZ(WZ);
        Cylinder w3 = new Cylinder(1 / SCALE, 1 / SCALE);
        w3.setMaterial(WHEEL_MAT);
        w3.setTranslateX(WX2);
        w3.setTranslateY(WY1);
        w3.setTranslateZ(WZ);
        Cylinder w4 = new Cylinder(1 / SCALE, 1 / SCALE);
        w4.setMaterial(WHEEL_MAT);
        w4.setTranslateX(WX2);
        w4.setTranslateY(WY2);
        w4.setTranslateZ(WZ);
        
        breakLight1 = new Box(D, 1 / SCALE, 1 / SCALE);
        breakLight1.setTranslateX(X1 / SCALE);
        breakLight1.setTranslateY(Y1 * 3 / 4 / SCALE);
        breakLight1.setTranslateZ(Z2 / SCALE);
        breakLight1.setMaterial(ACC_MAT);
        
        breakLight2 = new Box(D, 1 / SCALE, 1 / SCALE);
        breakLight2.setTranslateX(X1 / SCALE);
        breakLight2.setTranslateY(-Y1 * 3 / 4 / SCALE);
        breakLight2.setTranslateZ(Z2 / SCALE);
        breakLight2.setMaterial(ACC_MAT);
        
        getChildren().addAll(meshView, meshView1, w1, w2, w3, w4, breakLight1, breakLight2);
        
    }

    @Override
    protected double getMaxSpd() { return 0.1; }

    @Override
    protected double getAccelRate() { return 0.001; }

    @Override
    protected double getLen() { return 6; }

    private void addFace(ArrayList<Integer> facesChrList, int id1, int id2, int oneSidePoints) {
        Collections.addAll(facesChrList, id1, id2, id1 + oneSidePoints);
        Collections.addAll(facesChrList, id1 + oneSidePoints, id2 + oneSidePoints, id2);
    }
    
}
