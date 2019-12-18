package gui;

import algorithms.Graph_Algo;
import dataStructure.*;
import utils.Point3D;
import utils.StdDraw;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class Graph_GUI {
    Graph_Algo graphAlgo = new Graph_Algo();
    DGraph dGraph = new DGraph();

    public Graph_GUI(){

    }
    public void addPoint(Point3D p,double weight){
        NodeData temp = new NodeData(p,weight);
        dGraph.addNode(temp);

    }
    public void addE(int src,int dest, double weight){
        Edata edata = new Edata(src,dest,weight);
        ((NodeData)dGraph.getNode(src)).put(dest,edata);

    }
    public void draw(int width,int height){
        StdDraw.setCanvasSize(width,height);
        StdDraw.setXscale(0,100);
        StdDraw.setYscale(0,100);
        StdDraw.setPenRadius(0.01);
        for (node_data n: dGraph.getV()){
            List<edge_data> myE = new LinkedList<>(((NodeData)n).getE());
            StdDraw.setPenColor(Color.blue);
            StdDraw.setPenRadius(0.04);
            StdDraw.point(n.getLocation().x(),n.getLocation().y());
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.setPenRadius(0.01);
            for (edge_data edge :myE) {
                double x0 = n.getLocation().x();
                double y0 = n.getLocation().y();
                double y1 = dGraph.getNode(edge.getDest()).getLocation().y();
                double x1 = dGraph.getNode(edge.getDest()).getLocation().x();
                StdDraw.line(x0,y0,x1,y1);
                double deltaX = x1-x0;
                double deltaY = y1-y0;
                double slope = deltaY/deltaX;
                double f = y0 - x0*slope;
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.point((x1+1),(x1+1)*slope+f);
                StdDraw.setPenColor(StdDraw.BLACK);

            }

        }
    }
    public boolean isConected(){
        graphAlgo.init(dGraph);
        return graphAlgo.isConnected();
    }
    public static void main(String[] args){
        Graph_GUI test = new Graph_GUI();
        Point3D p1 = new Point3D(10,10);
        Point3D p2 = new Point3D(25,13);
        Point3D p3 = new Point3D(30,22);
        Point3D p4 = new Point3D(35,40);
        Point3D p5 = new Point3D(50,80);
        test.addPoint(p1,0);
        test.addPoint(p2,0);
        test.addPoint(p3,0);
        test.addPoint(p4,0);
        test.addPoint(p5,0);
        test.addE(0,4,0);
        test.addE(4,0,0);
        test.addE(1,4,0);
        test.addE(4,1,0);
        test.addE(3,4,0);
        test.addE(4,3,0);
        test.addE(2,4,0);
        test.addE(4,2,0);

        Point3D p6 = new Point3D(90,10);
        Point3D p7 = new Point3D(85,14);
        Point3D p8 = new Point3D(76,35);
        Point3D p9 = new Point3D(64,45);
        Point3D p10 = new Point3D(60,80);
        test.addPoint(p6,0);
        test.addPoint(p7,0);
        test.addPoint(p8,0);
        test.addPoint(p9,0);
        test.addPoint(p10,0);
        test.addE(5,6,0);
        test.addE(6,5,0);
        test.addE(7,6,0);
        test.addE(6,7,0);
        test.addE(8,6,0);
        test.addE(6,8,0);
        test.addE(9,6,0);
        test.addE(6,9,0);
        test.addE(9,4,0);
        test.addE(4,9,0);
        test.draw(800,600);
        System.out.println(test.isConected());


    }

}
