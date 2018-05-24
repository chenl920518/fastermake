package com.cbt.model;

import com.cbt.model.JsapiToken;
import com.cbt.wximpl.Wechatimpl;

public class SingleJsapi {

	private static SingleJsapi instance = null;
	
	private JsapiToken jsapiToken;

	public JsapiToken getJsapiToken() {
		return jsapiToken;
	}

	public void setJsapiToken(JsapiToken jsapiToken) {
		this.jsapiToken = jsapiToken;
	}

	private SingleJsapi() {
		jsapiToken = Wechatimpl.getJsapiToken();
		System.err.println("已重新获取jsapi_access_token:"+jsapiToken.getAccess_token());
		threadSleep();
	}

	public static SingleJsapi getInstance() {
		if (null == instance) {
			instance = new SingleJsapi();
		}
		return instance;
	}
	
	//线程休眠2小时
	private void threadSleep(){
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(7100 * 1000);
					instance = null;
				} catch (InterruptedException e) {
					System.err.println("休眠线程被打断！");
					e.printStackTrace();
				}	
			}
		}).start();
	}
	
}
