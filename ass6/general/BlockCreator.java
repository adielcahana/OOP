package general;

import gameobjects.Block;

public interface BlockCreator{
    Block create(int xpos, int ypos);
}
