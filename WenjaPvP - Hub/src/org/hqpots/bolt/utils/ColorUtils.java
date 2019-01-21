package org.hqpots.bolt.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.ChatColor;

public class ColorUtils
{
  public String translateFromString(String text)
  {
    return StringEscapeUtils.unescapeJava(ChatColor.translateAlternateColorCodes('&', text));
  }

  public List<String> translateFromArray(List<String> text)
  {
    List<String> messages = new ArrayList<>();
    
    for (String string : text)
    {
      messages.add(translateFromString(string));
    }
    return messages;
  }
}