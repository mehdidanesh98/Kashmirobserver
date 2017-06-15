package com.kashmirobserver.news.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.widget.Toast;

public class Tools {

	public static boolean isOnline(Context mContext) {
		ConnectivityManager cm = (ConnectivityManager) mContext
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo = cm.getActiveNetworkInfo();
		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		} else {
			return false;
		}
	}
	

	public static boolean fileExists(Context context, String fileName) {
		File file = context.getFileStreamPath(fileName);
		return file.exists();
	}

	public static void writeToFile(Context mContext, String fileName,
			String text) {
		FileOutputStream fos;
		try {
			fos = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.write(text.getBytes());
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToExternalStorage(Context mContext, String fileName,
			String text) {
		String path="/IsBooks/Books/"+fileName;
		
		String root = Environment.getExternalStorageDirectory().toString();
	    File myDir = new File(root + path); 
	    myDir.mkdirs();
	    File outputfile=new File(myDir,"/book.json");
		FileOutputStream outputStream;
		try {
			outputStream = new FileOutputStream(outputfile);
		    outputStream.write(text.getBytes());
		    outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Toast.makeText(mContext, "Downloaded", Toast.LENGTH_LONG).show();
	}

	public static String readOfFile(Context mContext, String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new InputStreamReader(
					mContext.openFileInput(fileName)));
			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString + "\n");
			}

			return stringBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String readOfAssets(Context mContext, String fileName) {
		StringBuffer stringBuffer = new StringBuffer();
		BufferedReader inputReader = null;
		try {
			inputReader = new BufferedReader(new InputStreamReader(mContext
					.getAssets().open(fileName), "UTF-8"));
			String inputString;

			while ((inputString = inputReader.readLine()) != null) {
				stringBuffer.append(inputString + "\n");
			}

			return stringBuffer.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if (inputReader != null) {
				try {
					inputReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static String readLastUpdate(Context mContext, String fileName) {

		StringBuffer stringBuffer = new StringBuffer();
		try {
			BufferedReader inputReader = new BufferedReader(
					new InputStreamReader(mContext.openFileInput(fileName)));
			String inputString;
			for (int i = 0; i < 5; i++) {
				inputString = inputReader.readLine();
				stringBuffer.append(inputString + "\n");
			}

			int start = stringBuffer.indexOf("20");
			String lastUpdate = stringBuffer.substring(start, start + 19);

			return lastUpdate;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String md5(String s) {
		String MD5 = "MD5";
		try {
			// Create MD5 Hash
			MessageDigest digest = MessageDigest.getInstance(MD5);
			digest.update(s.getBytes());
			byte messageDigest[] = digest.digest();

			// Create Hex String
			StringBuilder hexString = new StringBuilder();
			for (byte aMessageDigest : messageDigest) {
				String h = Integer.toHexString(0xFF & aMessageDigest);
				while (h.length() < 2)
					h = "0" + h;
				hexString.append(h);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
