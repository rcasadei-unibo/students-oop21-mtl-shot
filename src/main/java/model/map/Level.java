package model.map;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import util.Vector2D;
import util.map.TextMap;

public class Level {

	final List<String> segmentTextList;
	final List<Segment> segments = new LinkedList<>();
	
	public Level(List<String> segmentTextList) throws IOException {
		this.segmentTextList = segmentTextList;
		double i = 0;
		for(String segmentText : segmentTextList) {
			TextMap tempMap = new TextMap(segmentText);
			segments.add(new Segment(tempMap, i));
			i += tempMap.getWidth();
		}
	}
	
	public List<Segment> getSegments(){
		return this.segments;
	}
	
	public Segment getSegmentAtPosition(Vector2D position) {
		double i = 0;
		for(Segment tempSegment : this.getSegments()) {
			if(position.getX() >= i && position.getX() < i + tempSegment.getTextMap().getWidth()) {
				return tempSegment;
			}
			i += tempSegment.getTextMap().getWidth(); 			
		}
		return null;
	}
	
	public Vector2D getPlayerSpawn() {
		for(Segment tempSegment : this.getSegments()) {
			if(tempSegment.getPlayerSpawn() != null) {
				return tempSegment.getPlayerSpawn();
			}
		}
		return null;
	}

	
}

