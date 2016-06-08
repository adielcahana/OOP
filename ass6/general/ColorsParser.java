package general;

import java.awt.Color;

public class ColorsParser {

    public Color colorFromString(String line){
        Color color;
        if(line.startsWith("color(RGB)"))
        {
            String cutLine = line.substring(10);
            String[] splitCut = cutLine.split(")");
            String[] colors = splitCut[0].split(",");
            int R = Integer.parseInt(colors[0]);
            int G = Integer.parseInt(colors[1]);
            int B = Integer.parseInt(colors[2]);
            return new Color(R, G, B); 
        }
        String cutLine = line.substring(6);
        String colorLine = cutLine.substring(0, cutLine.length() - 1);  
        try {
            java.lang.reflect.Field field = Color.class.getField(colorLine);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; 
        }
        return color;
    }
}