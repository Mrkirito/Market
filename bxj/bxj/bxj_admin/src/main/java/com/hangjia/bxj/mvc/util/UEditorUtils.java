package com.hangjia.bxj.mvc.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.multipart.MultipartFile;

public class UEditorUtils {
	
	private static final Log log = LogFactory.getLog(UEditorUtils.class);

	public static UECatchResp saveRemoteFile(String[] sources, String storyBasePath, String requestBasePath) {
		
		if (sources == null || sources.length == 0) {
			return null;
		}
		
		UECatchRespEntry[] list = new UECatchRespEntry[sources.length];
		
		for (int i = 0; i < list.length; i++) {
			String src = sources[i];
			String filename = FilenameUtils.resolveFileName(src);
			String state;
			
			String fullName = storyBasePath + filename;
			try {
				doSave(src, fullName);
				state = "SUCCESS";
				
				if (log.isDebugEnabled()) {
					log.debug("保存文件：" + fullName + "，源：" + src);
				}
				
			} catch (Exception e) {
				state = "FAIL";
				log.error("保存远程文件异常", e);
			}
			
			list[i] = new UECatchRespEntry(state, filename, src, requestBasePath + filename);
		}
		
		return new UECatchResp(list);
		
	}
	
	public static Object uploadFile(MultipartFile upfile, String storyBasePath, String requestBasePath) {
		
		String filename = FilenameUtils.resolveFileName(upfile.getOriginalFilename());
		String state;
		try {
			upfile.transferTo(new File(storyBasePath + filename));
			state = "SUCCESS";
		} catch (IOException e) {
			state = "FAIL";
			log.error("上传文件异常", e);
		}
		
		return new UECatchRespEntry(state, filename, null, requestBasePath + filename);
	}
	
	private static void doSave(String src, String saveFile) throws IOException {
		
		HttpURLConnection conn = null;
		DataInputStream in = null;
		DataOutputStream out = null;
		try {
			
			URL url = new URL(src);
			
			conn = (HttpURLConnection) url.openConnection();
			
			in = new DataInputStream(conn.getInputStream());
            out = new DataOutputStream(new FileOutputStream(saveFile));
            
            byte[] buffer = new byte[4096];
            int count = 0;
            while ((count = in.read(buffer)) > 0) {
                out.write(buffer, 0, count);
            }
		} finally {
			
			if (in != null) {
				in.close();
			}
			
			if (out != null) {
				out.close();
			}
			
			if (conn != null) {
				conn.disconnect();
			}
			
		}
		
	}
	
	public static class UECatchResp {
		
		private final String state;
		
		private final UECatchRespEntry[] list;

		public UECatchResp(UECatchRespEntry[] list) {
			this.list = list;
			this.state = "SUCCESS";
		}

		public String getState() {
			return state;
		}

		public UECatchRespEntry[] getList() {
			return list;
		}
		
	}
	
	public static class UECatchRespEntry {
		
		private final String state;
		
		private final String title;
		
		private final String source;
		
		private final String url;

		public UECatchRespEntry(String state, String title, String source, String url) {
			this.state = state;
			this.title = title;
			this.source = source;
			this.url = url;
		}

		public String getState() {
			return state;
		}

		public String getTitle() {
			return title;
		}

		public String getSource() {
			return source;
		}

		public String getUrl() {
			return url;
		}
		
	}
	//{"state": "SUCCESS", list: [{"state": "SUCCESS","title": "1467275561432060056.jpg","source": "http://img1.gtimg.com/news/pics/hv1/92/126/2091/135999497.jpg","url": "/ueditor/jsp/upload/image/20160630/1467275561432060056.jpg","size": "65748"} ]}

	public static class FileListEntry {
		
		private String state;
		
		private String url;

		public FileListEntry(String url) {
			state = "SUCCESS";
			this.url = url;
		}
		
		public String getState() {
			return state;
		}
		
		public String getUrl() {
			return url;
		}
		
	}
	
	public static class FileList {
		
		private String state;
		
		private int total;
		
		private int start;
		
		private FileListEntry[] list;

		public FileList(FileListEntry[] list, int total, int start) {
			state = "SUCCESS";
			this.list = list;
			this.total = total;
			this.start = start;
		}

		public String getState() {
			return state;
		}

		public int getTotal() {
			return total;
		}

		public int getStart() {
			return start;
		}

		public FileListEntry[] getList() {
			return list;
		}
		
	}
	
	public static Object listFile(int start, int size, String dir, String requestBasePath) {
		File path = new File(dir);
		File[] files = path.listFiles();
		
		// 如果文件夹不存在，会返回 null。
		if (files == null) {
			return new FileList(null, 0, start);
		}
		
		Arrays.sort(files, new Comparator<File>() {
			
			@Override
			public int compare(File f1, File f2) {
				return (int) (f2.lastModified() - f1.lastModified());
			}
		});
		
		int total = files.length;
		
		int max = start + size;
		
		if (max > total) {
			max = total;
		}
		
		FileListEntry[] ary = new FileListEntry[size];
		
		int index = 0;
		for (int i = start; i < max; i++) {
			File file = files[i];
			ary[index++] = new FileListEntry(requestBasePath + file.getName());
			
		}
		
		return new FileList(ary, total, start);
	}

}
