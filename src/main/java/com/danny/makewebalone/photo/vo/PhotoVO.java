package com.danny.makewebalone.photo.vo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PhotoVO {
    private int nurseNum;
    private String fileName;
    private MultipartFile profileImg;
    private MultipartFile licenseImg;
    private List<MultipartFile> files;

    public PhotoVO(int nurseNum, String fileName) {
        this.nurseNum = nurseNum;
        this.fileName = fileName;
    }

}
