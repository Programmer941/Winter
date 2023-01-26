package com.winterclient.gui.shader;

import com.winterclient.util.GeneralUtil;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class Shader {

    public final int programID;
    private int vertexShaderID;
    private int fragmentShaderID;

    public Shader(String vert, String frag) {
        programID = GL20.glCreateProgram();

        vertexShaderID = createShader(GeneralUtil.getResource(vert), GL_VERTEX_SHADER);
        fragmentShaderID = createShader(GeneralUtil.getResource(frag), GL_FRAGMENT_SHADER);


        glAttachShader(programID, fragmentShaderID);
        glAttachShader(programID, vertexShaderID);

        glLinkProgram(programID);
        glValidateProgram(programID);

    }

    public void load() {
        glUseProgram(programID);
    }

    public void unload() {
        glUseProgram(0);
    }


    private int createShader(InputStream inputStream, int shaderType) {
        int shaderID = glCreateShader(shaderType);
        glShaderSource(shaderID, readInputStream(inputStream));
        glCompileShader(shaderID);
        if (GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader!");
            System.exit(-1);
        }
        return shaderID;
    }

    public String readInputStream(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null)
                stringBuilder.append(line).append('\n');

        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public int getUniform(String name) {
        return glGetUniformLocation(programID, name);
    }

    public void setUniformf(String name, float... args) {
        int loc = glGetUniformLocation(programID, name);
        switch (args.length) {
            case 1:
                glUniform1f(loc, args[0]);
                break;
            case 2:
                glUniform2f(loc, args[0], args[1]);
                break;
            case 3:
                glUniform3f(loc, args[0], args[1], args[2]);
                break;
            case 4:
                glUniform4f(loc, args[0], args[1], args[2], args[3]);
                break;
        }
    }

    public void setUniformi(String name, int... args) {
        int loc = glGetUniformLocation(programID, name);
        if (args.length > 1) glUniform2i(loc, args[0], args[1]);
        else glUniform1i(loc, args[0]);
    }

}
