package dataStructure;

import java.io.Serializable;
import java.util.*;

public class DGraph implements graph, Serializable {
	private HashMap<Integer,node_data> NMap = new LinkedHashMap<>();
	private HashMap<Integer,HashMap<Integer,edge_data>> EMap = new LinkedHashMap<>();
	private int _EdgeZise = 0;
	private int _MC=0;

	public DGraph(){
    }
	public DGraph(graph g){
        Collection<node_data> tempV = g.getV();

		for (dataStructure.node_data node_data : tempV) {
			NodeData nodeData = new NodeData(node_data);
			NMap.put(nodeData.getKey(), nodeData);
			Collection<edge_data> tempE = g.getE(node_data.getKey());
			if(tempE!=null) {
				for (dataStructure.edge_data edge_data : tempE) {
					Edata edata = new Edata(edge_data);
					if (!EMap.containsKey(edata.getSrc())) {
						HashMap<Integer, edge_data> tempHASH = new LinkedHashMap<>();
						tempHASH.put(edata.getDest(), edata);
						EMap.put(edata.getSrc(), tempHASH);
					} else {
						EMap.get(edata.getSrc()).put(edata.getDest(), edata);
					}
				}
			}
			else {
				HashMap<Integer, edge_data> tempHASH = new LinkedHashMap<>();
				EMap.put(nodeData.getKey(), tempHASH);

			}
		}

    }

	@Override
	public node_data getNode(int key) {
		if (NMap.containsKey(key))
			return NMap.get(key);
		return null;
	}

	@Override
	public edge_data getEdge(int src, int dest) {
		if (EMap.containsKey(src))
			if (EMap.get(src).containsKey(dest))
				return EMap.get(src).get(dest);
		return null;
	}

	@Override
	public void addNode(node_data n) {
		if (!NMap.containsKey(n.getKey())) {
			NMap.put(n.getKey(), new NodeData(n));
			HashMap<Integer,edge_data> temp = new LinkedHashMap<>();
			EMap.put(n.getKey(),temp);
			_MC++;
		}
	}

	@Override
	public void connect(int src, int dest, double w) {
		if (!(NMap.containsKey(dest)&&NMap.containsKey(src)))
			throw new RuntimeException("not Exist");
		edge_data edata = new Edata(src,dest,w);
		if (EMap.containsKey(src)){
			EMap.get(src).put(dest,edata);
		}else {
			HashMap<Integer,edge_data> temp = new LinkedHashMap<>();
			temp.put(dest,edata);
			EMap.put(src,temp);
		}
		_EdgeZise++;
		_MC++;
		
	}

	@Override
	public Collection<node_data> getV() {
		return NMap.values();
	}

	@Override
	public Collection<edge_data> getE(int node_id) {
		if (!NMap.containsKey(node_id)) {
			return null;
		}
		if (!EMap.containsKey(node_id)) {
			return null;
		}
		return EMap.get(node_id).values();
	}

	@Override
	public node_data removeNode(int key) {
		if (NMap.containsKey(key)){
			_MC++;
			for (int i =0; i<NMap.size();i++){
				if (NMap.containsKey(i)){
					removeEdge(i,key);
				}
			}
			return NMap.remove(key);
		}
		else return null;

	}

	@Override
	public edge_data removeEdge(int src, int dest) {
		if (!(NMap.containsKey(src)&&NMap.containsKey(dest))){
			return null;
		}
		_MC++;
		if (EMap.containsKey(src)) {
			edge_data temp = EMap.get(src).remove(dest);

			if (temp != null) {
				_EdgeZise--;
			}
			return temp;
		}
		return null;
	}

	@Override
	public int nodeSize() {
		return NMap.size();
	}

	@Override
	public int edgeSize() {
		return _EdgeZise;
	}

	@Override
	public int getMC() {
		return _MC;
	}



}
