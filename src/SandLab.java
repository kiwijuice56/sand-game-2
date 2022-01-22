/*************************/
/* Eric Alfaro           */   
/* 2021/10/28            */ 
/*************************/

import java.awt.Color;
import java.util.Arrays;

public class SandLab {
    public static void main(String[] args) {
        SandLab lab = new SandLab(145, 95);
        lab.run();
    }
    
    /*************************/
    /*    MATERIAL INIT.     */ 
    /*************************/
    
    // Number represents ID and density; lower = less dense
    public static final int EXPLOSION = 29;     // Causes chain destruction reactions
    public static final int FUNGUS = 28;        // Grows upwards from germinated spores
    public static final int DIAMOND = 27;       // Most stable material
    public static final int ACID_TAP = 26;      // Spawns fire
    public static final int LAVA_TAP = 25;      // Spawns fire
    public static final int FIRE_TAP = 24;      // Spawns fire
    public static final int WATER_TAP = 23;     // Spawns water
    public static final int METAL = 22;         // Second most stable material, can be exploded
    public static final int SLIME = 21;         // Grows downwards, or in all directions containing acid
    public static final int TNT = 20;           // Explodes when touched by fire or lava
    public static final int ROCK = 19;          // Stable material but it may be dissolved and weathered down
    public static final int GLASS = 18;         // Created from sand, magic or normal
    public static final int LAVA = 17;          // Dissolves material but cools into rock or fire
    public static final int DIRT = 16;          // Supports spore germination
    public static final int SAND_DUCK = 15;         // Grows in water
    public static final int MAGIC_SAND = 14;    // Falls sideways
    public static final int SAND = 13;          // Most basic falling element, can be smelted into glass
    public static final int ALGAE = 12;         // Grows in water
    public static final int SPORE = 11;          // Germinates in presence of water and dirt
    public static final int POLLIWOG = 10;       // Spreads in water without completely overtaking it
    public static final int FAIRY = 9;          // Purifies acid, TNT, slime, fire, and lava
    public static final int ICE = 8;            // Creates crystals in water
    public static final int WATER = 7;          // Basic fluid, supports many life materials but can evaporate
    public static final int SNOW = 6;           // Falls slowly and melts to water
    public static final int ACID = 5;          // Dissolves almost everything
    public static final int BLUE_FIRE = 4;      // Similar to fire but lasts longer
    public static final int FIRE = 3;           // Burns fungus, algae and spores
    public static final int ACID_GAS = 2;
    public static final int GAS = 1;            // Goes upwards before dissapearing
    public static final int EMPTY = 0;          // Eraser
    public static final int ALL = -1;           // Code-use only; describes case where material type is irrelevant 

    // Control rate of random reactions in particles
    public static final double GAS_DECAY_CHANCE = 0.025;
    public static final double LAVA_DECAY_CHANCE = 0.0015;
    public static final double LAVA_COOL_CHANCE = 0.0005;
    public static final double ALGAE_GROW_CHANCE = 0.0025;
    public static final double SLIME_GROW_CHANCE = 0.0035;
    public static final double EXPLOSION_DECAY_CHANCE = 0.04;
    public static final double FIRE_DECAY_CHANCE = 0.025;
    public static final double BLUE_FIRE_DECAY_CHANCE = 0.0065;
    public static final double ROCK_WEATHER_CHANCE = 0.0025;
    public static final double ROCK_MELT_CHANCE = 0.0025;
    public static final double SPORE_GROW_CHANCE = 0.0025;
    public static final double FUNGUS_GROW_CHANCE = 0.0025;
    public static final double POLLIWOG_GROW_CHANCE = 0.05;
    public static final double SAND_DUCK_GROW_CHANCE = 0.01;
    public static final double FAIRY_GROW_CHANCE = 0.05;
    public static final double SNOW_FALL_CHANCE = 0.25;
    public static final double SNOW_MELT_CHANCE = 0.0001;
    public static final double TAP_SPAWN_CHANCE = 0.015;
    public static final double WATER_FREEZE_CHANCE = 0.01;
    
    // Other game fields
    private int[][] grid;
    private SandDisplay display;
  
