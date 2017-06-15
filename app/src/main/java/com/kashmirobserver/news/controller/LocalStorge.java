package com.kashmirobserver.news.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.List;

public class LocalStorge {
	SharedPreferences mSharedPreferences;

	public LocalStorge(Context context) {
		mSharedPreferences = PreferenceManager
				.getDefaultSharedPreferences(context);
	}

	public String getstr(String key) {

		return mSharedPreferences.getString(key,"");
	}

	public void Setstr(String key,String val) {
		mSharedPreferences.edit().putString(key, val).apply();
	}


	public Boolean getbool(String key) {

		return mSharedPreferences.getBoolean(key,false);
	}

	public void Setbool(String key,Boolean val) {
		mSharedPreferences.edit().putBoolean(key, val).apply();
	}

	public int getint(String key) {

		return mSharedPreferences.getInt(key,0);
	}

	public void Setint(String key,int val) {
		mSharedPreferences.edit().putInt(key, val).apply();
	}
}
