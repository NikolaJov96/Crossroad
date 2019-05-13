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
public class TrailerTruck extends Vehicle {
    
    private static final float SCALE = 3.5f;

    private static final float XT1 = -12.0f;
    private static final float XT2 = -11.0f;
    private static final float XT3 = -9.0f;
    private static final float XT4 = -4.0f;
    private static final float XT5 = -2.0f;
    private static final float XT6 = -1.0f;
    
    private static final float X1 = 0.0f;
    private static final float X2 = 1.0f;
    private static final float X3 = 5.0f;
    private static final float X4 = 9.0f;
    private static final float X5 = 11.0f;
    private static final float X6 = 12.0f;
    
    private static final float Y1 = -2.8f;
    private static final float Y2 = -0.5f;
    
    private static final float Z1 = -1.0f;
    private static final float Z2 = -2.0f;
    private static final float Z3 = -3.0f;
    private static final float Z4 = -5.0f;
    
    private static final float WXT1 = -10.0f / SCALE;
    private static final float WXT2 = -3.0f / SCALE;
    private static final float WX1 = 2.0f / SCALE;
    private static final float WX2 = 4.0f / SCALE;
    private static final float WX3 = 10.0f / SCALE;
    private static final float WY1 = -2.0f / SCALE;
    private static final float WY2 = 2.0f / SCALE;
    private static final float WZ = -1.0f / SCALE;
    
