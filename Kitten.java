public class Kitten implements Contract {

    /** What item is being acted on */
    private String activeItem;
    
    /** Name of kitten */
    private String name;

    /** Size of kitten */
    private int size;

    /** Represents if lastAction is reversible */
    private boolean isReversible;

    /** Name of the last method called */
    private String lastAction;

    /**
     * Constructor for Kitten class
     * @param name The name of the kitten
     */
    public Kitten(String name){
        this.name = name;
        size = 0;
        activeItem = "Nothing";
        lastAction = "Nothing";
        
    }

    /**
     * Picks up an item.
     * @param item The item to be picked up.
     */
    public void grab(String item){
        activeItem = item;
        lastAction = "grab";
        isReversible = true;
        System.out.println("Holding "+ item);
       
    }

    /**
     * Accessor for activeItem 
     * @return Returns activeItem
     */
    public String getActiveItem(){
        return activeItem;
    }

    /**
     * Drops an item, if the active item is not nothing
     * @param item The item to be dropped
     * @return Returns the item 
     */
    public String drop(String item){
        if (!activeItem.equals(item)){
            isReversible = false;
            throw new RuntimeException("Not holding "+ item); 
        } else {
            System.out.println("Dropped " + item);
            lastAction = "drop";
            isReversible = true;
        }
        return item;

    }

    /** 
     * Examines an item
     * @param item The item to be examined
     */
    public void examine(String item){
        System.out.println("Looking at " + item);
        lastAction = "examine";
        isReversible = false;
        
    }

    /**
     * Uses an item if it is being held.
     * @param item The item to be used
     */
    public void use(String item){
        if (!activeItem.equals(item)){
            throw new RuntimeException("Not holding "+ item); 
        } else {
            System.out.println("Used " + item);
            lastAction = "use";
        } 
        isReversible = false;

    }

    /**
     * Walks in a given direction.
     * @param direction The direction to be walked in
     * @return Returns true if walking is successful, false otherwise
     */
    public boolean walk(String direction){
        System.out.println("Walking " + direction);
        lastAction = "walk";
        return true;
    }

    /**
     * Flies to a location
     * @param x The minutes the kitten is flying for.
     * @param y The seconds the kitten is flying for.
     * @return Returns true if flying is successful, false otherwise.
     */
    public boolean fly(int x, int y){
        if (!(x >= 0 && y >= 0)) { 
            System.out.println("Flying back towards the start");
            isReversible = false;
            return false;
        } else {
            System.out.println("Flew away for " + x + " minutes and "+ y + "seconds." );
            lastAction = "fly";
            isReversible = true;
            return true;
        }
    }

    /**
     * Stops user from shrinking the kitten.
     * @return Returns a Number representing the size of the kitten
     */
    public Number shrink(){
        System.out.println("Kittens don't shrink...");
        isReversible = false;
        return -1;
    }

    /**
     * Grows the kitten
     * @return Returns a Number representing the size of the kitten.
     */
    public Number grow(){
        System.out.println(name + "grew");
        isReversible = false;
        lastAction = "grow";
        return size + 1;

    }


    /**
     * Rests kitten
     */
    public void rest(){
        System.out.println(name + " is resting...");
        isReversible = false;
        lastAction = "rest";

    }

    /**
     * Attempts to undo an action.
     */
    public void undo(){
        if (!isReversible){
            throw new RuntimeException("Can't un"+lastAction +".");
        } else { //The only options that can be undone are drop, grab, walk, and fly
            if (lastAction.equals("drop")){
                this.grab(activeItem);
            } else if (lastAction.equals("grab")){
                this.drop(activeItem);
            } else if (lastAction.equals("walk")){
                this.walk("back to the start");
            } else {
                this.fly(-1,-1);
            }
        }
    }

    public static void main(String[] args) {
        Kitten kitten = new Kitten("Kitty");
        //kitten.undo();
        kitten.grab("fish");
        kitten.undo();
        kitten.fly(10, 2);
        kitten.undo();
    }
}