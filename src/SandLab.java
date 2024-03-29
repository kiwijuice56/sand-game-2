// Eric Alfaro
// 2021/10/28
import java.awt.Color;

public class SandLab {
    public static void main(String[] args) {
        SandLab lab = new SandLab(150, 240);
        lab.run();
    }
    
    // Material initialization
    
    // Number represents ID and density; lower = less dense
    public static final int EXPLOSION = 37;     // Causes chain destruction reactions
    public static final int FUNGUS = 36;        // Grows upwards from germinated spores
    public static final int DIAMOND = 35;       // Most stable material
    public static final int LAVA_TAP = 34;      // Spawns lava
    public static final int MERCURY_TAP = 33;   // Spawns mercury
    public static final int ACID_TAP = 32;      // Spawns acid
    public static final int BLUE_FIRE_TAP = 31; // Spawns blue fire
    public static final int FIRE_TAP = 30;      // Spawns fire
    public static final int WATER_TAP = 29;     // Spawns water
    public static final int ACID_GAS_TAP = 28;     // Spawns water
    public static final int METAL = 27;         // Second most stable material, can be exploded
    public static final int SLIME = 26;         // Grows downwards, or in all directions containing acid
    public static final int TNT = 25;           // Explodes when touched by fire or lava
    public static final int ROCK = 24;          // Stable material but it may be dissolved and weathered down
    public static final int GLASS = 23;         // Created from sand, magic or normal
    public static final int LAVA = 22;          // Dissolves material but cools into rock or fire
    public static final int DIRT = 21;          // Supports spore germination
    public static final int PENGUIN = 20;       // Grows in sand
    public static final int SAND_DUCK = 19;     // Grows in sand
    public static final int MAGIC_SAND = 18;    // Falls sideways
    public static final int SAND = 17;          // Most basic falling element, can be smelted into glass
    public static final int MERCURY = 16;       // Destroys all life materials
    public static final int ALGAE = 15;         // Grows in water
    public static final int SPORE = 14;          // Germinates in presence of water and dirt
    public static final int URCHIN = 13;       // Spreads in water without completely overtaking it
    public static final int POLLIWOG = 12;       // Spreads in water without completely overtaking it
    public static final int FAIRY = 11;          // Purifies acid, TNT, slime, fire, and lava
    public static final int ICE = 10;            // Creates crystals in water
    public static final int WATER = 9;          // Basic fluid, supports many life materials but can evaporate
    public static final int SNOW = 8;           // Falls slowly and melts to water
    public static final int ALCOHOL = 7;           // Dissolves almost everything
    public static final int ACID = 6;           // Dissolves almost everything
    public static final int BLUE_FIRE = 5;      // Similar to fire but lasts longer
    public static final int FIRE = 4;           // Burns fungus, algae and spores
    public static final int HYDROGEN_GAS = 3;
    public static final int ACID_GAS = 2;
    public static final int GAS = 1;            // Goes upwards before disappearing
    public static final int EMPTY = 0;          // Eraser
    public static final int ALL = -1;           // Code-use only; describes case where material type is irrelevant 

    // Control rate of random reactions in particles
    public static final double GAS_DECAY_CHANCE = 0.025;
    public static final double LAVA_DECAY_CHANCE = 0.0005;
    public static final double LAVA_COOL_CHANCE = 0.0002;
    public static final double ALGAE_GROW_CHANCE = 0.0025;
    public static final double ALGAE_DIE_CHANCE = 0.00025;
    public static final double SLIME_GROW_CHANCE = 0.0035;
    public static final double SLIME_DIE_CHANCE = 0.00025;
    public static final double EXPLOSION_DECAY_CHANCE = 0.04;
    public static final double ACID_GAS_DECAY_CHANGE = 0.001;
    public static final double HYDROGEN_GAS_DECAY_CHANCE = 0.0005;
    public static final double FIRE_DECAY_CHANCE = 0.025;
    public static final double BLUE_FIRE_DECAY_CHANCE = 0.0065;
    public static final double ROCK_WEATHER_CHANCE = 0.0025;
    public static final double ROCK_MELT_CHANCE = 0.0025;
    public static final double METAL_MELT_CHANCE = 0.0005;
    public static final double SPORE_GROW_CHANCE = 0.0025;
    public static final double FUNGUS_GROW_CHANCE = 0.0025;
    public static final double POLLIWOG_GROW_CHANCE = 0.05;
    public static final double URCHIN_GROW_CHANCE = 0.02;
    public static final double SAND_DUCK_GROW_CHANCE = 0.01;
    public static final double FAIRY_GROW_CHANCE = 0.05;
    public static final double SNOW_FALL_CHANCE = 0.25;
    public static final double SNOW_MELT_CHANCE = 0.0001;
    public static final double TAP_SPAWN_CHANCE = 0.015;
    public static final double WATER_FREEZE_CHANCE = 0.01;
    public static final double PENGUIN_GROW_CHANCE = 0.003;
    
