package skillter.autoskipper;

import javax.swing.*;

public class AntiJarLaunch {


    public static void main(String[] args) {
        String title = Reference.MOD_NAME;
        String message = "This is a Forge mod, you can't launch it. Place it in the mod folder in the main directory of Minecraft.\n\nMod Name: " + Reference.MOD_NAME + "\nMod ID: " + Reference.MOD_ID + "\nMod Version: " + Reference.VERSION + "\nMinecraft Version: " + Reference.ACCEPTED_MINECRAFT_VERSIONS;

        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

}
