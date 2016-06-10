package general;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import gameobjects.Block;
import score.SerializationException;

public class BlocksFromSymbolsFactory {


    private Map<String, Integer> spacerWidths = new TreeMap<String, Integer>();
    private Map<String, BlocksCreator> blockCreators= new TreeMap<String, BlocksCreator>();

    public BlocksFromSymbolsFactory(Map<String, String> defaultDef, Map<String, String> blockDef,
            Map<String, Integer> spaceDef) throws SerializationException {
        setSpaceCreators(spaceDef);
        setBlockCreators(defaultDef, blockDef);
    }

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
        return spacerWidths.containsKey(s);
    }
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {
        return blockCreators.containsKey(s);
    }

    public int getBlockWidth(String s){
        return blockCreators.get(s).getWidth();
    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s){
        return spacerWidths.get(s);
    }

    public void setBlockCreators(Map<String, String> defaultDef, Map<String, String> blockDef) throws SerializationException{
        Map<String,String> parameters = new TreeMap<String,String>();
        parameters.put("symbol", null);
        parameters.put("width", null);
        parameters.put("height", null);
        parameters.put("hit_points", null);
        parameters.put("stroke", "");
        parameters.putAll(defaultDef);

        Set<Entry<String, String>> values = blockDef.entrySet();
        Iterator<Entry<String, String>> i = values.iterator();
        Entry<String, String> value = i.next();
        parameters.put("symbol", value.getKey());
        while (i.hasNext()) {
            Map <Integer,String> fills = new TreeMap <Integer,String>();
            value = i.next();
            String line = value.getValue();
            String[] parts = line.split(" ");
            for (int j = 0; j < parts.length; j++) {
                String[] parts1 = parts[j].split(":");
                switch (parts1[0]) {
                case "width":
                    parameters.put("width", parts1[1]);
                    System.out.println(parameters.get("width"));
                    break;
                case "height":
                    parameters.put("height", parts1[1]);
                    System.out.println(parameters.get("height"));
                    break;
                case "hit_points":
                    parameters.put("hit_points", parts1[1]);
                    System.out.println(parameters.get("hit_points"));
                    break;
                case "stroke":
                    parameters.put("stroke", parts1[1]);
                    System.out.println(parameters.get("stroke"));
                    break;
                default:
                    if (parts1[0].contains("fill")) {
                        String fillNum = null;
                        if(parts1[0].length() > 4) {
                            fillNum = parts1[0].substring(5);
                        } else {
                            fillNum = "1";
                        }
                        fills.put(Integer.parseInt(fillNum), parts1[1]);
                    } else {
                        throw new SerializationException("Wrong parameters");
                    }
                    break;
                }
            }
            if(parameters.containsValue(null)){
                throw new SerializationException("Not enough parameters");
            }
            BlocksCreator block = new BlocksCreator(parameters, fills);
            blockCreators.put(value.getKey(), block);
        }
    }

    public void setSpaceCreators(Map<String, Integer> spaceDef) throws SerializationException{
        Set<Entry<String, Integer>> values = spaceDef.entrySet();
        Iterator<Entry<String, Integer>> i = values.iterator();
        Entry<String, Integer> value = i.next();
        while (i.hasNext()) {
            value = i.next();
            spacerWidths.put(value.getKey(), value.getValue());
        }
    }
}