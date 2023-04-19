#version 120

uniform vec2 texelSize;
uniform float bottom;
uniform sampler2D textureIn;


#define offset texelSize * direction

void main() {
        vec2 pixel = gl_TexCoord[0].st;
    float bottom2 = 1-((1-bottom)*2);
        float strength = 1;
        if(pixel.y>bottom2){
            strength = smoothstep(bottom,bottom2,pixel.y);
        }
    if(pixel.y>bottom)
            strength=0.0;
        vec3 color = texture2D(textureIn,pixel).rgb;

    gl_FragColor = vec4(color, strength);
}