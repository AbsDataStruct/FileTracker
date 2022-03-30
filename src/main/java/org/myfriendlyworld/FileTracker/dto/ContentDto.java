package org.myfriendlyworld.FileTracker.dto;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.util.List;

public class ContentDto {
	
	
	private static final char[] HEX = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};

	private String hashContent;   // always update both mdId and fileSize
	private long fileSize; 		  // always update both mdId and fileSize
	private List<PathDto> locations;
	private long creationDate = 0;
	private PathDto olderContent = null;
	private PathDto newerContent = null;
	private List<String> backupMediaNames = null;
	private boolean hasBackup = false;
	private char status = VALID;
	private String filename = null;
		
	public static final char VALID = 'V';           // currently in use
	public static final char DISAPEARED = 'D';		// no more present
	public static final char OLDCONTENT = 'O'; 		// old version
	public static final char UNKNOWNFROMMEDIA = 'M';// scanned from media but not present
	public static final char REMOVEDBYUSER = 'R';   // removed and confirmed by user
	public static final char DATABASEFILE = 'Z';
	
	
	private String computeContentHash(File aFile) throws Exception
	{
		MessageDigest digestmd5 = MessageDigest.getInstance("MD5"); // 16 bytes
		MessageDigest digestsha1 = MessageDigest.getInstance("SHA-512"); // 32 bytes
		// length 4 bytes
		FileInputStream input = new FileInputStream(aFile);
		try
		{
//			byte[] buffer = new byte[4096];
			byte[] buffer = new byte[1 << 25];
			int readLength = 0;
			while ((readLength = input.read(buffer)) > 0)
			{
				digestmd5.update(buffer, 0, readLength);
				digestsha1.update(buffer, 0, readLength);
			}
		}
		finally
		{
			input.close();
		}
		byte[] md5 = digestmd5.digest();
		byte[] sha1 = digestsha1.digest();
//		hashContent = sha1.
		StringBuilder builder = new StringBuilder(256);
		builder.append(aFile.length());
		builder.append('-');
		for (byte b: md5)
		{
			int i = b & 0xFF;
			builder.append(HEX[i >> 4]);
			builder.append(HEX[i & 0xF]);
		}
		builder.append('-');
		for (byte b: sha1)
		{
			int i = b & 0xFF;
			builder.append(HEX[i >> 4]);
			builder.append(HEX[i & 0xF]);
		}
//		builder.append(HEX[i & 0xF]);
		return builder.toString();
	}

}
