package org.hqpots.bolt.utils;

import java.io.IOException;

import org.bukkit.ChatColor;

public class ServerInfo
  implements Cloneable
{
  public String name;
  public String address;
  int onlinePlayers = 0;
  int maxPlayers = 0;
  boolean online = false;
  Long lastUpdated = Long.valueOf(0L);
  
  public String getParsedName()
  {
    String response = ChatColor.YELLOW + "[";
    if (!this.online)
    {
      response = response + ChatColor.RED + "Offline";
    }
    else
    {
      response = response + ChatColor.GREEN + "" + this.onlinePlayers;
      response = response + ChatColor.YELLOW + "/";
      response = response + ChatColor.GREEN + "" + this.maxPlayers;
    }
    response = response + ChatColor.YELLOW + "]";
    return response;
  }
  
  public ServerInfo(String name, String address)
  {
    this.name = name;
    this.address = address;
  }
  
  public void updatePlayerCount()
  {
    Long start = Long.valueOf(System.currentTimeMillis());
    String[] ip = this.address.split(":");
    try
    {
      int[] players = Pinger.ping(ip[0], Integer.parseInt(ip[1]));
      if (this.lastUpdated.longValue() > start.longValue()) {
        return;
      }
      if ((players[0] == -1) || (players[1] == -1))
      {
        this.online = false;
      }
      else
      {
        this.online = true;
        this.onlinePlayers = players[0];
        this.maxPlayers = players[1];
      }
    }
    catch (IOException e)
    {
      this.online = false;
    }
    this.lastUpdated = Long.valueOf(System.currentTimeMillis());
  }
}
