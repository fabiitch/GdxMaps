package com.nzt.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.HttpMethods;
import com.badlogic.gdx.Net.HttpRequest;
import com.badlogic.gdx.Net.HttpResponse;
import com.badlogic.gdx.Net.HttpResponseListener;
import com.badlogic.gdx.net.HttpRequestBuilder;

public class GMaps {

	public static String KEY = "AIzaSyCgdHanyklEnIMsnejdcoZtkRXKgCvvX7Y";
//	AIzaSyD5ln2T7u7WECk-zKWhSP44zg7I1OlH1is
	
//	ABQIAAAAPVqeWSHf73z5ueq1roWAtRTcx98JgL9tO18Qz4pOt4eQXE8GCBSwOFOnAV8zTxMPncAMt90L78uTDw
	public static String getGMapKey() {
		return KEY;
	}

	public static String geoLocateUrl() {
		return "https://www.googleapis.com/geolocation/v1/geolocate?" + KEY;
	}
	
	public static void geoLocate() {
		HttpRequestBuilder builder = new HttpRequestBuilder();
		HttpRequest  request = builder.newRequest().method(HttpMethods.GET)
				.url("https://www.googleapis.com/geolocation/v1/geolocate?key=AIzaSyCvI7akXTNylYlnbcVAB-LOaSGXXL-k0AY").build();
        request.setHeader("Content-Type", "application/json");
		Gdx.app.log("aa","aaaaaaaa");
		Gdx.net.sendHttpRequest(request, new HttpResponseListener() {
			@Override
			public void handleHttpResponse (HttpResponse httpResponse) {
				Gdx.app.log("handleHttpResponse","handleHttpResponse");
				Gdx.app.log("status", String.valueOf(httpResponse.getStatus().getStatusCode()));
				Gdx.app.log("header", httpResponse.getHeaders().toString());
				Gdx.app.log("HttpRequestExample", "response: " + httpResponse.getResultAsString());
				Gdx.app.log("HttpRequestExample", "response: " + httpResponse.getResult().length);
			}

			@Override
			public void failed (Throwable t) {
				Gdx.app.log("cc","cccc");
				Gdx.app.error("HttpRequestExample", "something went wrong", t);
			}

			@Override
			public void cancelled () {
				Gdx.app.log("ttt","ttt");
				Gdx.app.log("HttpRequestExample", "cancelled");
			}
		});
	}

	
}
