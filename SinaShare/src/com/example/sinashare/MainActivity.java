package com.example.sinashare;

import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	 /** 微博分享的接口实例 */
    private IWeiboShareAPI mWeiboShareAPI;
    
    /** 微博分享按钮 */
    //private Button mShareButton;
    
    /** 微博 ALL IN ONE 分享按钮 */
    private Button mShareAllInOneButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initialize();
	}
	
	
private void initialize() {
        
        // 创建微博 SDK 接口实例
        mWeiboShareAPI = WeiboShareSDK.createWeiboAPI(this, Constants.APP_KEY);
        
        // 获取微博客户端相关信息，如是否安装、支持 SDK 的版本
        boolean isInstalledWeibo = mWeiboShareAPI.isWeiboAppInstalled();
        int supportApiLevel = mWeiboShareAPI.getWeiboAppSupportAPI(); 
        
        
        
        // 设置注册按钮对应回调
        ((Button) findViewById(R.id.register_app_to_weibo)).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // 注册到新浪微博
                mWeiboShareAPI.registerApp();
                Toast.makeText(MainActivity.this, 
                        "注册成功", Toast.LENGTH_LONG).show();
                
                //mShareButton.setEnabled(true);
                mShareAllInOneButton.setEnabled(true);
            }
        });
        
        // 设置分享按钮对应回调
        /*mShareButton = (Button) findViewById(R.id.share_to_weibo);
        mShareButton.setEnabled(false);
        mShareButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WBShareMainActivity.this, WBShareActivity.class);
                i.putExtra(WBShareActivity.KEY_SHARE_TYPE, WBShareActivity.SHARE_CLIENT);
                startActivity(i);
            }
        });*/
        
        // 设置ALL IN ONE分享按钮对应回调
        mShareAllInOneButton = (Button) findViewById(R.id.share_to_weibo_all_in_one);
        mShareAllInOneButton.setEnabled(false);
        mShareAllInOneButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	Log.i("TAG", "mShareAllInOneButton");
                Intent i = new Intent(MainActivity.this, WBShareActivity.class);
                i.putExtra(WBShareActivity.KEY_SHARE_TYPE, WBShareActivity.SHARE_ALL_IN_ONE);
                startActivity(i);
            }
        });
    }

	
}
