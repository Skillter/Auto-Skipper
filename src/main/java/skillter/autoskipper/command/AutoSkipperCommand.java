package skillter.autoskipper.command;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.Collections;
import java.util.List;

public class AutoSkipperCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "autoskipper";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/" + this.getCommandName() + "[toggle|api_key|better_than_me|above]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {

    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        if (args.length == 0) {
            return getListOfStringsMatchingLastWord(args, "toggle", "api_key", "better_than_me", "above");
        }
        return null;
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }

    private static void sendHelp(EntityPlayerSP player) {
        player.addChatMessage(new ChatComponentText("Coded by Skillter"));
    }

}
