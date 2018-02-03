package org.zerock.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 파일의 저장경로, 원본 파일의 이름, 파일 데이터
/*
 * 1. UUID를 이용한 고유 값 생성
 * 2. UUID와 결합한 업로드 파일 이름 생성
 * 3. 파일이 저장될 '/년/월/일' 정보 생성
 * 4. 업로드 기본 경로(uploadPath)와 '/년/월/일' 폴더 생성
 * 5. 기본경로 + 폴더경로 + 파일이름으로 파일저장
 * */

public class UploadFileUtils {

	private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

	// 보관될 필요없음 static선언
	public static String uploadFile(String uploadPath, String originalName, byte[] fileData) throws Exception {
		return null;
	}

	private static String calcPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();

		String yearPath = File.separator + cal.get(Calendar.YEAR);

		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);

		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		makeDir(uploadPath, yearPath, monthPath, datePath);

		logger.info(datePath);

		return datePath;
	}

	private static void makeDir(String uploadPath, String... paths) {

		if (new File(uploadPath, paths[paths.length - 1]).exists()) {
			return;
		}
		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			if (!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	//기본경로, 년/월/일 폴더, 현재 업로드 된 파일의 이름을 이용
	private static String makeThumbnail(
			String uploadPath,
			String path,
			String fileName)throws Exception{
		//메모리상의 이미지
		BufferedImage sourceImg =
				ImageIO.read(new File(uploadPath + path, fileName));
		BufferedImage destImg = 
				Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100);
		//지정된 100px로 동일하게 만들어주는 역할
		String thumbnailName = 
				uploadPath + path + File.separator+"s_"+fileName;
		//UUID값이 사용 된 이후 반드시 s_로 시작하도록 설정
		//동일한 파일 이름이지만 s_로 시작하면 섬네일 이미지, 제외 시 원본파일 이름
		File newFile = new File(thumbnailName);
		String formatName =
				fileName.substring(fileName.lastIndexOf(".")+1);
		
		ImageIO.write(destImg, formatName.toUpperCase(), newFile);
		return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar,'/');
		//문자열 치환하는 이유 \[원화표시]가 정상적인 경로로 인식되지 않기때문에 \로 치환 리턴
	}
}