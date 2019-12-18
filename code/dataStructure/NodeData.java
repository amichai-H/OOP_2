package dataStructure;

import utils.Point3D;

import java.util.*;

public class NodeData implements node_data{
    private static int _ID=0;
    private int _myID;
    private Point3D _location = null;
    private double _weight;
    private String _info = "";
    private int _tag;
    private int _tagFolow;
    private HashMap<Integer,Edata> EMap = new LinkedHashMap<>();



    public NodeData(Point3D location,double weight){
        _myID = _ID;
        _ID++;
        _location = location;
        _weight = weight;

    }
    public NodeData(node_data n){
        _myID = n.getKey();
        if (_ID<n.getKey())
            _ID = n.getKey()+1;
        _location = n.getLocation();
        _weight = n.getWeight();
        _info = n.getInfo();
        _tag = n.getTag();
    }
    @Override
    public int getKey() {
        return _myID;
    }

    @Override
    public Point3D getLocation() {
        return _location;
    }

    @Override
    public void setLocation(Point3D p) {
        _location = new Point3D(p);
    }

    @Override
    public double getWeight() {
        return _weight;
    }

    @Override
    public void setWeight(double w) {
        _weight = w;

    }

    @Override
    public String getInfo() {
        return _info;
    }

    @Override
    public void setInfo(String s) {
        _info = s;
    }

    @Override
    public int getTag() {
        return _tag;
    }

    @Override
    public void setTag(int t) {
        _tag = t;
    }
    public Edata getEdata(int dest){
        if (EMap.containsKey(dest)) {
            return EMap.get(dest);
        }
        return null;
    }
    public void put(int des, Edata edata){
        EMap.put(des,edata);
    }
    public Collection<edge_data> getE() {
        List<edge_data> list = new ArrayList<>(EMap.values());
        return list;
    }

    public void setE(Collection<edge_data> E) {
        List<edge_data> list =new LinkedList<>(E);
        for (edge_data i : list) {
            Edata edata = new Edata(i);
            EMap.put(edata.getDest(),edata);
        }
    }
    public Edata removeEdge(int des){
        if (EMap.containsKey(des))
            return EMap.remove(des);
        return null;
    }
    public static int getIDMAX(){
        return _ID;
    }

    public void set_tagFolow(int _tagFolow) {
        this._tagFolow = _tagFolow;
    }

    public int get_tagFolow() {
        return _tagFolow;
    }
}
