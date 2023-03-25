package com.winterclient.mixins;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;
import net.minecraft.util.Session;
import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.MixinEnvironment;
import org.spongepowered.asm.mixin.Mixins;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* Tweaker Used to Start Mixin Bootstrap - called in Launch Arguments */
public class WinterClientTweaker implements ITweaker {

    // List of Launch Arguments for getLaunchArguments[]
    private final List<String> launchArguments = new ArrayList<>();

    @Override
    public void acceptOptions(List<String> args, File gameDir, File assetsDir, String profile) {
        //this.launchArguments.addAll(args);
        for(String arg: args){
            System.out.println(arg);
        }

        if (!args.contains("--version") && profile != null) {
            System.out.println("not adding version");
//            launchArguments.add("--version");
//            launchArguments.add(profile);
        }

        if (!args.contains("--assetsDir") && assetsDir != null) {
            System.out.println("not adding assetsDir");
//            launchArguments.add("--assetsDir");
//            launchArguments.add(assetsDir.getAbsolutePath());
        }

        if (!args.contains("--gameDir") && gameDir != null) {
            System.out.println("not adding gameDir");
//            launchArguments.add("--gameDir");
//            launchArguments.add(gameDir.getAbsolutePath());
        }

        for (String launchArg : launchArguments){
            System.out.println("launchArg: "+launchArg);
        }
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        MixinBootstrap.init();

        MixinEnvironment env = MixinEnvironment.getDefaultEnvironment();
        Mixins.addConfiguration("mixins.winterclient.json");

        if (env.getObfuscationContext() == null) {
            env.setObfuscationContext("notch");
        }

        env.setSide(MixinEnvironment.Side.CLIENT);
    }

    @Override
    public String getLaunchTarget() {
        return "net.minecraft.client.main.Main";
    }

    @Override
    public String[] getLaunchArguments() {
        return launchArguments.toArray(new String[0]);
    }
}