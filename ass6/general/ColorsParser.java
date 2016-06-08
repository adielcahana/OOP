package general;

import java.awt.Color;

public class ColorsParser {

    public java.awt.Color colorFromString(String s){
        Color color;
        try {
            java.lang.reflect.Field field = Color.class.getField(s);
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; 
        }
    return color;
    }
}