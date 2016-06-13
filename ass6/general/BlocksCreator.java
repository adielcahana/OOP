package general;

import java.awt.Color;

import java.util.Map;
import java.util.TreeMap;

import gameobjects.Block;
import geometry.Point;
import geometry.Rectangle;
import score.SerializationException;

/**
 * @author Ori Engelberg <turht50@gmail.com>
 * @version 1.0
 * @since 2016-06-8 */
public class BlocksCreator implements BlockCreator {

    private int width;
    private int height;
    private int hitPoints;
    private Map<Integer, String> fill;
    private Color stroke;

    /** BlocksCreator constructor - give the block all his parameters.
     * <p>
     * @param block - a map with the width, height, hit points and stroke of the block.
     * @param fills - map of the fills of the block.
     * @throws SerializationException if failed read stroke color. */
    public BlocksCreator(Map<String, String> block, Map<Integer, String> fills) throws SerializationException {
        this.width = Integer.parseInt(block.get("width"));
        this.height = Integer.parseInt(block.get("height"));
        this.hitPoints = Integer.parseInt(block.get("hit_points"));
        setStroke(block.get("stroke"));
        this.fill = new TreeMap<Integer, String>();
        fill.putAll(fills);
    }

    @Override
    /**
     * Create a new block.
     * <p>
     * @param xpos - the x value of the upper left point of the block.
     * @param ypos - the y value of the upper left point of the block.
     * @return new block.
     * */
    public Block create(int xpos, int ypos) {
        Point point = new Point(xpos, ypos);
        return new Block(new Rectangle(point, width, height), hitPoints, stroke, fill);
    }

    /**
     * @return the block width. */
    public int getWidth() {
        return this.width;
    }

    /**
     * Set the stroke color from a string.
     * <p>
     * @param color - the color name.
     * @throws SerializationException if failed read stroke color. */
    public void setStroke(String color) throws SerializationException {
        if (color.isEmpty()) {
            this.stroke = Color.BLACK;
            return;
        }
        ColorsParser colorParser = new ColorsParser();
        this.stroke = colorParser.colorFromString(color);
    }
}

