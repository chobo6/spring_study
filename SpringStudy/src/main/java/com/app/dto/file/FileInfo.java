package com.app.dto.file;

import lombok.Data;

@Data
public class FileInfo {  
	String fileName;  //실제 저장된 파일이름 (유니크한값) PK  dsoi30fivbn2dovdsvdjof.jpg
	String originalFileName; //사용자가 업로드하던 당시의 원래 파일이름 veronika.jpg
	String filePath; //파일이 저장된 저장소 경로
	String urlFilePath;  //나중에 화면에 띄울때, image url 경로 표시할때 사용할 값
	
	//확장자
	//파일사이즈
	//업로드일자
	//...
}
