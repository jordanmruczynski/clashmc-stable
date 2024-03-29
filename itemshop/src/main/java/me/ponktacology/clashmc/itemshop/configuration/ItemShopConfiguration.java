package me.ponktacology.clashmc.itemshop.configuration;

import me.ponktacology.clashmc.api.configuration.GsonConfiguration;
import me.ponktacology.clashmc.api.serializer.Serializer;


import java.util.Map;
import java.util.TreeMap;

public class ItemShopConfiguration extends GsonConfiguration {

  public ItemShopConfiguration(Serializer serializer) {
    super("ItemShop/config", serializer);
  }


  @Override
  public Map<String, Object> getDefaults() {
    Map<String, Object> defaults = new TreeMap<>();

    defaults.put("main", false);
    defaults.put("itemshop-mysql-url", "jdbc:mysql://94.130.107.200/prod_clashmc?user=prod_clashmc&password=st78IiBdhQ");

    return defaults;
  }

  public boolean isMain() {
    return get("main");
  }


  public String getItemShopMySqlUrl() {
    return get("itemshop-mysql-url");
  }
}