    // Other game fields
    private final int[][] grid;
    private final SandDisplay display;
  
    // Constructs UI labels
    public SandLab(int numRows, int numCols) {
        String[] names;
        names = new String[36];
        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[MAGIC_SAND] = "Magic Sand";
        names[WATER] = "Water";
        names[ALGAE] = "Algae";
        names[SLIME] = "Slime";
        names[MERCURY] = "Mercury";
        names[ACID] = "Acid";
        names[GAS] = "Gas";
        names[LAVA] = "Lava";
        names[ROCK] = "Rock";
        names[TNT] = "TNT";
        names[FIRE] = "Fire";
        names[BLUE_FIRE] = "Blue Fire";
        names[DIRT] = "Dirt";
        names[SPORE] = "Spore";
        names[FAIRY] = "Fairy";
        names[SNOW] = "Snow";
        names[WATER_TAP] = "Water Tap";
        names[FIRE_TAP] = "Fire Tap";
        names[BLUE_FIRE_TAP] = "Blue Fire Tap";
        names[PENGUIN] = "Penguin";
        names[DIAMOND] = "Diamond";
        names[ALCOHOL] = "Alcohol";
        names[HYDROGEN_GAS] = "Hydrogen";
        names[GLASS] = "Glass";
        names[POLLIWOG] = "Polliwog";
        names[ICE] = "Ice";
        names[LAVA_TAP] = "Lava Tap";
        names[ACID_TAP] = "Acid Tap";
        names[ACID_GAS] = "Acid Gas";
        names[ACID_GAS_TAP] = "Acid Gas Tap";
        names[SAND_DUCK] = "Sand Duck";
        names[MERCURY_TAP] = "Mercury Tap";
        names[URCHIN] = "Urchin";
        display = new SandDisplay("Cypress Ranch Lab: Falling Sand", numRows, numCols, names);
        grid = new int[numRows][numCols];
    }
  
    // Main methods
    
    // Called when the user clicks on a location using the given tool
    private void locationClicked(int row, int col, int tool, int size) {
        for (int i = row-(size/2); i < row+(size/2)+1; i++){
           for (int j = col-(size/2); j < col+(size/2)+1; j++){ 
               if (inBounds(i, j) && (tool == EMPTY || tool > grid[i][j]))
                    grid[i][j] = tool;
           }
        }
    }