    public TrailerTruck(Cns.DIRECTION dir, Raskrsnica raskrsnica) {
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
        
        Collections.addAll(pointsList, XT1, Y1, Z1);
        Collections.addAll(pointsList, XT1, Y1, Z3);
        Collections.addAll(pointsList, XT1, Y1, Z4);
        Collections.addAll(pointsList, XT2, Y1, Z1);
        Collections.addAll(pointsList, XT2, Y1, Z2);
        Collections.addAll(pointsList, XT3, Y1, Z1);
        Collections.addAll(pointsList, XT3, Y1, Z2);
        Collections.addAll(pointsList, XT4, Y1, Z1);
        Collections.addAll(pointsList, XT4, Y1, Z2);
        Collections.addAll(pointsList, XT5, Y1, Z1);
        Collections.addAll(pointsList, XT5, Y1, Z2);
        Collections.addAll(pointsList, XT6, Y1, Z1);
        Collections.addAll(pointsList, XT6, Y1, Z3);
        Collections.addAll(pointsList, XT6, Y1, Z4);
        
        Collections.addAll(pointsList, XT6, -0.3f, Z2 + 0.3f);
        Collections.addAll(pointsList, XT6, -0.3f, Z2 - 0.3f);
        Collections.addAll(pointsList, X1, -0.3f, Z2 + 0.3f);
        Collections.addAll(pointsList, X1, -0.3f, Z2 - 0.3f);
        int oneSidePoints = 16 + 14 + 4;
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
        
        Collections.addAll(facesChrList, 16 + 0, 16 + 3, 16 + 4);
        Collections.addAll(facesChrList, 16 + 0, 16 + 1, 16 + 4);
        Collections.addAll(facesChrList, 16 + 1, 16 + 4, 16 + 10);
        Collections.addAll(facesChrList, 16 + 1, 16 + 10, 16 + 12);
        Collections.addAll(facesChrList, 16 + 5, 16 + 7, 16 + 8);
        Collections.addAll(facesChrList, 16 + 5, 16 + 6, 16 + 8);
        Collections.addAll(facesChrList, 16 + 9, 16 + 11, 16 + 12);
        Collections.addAll(facesChrList, 16 + 9, 16 + 10, 16 + 12);
        for (i = 0; i < 8; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        Collections.addAll(facesChrList, 16 + 1, 16 + 12, 16 + 13);
        Collections.addAll(facesChrList, 16 + 1, 16 + 2, 16 + 13);
        for (i = 0; i < 2; i++) { Collections.addAll(facesTexList, bc, bc, bc); }
        Collections.addAll(facesChrList, 30 + 0, 30 + 1, 30 + 2);
        Collections.addAll(facesChrList, 30 + 1, 30 + 2, 30 + 3);
        for (i = 0; i < 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
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
        
        addFace(facesChrList, 16 + 0, 16 + 1, oneSidePoints);
        for (i = 0; i < 1 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        addFace(facesChrList, 16 + 1, 16 + 2, oneSidePoints);
        addFace(facesChrList, 16 + 2, 16 + 13, oneSidePoints);
        addFace(facesChrList, 16 + 13, 16 + 12, oneSidePoints);
        for (i = 0; i < 3 * 2; i++) { Collections.addAll(facesTexList, bc, bc, bc); }
        addFace(facesChrList, 16 + 12, 16 + 11, oneSidePoints);
        addFace(facesChrList, 16 + 11, 16 + 9, oneSidePoints);
        addFace(facesChrList, 16 + 9, 16 + 10, oneSidePoints);
        addFace(facesChrList, 16 + 10, 16 + 8, oneSidePoints);
        addFace(facesChrList, 16 + 8, 16 + 7, oneSidePoints);
        addFace(facesChrList, 16 + 7, 16 + 5, oneSidePoints);
        addFace(facesChrList, 16 + 5, 16 + 6, oneSidePoints);
        addFace(facesChrList, 16 + 6, 16 + 4, oneSidePoints);
        addFace(facesChrList, 16 + 4, 16 + 3, oneSidePoints);
        addFace(facesChrList, 16 + 3, 16 + 0, oneSidePoints);
        for (i = 0; i < 10 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
        addFace(facesChrList, 30 + 0, 30 + 2, oneSidePoints);
        addFace(facesChrList, 30 + 1, 30 + 3, oneSidePoints);
        for (i = 0; i < 2 * 2; i++) { Collections.addAll(facesTexList, rc, rc, rc); }
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
        Cylinder tw1 = getWheel(1 / SCALE, 2 / SCALE, WXT1, WY1, WZ);
        Cylinder tw2 = getWheel(1 / SCALE, 2 / SCALE, WXT1, WY2, WZ);
        Cylinder tw3 = getWheel(1 / SCALE, 2 / SCALE, WXT2, WY1, WZ);
        Cylinder tw4 = getWheel(1 / SCALE, 2 / SCALE, WXT2, WY2, WZ);
        Cylinder w1 = getWheel(1 / SCALE, 2 / SCALE, WX1, WY1, WZ);
        Cylinder w2 = getWheel(1 / SCALE, 2 / SCALE, WX1, WY2, WZ);
        Cylinder w3 = getWheel(1 / SCALE, 2 / SCALE, WX2, WY1, WZ);
        Cylinder w4 = getWheel(1 / SCALE, 2 / SCALE, WX2, WY2, WZ);
        Cylinder w5 = getWheel(1 / SCALE, 2 / SCALE, WX3, WY1, WZ);
        Cylinder w6 = getWheel(1 / SCALE, 2 / SCALE, WX3, WY2, WZ);
        
        breakLight1 = new Box(D, 1 / SCALE, 1 / SCALE);
        breakLight1.setTranslateX(XT1 / SCALE);
        breakLight1.setTranslateY(Y1 * 3 / 4 / SCALE);
        breakLight1.setTranslateZ(Z2 / SCALE);
        breakLight1.setMaterial(ACC_MAT);
        
        breakLight2 = new Box(D, 1 / SCALE, 1 / SCALE);
        breakLight2.setTranslateX(XT1 / SCALE);
        breakLight2.setTranslateY(-Y1 * 3 / 4 / SCALE);
        breakLight2.setTranslateZ(Z2 / SCALE);
        breakLight2.setMaterial(ACC_MAT);
        
        getChildren().addAll(meshView, meshView1, tw1, tw2, tw3, tw4, w1, w2, w3, w4, w5, w6, breakLight1, breakLight2);
    }

    @Override
    protected double getMaxSpd() { return 0.08; }

    @Override
    protected double getAccelRate() { return 0.0003; }

    @Override
    protected double getLen() { return 6.5; }

    private void addFace(ArrayList<Integer> facesChrList, int id1, int id2, int oneSidePoints) {
        Collections.addAll(facesChrList, id1, id2, id1 + oneSidePoints);
        Collections.addAll(facesChrList, id1 + oneSidePoints, id2 + oneSidePoints, id2);
    }
    
}
