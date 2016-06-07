package general;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class BlockDefinitions {

    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blockCreators;
    
    public static BlockDefinitions fromReader(Reader reader) throws IOException {

        BlockDefinitions blockDefinitions;
        BufferedReader bufferReader = new BufferedReader(reader);
        try {
            blockDefinitions = new BlockDefinitions();
            String line = null;
            while (( line = bufferReader.readLine ()) != null ){
                
                
            }




                return blockDefinitions;
        }
    }
}
