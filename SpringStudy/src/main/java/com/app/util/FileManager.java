package com.app.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.file.FileInfo;

public class FileManager {

	static final String FILE_STORAGE_PATH = "d:/fileStorage/";
	static final String FILE_URL_PATH = "/fileStorage/";
	
	
	public static FileInfo storeFile(MultipartFile file) throws IllegalStateException, IOException {
		//매개변수 : 저장하려고 하는 파일
		
		//파일 저장 -> 저장한 파일에 대한 정보 -> 파일정보객체 (FileInfo)
		
		FileInfo fileInfo = new FileInfo();
		
		
		//실제 저장 filename -> Unique 
		//사용자 업로드한 파일명 -> originalFileName
		
		fileInfo.setOriginalFileName(file.getOriginalFilename());
		fileInfo.setFilePath(FILE_STORAGE_PATH);
		fileInfo.setUrlFilePath(FILE_URL_PATH);
		
		
		//실제 파일 저장
		//유니크한 파일이름
		
		// veronika.jpg
		
		String extension = extractExtenstion(file.getOriginalFilename());
		String fileName = createFileName(extension);  //유니크한 파일이름  
		
		fileInfo.setFileName(fileName);
		
		file.transferTo(new File( fileInfo.getFilePath() + fileInfo.getFileName()  ));
						//				d:/fileStorage/  + asdfoaosidjfo3oi2jf98asdf.jpg
		
		return fileInfo;
		
	}
	
	static String createNewFileName(String fileName) {
		String newFileName = UUID.randomUUID().toString() + fileName.substring((fileName.lastIndexOf(".")));
		return newFileName;
	}
	
	static String createFileName(String extension) {
		String fileName = UUID.randomUUID().toString();
		
		//asdfoaosidjfo3oi2jf98asdf
		
		fileName = fileName + "." + extension;
		
		return fileName;
	}
	
	static String extractExtenstion(String fileName) {  //veronika.jpg		
		//cat.jpg   확장자 jpg
		// abc.xlsx       xlsx
		// qwe.hwp 		  hwp   확장자(jpg, png, jepg)
		
		return fileName.substring((fileName.lastIndexOf(".") + 1 ));
	}
}
