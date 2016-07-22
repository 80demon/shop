package com.panjoy.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class FileUploadAction extends ActionSupport {

	private File file;
	private String fileFileName;
	private String fileContentType;
	private long maximumSize;
	private String allowedTypes;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileName() {
		return fileFileName;
	}

	public void setFileName(String fileName) {
		this.fileFileName = fileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public long getMaximumSize() {
		return maximumSize;
	}

	public void setMaximumSize(long maximumSize) {
		this.maximumSize = maximumSize;
	}

	public String getAllowedTypes() {
		return allowedTypes;
	}

	public void setAllowedTypes(String allowedTypes) {
		this.allowedTypes = allowedTypes;
	}
	@Override
	public String execute() throws Exception {
		File uploadFile = new File(ServletActionContext.getServletContext()
				.getRealPath("upload"));
		System.out.println("path-----------:"+ServletActionContext.getServletContext()
				.getRealPath("upload"));
		if (!uploadFile.exists()) {
			uploadFile.mkdir();
		}
	
		// 验证文件大小及格式
		if (maximumSize < file.length()) {
			return "error";
		}

		boolean flag = false;
		String[] allowedTypesStr = allowedTypes.split(",");
		for (int i = 0; i < allowedTypesStr.length; i++) {
			if (fileContentType.equals(allowedTypesStr[i])) {
				flag = true;
			}
		}
		if (flag == false) {
			Map request = (Map) ActionContext.getContext().get("request");
			request.put("errorMassage", "文件类型不合法！");
			System.out.println(request.toString());
			return "error";
		}
		InputStream fis = null;
		OutputStream fos = null;
		try {
			fis = new FileInputStream(file);
		     String _extName = fileFileName.substring(fileFileName.indexOf(".")); // 扩展名  
		     String  filename = UUID.randomUUID().toString() + _extName; //图片名称定义为UUID，防止并发文件被覆盖的情况  
		        // filename = System.nanoTime() + _extName; //nanoTime数值  
		      //String filename = System.currentTimeMillis() + _extName; // currentTimeMillis数值  
			fos = new FileOutputStream(new File(uploadFile, filename));
			System.out.println("fileName:" + fileFileName);
			byte[] buffer = new byte[1024];
			int length = 0;
			while((length=fis.read(buffer))!=-1){
				fos.write(buffer, 0, length);
				
			}
/*			while (-1 != (length = fis.read(buffer, length, buffer.length))) {
				fos.write(buffer);
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";

		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					System.out.println("FileInputStream关闭失败");
					e.printStackTrace();
					return "error";
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					System.out.println("FileOutputStream关闭失败");
					e.printStackTrace();
					return "error";
				}
			}
		}
		return "success";
	}
/*	@SuppressWarnings("unchecked")
	public String upload() {
	

	}*/

}
