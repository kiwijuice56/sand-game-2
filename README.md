# sand-game-2
Expanded version of Cypress Ranch Computer Science II "Falling Sand" project.

![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot2.png)
![Gameplay in sand-game-2](https://github.com/kiwijuice56/sand-game-2/blob/main/img/screenshot1.png)

## Table of Elements

|  | Name | Description |
| ----------- | ----------- | ---|
| <img src="resources/empty.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Empty     | Erases other elements  |
| <img src="resources/gas.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Gas     | Inert substance that flows upwards and dissipates  |
| <img src="resources/acid gas.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Acid Gas     | Lingers for longer than gas and poisons life  |
| <img src="resources/hydrogen.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Hydrogen  | Extremely flammable gas|
| <img src="resources/fire.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Fire   | Spreads over flammable objects triggers explosions  |
| <img src="resources/blue fire.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Blue Fire   | Hotter and longer-lasting fire  |
| <img src="resources/acid.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Acid   | Melts through most materials |
| <img src="resources/alcohol.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Alcohol   | Flammable liquid that can poison life |
| <img src="resources/snow.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Snow   | Falls slowly and freezes water 
| <img src="resources/water.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Water   | Flows into containers and supports life |
| <img src="resources/ice.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Ice   | Creates crystal patterns in water|
| <img src="resources/fairy.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Fairy   | Flutters in the air, magically transforms toxic substances, and is immune to fire|
| <img src="resources/polliwog.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Polliwog   | Creates colonies in water, eats algae and urchins |
| <img src="resources/urchin.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Urchin   | Creates colonies in water, eats polliwogs |
| <img src="resources/spore.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Spore   | Grows into fungus in presence of dirt and water |
| <img src="resources/algae.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Algae   | Flammable blankets of life in water |
| <img src="resources/mercury.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Mercury   | Destroys all life |
| <img src="resources/sand.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Sand   | Basic falling particles, turns into glass when burned |
| <img src="resources/magic sand.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Magic Sand   | Falls sideways to the right |
| <img src="resources/sand duck.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Sand Duck  | Creates colonies in the sand, eats urchins and fungus |
| <img src="resources/penguin.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Penguin   | Creates colonies in the ice/snow, eats urchins and polliwogs |
| <img src="resources/dirt.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Dirt   | Basic falling particles |
| <img src="resources/lava.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Lava   | Extremely hot liquid that cools into rock and starts fires |
| <img src="resources/glass.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Glass   | Basic structural particles |
| <img src="resources/rock.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Rock   | Weathers into dirt when touching water and can melt to lava |
| <img src="resources/tnt.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| TNT   | Extremely explosive solid material |
| <img src="resources/slime.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Slime   | Grows inside of acid and dissolves into acid gas when harmed |
| <img src="resources/metal.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Metal   | Basic structural particles, can melt into mercury |
| <img src="resources/water tap.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| X-tap   | Spawns material X around it |
| <img src="resources/diamond.png" style="image-rendering:pixelated;image-rendering:-moz-crisp-edges;width:64px;height:64px;"/>| Diamond   | Indestructible material |
