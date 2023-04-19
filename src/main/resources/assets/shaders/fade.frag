#version 120


uniform float opacity;
uniform sampler2D textureIn;


void main() {
    vec2 samplePixel = gl_TexCoord[0].st;
    vec4 color =texture2D(textureIn, samplePixel).rgba;
    gl_FragColor = vec4(color.rgb,color.a*opacity);
}