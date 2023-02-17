#version 120

uniform sampler2D textureIn;
uniform vec2 texelSize, direction;
uniform float radius;
uniform float weights[256];


#define offset texelSize * direction

void main() {
    vec3 output = vec3(0.0);
    float totalWeight=0.0;
    for (float f = -radius; f <= radius; f++) {
        vec2 samplePixel = gl_TexCoord[0].st + f * offset;
        float weight = (weights[int(abs(f))]);
        output += texture2D(textureIn, samplePixel).rgb * weight;
        totalWeight+=weight;
    }

    gl_FragColor = vec4(output/totalWeight, 1.0);
}