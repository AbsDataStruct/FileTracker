package org.myfriendlyworld.FileTracker.dto;

import java.io.Serializable;

import lombok.Getter;

@Getter
public class PathDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7928980992383341922L;
	long hashPath;
	long hashStorage;
	long[] hashVolume;
	String pathWithinStorage;
	
	public PathDto (long pathHash, String path){
		this.hashPath=pathHash;
		this.pathWithinStorage=path;
	}
}
