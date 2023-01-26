#version 120

uniform sampler2D textureIn;
uniform vec2 texelSize, direction;
uniform float radius;
uniform float weights[256];

#define offset texelSize * direction

void main() {
    vec3 blr = texture2D(textureIn, gl_TexCoord[0].st).rgb * weights[0];

    for (float f = 1.0; f <= radius; f++) {
        vec2 pos1 = gl_TexCoord[0].st + f * offset;
        vec2 pos2 = gl_TexCoord[0].st - f * offset;

        blr += texture2D(textureIn, pos1).rgb * (weights[int(abs(f))]);
        blr += texture2D(textureIn, pos2).rgb * (weights[int(abs(f))]);
    }

    gl_FragColor = vec4(blr, 1.0);
}