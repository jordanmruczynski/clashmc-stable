package me.ponktacology.clashmc.kit.kit.adapter;

import com.google.common.base.Strings;
import lombok.RequiredArgsConstructor;
import me.ponktacology.clashmc.kit.kit.Kit;
import me.ponktacology.clashmc.kit.kit.cache.KitCache;
import me.vaperion.blade.command.argument.BladeProvider;
import me.vaperion.blade.command.container.BladeParameter;
import me.vaperion.blade.command.context.BladeContext;
import me.vaperion.blade.command.exception.BladeExitMessage;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;



import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class KitParameterAdapter implements BladeProvider<Kit> {


  private final KitCache kitCache;

  
  @Override
  public Kit provide(
       BladeContext bladeContext,
       BladeParameter bladeParameter,
       String input)
      throws BladeExitMessage {
    if (input == null) return null;
    input = input.trim();

    Player player = bladeContext.sender().parseAs(Player.class);

    if (player != null && (input.isEmpty() || input.equalsIgnoreCase("self"))) {
      input = bladeContext.sender().getName();
    }

    String finalInput = input;
    return this.kitCache
        .get(input)
        .orElseThrow(
            () ->
                new BladeExitMessage(
                    ChatColor.RED
                        + "Nie znaleziono zestawu "
                        + ChatColor.YELLOW
                        + finalInput
                        + ChatColor.RED
                        + "."));
  }


  @Override
  public List<String> suggest( BladeContext context,  String input)
      throws BladeExitMessage {
    return this.kitCache.values().stream()
        .map(Kit::getName)
        .filter(
            it ->
                Strings.isNullOrEmpty(input)
                    || it.toLowerCase(Locale.ROOT).startsWith(input.toLowerCase(Locale.ROOT)))
        .collect(Collectors.toList());
  }
}
