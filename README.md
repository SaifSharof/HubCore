HubCore
An Advanced HubCore for your server or any large network
Discord: discord.me/esdevelopment
![image](https://user-images.githubusercontent.com/67561225/115079778-52d05e80-9ec7-11eb-8732-40cf730e7b8e.png)
![image](https://user-images.githubusercontent.com/67561225/115079789-56fc7c00-9ec7-11eb-82e0-f6002048b3a2.png)
![image](https://user-images.githubusercontent.com/67561225/115079796-595ed600-9ec7-11eb-8a12-3ea98e613e59.png)
![image](https://user-images.githubusercontent.com/67561225/115079806-5bc13000-9ec7-11eb-863a-0f22c623f862.png)

### Config:
```yml
#################################################
#            HubCore Configuration              #
#         Discord: discord.me/esdevelopment     #
#################################################


ITEM:
  SERVER_SELECTOR:
    MATERIAL: BOOK
    SLOT: 4
    NAME: "&6Servers"

GLASS_PANE:
  MATERIAL: STAINED_GLASS_PANE
  NAME: " "
  AMOUNT: 1
  VALUE: 15

## MEDIA LINKS
MEDIA_LINKS:
  WEBSITE: "&7&l%bullet_point% &aWebsite: &fwww.server.com"
  DISCORD: "&7&l%bullet_point% &aDiscord: &fdiscord.gg/server"
  STORE: "&7&l%bullet_point% &aStore: &fstore.server.com"
## WELCOME MESSAGE (PAPI SUPPORTED)
WELCOME_MESSAGE:
  - "&7&m---------------------------------------"
  - ""
  - "&fWelcome to the &a&lServer!"
  - ""
  - "&7&l%bullet_point% &aStore: &fstore.server.com"
  - "&7&l%bullet_point% &aDiscord: &fdiscord.gg/server"
  - "&7&l%bullet_point% &aTwitter: &ftwitter.com/server"
  - ""
  - "&7&m---------------------------------------"

## SELECTOR CONFIGURATION (PAPI SUPPORTED)
SERVER_SELECTOR:
  SIZE: 27
  NAME: "&7Servers"
  SERVERS:
    1:
      SERVER: server
      MATERIAL: TNT
      SLOT: 13
      NAME: "&6&lServer"
      LORE:
        - ""
        - "&7&l%bullet_point% &a&lServer"
        - ""
        - "&7%right_arrow% &fClick to join &7%right_arrow%"

## SCOREBOARD CONFIGURATION (PAPI SUPPORTED)

SCOREBOARD:
  TITLE: "&4&lServer &fHub"
  LINES:
    - "&7&m-------------------"
    - "&cOnline"
    - "{online}"
    - ""
    - "&cPing: &f{ping}"
    - ""
    - "&7&ostore.server.com"
    - "&7&m-------------------"

Spawn:
  send:
    message: "&aSending you to spawn"
    sound: "PORTAL"
  void:
    enable: true
    message: "&aSending to spawn"
```
    
![image](https://user-images.githubusercontent.com/67561225/115079998-a6db4300-9ec7-11eb-8116-183088e7fddb.png)

- Discord: discord.me/esdevelopment
- Spigot: https://www.spigotmc.org/resources/hubcore-server-selector-scoreboard-and-more.91347/
