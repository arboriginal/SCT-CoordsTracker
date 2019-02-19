# SCT-CoordsTracker

This is a tracker addon for [SimpleCompass](https://www.spigotmc.org/resources/simplecompass.63140/).
It allows to track specific coordinates.

## How to install

- Drop the [jar file](https://github.com/arboriginal/SCT-CoordsTracker/releases) into your `plugins/SimpleCompass/trackers` folder
- Restart your server.

## Configuration

Edit the file `plugins/SimpleCompass/trackers/CoordsTracker.yml` (automatically created the first time the tracker is loaded).

Read [settings.yml](https://github.com/arboriginal/SCT-CoordsTracker/blob/master/src/settings.yml) to have a look on available parameters.

## Permissions

- To use this tracker, players must have:
    - **scompass.use**
    - **scompass.track**
    - **scompass.track.COORDS** (or **scompass.track.***)

**Optional:** (require you set `settings.autoload_target:true`)
    
- All named coords also use dynamic permissions to be automatically tracked on player join:
    - **scompass.track.auto.COORDS.`<name>`**
    - **scompass.track.auto.COORDS.base** for example to auto track the coordinate named "base"
- To auto track all named coords (without **scompass.track.auto.COORDS.`<name>`** for each), players need:
    - **scompass.track.auto.COORDS.***