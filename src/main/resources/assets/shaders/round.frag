#version 100

uniform float radius;
uniform int width;
uniform int height;
uniform vec2 texelSize;


void main() {
//    vec2 pixel = gl_TexCoord[0].st;
//    vec3 color = vec3(0,0,0);
//    if(pixel.x>width/2*texelSize){
//        color = vec3(0,1,0);
//    }
    gl_FragColor = vec4(0,0,0, 1.0);

}