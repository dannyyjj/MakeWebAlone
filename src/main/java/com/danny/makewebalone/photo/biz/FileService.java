package com.danny.makewebalone.photo.biz;

import com.danny.makewebalone.photo.vo.PhotoVO;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class FileService {


    @SneakyThrows
    public String uploadFile(MultipartFile file, int id, String type) {
        List<PhotoVO> photoVOList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // 파일명 날짜 시간으로 저장하기 https://forest-coding.tistory.com/10
        String fileName = (id + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));
        String originalName = file.getOriginalFilename();

        // 현재 날짜로 Directory 생성
        SimpleDateFormat dateFormatForDir = new SimpleDateFormat("yyyy/MM/dd"); // 경로잡기용
        String currentDate = dateFormatForDir.format(date); // 저장할 때 시간
        log.error("currentDate ===> {}", currentDate);

        String dirPath = "C:/img/" + type + File.separator + currentDate + File.separator;

        // 확장자 가져오기 https://codechacha.com/ko/java-file-extension/
        String imgType = originalName.substring(originalName.lastIndexOf("."));
        String imgType2 = originalName.substring(originalName.lastIndexOf(".") + 1);
        log.error("확장자===>{}", imgType);
        String name = fileName + imgType;

        Path copyLocation = Paths.get(dirPath + File.separator + "or" + File.separator + name);
        Path smallCopyLocation = Paths.get(dirPath + File.separator + "s" + File.separator + name);
        Path largeCopyLocation = Paths.get(dirPath + File.separator + "lg" + File.separator + name);

        BufferedImage inputImage = ImageIO.read(file.getInputStream());
        int originWidth = inputImage.getWidth();
        int originHeight = inputImage.getHeight();

        // 변경할 가로 길이
        int smallWidth = 300;
        int largeWidth = 500;

        // 기존 이미지 비율을 유지하여 세로 길이 설정
        int smallHeight = (originHeight * smallWidth) / originWidth;
        int largeHeight = (originHeight * largeWidth) / originWidth;

        Image smallImage = inputImage.getScaledInstance(smallWidth, smallHeight, Image.SCALE_FAST);
        BufferedImage newsmallImage = new BufferedImage(smallWidth, smallHeight, BufferedImage.TYPE_INT_RGB);
        Graphics smsllGraphics = newsmallImage.getGraphics();
        smsllGraphics.drawImage(smallImage, 0, 0, null); // 없으면 그림안그림, 검은색만 저장
        smsllGraphics.dispose();

        // 이미지 저장
        File newsmallFile = new File(smallCopyLocation.toString());

        if (!newsmallFile.exists()) {
            newsmallFile.mkdirs();
        }

        ImageIO.write(newsmallImage, imgType2, newsmallFile);

        Image largeImage = inputImage.getScaledInstance(largeWidth, largeHeight, Image.SCALE_FAST);
        BufferedImage newImage = new BufferedImage(largeWidth, largeHeight, BufferedImage.TYPE_INT_RGB);
        Graphics largeGraphics = newImage.getGraphics();
        largeGraphics.drawImage(largeImage, 0, 0, null);
        largeGraphics.dispose();

        // 이미지 저장
        File newlargeFile = new File(largeCopyLocation.toString());

        if (!newlargeFile.exists()) {
            newlargeFile.mkdirs();
        }

        ImageIO.write(newImage, imgType2, newlargeFile);

        log.error("카피로캐이션===>{}", copyLocation);


        File originalFile = new File(copyLocation.toString());

        if (!originalFile.exists()) {
            originalFile.mkdirs();
        }

        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

        return name;
    }

    @SneakyThrows
    public String uploadFileLicense(MultipartFile file, int id, String type) {
        List<PhotoVO> photoVOList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();

        // 파일명 날짜 시간으로 저장하기 https://forest-coding.tistory.com/10
        String fileName = (id + "-" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date));

        String originalName = file.getOriginalFilename();

        // 현재 날짜로 Directory 생성
        SimpleDateFormat dateFormatForDir = new SimpleDateFormat("yyyy/MM/dd"); // 경로잡기용
        String currentDate = dateFormatForDir.format(date); // 저장할 때 시간
        log.error("currentDate ===> {}", currentDate);

        String dirPath = "C:/img/" + type + "/" + currentDate + "/";

        String imgType = originalName.substring(originalName.lastIndexOf("."));
        String imgType2 = originalName.substring(originalName.lastIndexOf(".") + 1);
        log.error("확장자===>{}", imgType);
        String name = fileName + imgType;
        Path copyLocation = Paths.get(dirPath + File.separator + name);

        BufferedImage inputImage = ImageIO.read(file.getInputStream());
        int originWidth = inputImage.getWidth();
        int originHeight = inputImage.getHeight();

        // 변경할 가로 길이
        int width = 300;

        // 기존 이미지 비율을 유지하여 세로 길이 설정
        int Height = (originHeight * width) / originWidth;

        Image smallImage = inputImage.getScaledInstance(width, Height, Image.SCALE_FAST);
        BufferedImage licenseImage = new BufferedImage(width, Height, BufferedImage.TYPE_INT_RGB);
        Graphics graphics = licenseImage.getGraphics();
        graphics.drawImage(smallImage, 0, 0, null); // 없으면 그림안그림, 검은색만 저장
        graphics.dispose();

        // 이미지 저장
        File licenseFile = new File(copyLocation.toString());

        if (!licenseFile.exists()) {
            licenseFile.mkdirs();
        }

        ImageIO.write(licenseImage, imgType2, licenseFile);


        return name;
    }

    @SneakyThrows
    public String updateFile(MultipartFile file, String beforeFileName) {
        String afterFileName = "";

        String type = beforeFileName.split("/")[0];
        String realFileName = beforeFileName.split("/")[2];
        String date = beforeFileName.split("-")[1];
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        int id = Integer.valueOf(beforeFileName.split("/")[2].split("-")[0]);

        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/s/" + realFileName);
        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/lg/" + realFileName);
        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/or/" + realFileName);

        afterFileName = uploadFile(file, id, "prot");

        return afterFileName;
    }

    @SneakyThrows
    public void justDelete(String fileName, String type) {
        String date = fileName.split("-")[1];
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);
        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/s/" + fileName);
        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/lg/" + fileName);
        delete("C:/img/" + type + "/" + year + "/" + month + "/" + day + "/or/" + fileName);
    }

    public void delete(String path) {
        File beforeimg = new File(path);
        if (beforeimg.isFile()) {
            beforeimg.delete();
        }
    }

}