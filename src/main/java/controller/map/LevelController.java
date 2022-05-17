package controller.map;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import util.Vector;
import util.map.TextMap;

public class LevelController {

	final List<String> segmentTextList;
	final List<MapController> mapControllerList = new LinkedList<>();
	
	public LevelController(List<String> segmentTextList) throws IOException {
		this.segmentTextList = segmentTextList;
		double i = 0;
		for(String segmentText : segmentTextList) {
			TextMap tempMap = new TextMap(segmentText);
			mapControllerList.add(new MapController(tempMap, i));
			i += tempMap.getWidth();
		}
	}
	
	public List<MapController> getMapControllers(){
		return this.mapControllerList;
	}
	
	public MapController getController(Vector position) {
		double i = 0;
		for(MapController mapTemp : this.getMapControllers()) {
			if(position.getX() >= i && position.getX() < i + mapTemp.getTextMap().getWidth()) {
				return mapTemp;
			}
			i += mapTemp.getTextMap().getWidth(); 			
		}
		return null;
	}
	
	public Vector getPlayerSpawn() {
		for(MapController mapTemp : this.getMapControllers()) {
			if(mapTemp.getPlayerSpawn() != null) {
				return mapTemp.getPlayerSpawn();
			}
		}
		return null;
	}

	
}
