package com.example.smsemailshare;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button bn = (Button)findViewById(R.id.bn);
		bn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showShareDialog();
			}
		});
		
		
	}


	/**
	    * 弹出分享列表
	    */
	   private void showShareDialog(){
	         AlertDialog.Builder builder = new AlertDialog.Builder( MainActivity.this);
	         builder.setTitle("选择分享类型");
	         builder.setItems(new String[]{"邮件","短信","其他"}, new DialogInterface.OnClickListener() {
	             
	            @Override
	            public void onClick(DialogInterface dialog, int which) {
	                dialog.dismiss();
	                switch (which) {
	                case 0: //邮件
	                    sendMail("http://www.baidu.com/");
	                    break;
	                 
	                case 1: //短信
	                    sendSMS("http://www.baidu.com/");
	                    break;
	                 
	                case 3: //调用系统分享
	                    /*Intent intent=new Intent(Intent.ACTION_SEND);
	                    intent.setType("text/plain");
	                    intent.putExtra(Intent.EXTRA_SUBJECT,"分享");  
	                    intent.putExtra(Intent.EXTRA_TEXT, "我正在浏览这个,觉得真不错,推荐给你哦~ 地址:"+"http://www.google.com.hk/");
	                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  
	                    startActivity(Intent.createChooser(intent, "share"));*/
	                	share("测试啦", null);
	                    break;
	                     
	                default:
	                    break;
	                }
	                 
	            }
	        });
	        builder.setNegativeButton( "取消" ,  new  DialogInterface.OnClickListener() {   
	           @Override    
	           public   void  onClick(DialogInterface dialog,  int  which) {   
	              dialog.dismiss();   
	          }   
	      });   
	      builder.create().show();
	    }
	    
	    
	   /**
	    * 发送邮件
	    * @param emailBody
	    */
	   private void sendMail(String emailUrl){
	        Intent email = new Intent(android.content.Intent.ACTION_SEND);
	        email.setType("plain/text");
	         
	        String emailBody = "我正在浏览这个,觉得真不错,推荐给你哦~ 地址:" + emailUrl;
	        String subjectStr = "测试邮件";
	        
	        //邮件主题
	        email.putExtra(android.content.Intent.EXTRA_SUBJECT, subjectStr);
	        //邮件内容
	        email.putExtra(android.content.Intent.EXTRA_TEXT, emailBody); 
	         
	        startActivityForResult(Intent.createChooser(email,  "请选择邮件发送内容" ), 1001 );
	    }
	    
	    
	   /**
	    * 发短信
	    */
	   private   void  sendSMS(String webUrl){ 
	      String smsBody = "我正在浏览这个,觉得真不错,推荐给你哦~ 地址:" + webUrl;
	      Uri smsToUri = Uri.parse( "smsto:" ); 
	      Intent sendIntent =  new  Intent(Intent.ACTION_VIEW, smsToUri); 
	       //sendIntent.putExtra("address", "123456"); // 电话号码，这行去掉的话，默认就没有电话  
	      //短信内容
	      sendIntent.putExtra( "sms_body", smsBody); 
	      sendIntent.setType( "vnd.android-dir/mms-sms" ); 
	      startActivityForResult(sendIntent, 1002 ); 
	   }
	   
	   //系统分享
	    private void share(String content, Uri uri){  
	        Intent shareIntent = new Intent(Intent.ACTION_SEND);   
	        if(uri!=null){  
	            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);  
	            shareIntent.setType("image/*");   
	            //当用户选择短信时使用sms_body取得文字  
	            shareIntent.putExtra("sms_body", content);  
	        }else{  
	            shareIntent.setType("text/plain");   
	        }  
	        shareIntent.putExtra(Intent.EXTRA_TEXT, content);  
	        //自定义选择框的标题  
	        //startActivity(Intent.createChooser(shareIntent, "邀请好友"));  
	        //系统默认标题  
	        startActivity(shareIntent);  
	    }  
	   
	
}
