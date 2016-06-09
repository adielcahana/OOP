package general;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import score.SerializationException;

public class BlocksCreator implements BlockCreator {

    private int width;
    private int height;
    private int hitPoints;
    private Map <Integer, String> fill;
    private Color stroke;

    public BlocksCreator(Map<String, String> block, Map<Integer, String> fills) throws SerializationException{
        this.width = Integer.parseInt(block.get("width"));
        this.height = Integer.parseInt(block.get("height"));
        this.hitPoints = Integer.parseInt(block.get("hit_points"));
        setStroke(block.get("stroke"));  
        this.fill = new TreeMap<Integer, String>();
        fill.putAll(fills);
    }

    @Override
    public Block create(int xpos, int ypos) {
        Point point = new Point(xpos, ypos);
        return new Block(new Rectangle(point, width, height), hitPoints, stroke, fill);
    }
    
    public int getWidth(){
        return this.width;
    }
    
    public void setStroke(String color) throws SerializationException{
        ColorsParser colorParser = new ColorsParser();
        this.stroke = colorParser.colorFromString(color);
    }
}

