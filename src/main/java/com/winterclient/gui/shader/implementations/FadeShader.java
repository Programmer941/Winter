package com.winterclient.gui.shader.implementations;

import com.winterclient.gui.shader.Shader;

public class FadeShader extends Shader {
    public FadeShader() {
        super("/assets/shaders/fade.vert", "/assets/shaders/fade.frag");
    }

    public void setupUniforms(float opacity) {
        setUniformi("textureIn", 0);
        setUniformf("opacity", opacity);
    }

    public void init(float opacity) {
        load();
        setupUniforms(opacity);
    }

    public void end() {
        unload();
    }

}
