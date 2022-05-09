# minecraft_vpn_checker
Checks when user logs in, if thier ip is from a known vpn / proxy provider, if so, it kicks the player. Super useful to keep bots / hackers off your server.

# Setup
1. Put the plugin into your server's plugin folder
2. Run your server, the plugin will create its config files and disable itself
3. Stop the server
4. Get an api key from https://vpnapi.io/ (free plan has 1k requests per day)
5. Put your api key into the config.yml located in your servers plugin folder > IPChecker > config.yml & edit the disconnect messages to your liking
6. Run your server again and it should be working (test by connecting to a vpn and trying to join)
7. Profit?
