# sand-game-2
Expanded version of Cypress Ranch Computer Science II "Falling Sand" project.

![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot2.png)
![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot1.png)

## Table of Elements
<style>
img { 
    image-rendering: pixelated;
    image-rendering: -moz-crisp-edges;
    width: 64px;
    height: 64px;
}
</style>

|  | Name | Description |
| ----------- | ----------- | ---|
| <img src="resources/empty.png"/>| Empty     | Erases other elements  |
| <img src="resources/gas.png"/>| Gas     | Inert substance that flows upwards and dissipates  |
| <img src="resources/acid gas.png"/>| Acid Gas     | Lingers for longer than gas and poisons life  |
| <img src="resources/hydrogen.png"/>| Hydrogen  | Extremely flammable gas|
| <img src="resources/fire.png"/>| Fire   | Spreads over flammable objects triggers explosions  |
| <img src="resources/blue fire.png"/>| Blue Fire   | Hotter and longer-lasting fire  |
| <img src="resources/acid.png"/>| Acid   | Melts through most materials |
| <img src="resources/alcohol.png"/>| Alcohol   | Flammable liquid that can poison life |
| <img src="resources/snow.png"/>| Snow   | Falls slowly and freezes water 
| <img src="resources/water.png"/>| Water   | Flows into containers and supports life |
| <img src="resources/ice.png"/>| Ice   | Creates crystal patterns in water|
| <img src="resources/fairy.png"/>| Fairy   | Flutters in the air, magically transforms toxic substances, and is immune to fire|
| <img src="resources/polliwog.png"/>| Polliwog   | Creates colonies in water, eats algae and urchins |
| <img src="resources/urchin.png"/>| Urchin   | Creates colonies in water, eats polliwogs |
| <img src="resources/spore.png"/>| Spore   | Grows into fungus in presence of dirt and water |
| <img src="resources/algae.png"/>| Algae   | Flammable blankets of life in water |
| <img src="resources/mercury.png"/>| Mercury   | Destroys all life |
| <img src="resources/sand.png"/>| Sand   | Basic falling particles, turns into glass when burned |
| <img src="resources/magic sand.png"/>| Magic Sand   | Falls sideways to the right |
| <img src="resources/sand duck.png"/>| Sand Duck  | Creates colonies in the sand, eats urchins and fungus |
| <img src="resources/penguin.png"/>| Penguin   | Creates colonies in the ice/snow, eats urchins and polliwogs |
| <img src="resources/dirt.png"/>| Dirt   | Basic falling particles |
| <img src="resources/lava.png"/>| Lava   | Extremely hot liquid that cools into rock and starts fires |
| <img src="resources/glass.png"/>| Glass   | Basic structural particles |
| <img src="resources/rock.png"/>| Rock   | Weathers into dirt when touching water and can melt to lava |
| <img src="resources/tnt.png"/>| TNT   | Extremely explosive solid material |
| <img src="resources/slime.png"/>| Slime   | Grows inside of acid and dissolves into acid gas when harmed |
| <img src="resources/metal.png"/>| Metal   | Basic structural particles, can melt into mercury |
| <img src="resources/water tap.png"/>| X-tap   | Spawns material X around it |
| <img src="resources/diamond.png"/>| Diamond   | Indestructible material |
