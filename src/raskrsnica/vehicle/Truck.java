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
public class Truck extends Vehicle {
    
    private static final float SCALE = 3.5f;

    private static final float X1 = -6.0f;
    private static final float X2 = -5.0f;
    private static final float X3 = -1.0f;
    private static final float X4 = 3.0f;
    private static final float X5 = 5.0f;
    private static final float X6 = 6.0f;
    
    private static final float Y1 = -2.8f;
    
    private static final float Z1 = -1.0f;
    private static final float Z2 = -2.0f;
    private static final float Z3 = -3.0f;
    private static final float Z4 = -5.0f;
    
    private static final float WX1 = -4.0f / SCALE;
    private static final float WX2 = -2.0f / SCALE;
    private static final float WX3 = 4.0f / SCALE;
    private static final float WY1 = -2.0f / SCALE;
    private static final float WY2 = 2.0f / SCALE;
    private static final float WZ = -1.0f / SCALE;
    
    public Truck(Cns.DIRECTION dir, Raskrsnica raskrsnica) {
        super(dir, raskrsnica);
        int rc = 3 + (new Random()).nextInt(7);
        int bc = 0;
        
        // One side points
        ArrayList<Float> pointsList = new ArrayList();
        Collections.addAll(pointsList, X1, Y1, Z1);
        Collections.addAll(pointsList, X1, Y1, Z3);
        Collections.addAll(pointsList, X1, Y1, Z4);
        Collections.addAll(pointsList, X2, Y1, Z1);
        Collections.addAll(pointsList, X2, Y1, Z2);
        Collections.addAll(pointsList, X3, Y1, Z1);
        Collections.addAll(pointsList, X3, Y1, Z2);
        Collections.addAll(pointsList, X4, Y1, Z1);
        Collections.addAll(pointsList, X4, Y1, Z2);
        Collections.addAll(pointsList, X4, Y1, Z3);
        Collections.addAll(pointsList, X4, Y1, Z4);
        Collections.addAll(pointsList, X5, Y1, Z1);
        Collections.addAll(pointsList, X5, Y1, Z2);
        Collections.addAll(pointsList, X6, Y1, Z1);
        Collections.addAll(pointsList, X6, Y1, Z3);
        Collections.addAll(pointsList, X6, Y1, Z4);
        int oneSidePoints = 16;
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
        Collections.addAll(facesChrList, 0, 3, 4);
        Collections.addAll(facesChrList, 0, 1, 4);
        Collections.addAll(facesChrList, 1, 4, 12);
        Collections.addAll(facesChrList, 1, 12, 14);
        Collections.addAll(facesChrList, 5, 7, 8);
        Collections.addAll(facesChrList, 5, 6, 8);
        Collections.addAll(facesChrList, 11, 13, 14);
        Collections.addAll(facesChrList, 11, 12, 14);
        for (i = 0; i < 8; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        Collections.addAll(facesChrList, 1, 9, 10);
        Collections.addAll(facesChrList, 1, 2, 10);
        for (i = 0; i < 2; i++) { Collections.addAll(facesTexList, bc, bc, bc); }
        Collections.addAll(facesChrList, 9, 14, 15);
        Collections.addAll(facesChrList, 9, 10, 15);
        for (i = 0; i < 2; i++) { Collections.addAll(facesTexList, WINDOW_CLR, WINDOW_CLR, WINDOW_CLR); }
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
        addFace(facesChrList, 2, 10, oneSidePoints);
        for (i = 0; i < 2 * 2; i++) { Collections.addAll(facesTexList, bc, bc, bc); }
        addFace(facesChrList, 10, 15, oneSidePoints);
        addFace(facesChrList, 15, 14, oneSidePoints);
        for (i = 0; i < 2 * 2; i++) { Collections.addAll(facesTexList, WINDOW_CLR, WINDOW_CLR, WINDOW_CLR); }
        addFace(facesChrList, 14, 13, oneSidePoints);
        addFace(facesChrList, 13, 11, oneSidePoints);
        addFace(facesChrList, 11, 12, oneSidePoints);
        addFace(facesChrList, 12, 8, oneSidePoints);
        addFace(facesChrList, 8, 7, oneSidePoints);
        addFace(facesChrList, 7, 5, oneSidePoints);
        addFace(facesChrList, 5, 6, oneSidePoints);
        addFace(facesChrList, 6, 4, oneSidePoints);
        addFace(facesChrList, 4, 3, oneSidePoints);
        addFace(facesChrList, 3, 0, oneSidePoints);
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
        meshView1.setMaterial(pm1);
        meshView1.setDrawMode(DrawMode.FILL);
        
        // Wheels
        Cylinder w1 = getWheel(1 / SCALE, 2 / SCALE, WX1, WY1, WZ);
        Cylinder w2 = getWheel(1 / SCALE, 2 / SCALE, WX1, WY2, WZ);
        Cylinder w3 = getWheel(1 / SCALE, 2 / SCALE, WX2, WY1, WZ);
        Cylinder w4 = getWheel(1 / SCALE, 2 / SCALE, WX2, WY2, WZ);
        Cylinder w5 = getWheel(1 / SCALE, 2 / SCALE, WX3, WY1, WZ);
        Cylinder w6 = getWheel(1 / SCALE, 2 / SCALE, WX3, WY2, WZ);
        
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
        
        getChildren().addAll(meshView, meshView1, w1, w2, w3, w4, w5, w6, breakLight1, breakLight2);
    }

    @Override
    protected double getMaxSpd() { return 0.08; }

    @Override
    protected double getAccelRate() { return 0.0005; }

    @Override
    protected double getLen() { return 5; }

    private void addFace(ArrayList<Integer> facesChrList, int id1, int id2, int oneSidePoints) {
        Collections.addAll(facesChrList, id1, id2, id1 + oneSidePoints);
        Collections.addAll(facesChrList, id1 + oneSidePoints, id2 + oneSidePoints, id2);
    }
    
}
