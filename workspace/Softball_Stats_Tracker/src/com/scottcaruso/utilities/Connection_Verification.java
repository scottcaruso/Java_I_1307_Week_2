package com.scottcaruso.utilities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Connection_Verification {
	
	public static boolean areWeConnected (Context context)
	{
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		//String connectionType = "Unavailable";
		boolean connectionStatus = false;
		if(ni!=null)
		{
			if(ni.isConnected())
			{
				connectionStatus = true;
				//connectionType = ni.getTypeName();
			} 
		}
		
		return connectionStatus;
	}

}
