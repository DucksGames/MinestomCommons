package com.ducksgames.commons.commands;

import net.minestom.server.MinecraftServer;
import net.minestom.server.command.ConsoleSender;
import net.minestom.server.command.builder.Command;

public class StopCommand extends Command {

    public StopCommand(String permission) {
        super("stop");
        setCondition((sender, commandString) -> sender instanceof ConsoleSender || sender.hasPermission(permission));
        setDefaultExecutor((sender, context) -> MinecraftServer.stopCleanly());
    }
}
