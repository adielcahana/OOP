package general;

import java.awt.Color;

import score.SerializationException;

public class ColorsParser {

    public Color colorFromString(String line) throws SerializationException{
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
        switch (colorLine) {
        case "black":
            color = Color.black;
            break;
        case "blue":
            color = Color.blue;
            break;
        case "cyan":
            color = Color.cyan;
            break;     
        case "gray":
            color = Color.gray;
            break;  
        case "lightGray":
            color = Color.lightGray;
            break;
        case "green":
            color = Color.green;
            break;
        case "orange":
            color = Color.orange;
            break;
        case "pink":
            color = Color.pink;
            break;
        case "red":
            color = Color.red;
            break;
        case "white":
            color = Color.white;
            break;
        case "yellow":
            color = Color.yellow;
            break;
        default:
            throw new SerializationException(" Color not exist.");
        }
        return color;
    }
}