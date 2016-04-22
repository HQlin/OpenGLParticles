package com.example.particles.programs;

import com.example.partivles.util.ShaderHelper;
import com.example.partivles.util.TRReader;

import android.content.Context;

public class ShaderProgram {
	//Uniform constants
	protected static final String U_MATRIX = "u_Matrix";
	protected static final String U_TEXTURE_UNIT = "u_TextureUnit";
	protected static final String U_COLOR = "u_Color";
	protected static final String U_TIME = "u_Time";
		
	//Attribute constants
	protected static final String A_POSITION = "a_Position";
	protected static final String A_COLOR = "a_Color";
	protected static final String A_TEXTURE_COORDINATES = "a_TextureCoordinates";
	protected static final String A_DIRECTION_VECTOR = "a_DirectionVector";
	protected static final String A_PARTICLE_START_TIME = "a_ParticleStartTime";
	
	protected final int program;
	protected ShaderProgram(Context context, int vertexShaderResId,
			int fragmentShaderResId){
		program = ShaderHelper.buildProgram(
				TRReader.readTFfromRes(context, vertexShaderResId),
				TRReader.readTFfromRes(context, fragmentShaderResId));
	}
	
}
