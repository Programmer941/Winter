package com.winterclient.gui.shader.implementations;

import com.winterclient.gui.shader.Shader;
import net.minecraft.client.Minecraft;

public class RoundShader extends Shader {
    public RoundShader() {
        super("/assets/shaders/round.vert", "/assets/shaders/round.frag");
    }

    public void setupUniforms() {
        setUniformf("texelSize", 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferWidth, 1.0F / (float) Minecraft.getMinecraft().getFramebuffer().framebufferHeight);
        setUniformf("radius", 10f);
        setUniformi("width", 100);
        setUniformi("height", 300);

    }

    public void init() {
        load();
        setupUniforms();
    }

    public void end() {
        unload();
    }
}
