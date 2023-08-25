package com.ducksgames.commons;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.fakeplayer.FakePlayer;
import net.minestom.server.event.EventFilter;
import net.minestom.server.event.EventNode;
import net.minestom.server.event.player.PlayerChatEvent;
import net.minestom.server.event.player.PlayerCommandEvent;
import net.minestom.server.event.player.PlayerDisconnectEvent;
import net.minestom.server.event.player.PlayerLoginEvent;
import net.minestom.server.event.trait.PlayerEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static net.kyori.adventure.text.minimessage.MiniMessage.miniMessage;

public class ConsoleLogger {
    public static final Logger LOGGER = LoggerFactory.getLogger("");

    public static void enable() {
        EventNode<PlayerEvent> playerLoggerNode = EventNode.value("player-logger", EventFilter.PLAYER, p -> !(p instanceof FakePlayer));
        playerLoggerNode
                .addListener(PlayerChatEvent.class, event -> LOGGER.info(miniMessage().serialize(event.getChatFormatFunction().apply(event))))
                .addListener(PlayerDisconnectEvent.class, event -> LOGGER.info(String.format("%s lost connection: Disconnected", event.getPlayer().getUsername())))
                .addListener(PlayerLoginEvent.class, event -> LOGGER.info(String.format("%s[%s] logged in successfully with entity id %s", event.getPlayer().getUsername(), event.getPlayer().getPlayerConnection().getRemoteAddress(), event.getPlayer().getEntityId())))
                .addListener(PlayerCommandEvent.class, event -> LOGGER.info(String.format("%s issued server command: /%s", event.getPlayer().getUsername(), event.getCommand())))
        ;
        playerLoggerNode.setPriority(Integer.MAX_VALUE);
        MinecraftServer.getGlobalEventHandler().addChild(playerLoggerNode);
    }

}
