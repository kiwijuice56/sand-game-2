# sand-game-2
Expanded version of Cypress Ranch Computer Science II "Falling Sand" project.

![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot2.png)
![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot1.png)

## Table of Elements
<style>
img { 
    image-rendering: pixelated;
    image-rendering: -moz-crisp-edges;
}
</style>

|  | Name | Description |
| ----------- | ----------- | ---|
| <img src="resources/empty.png" width="64" height="64"/>| Empty     | Erases other elements  |
| <img src="resources/gas.png" width="64" height="64"/>| Gas     | Inert substance that flows upwards and dissipates  |
| <img src="resources/acid gas.png" width="64" height="64"/>| Acid Gas     | Lingers for longer than gas and poisons life  |
| <img src="resources/hydrogen.png" width="64" height="64"/>| Hydrogen  | Extremely flammable gas|
| <img src="resources/fire.png" width="64" height="64"/>| Fire   | Spreads over flammable objects triggers explosions  |
| <img src="resources/blue fire.png" width="64" height="64"/>| Blue Fire   | Hotter and longer-lasting fire  |
| <img src="resources/acid.png" width="64" height="64"/>| Acid   | Melts through most materials |
| <img src="resources/alcohol.png" width="64" height="64"/>| Alcohol   | Flammable liquid that can poison life |
| <img src="resources/snow.png" width="64" height="64"/>| Snow   | Falls slowly and freezes water 
| <img src="resources/water.png" width="64" height="64"/>| Water   | Flows into containers and supports life |
| <img src="resources/ice.png" width="64" height="64"/>| Ice   | Creates crystal patterns in water|
| <img src="resources/fairy.png" width="64" height="64"/>| Fairy   | Flutters in the air, magically transforms toxic substances, and is immune to fire|
| <img src="resources/polliwog.png" width="64" height="64"/>| Polliwog   | Creates colonies in water, eats algae and urchins |
| <img src="resources/urchin.png" width="64" height="64"/>| Urchin   | Creates colonies in water, eats polliwogs |
| <img src="resources/spore.png" width="64" height="64"/>| Spore   | Grows into fungus in presence of dirt and water |
| <img src="resources/algae.png" width="64" height="64"/>| Algae   | Flammable blankets of life in water |
| <img src="resources/mercury.png" width="64" height="64"/>| Mercury   | Destroys all life |
| <img src="resources/sand.png" width="64" height="64"/>| Sand   | Basic falling particles, turns into glass when burned |
| <img src="resources/magic sand.png" width="64" height="64"/>| Magic Sand   | Falls sideways to the right |
| <img src="resources/sand duck.png" width="64" height="64"/>| Sand Duck  | Creates colonies in the sand, eats urchins and fungus |
| <img src="resources/penguin.png" width="64" height="64"/>| Penguin   | Creates colonies in the ice/snow, eats urchins and polliwogs |
| <img src="resources/dirt.png" width="64" height="64"/>| Dirt   | Basic falling particles |
| <img src="resources/lava.png" width="64" height="64"/>| Lava   | Extremely hot liquid that cools into rock and starts fires |
| <img src="resources/glass.png" width="64" height="64"/>| Glass   | Basic structural particles |
| <img src="resources/rock.png" width="64" height="64"/>| Rock   | Weathers into dirt when touching water and can melt to lava |
| <img src="resources/tnt.png" width="64" height="64"/>| TNT   | Extremely explosive solid material |
| <img src="resources/slime.png" width="64" height="64"/>| Slime   | Grows inside of acid and dissolves into acid gas when harmed |
| <img src="resources/metal.png" width="64" height="64"/>| Metal   | Basic structural particles, can melt into mercury |
| <img src="resources/wate tap.png" width="64" height="64"/>| X-tap   | Spawns material X around it |
| <img src="resources/diamond.png" width="64" height="64"/>| Diamond   | Indestructible material |
