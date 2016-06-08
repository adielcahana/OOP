package general;

import java.security.Policy.Parameters;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import java.util.Map.Entry;

import gameobjects.Block;
import score.SerializationException;

public class BlocksFromSymbolsFactory {


    private Map<String, Integer> spacerWidths;
    private Map<String, BlocksCreator> blockCreators;

    // returns true if 's' is a valid space symbol.
    public boolean isSpaceSymbol(String s) {
    }
    // returns true if 's' is a valid block symbol.
    public boolean isBlockSymbol(String s) {

    }

    // Return a block according to the definitions associated
    // with symbol s. The block will be located at position (xpos, ypos).
    public Block getBlock(String s, int xpos, int ypos) {

    }

    // Returns the width in pixels associated with the given spacer-symbol.
    public int getSpaceWidth(String s){

    }

    public void setBlockCreators(Map<String, String> defaultDef, TreeMap<String, String> blockDef) throws SerializationException{
        Map<String,String> parameters = new TreeMap<String,String>();
        parameters.put("symbol", null);
        parameters.put("width", null);
        parameters.put("hight", null);
        parameters.put("hitPoints", null);
        parameters.put("stroke", "");
        parameters.put("fill", null);
        parameters.putAll(defaultDef);

        Set<Entry<String, String>> values = parameters.entrySet();
        Iterator<Entry<String, String>> i = values.iterator();
        Entry<String, String> value = i.next();
        parameters.put("symbol", value.getKey());
        while (i.hasNext()) {
            List <String> fills = new ArrayList<String>();
            value = i.next();
            String line = value.getValue();
            String[] parts = line.split(" ");
            for (int j = 0; j < parts.length; j++){
                String[] parts1 = parts[j].split(":");
                switch (parts1[0]) {
                case "width":
                    parameters.put("width", parts1[1]);
                    break;
                case "hight":
                    parameters.put("hight", parts1[1]);
                    break;
                case "hitPoints":
                    parameters.put("hitPoints", parts1[1]);
                    break;
                case "stroke":
                    parameters.put("stroke", parts1[1]);
                default:
                    if (parts1[0].contains("fill")) {
                        fills.add(parts1[0] + parts1[1]);
                    }
                    else{
                        throw new SerializationException("Wrong parameters");
                    }
                    break;
                }
            }
            if(parameters.containsValue(null)){
                throw new SerializationException("Not enough parameters");
            }
            BlocksCreator block = new BlocksCreator(parameters, fills);
            blockCreators.put(parameters.get("symbol"), block);
        }
    }

    public void setSpaceCreators(Map<String, String> spaceDef) throws SerializationException{
        Map<String,Integer> parameters = new TreeMap<String,Integer>();
        Set<Entry<String, Integer>> values = parameters.entrySet();
        Iterator<Entry<String, Integer>> i = values.iterator();
        Entry<String, Integer> value = i.next();
        while (i.hasNext()) {
            value = i.next();
            spacerWidths.put(value.getKey(), value.getValue());
        }

    }




}