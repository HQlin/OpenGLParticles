package com.example.particles;

import android.os.Bundle;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GLSurfaceView glSurfaceView;
	private boolean rendererSet;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//1.����GLSurfaceViewʵ��
		glSurfaceView = new GLSurfaceView(this);
		//2.���ϵͳ�Ƿ�֧��opengl es2.0
		final ActivityManager am = (ActivityManager)getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo ci = am.getDeviceConfigurationInfo();
		final boolean supportsEs2 = ci.reqGlEsVersion >= 0x20000;
		final ParticlesRenderer renderer = new ParticlesRenderer(this);
		//3.Ϊopengl es2.0������Ⱦ����
		if(supportsEs2){
			glSurfaceView.setEGLContextClientVersion(2);
			glSurfaceView.setRenderer(renderer);
			rendererSet = true;
		} else {
			Toast.makeText(this, "�豸��֧��opengl es 2.0", Toast.LENGTH_SHORT).show();
			return;
		}		
		
		//4.��ʾGLSurfaceView
		setContentView(glSurfaceView);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(rendererSet){
			glSurfaceView.onResume();
		}
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(rendererSet){
			glSurfaceView.onPause();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}