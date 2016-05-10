
/**
* @author Adiel cahana <adiel.cahana@gmail.com>
* @version 1.0
* @since 2016-05-10 */
public class Counter {
    private int count;
    /**
     * Counter constructor.
     * <p>
     * empty constructor, initialized to 0*/
    public Counter(){
        this.count = 0;
    }
    /**
     * Counter constructor.
     * <p>
     * @param number - counter initialization */
    public Counter(int number){
        this.count = number;
    }
    
    /**
     * increase the counter by a given integer.
     * <p>
     * @param number - the increase amount*/
    void increase(int number) {
        count+=number;     
    }
    /**
     * decrease the counter by a given integer.
     * <p>
     * @param number - the decrease amount*/
    void decrease(int number) {
         count-=number;   
    }
    /**
     * get the counter state.
     * <p>
     * @return count - the counter amount*/
    int getValue(){
        return this.count;
    }
}