    // Copies each element of grid into the display
    public void updateDisplay() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                // Colors for each material
                switch (grid[i][j]) {
                    case SAND, MAGIC_SAND -> display.setColor(i, j, new Color(252, 161, 3));
                    case EMPTY -> display.setColor(i, j, new Color(16, 8, 32));
                    case WATER -> {
                        double x = ((j + i + Math.sin(j / 6.0) * 4  + System.currentTimeMillis() / 90.0) % 64) / (double) 64;
                        x = Math.abs(0.5 - x);
                        double time = Math.cos(x * Math.PI * 2);
                        time = Math.abs(0.5 - Math.abs(time));
                        display.setColor(i, j, new Color((int) ((45 * time) + (25 * (1 - time))), (int) ((80 * time) + (125 * (1 - time))), (int) ((215 * time) + (255 * (1 - time)))));
                    }
                    case ALCOHOL -> {
                        double x = ((j + i + Math.sin(j / 6.0) * 4  + System.currentTimeMillis() / 128.0) % 64) / (double) 64;
                        x = Math.abs(0.5 - x);
                        double time = Math.cos(x * Math.PI * 2);
                        time = Math.abs(0.5 - Math.abs(time));
                        display.setColor(i, j, new Color((int) ((200 * time) + (150 * (1 - time))), (int) ((200 * time) + (150 * (1 - time))), (int) ((200 * time) + (150 * (1 - time)))));
                    }
                    case HYDROGEN_GAS -> {
                        double x = touchCount(i, j, HYDROGEN_GAS) /  8.0;
                        display.setColor(i, j, new Color((int) ((255 * x) + (200 * (1 - x))), (int) ((255 * x) + (200 * (1 - x))), (int) ((255 * x) + (200 * (1 - x)))));
                    }
                    case GAS -> {
                        double x = touchCount(i, j, GAS) /  8.0;
                        display.setColor(i, j, new Color((int) ((255 * x) + (150 * (1 - x))), (int) ((255 * x) + (150 * (1 - x))), (int) ((255 * x) + (150 * (1 - x)))));
                    }
                    case ACID_GAS -> {
                        double x = touchCount(i, j, ACID_GAS) /  8.0;
                        display.setColor(i, j, new Color((int) ((255 * x) + (150 * (1 - x))), (int) ((255 * x) + (100 * (1 - x))), (int) ((255 * x) + (150 * (1 - x)))));
                    }
                    case ALGAE -> display.setColor(i, j, new Color(44, 145, 41));
                    case ACID -> {
                        double x = ((j + i + Math.sin(j / 6.0) * 4 + System.currentTimeMillis() / 180.0) % 64) / (double) 64;
                        x = Math.abs(0.5 - x);
                        double time = Math.cos(Math.sin(i / 6.0) * x * Math.PI * 2);
                        time = 1.0 - Math.abs(0.5 - Math.abs(time));
                        display.setColor(i, j, new Color((int) ((245 * time) + (125 * (1 - time))), (int) ((66 * time) + (21 * (1 - time))), (int) ((191 * time) + (163 * (1 - time)))));
                    }
                    case LAVA -> {
                        double x = ((j + i + Math.sin(j / 6.0) * 4 + System.currentTimeMillis() / 256.0) % 86) / (double) 86;
                        x = Math.abs(0.5 - x);
                        double time = Math.cos(x * Math.PI * 2);
                        time = Math.abs(0.5 - Math.abs(time));
                        display.setColor(i, j, new Color((int) (255 * time + 217 * (1 - time)), (int) (162 * time), (int) ((14 * (1 - time)))));
                    }
                    case TNT ->  {
                        if (j % 4 == 0)
                            display.setColor(i, j, new Color(150, 5, 36));
                        else
                            display.setColor(i, j, new Color(217, 39, 26));
                    }
                    case FIRE -> {
                        double x = touchCount(i, j, FIRE) /  8.0;
                        display.setColor(i, j, new Color((int) ((255 * x) + (232 * (1 - x))), (int) ((200 * x) + (35 * (1 - x))), (int) ((0 * x) + (5 * (1 - x)))));
                    }
                    case BLUE_FIRE -> {
                        double x = touchCount(i, j, BLUE_FIRE) /  8.0;
                        display.setColor(i, j, new Color((int) ((51 * x) + (66 * (1 - x))), (int) ((194 * x) + (13 * (1 - x))), (int) ((255 * x) + (224 * (1 - x)))));
                    }
                    case EXPLOSION -> {
                        double x = touchCount(i, j, EXPLOSION) /  8.0;
                        display.setColor(i, j, new Color((int) ((255 * x) + (232 * (1 - x))), (int) ((200 * x) + (35 * (1 - x))), (int) ((0 * x) + (5 * (1 - x)))));
                    }
                    case DIRT -> display.setColor(i, j, new Color(130, 56, 25));
                    case SLIME -> display.setColor(i, j, new Color(37, 26, 161));
                    case SPORE -> display.setColor(i, j, new Color(125, 125, 55));
                    case FUNGUS -> display.setColor(i, j, new Color(155, 155, 75));
                    case FAIRY -> display.setColor(i, j, new Color(255, 143, 248));
                    case SNOW -> display.setColor(i, j, new Color(245, 245, 255));
                    case ICE -> {
                        double x = ((j + i) % 64) / (double) 64;
                        x = Math.abs(0.5 - x);
                        double time = Math.cos(x * Math.PI * 2);
                        time = Math.abs(0.5 - time);
                        display.setColor(i, j, new Color((int) ((85 * time) + (64 * (1 - time))), (int) ((145 * time) + (175 * (1 - time))), (int) ((245 * time) + (255 * (1 - time)))));
                    }
                    case DIAMOND -> display.setColor(i, j, new Color(225 - i, 235 - i, 245 - i));
                    case MERCURY -> display.setColor(i, j, new Color(215 - i, 215 - i, 215 - i));
                    case POLLIWOG -> display.setColor(i, j, new Color(122, 235, 250));
                    case SAND_DUCK -> display.setColor(i, j, new Color(255, 218, 97));
                    case URCHIN -> display.setColor(i, j, new Color(48, 0, 24));
                    case ROCK -> {
                        if (i % 2 == 0 && j % 2 == 0)
                            display.setColor(i, j, new Color(115, 60, 33));
                        else
                            display.setColor(i, j, new Color(97, 44, 24));
                    }
                    case METAL -> {
                        if (i % 3 == 0 || j % 3 == 0)
                            display.setColor(i, j, new Color(145 - (i / 2), 130 - (i / 2), 130 - (i / 2)));
                        else
                            display.setColor(i, j, new Color(165 - (i / 2), 150 - (i / 2), 150 - (i / 2)));
                    }
                    case GLASS -> {
                        if (isTouching(i, j, EMPTY) || (j % 5 != 0 && i % (j % 5) == 0))
                            display.setColor(i, j, new Color(125 + (i / 2), 125 + (i / 2), 125 + (i / 2)));
                        else
                            display.setColor(i, j, new Color(35 + i, 35 + i, 35 + i));
                    }
                    case PENGUIN -> display.setColor(i, j, new Color(0, 0, 0));
                    default -> display.setColor(i, j, new Color(255, 255, 255));
                }
            }
        }
    }

    // Called repeatedly, causes one random particle to update its state
    public void step() {
        int row = (int)(Math.random() * (grid.length));
        int col = (int)(Math.random() * (grid[row].length));
        switch (grid[row][col]) {
            case SAND -> stepSand(row, col);
            case POLLIWOG -> stepPolliwog(row, col);
            case MAGIC_SAND -> stepMagicSand(row, col);
            case DIRT -> moveAndSwap(row, col, row + 1, col);
            case SPORE -> stepSpore(row, col);
            case FUNGUS -> stepFungus(row, col);
            case FAIRY -> stepFairy(row, col);
            case WATER, MERCURY -> stepWater(row, col);
            case ICE -> stepIce(row, col);
            case ACID -> stepAcid(row, col);
            case GAS -> stepGas(row, col);
            case ACID_GAS -> stepAcidGas(row, col);
            case ALGAE -> stepAlgae(row, col);
            case SLIME -> stepSlime(row, col);
            case LAVA -> stepLava(row, col);
            case TNT -> stepTnt(row, col);
            case FIRE -> stepFire(row, col, FIRE);
            case BLUE_FIRE -> stepFire(row, col, BLUE_FIRE);
            case EXPLOSION -> stepExplosion(row, col);
            case ROCK -> stepRock(row, col);
            case SNOW -> stepSnow(row, col);
            case WATER_TAP -> stepTap(row, col, WATER);
            case FIRE_TAP -> stepTap(row, col, FIRE);
            case BLUE_FIRE_TAP -> stepTap(row, col, BLUE_FIRE);
            case LAVA_TAP -> stepTap(row, col, LAVA);
            case ACID_TAP -> stepTap(row, col, ACID);
            case MERCURY_TAP -> stepTap(row, col, MERCURY);
            case ACID_GAS_TAP -> stepTap(row, col, ACID_GAS);
            case SAND_DUCK -> stepSandDuck(row, col);
            case URCHIN -> stepUrchin(row, col);
            case PENGUIN -> stepPenguin(row, col);
            case METAL -> stepMetal(row, col);
            case ALCOHOL -> stepAlcohol(row, col);
            case HYDROGEN_GAS -> stepHydrogenGas(row, col);
        }
    }

    // Main game loop
    public void run() {
        while (true) {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            display.pause(1);  // Wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null)  // Test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool(), display.getPenSize());
        }
    }
    
    // Helper methods
    
    // Returns if the cell is touching any flame particle
    private boolean isOnFire(int row, int col){
        return  isTouching(row, col, FIRE) || 
                isTouching(row, col, LAVA) || 
                isTouching(row, col, BLUE_FIRE);
    }
    
    // Checks if any adjacent spaces (only cardinal directions) are the specified particle type
    private boolean isTouching(int row, int col, int particle){
        if (inBounds(row+1, col) && grid[row+1][col] == particle) return true;
        if (inBounds(row-1, col) && grid[row-1][col] == particle) return true;
        if (inBounds(row, col+1) && grid[row][col+1] == particle) return true;
        return inBounds(row, col - 1) && grid[row][col - 1] == particle;
    }

    private int touchCount(int row, int col, int particle) {
        int count = 0;
        if (inBounds(row+1, col) && grid[row+1][col] == particle) count++;
        if (inBounds(row-1, col) && grid[row-1][col] == particle) count++;
        if (inBounds(row, col+1) && grid[row][col+1] == particle) count++;
        if (inBounds(row, col - 1) && grid[row][col - 1] == particle) count++;
        if (inBounds(row+1, col+1) && grid[row+1][col+1] == particle) count++;
        if (inBounds(row-1, col-1) && grid[row-1][col-1] == particle) count++;
        if (inBounds(row-1, col+1) && grid[row-1][col+1] == particle) count++;
        if (inBounds(row+1, col - 1) && grid[row+1][col - 1] == particle) count++;
        return count;
    }
    
    // Sets the new spot in the grid to replaceParticle if it is foodParticle
    private void grow(int newRow, int newCol, int foodParticle, int replaceParticle){
        if (!(inBounds(newRow, newCol) &&  (foodParticle == ALL || grid[newRow][newCol] == foodParticle)))
            return;
        if (grid[newRow][newCol] == DIAMOND) return;
        grid[newRow][newCol] = replaceParticle;
    }
    
    // Moves the old particle and sets its old spot to EMPTY
    private void moveAndDestroy(int row, int col, int newRow, int newCol, int decayParticle, int[] immune){
        if (!inBounds(newRow, newCol) || grid[row][col] == grid[newRow][newCol] || grid[newRow][newCol] == decayParticle)
            return;
        for (int p : immune)
            if (grid[newRow][newCol] == p) 
                return;
        if (grid[newRow][newCol] == EMPTY)
            grid[newRow][newCol] = grid[row][col];
        else
            grid[newRow][newCol] = decayParticle;
        grid[row][col] = EMPTY;
    }
    
    // Moves the old particle and swaps with the overwritten particle 
    private void moveAndSwap(int row, int col, int newRow, int newCol){
        if (!inBounds(newRow, newCol) || grid[newRow][newCol] >= grid[row][col])
            return;
        int temp = grid[row][col];
        grid[row][col] = grid[newRow][newCol];
        grid[newRow][newCol] = temp;
    }
    
    
    // Checks if within grid boundaries
    private boolean inBounds(int row, int col){
        return row < grid.length && row > 0 && col < grid[row].length && col >= 0;
    }
    
    // Particle methods
    
    private void stepSand(int row, int col){
        if (isOnFire(row, col))
            grid[row][col] = GLASS;
        else
            moveAndSwap(row, col, row+1, col);
    }
    
    private void stepMagicSand(int row, int col){
        if (isOnFire(row, col))
            grid[row][col] = GLASS;
        else
            moveAndSwap(row, col, row, col+1);
    }
    
    private void stepPolliwog(int row, int col){
        if (isTouching(row, col, WATER) && isTouching(row, col, POLLIWOG) || isTouching(row, col, ALCOHOL))
            grid[row][col] = WATER; 
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS) || isTouching(row, col, URCHIN) || isTouching(row, col, PENGUIN) || isTouching(row, col, MERCURY))
            grid[row][col] = GAS; 
        else if (Math.random() < POLLIWOG_GROW_CHANCE){ 
            grow(row, col+1, WATER, POLLIWOG);
            grow(row, col-1, WATER, POLLIWOG);
            grow(row+1, col, WATER, POLLIWOG);
            grow(row-1, col, WATER, POLLIWOG);
        }
    }

    private void stepPenguin(int row, int col){
        int count = touchCount(row, col, PENGUIN);
        if (count >= 8 || count <= 2)
            grid[row][col] = ICE;
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS) || isTouching(row, col, MERCURY) || isTouching(row, col, SPORE))
            grid[row][col] = GAS;
        else if (count == 3 && Math.random() < PENGUIN_GROW_CHANCE){
            grow(row, col+1, ICE, PENGUIN);
            grow(row, col-1, ICE, PENGUIN);
            grow(row+1, col, ICE, PENGUIN);
            grow(row-1, col, ICE, PENGUIN);
            grow(row, col+1, SNOW, PENGUIN);
            grow(row, col-1, SNOW, PENGUIN);
            grow(row+1, col, SNOW, PENGUIN);
            grow(row-1, col, SNOW, PENGUIN);
        }
    }

    private void stepUrchin(int row, int col){
        int count = touchCount(row, col, URCHIN);
        if (count >= 5 || count <= 1 || isTouching(row, col, ALCOHOL))
            grid[row][col] = WATER;
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS) || isTouching(row, col, SAND_DUCK) || isTouching(row, col, PENGUIN) || isTouching(row, col, POLLIWOG) || isTouching(row, col, MERCURY))
            grid[row][col] = HYDROGEN_GAS;
        else if (isTouching(row, col, WATER) && (count == 2) && Math.random() < URCHIN_GROW_CHANCE){
            grow(row, col+1, WATER, URCHIN);
            grow(row, col-1, WATER, URCHIN);
            grow(row+1, col, WATER, URCHIN);
            grow(row-1, col, WATER, URCHIN);
        }
    }

    private void stepSandDuck(int row, int col){
        if (isTouching(row, col, SAND) && isTouching(row, col, SAND_DUCK))
            grid[row][col] = SAND; 
        else if (isTouching(row, col, MAGIC_SAND) && isTouching(row, col, SAND_DUCK))
            grid[row][col] = MAGIC_SAND;
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS) || isTouching(row, col, MERCURY))
            grid[row][col] = DIRT; 
        else if (Math.random() < SAND_DUCK_GROW_CHANCE){ 
            grow(row, col+1, SAND, SAND_DUCK);
            grow(row, col-1, SAND, SAND_DUCK);
            grow(row+1, col, SAND, SAND_DUCK);
            grow(row-1, col, SAND, SAND_DUCK);
            grow(row, col+1, MAGIC_SAND, SAND_DUCK);
            grow(row, col-1, MAGIC_SAND, SAND_DUCK);
            grow(row+1, col, MAGIC_SAND, SAND_DUCK);
            grow(row-1, col, MAGIC_SAND, SAND_DUCK);
        }
    }
    
    private void stepSpore(int row, int col){
        if (isOnFire(row, col))
            grid[row][col] = FIRE;
        else if (isTouching(row, col, SAND_DUCK) || isTouching(row, col, MERCURY))
            grid[row][col] = GAS;
        else if (Math.random() < SPORE_GROW_CHANCE && isTouching(row, col, DIRT) && isTouching(row, col, WATER))
            grid[row][col] = FUNGUS;
        else
            moveAndSwap(row, col, row+1, col);
    }
    
    private void stepFungus(int row, int col){
        if (isOnFire(row, col))
            grid[row][col] = FIRE;
        else if (isTouching(row, col, SAND_DUCK) || isTouching(row, col, URCHIN) || isTouching(row, col, MERCURY))
            grid[row][col] = GAS;
        else if (Math.random() < FUNGUS_GROW_CHANCE){
            grow(row-1, col, WATER, FUNGUS);
            grow(row-1, col, SPORE, FUNGUS);
            grow(row-1, col, EMPTY, FUNGUS);
        }
    }
    
    private void stepFairy(int row, int col){
        if (Math.random() >= FAIRY_GROW_CHANCE)
            return;
        int randDir = ((int)(Math.random() * 2));
        if (isTouching(row, col, POLLIWOG) || isTouching(row, col, SPORE) || isTouching(row, col, FUNGUS) ||  isTouching(row, col, MERCURY))
            grid[row][col] = GAS;
        else if (isTouching(row, col, FAIRY))
            grid[row][col] = EMPTY;
        else if (randDir == 0) {
            grow(row-1, col, EMPTY, FAIRY);
            grow(row+1, col, EMPTY, FAIRY);
        } else {
            grow(row, col-1, EMPTY, FAIRY);
            grow(row, col+1, EMPTY, FAIRY);
        }
    }
    
    private void stepWater(int row, int col){
        int randDir = (int)(Math.random() * 3) - 1;
        if (isOnFire(row, col))
            grid[row][col] = GAS;
        else if (isTouching(row, col, SNOW))
            grid[row][col] = ICE;
        else if (randDir == -1 || randDir == 1)
            moveAndSwap(row, col, row, col+randDir);
        else
            moveAndSwap(row, col, row+1, col);
    }

    private void stepAlcohol(int row, int col){
        int randDir = (int)(Math.random() * 3) - 1;
        if (isTouching(row, col, FAIRY))
            grid[row][col] = WATER;
        else if (isOnFire(row, col) || isTouching(row, col, EXPLOSION))
            grid[row][col] = EXPLOSION;
        else if (randDir == -1 || randDir == 1)
            moveAndSwap(row, col, row, col+randDir);
        else
            moveAndSwap(row, col, row+1, col);
    }

    private void stepIce(int row, int col){
        if (isOnFire(row, col)) {
            grid[row][col] = WATER;
            return;
        }
        if (Math.random() >= WATER_FREEZE_CHANCE)
            return;
        // must be temporarily empty to check ice collision
        grid[row][col] = EMPTY;
        int randDir = (int)(Math.random() * 3) - 1;
        if ((randDir == -1 || randDir == 1) && !isTouching(row, col+randDir, ICE))
            grow(row, col+randDir, WATER, ICE);
        else if (!isTouching(row+1, col, ICE))
            grow(row+1, col, WATER, ICE);
        grid[row][col] = ICE;
    }
    
    private void stepAcid(int row, int col){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = WATER;
        else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndDestroy(row, col, row, col+randDir, ACID_GAS, new int[] {SLIME, ACID_TAP, DIAMOND});
            else 
                moveAndDestroy(row, col, row+1, col, ACID_GAS, new int[] {SLIME, ACID_TAP, DIAMOND});
        }
    }
    
    private void stepGas(int row, int col){
        if (Math.random() < GAS_DECAY_CHANCE) 
            grid[row][col] = EMPTY;
        else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndSwap(row, col, row, col+randDir);
            else 
                moveAndSwap(row, col, row-1, col);
        }
    }

    private void stepHydrogenGas(int row, int col){
        if (isOnFire(row, col) || isTouching(row, col, EXPLOSION))
            grid[row][col] = EXPLOSION;
        else if (Math.random() < HYDROGEN_GAS_DECAY_CHANCE)
            grid[row][col] = EMPTY;
        else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndSwap(row, col, row, col+randDir);
            else
                moveAndSwap(row, col, row-1, col);
        }
    }

    private void stepAcidGas(int row, int col){
        if (Math.random() < ACID_GAS_DECAY_CHANGE)
            grid[row][col] = EMPTY;
        else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndSwap(row, col, row, col+randDir);
            else
                moveAndSwap(row, col, row-1, col);
        }
    }
    
    private void stepAlgae(int row, int col){
        int randDir = ((int)(Math.random() * 4));
        if (isOnFire(row, col))
            grid[row][col] = FIRE;
        else if (isTouching(row, col, POLLIWOG) || isTouching(row, col, ALCOHOL))
            grid[row][col] = WATER;
        else if (isTouching(row, col, ACID_GAS) ||  isTouching(row, col, MERCURY))
            grid[row][col] = GAS;
        else if (Math.random() < ALGAE_GROW_CHANCE){
            if (Math.abs(randDir) % 2 == 0)
                grow(row+randDir-1, col, WATER, ALGAE);
            else
                grow(row, col+randDir-2, WATER, ALGAE);
        }
        else if (Math.random() < ALGAE_DIE_CHANCE)
            grid[row][col] = WATER;
    }
    
    private void stepSlime(int row, int col){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = ALGAE; 
        else if (isTouching(row, col, WATER) || isTouching(row, col, BLUE_FIRE) ||  isTouching(row, col, MERCURY))
            grid[row][col] = ACID_GAS; 
        else if (Math.random() < SLIME_GROW_CHANCE){
            grow(row+1, col, ALL, SLIME);
            int randDir = ((int)(Math.random() * 4));
            if (Math.abs(randDir) % 2 == 0)
                grow(row+randDir-1, col, ACID, SLIME);
            else
                grow(row, col+randDir-2, ACID, SLIME);
        }
        else if (Math.random() < SLIME_DIE_CHANCE)
            grid[row][col] = ACID_GAS;
    }
    
    private void stepLava(int row, int col){
        if (Math.random() < LAVA_DECAY_CHANCE) 
            grid[row][col] = FIRE;
        else if (isTouching(row, col, FAIRY) || isTouching(row, col, WATER) || 
                (Math.random() < LAVA_COOL_CHANCE && !isTouching(row, col, EMPTY))){
            grid[row][col] = ROCK;
        } else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndDestroy(row, col, row, col+randDir, FIRE, new int[] {LAVA_TAP, ROCK, GLASS, FAIRY, DIAMOND});
            else 
                moveAndDestroy(row, col, row+1, col, FIRE, new int[] {LAVA_TAP, ROCK, GLASS, FAIRY, DIAMOND});
        }
    }
    
    private void stepTnt(int row, int col){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = DIRT; 
        else if (isOnFire(row, col) || isTouching(row, col, EXPLOSION) )
            grid[row][col] = EXPLOSION;
    }
    
    private void stepFire(int row, int col, int fire_type){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = GAS; 
        else if (Math.random() < (fire_type == BLUE_FIRE ? BLUE_FIRE_DECAY_CHANCE : FIRE_DECAY_CHANCE))
            grid[row][col] = GAS;
        else {
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndSwap(row, col, row, col+randDir);
            else 
                moveAndSwap(row, col, row-1, col);
        }
    }
    
    private void stepExplosion(int row, int col){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = GAS; 
        else if (Math.random() < EXPLOSION_DECAY_CHANCE || isTouching(row, col, GAS)) 
            grid[row][col] = GAS; 
        else {
            grow(row, col+1, ALL, EXPLOSION);
            grow(row, col-1, ALL, EXPLOSION);
            grow(row+1, col, ALL, EXPLOSION);
            grow(row-1, col, ALL, EXPLOSION);
        }
    }
    
    private void stepRock(int row, int col){
        if (isTouching(row, col, WATER) && Math.random() < ROCK_WEATHER_CHANCE)
            grid[row][col] = DIRT;
        if (isTouching(row, col, BLUE_FIRE) && Math.random() < ROCK_MELT_CHANCE)
            grid[row][col] = LAVA;
    }

    private void stepMetal(int row, int col){
        if (isTouching(row, col, BLUE_FIRE) && Math.random() < METAL_MELT_CHANCE)
            grid[row][col] = MERCURY;
    }
    
    private void stepSnow(int row, int col){
        if ((!isTouching(row, col, ICE) && Math.random() < SNOW_MELT_CHANCE) || isOnFire(row, col))
            grid[row][col] = WATER;
        else if (Math.random() < SNOW_FALL_CHANCE){
            int randDir = (int)(Math.random() * 3) - 1;
            if (randDir == -1 || randDir == 1)
                moveAndSwap(row, col, row, col+randDir);
            else 
                moveAndSwap(row, col, row+1, col);
        }
    }
    
    private void stepTap(int row, int col, int particle){
        if (Math.random() >= TAP_SPAWN_CHANCE){
            grow(row, col+1, EMPTY, particle);
            grow(row, col-1, EMPTY, particle);
            grow(row+1, col, EMPTY, particle);
            grow(row-1, col, EMPTY, particle);
        }
    }
}