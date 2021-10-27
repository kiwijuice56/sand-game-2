import java.awt.*;
import java.util.*;

public class SandLab {
    public static void main(String[] args) {
        SandLab lab = new SandLab(145, 95);
        lab.run();
    }
    
    // Number represents ID and density; lower = less dense
    public static final int EXPLOSION = 24; // Causes chain destruction reactions
    public static final int FUNGUS = 23; // Grows upwards
    public static final int DIAMOND = 22; // Causes chain destruction reactions
    public static final int FIRE_TAP = 21; // Grows upwards
    public static final int WATER_TAP = 20; // Grows upwards
    public static final int METAL = 19; // Strongest sturdy element
    public static final int SLIME = 18; // Grows downards and in all direction in acid
    public static final int TNT = 17; // Explodes when touched by fire or lava
    public static final int ACID = 16; // Dissolves almost everything
    public static final int ROCK = 15; // Sturdy element but it may be dissolved or weathered down
    public static final int GLASS = 14;
    public static final int LAVA = 13; // Dissolves material but cools into rock or fire
    public static final int DIRT = 12; 
    public static final int MAGIC_SAND = 11; // Falls sideways
    public static final int SAND = 10;
    public static final int ALGAE = 9; // Grows in water
    public static final int SPORE = 8; // Germinates in presence of water and dirt
    public static final int POLLIWOG = 7;
    public static final int FAIRY = 6; // Purifies acid, TNT, slime, fire, lava but dies to metal
    public static final int WATER = 5; // Basic fluid
    public static final int SNOW = 4;
    public static final int BLUE_FIRE = 3; // Burns fungus, algae and spores
    public static final int FIRE = 2; // Burns fungus, algae and spores
    public static final int GAS = 1; // Goes upwards before dissapearing
    public static final int EMPTY = 0; // Eraser
    public static final int ALL = -1; // Code-use only; describes case where element is irrelevant 

    // Control rate of random reactions in particles
    public static final double GAS_DECAY_CHANCE = 0.025;
    public static final double LAVA_DECAY_CHANCE = 0.0035;
    public static final double LAVA_COOL_CHANCE = 0.0035;
    public static final double ALGAE_GROW_CHANCE = 0.0025;
    public static final double SLIME_GROW_CHANCE = 0.0035;
    public static final double EXPLOSION_DECAY_CHANCE = 0.04;
    public static final double FIRE_DECAY_CHANCE = 0.025;
    public static final double BLUE_FIRE_DECAY_CHANCE = 0.0065;
    public static final double ROCK_WEATHER_CHANCE = 0.0025;
    public static final double ROCK_MELT_CHANCE = 0.0025;
    public static final double SPORE_GROW_CHANCE = 0.0025;
    public static final double FUNGUS_GROW_CHANCE = 0.0025;
    public static final double POLLIWOG_GROW_CHANCE = 0.25;
    public static final double SNOW_FALL_CHANCE = 0.25;
    public static final double SNOW_MELT_CHANCE = 0.0001;
    public static final double TAP_SPAWN_CHANCE = 0.015;
    
    // Game fields
    private int[][] grid;
    private SandDisplay display;
  
