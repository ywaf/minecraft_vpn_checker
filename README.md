# minecraft_vpn_checker
Checks when user logs in, if thier ip is from a known vpn / proxy provider, if so, it kicks the player. Super useful to keep bots / hackers off your server.

Compiled for 1.12 but should work on any version (not tested).

# Setup
1. Build from source / download the plugin jar from the releases
2. Put the jar into your servers plugin folder
3. Run your server, the plugin will create its config files and disable itself
4. Stop the server
5. Get an api key from https://vpnapi.io/ (free plan has 1k requests per day)
6. Put your api key into the config.yml located in your servers plugin folder > IPChecker > config.yml & edit the disconnect messages to your liking
7. Run your server again and it should be working (test by connecting to a vpn and trying to join)
8. Profit?
