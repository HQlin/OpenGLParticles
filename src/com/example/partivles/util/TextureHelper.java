package com.example.partivles.util;

import static android.opengl.GLES20.GL_LINEAR;
import static android.opengl.GLES20.GL_LINEAR_MIPMAP_LINEAR;
import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.GL_TEXTURE_MAG_FILTER;
import static android.opengl.GLES20.GL_TEXTURE_MIN_FILTER;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glDeleteTextures;
import static android.opengl.GLES20.glGenTextures;
import static android.opengl.GLES20.glGenerateMipmap;
import static android.opengl.GLES20.glTexParameteri;
import static android.opengl.GLUtils.texImage2D;

import com.example.particles.LoggerConfig;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class TextureHelper {
	private static final String TAG = "TestureHelper";
	/**
	 * ��������
	 * @param context    
	 * @param resourceId  ��Դid
	 * @return            ��������
	 */
	public static int loadTexture(Context context, int resourceId){		
		final int[] textureObjectIds = new int[1];
		glGenTextures(1, textureObjectIds, 0);
		
		if(textureObjectIds[0] == 0){
			if(LoggerConfig.ON){
				Log.w(TAG, "���ܴ����������");
			}
			return 0;
		}
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inScaled = false;//ͼ��ԭʼ����
		
		final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
		
		if(bitmap==null){
			if(LoggerConfig.ON){
				Log.w(TAG, "��Դ���ܱ�����");
			}
			glDeleteTextures(1, textureObjectIds, 0);
			return 0;
		}
		//�������ſɽ��ж��������
		glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);
		//opengl�������ģʽ http://blog.csdn.net/pizi0475/article/details/49740879
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);	
		//����λͼ���ݵ�����
		texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
		bitmap.recycle();
		//����MIP��ͼ
		glGenerateMipmap(GL_TEXTURE_2D);
		//��������
		glBindTexture(GL_TEXTURE_2D, 0);
		
		return textureObjectIds[0];
	}
}
