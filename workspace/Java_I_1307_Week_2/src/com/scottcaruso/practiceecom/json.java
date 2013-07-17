package com.scottcaruso.practiceecom;

import org.json.JSONObject;

public class json 
{
	public static JSONObject buildJSON()
	{
		//create Songs JSON
		JSONObject songsObject = new JSONObject();
		
		JSONObject queryObject = new JSONObject();

		for(Songs song : Songs.values())
		{
			JSONObject tracksObject = newJSONObject();
			
			//setArtist comes form an enum I never created
			tracksObject.put("artist", song.setArtist());
			tracksObject.put("album", song.setAlbum());
			tracksObject.put("info", song.setInfo());
			tracksObject.put("track name", song.setTrack());
			
			queryObject.put(song.name().toString(), tracksObject);
		}
		
		songsObject.put("query", queryObject);
		
		return songsObject;
	}
	
	public static String readJSON(String selected)
	{
		String result, artist, album, info, track;
		JSONObject object = buildJSON();
		
		artist = object.getJSONObject("query").getJSONObject(selected).getString("artist");
		
		
		return null;
	}
	
}