    public SandLab(int numRows, int numCols) {
        String[] names;
        names = new String[23];
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
        display = new SandDisplay("Cypress Ranch Lab: Falling Sand", numRows, numCols, names);
        grid = new int[numRows][numCols];
    }
  
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
                    case METAL:     display.setColor(i, j, new Color(145, 145, 175)); break;
                    case SAND:      display.setColor(i, j, new Color(255, 191, 28)); break;
                    case MAGIC_SAND:display.setColor(i, j, new Color(255, 191, 28)); break;
                    case EMPTY:     display.setColor(i, j, new Color(0, 0, 0)); break;
                    case WATER:     display.setColor(i, j, new Color(50, 50, 250)); break;
                    case GAS:       display.setColor(i, j, new Color(225, 225, 245)); break;
                    case ALGAE:     display.setColor(i, j, new Color(44, 145, 41)); break;
                    case ACID:      display.setColor(i, j, new Color(135, 49, 222)); break;
                    case LAVA:      display.setColor(i, j, new Color(255, 75, 45)); break;
                    case ROCK:      display.setColor(i, j, new Color(110, 95, 95)); break;
                    case TNT:       display.setColor(i, j, new Color(255, 15, 15)); break;
                    case FIRE:      display.setColor(i, j, new Color(255, 75, 0)); break;
                    case BLUE_FIRE: display.setColor(i, j, new Color(5, 165, 255)); break;
                    case EXPLOSION: display.setColor(i, j, new Color(255, 175, 0)); break;
                    case DIRT:      display.setColor(i, j, new Color(155, 75, 45)); break;
                    case SLIME:     display.setColor(i, j, new Color(37, 26, 161)); break;
                    case SPORE:     display.setColor(i, j, new Color(125, 125, 55)); break;
                    case FUNGUS:    display.setColor(i, j, new Color(155, 155, 75)); break;
                    case FAIRY:     display.setColor(i, j, new Color(195 + (int)(Math.random()*100)-50, 145, 195 + (int)(Math.random()*100)-50) ); break;
                    case SNOW:      display.setColor(i, j, new Color(245, 245, 255)); break;
                    case DIAMOND:   display.setColor(i, j, new Color(215, 215, 255)); break;
                    case POLLIWOG:  display.setColor(i, j, new Color(207, 48, 255)); break;
                    case GLASS: {
                        if (isTouching(i, j, EMPTY)) display.setColor(i, j, new Color(225, 225, 255));
                        else display.setColor(i, j, new Color(215, 215, 255)); 
                        break;
                    }
                    default:        display.setColor(i, j, new Color(255, 255, 255)); break;
                }
            }
        }
    }

    // Called repeatedly.
    // Causes one random particle to update its state
    public void step() {
        int randRow = (int)(Math.random() * (grid.length));
        int randCol = (int)(Math.random() * (grid[randRow].length));
        switch (grid[randRow][randCol]){
            case SAND: {
                if (isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = GLASS; break;
                }
                moveAndSwap(randRow, randCol, randRow+1, randCol);
                break;
            }
            case POLLIWOG: {
                if (Math.random() >= POLLIWOG_GROW_CHANCE) break;
                if (isTouching(randRow, randCol, WATER) && isTouching(randRow, randCol, POLLIWOG)){
                    grid[randRow][randCol] = WATER; break;
                }
                if (isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = GAS; break;
                }
                grow(randRow, randCol, randRow, randCol+1, WATER, POLLIWOG);
                grow(randRow, randCol, randRow, randCol-1, WATER, POLLIWOG);
                grow(randRow, randCol, randRow+1, randCol, WATER, POLLIWOG);
                grow(randRow, randCol, randRow-1, randCol, WATER, POLLIWOG);
                break;
            }
            case MAGIC_SAND: {
                moveAndSwap(randRow, randCol, randRow, randCol + (randCol > grid[randRow].length/2 ? 1 : -1) );
                break;
            }
            case DIRT: {
                moveAndSwap(randRow, randCol, randRow+1, randCol);
                break;
            }
            case SPORE: {
                if (isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = FIRE; break;
                }
                if (Math.random() < SPORE_GROW_CHANCE && isTouching(randRow, randCol, DIRT) && isTouching(randRow, randCol, WATER)){
                    grid[randRow][randCol] = FUNGUS; break;
                }
                moveAndSwap(randRow, randCol, randRow+1, randCol);
                break;
            }
            case FUNGUS: {
                if (isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = FIRE; break;
                }
                if (Math.random() < FUNGUS_GROW_CHANCE){
                    grow(randRow, randCol, randRow-1, randCol, WATER, FUNGUS);
                    grow(randRow, randCol, randRow-1, randCol, SPORE, FUNGUS);
                    grow(randRow, randCol, randRow-1, randCol, EMPTY, FUNGUS);
                }
                break;
            }
            case FAIRY: {
                int randDir = ((int)(Math.random() * 4));
                if (isTouching(randRow, randCol, METAL)){
                    grid[randRow][randCol] = SAND; break;
                }
                if (Math.abs(randDir) % 2 == 0)
                    grow(randRow, randCol, randRow+randDir-1, randCol, EMPTY, FAIRY);
                else
                    grow(randRow, randCol, randRow, randCol+randDir-2, EMPTY, FAIRY);
                grid[randRow][randCol] = EMPTY;
                break;
            }
            case WATER: {
                int randDir = (int)(Math.random() * 3) - 1;
                if (isTouching(randRow, randCol, FIRE)){
                    grid[randRow][randCol] = GAS; break;
                }
                if (randDir == -1 || randDir == 1)
                    moveAndSwap(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndSwap(randRow, randCol, randRow+1, randCol);
                break;
            }
            case ACID: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = WATER; break;
                }
                int randDir = (int)(Math.random() * 3) - 1;
                if (randDir == -1 || randDir == 1)
                    moveAndDestroy(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndDestroy(randRow, randCol, randRow+1, randCol);
                break;
            }
            case GAS: {
                if (Math.random() < GAS_DECAY_CHANCE) {
                    grid[randRow][randCol] = EMPTY; break;
                }
                int randDir = (int)(Math.random() * 3) - 1;
                if (randDir == -1 || randDir == 1)
                    moveAndSwap(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndSwap(randRow, randCol, randRow-1, randCol);
                break;
            }
            case ALGAE: {
                int randDir = ((int)(Math.random() * 4));
                if (isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = FIRE; break;
                }
                if (Math.random() >= ALGAE_GROW_CHANCE) break;
                if (Math.abs(randDir) % 2 == 0)
                    grow(randRow, randCol, randRow+randDir-1, randCol, WATER, ALGAE);
                else
                    grow(randRow, randCol, randRow, randCol+randDir-2, WATER, ALGAE);
                break;
            }
            case SLIME: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = ALGAE; break;
                }
                if (isTouching(randRow, randCol, WATER) || isTouching(randRow, randCol, BLUE_FIRE)){
                    grid[randRow][randCol] = GAS; break;
                }
                if (Math.random() >= SLIME_GROW_CHANCE) break;
                grow(randRow, randCol, randRow+1, randCol, ALL, SLIME);
                int randDir = ((int)(Math.random() * 4));
                if (Math.abs(randDir) % 2 == 0)
                    grow(randRow, randCol, randRow+randDir-1, randCol, ACID, SLIME);
                else
                    grow(randRow, randCol, randRow, randCol+randDir-2, ACID, SLIME);
                break;
            }
            case LAVA: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = ROCK; break;
                }
                else if (Math.random() < LAVA_DECAY_CHANCE) {
                    grid[randRow][randCol] = FIRE; break;
                } else if (isTouching(randRow, randCol, WATER) || 
                          (Math.random() < LAVA_COOL_CHANCE && !isTouching(randRow, randCol, EMPTY))){
                    grid[randRow][randCol] = ROCK; break;
                }
                int randDir = (int)(Math.random() * 3) - 1;
                if (randDir == -1 || randDir == 1)
                    moveAndDestroy(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndDestroy(randRow, randCol, randRow+1, randCol);
                break;
            }
            case TNT: {
                if (isTouching(randRow, randCol, FAIRY))
                    grid[randRow][randCol] = DIRT; 
                else if (isOnFire(randRow, randCol))
                    grid[randRow][randCol] = EXPLOSION;
                break;
            }
            case FIRE: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = GAS; break;
                }
                if (Math.random() < FIRE_DECAY_CHANCE) {
                    grid[randRow][randCol] = GAS; break;
                }
                int randDir = (int)(Math.random() * 3) - 1;
                if (randDir == -1 || randDir == 1)
                    moveAndSwap(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndSwap(randRow, randCol, randRow-1, randCol);
                break;
            }
            case BLUE_FIRE: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = GAS; break;
                }
                if (Math.random() < BLUE_FIRE_DECAY_CHANCE) {
                    grid[randRow][randCol] = GAS; break;
                }
                int randDir = (int)(Math.random() * 3) - 1;
                if (randDir == -1 || randDir == 1)
                    moveAndSwap(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndSwap(randRow, randCol, randRow-1, randCol);
                break;
            }
            case EXPLOSION: {
                if (isTouching(randRow, randCol, FAIRY)){
                    grid[randRow][randCol] = GAS; break;
                }
                if (Math.random() < EXPLOSION_DECAY_CHANCE || isTouching(randRow, randCol, GAS)) {
                    grid[randRow][randCol] = GAS; break;
                }
                grow(randRow, randCol, randRow, randCol+1, ALL, EXPLOSION);
                grow(randRow, randCol, randRow, randCol-1, ALL, EXPLOSION);
                grow(randRow, randCol, randRow+1, randCol, ALL, EXPLOSION);
                grow(randRow, randCol, randRow-1, randCol, ALL, EXPLOSION);
                break;
            }
            case ROCK: {
                if (isTouching(randRow, randCol, WATER) && Math.random() < ROCK_WEATHER_CHANCE)
                    grid[randRow][randCol] = DIRT;
                if (isTouching(randRow, randCol, BLUE_FIRE) && Math.random() < ROCK_MELT_CHANCE)
                    grid[randRow][randCol] = LAVA;
                break;
            }
            case SNOW: {
                int randDir = (int)(Math.random() * 3) - 1;
                if (Math.random() < SNOW_MELT_CHANCE || isOnFire(randRow, randCol)){
                    grid[randRow][randCol] = WATER; break;
                }
                if (Math.random() >= SNOW_FALL_CHANCE) break;
                if (randDir == -1 || randDir == 1)
                    moveAndSwap(randRow, randCol, randRow, randCol+randDir);
                else 
                    moveAndSwap(randRow, randCol, randRow+1, randCol);
                break;
            }
            case WATER_TAP: {
                if (Math.random() >= TAP_SPAWN_CHANCE) break;
                grow(randRow, randCol, randRow, randCol+1, EMPTY, WATER);
                grow(randRow, randCol, randRow, randCol-1, EMPTY, WATER);
                grow(randRow, randCol, randRow+1, randCol, EMPTY, WATER);
                grow(randRow, randCol, randRow-1, randCol, EMPTY, WATER);
                break;
            }
            case FIRE_TAP: {
                if (Math.random() >= TAP_SPAWN_CHANCE) break;
                grow(randRow, randCol, randRow, randCol+1, EMPTY, FIRE);
                grow(randRow, randCol, randRow, randCol-1, EMPTY, FIRE);
                grow(randRow, randCol, randRow+1, randCol, EMPTY, FIRE);
                grow(randRow, randCol, randRow-1, randCol, EMPTY, FIRE);
                break;
            }
        }
    }
  
    
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
    private void grow(int row, int col, int newRow, int newCol, int foodParticle, int replaceParticle){
        if (!(inBounds(newRow, newCol) &&  (foodParticle == ALL || grid[newRow][newCol] == foodParticle)))
            return;
        if (grid[newRow][newCol] == DIAMOND) return;
        grid[newRow][newCol] = replaceParticle;
    }
    
    // Moves the old particle and sets its old spot to EMPTY
    private void moveAndDestroy(int row, int col, int newRow, int newCol){
        if (!inBounds(newRow, newCol) || grid[newRow][newCol] >= grid[row][col])
            return;
        grid[newRow][newCol] = grid[row][col];
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
    
    private boolean inBounds(int row, int col){
        if (row >= grid.length || row < 0) 
            return false; // out of bounds vertically
        if (col >= grid[row].length || col < 0)
            return false; //out of bounds horizontally
        return true;
    }
    
    // Do not modify
    public void run() {
        while (true) {
            for (int i = 0; i < display.getSpeed(); i++)
                step();
            updateDisplay();
            display.repaint();
            display.pause(1);  //wait for redrawing and for mouse
            int[] mouseLoc = display.getMouseLocation();
            if (mouseLoc != null)  //test if mouse clicked
                locationClicked(mouseLoc[0], mouseLoc[1], display.getTool(), display.getPenSize());
        }
    }
}
