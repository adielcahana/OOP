package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.TreeMap;

import score.SerializationException;

public class BlockDefinitionsReader {

    public static BlocksFromSymbolsFactory fromReader(Reader reader) throws IOException {

        Map <String,String> defaultDef = new TreeMap<String, String>();
        Map <String,String> blockDef = new TreeMap<String, String>();
        Map <String,Integer> spaceDef = new TreeMap<String, Integer>(); 
        BufferedReader bufferReader = new BufferedReader(reader);
        try {
            String line = null;
            while (( line = bufferReader.readLine ()) != null ){
                if(line.isEmpty() || line.startsWith("#")){
                    continue;
                }
                String[] parts = line.split(" ");
                switch (parts[0]) {
                case "default":
                    for (int i = 1; i < parts.length; i++) {
                        String[] parts2 = parts[i].split(":");
                        defaultDef.put(parts2[0], parts2[1]);       
                    }
                    break;
                case "bdef":
                    String[] parts2 = parts[1].split(":");
                    if(parts2[0].equals("symbol") && parts2[1].length() == 1){
                        blockDef.put(parts2[1], line.substring(14));
                    }
                    else{
                        throw new SerializationException();
                    }
                    break;

                case "sdef":

                    String[] parts3 = parts[1].split(":"); 
                    if(parts3[0].equals("symbol") && parts3[1].length() == 1){
                        Integer value = Integer.parseInt(parts[2].split(":")[1]);
                        spaceDef.put(parts3[1], value);
                    }
                    else{
                        throw new SerializationException();
                    }
                    break;

                default:
                    break;
                }
            }
            try{
                return new BlocksFromSymbolsFactory(defaultDef, blockDef, spaceDef);    
            } catch (SerializationException e) {
                throw e;
            }
        }catch (SerializationException e){
            throw e;
            
        }
    

    }
}
