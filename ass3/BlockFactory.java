import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class BlockFactory {
	private Point lowerFrameEdge;
    private Point upperFrameEdge;
    
    public BlockFactory(Point lowerFrameEdge, Point upperFrameEdge) {
		this.lowerFrameEdge = lowerFrameEdge;
		this.upperFrameEdge = upperFrameEdge;
	}

    public Block create(Point upperLeft, int maxHits, Color color) {
        return new Block(upperLeft, 50, 20, maxHits, color);
    }

    public List createBlockRaw(Point start, int numOfBlocks, int maxHits, Color color) {
    	List BlockList = new ArrayList();
    	Velocity velocity = new Velocity(50, 0);
        for (int i = 0; i < numOfBlocks; i++) {
        	BlockList.add(this.create(start, maxHits, color));
        	start = velocity.applyToPoint(start);
        }
        return BlockList;
    }

    public List createBlockRaw(Point start, int maxHits, Color color) {
    	List BlockList = new ArrayList();
    	int width = (int) (this.lowerFrameEdge.getX() - this.upperFrameEdge.getX()) - 20;
    	int numOfBlocks =  (int) ((width  - start.getX()) / 50);
    	BlockList = createBlockRaw(start, numOfBlocks, maxHits, color);
        return BlockList;
    }
}