    // Constructs UI labels
    public SandLab(int numRows, int numCols) {
        String[] names;
        names = new String[28];
        names[EMPTY] = "Empty";
        names[METAL] = "Metal";
        names[SAND] = "Sand";
        names[MAGIC_SAND] = "Magic Sand";
        names[WATER] = "Water";
        names[ALGAE] = "Algae";
        names[SLIME] = "Slime";
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
        names[DIAMOND] = "Diamond";
        names[GLASS] = "Glass";
        names[POLLIWOG] = "Polliwog";
        names[ICE] = "Ice";
        names[LAVA_TAP] = "Lava Tap";
        names[ACID_TAP] = "Acid Tap";
        names[ACID_GAS] = "Acid Gas";
        names[SAND_DUCK] = "Sand Duck";
        //Arrays.sort(names);
        display = new SandDisplay("Cypress Ranch Lab: Falling Sand", numRows, numCols, names);
        grid = new int[numRows][numCols];
    }
  
    /*************************/
    /*     MAIN METHODS      */ 
    /*************************/
    
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
        for (int i = 0; i < grid.length; i++){
            for (int j = 0; j < grid[i].length; j++){
                // Colors for each material
                switch (grid[i][j]){
                    case SAND:      display.setColor(i, j, new Color(255, 191, 28)); break;
                    case MAGIC_SAND:display.setColor(i, j, new Color(255, 191, 28)); break;
                    case EMPTY:     display.setColor(i, j, new Color(0, 0, 0)); break;
                    case WATER:     display.setColor(i, j, new Color(50, 50, 250)); break;
                    case GAS:       display.setColor(i, j, new Color(225, 225, 245)); break;
                    case ACID_GAS:  display.setColor(i, j, new Color(187, 158, 240)); break;
                    case ALGAE:     display.setColor(i, j, new Color(44, 145, 41)); break;
                    case ACID:      display.setColor(i, j, new Color(135, (int)(49*Math.random()), 222)); break;
                    case LAVA:      display.setColor(i, j, new Color(255, 95, 45)); break;
                    case TNT:       display.setColor(i, j, new Color(255, 15, 15)); break;
                    case FIRE:      display.setColor(i, j, new Color(255, 75, 0)); break;
                    case BLUE_FIRE: display.setColor(i, j, new Color(5, 165, 255)); break;
                    case EXPLOSION: display.setColor(i, j, new Color(255, 175, 0)); break;
                    case DIRT:      display.setColor(i, j, new Color(155, 75, 45)); break;
                    case SLIME:     display.setColor(i, j, new Color(37, 26, 161)); break;
                    case SPORE:     display.setColor(i, j, new Color(125, 125, 55)); break;
                    case FUNGUS:    display.setColor(i, j, new Color(155, 155, 75)); break;
                    case FAIRY:     display.setColor(i, j, new Color(195, 145, 195)); break;
                    case SNOW:      display.setColor(i, j, new Color(245, 245, 255)); break;
                    case ICE:       display.setColor(i, j, new Color(189, 246, 255)); break;
                    case DIAMOND:   display.setColor(i, j, new Color(225-i, 235-i, 245-i)); break;
                    case POLLIWOG:  display.setColor(i, j, new Color(40, 161, 161)); break;
                    case SAND_DUCK:  display.setColor(i, j, new Color(255, 218, 97)); break;
                    case ROCK: {
                        if (i % 2 == 0 && j % 2 == 0)
                            display.setColor(i, j, new Color(80, 65, 65));
                        else
                             display.setColor(i, j, new Color(90, 75, 75));
                        break;
                    }
                    case METAL: {
                        if (i % 3 == 0 || j % 3 == 0)
                            display.setColor(i, j, new Color(145-(i/2), 130-(i/2), 130-(i/2)));
                        else 
                            display.setColor(i, j, new Color(165-(i/2), 150-(i/2), 150-(i/2))); 
                        break;
                    }
                    case GLASS: {
                        if (isTouching(i, j, EMPTY) || (j%5 != 0 && i % (j%5) == 0))
                            display.setColor(i, j, new Color(125+(i/2), 125+(i/2), 125+(i/2))); 
                        else
                            display.setColor(i, j, new Color(35+i,35+i,35+i));
                        break;
                    }
                    default:        display.setColor(i, j, new Color(255, 255, 255)); break;
                }
            }
        }
    }

    // Called repeatedly, causes one random particle to update its state
    public void step() {
        int row = (int)(Math.random() * (grid.length));
        int col = (int)(Math.random() * (grid[row].length));
        switch (grid[row][col]){
            case SAND:          stepSand(row, col); break;
            case POLLIWOG:      stepPolliwog(row, col); break;
            case MAGIC_SAND:    stepMagicSand(row, col); break;
            case DIRT:          moveAndSwap(row, col, row+1, col); break;
            case SPORE:         stepSpore(row, col); break;
            case FUNGUS:        stepFungus(row, col); break;
            case FAIRY:         stepFairy(row, col); break;
            case WATER:         stepWater(row, col); break;
            case ICE:           stepIce(row, col); break;
            case ACID:          stepAcid(row, col); break;
            case GAS:           stepGas(row, col); break;
            case ACID_GAS:      stepGas(row, col); break;
            case ALGAE:         stepAlgae(row, col); break;
            case SLIME:         stepSlime(row, col); break;
            case LAVA:          stepLava(row, col); break;
            case TNT:           stepTnt(row, col); break;
            case FIRE:          stepFire(row, col, FIRE); break;
            case BLUE_FIRE:     stepFire(row, col, BLUE_FIRE); break;  
            case EXPLOSION:     stepExplosion(row, col); break;
            case ROCK:          stepRock(row, col); break;
            case SNOW:          stepSnow(row, col); break;
            case WATER_TAP:     stepTap(row, col, WATER); break;
            case FIRE_TAP:      stepTap(row, col, FIRE); break;
            case LAVA_TAP:      stepTap(row, col, LAVA); break;
            case ACID_TAP:      stepTap(row, col, ACID); break;
            case SAND_DUCK:     stepSandDuck(row, col); break;
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
    
    /*************************/
    /*     HELPER METHODS    */ 
    /*************************/
    
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
        if (inBounds(row, col-1) && grid[row][col-1] == particle) return true;
        return false;
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
        if (row >= grid.length || row < 0) 
            return false; // out of bounds vertically
        if (col >= grid[row].length || col < 0)
            return false; //out of bounds horizontally
        return true;
    }
    
    /*************************/
    /*   PARTICLE METHODS    */ 
    /*************************/
    
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
        if (isTouching(row, col, WATER) && isTouching(row, col, POLLIWOG))
            grid[row][col] = WATER; 
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS))
            grid[row][col] = GAS; 
        else if (Math.random() < POLLIWOG_GROW_CHANCE){ 
            grow(row, col+1, WATER, POLLIWOG);
            grow(row, col-1, WATER, POLLIWOG);
            grow(row+1, col, WATER, POLLIWOG);
            grow(row-1, col, WATER, POLLIWOG);
        }
    }
    
    private void stepSandDuck(int row, int col){
        if (isTouching(row, col, SAND) && isTouching(row, col, SAND_DUCK))
            grid[row][col] = SAND; 
        else if (isTouching(row, col, MAGIC_SAND) && isTouching(row, col, SAND_DUCK))
            grid[row][col] = MAGIC_SAND;
        else if (isOnFire(row, col) || isTouching(row, col, ACID_GAS))
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
        else if (isTouching(row, col, SAND_DUCK))
            grid[row][col] = GAS;
        else if (Math.random() < SPORE_GROW_CHANCE && isTouching(row, col, DIRT) && isTouching(row, col, WATER))
            grid[row][col] = FUNGUS;
        else
            moveAndSwap(row, col, row+1, col);
    }
    
    private void stepFungus(int row, int col){
        if (isOnFire(row, col))
            grid[row][col] = FIRE;
        else if (isTouching(row, col, SAND_DUCK))
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
        if (isTouching(row, col, POLLIWOG) || isTouching(row, col, SPORE) || isTouching(row, col, FUNGUS))
            grid[row][col] = GAS;
        if (isTouching(row, col, FAIRY))
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
    
    private void stepAlgae(int row, int col){
        int randDir = ((int)(Math.random() * 4));
        if (isOnFire(row, col))
            grid[row][col] = FIRE;
        else if (isTouching(row, col, POLLIWOG))
            grid[row][col] = WATER;
        else if (isTouching(row, col, ACID_GAS))
            grid[row][col] = GAS;
        else if (Math.random() < ALGAE_GROW_CHANCE){
            if (Math.abs(randDir) % 2 == 0)
                grow(row+randDir-1, col, WATER, ALGAE);
            else
                grow(row, col+randDir-2, WATER, ALGAE);
        }
    }
    
    private void stepSlime(int row, int col){
        if (isTouching(row, col, FAIRY))
            grid[row][col] = ALGAE; 
        else if (isTouching(row, col, WATER) || isTouching(row, col, BLUE_FIRE))
            grid[row][col] = ACID_GAS; 
        else if (Math.random() < SLIME_GROW_CHANCE){
            grow(row+1, col, ALL, SLIME);
            int randDir = ((int)(Math.random() * 4));
            if (Math.abs(randDir) % 2 == 0)
                grow(row+randDir-1, col, ACID, SLIME);
            else
                grow(row, col+randDir-2, ACID, SLIME);
        }
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
    
    private void stepSnow(int row, int col){
        if (Math.random() < SNOW_MELT_CHANCE || isOnFire(row, col))
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