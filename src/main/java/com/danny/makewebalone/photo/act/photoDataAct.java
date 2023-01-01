package com.danny.makewebalone.photo.act;

import com.danny.makewebalone.photo.biz.AES256;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;

@Slf4j
@RestController
@RequiredArgsConstructor
public class photoDataAct {

    final private AES256 aes256;

    @SneakyThrows
    @RequestMapping(value = "/image/{fileName}",produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE})
    public byte[] imgSearch(@PathVariable("fileName") String fileName) {
        fileName = aes256.decrypt(fileName);
        String type = fileName.split("/")[0];

        String realFileName = null;
        String size = null;
        if (type.equals("license")) {
            realFileName = fileName.split("/")[1];
        } else {
            size = fileName.split("/")[1];;
            realFileName = fileName.split("/")[2];
        }


        String date = fileName.split("-")[1];
        String year = date.substring(0, 4);
        String month = date.substring(4, 6);
        String day = date.substring(6, 8);

        String path = null;
        if (type.equals("license")) {
            path = "C:/img/" + type +"/" + year + "/" + month + "/" + day +"/"+realFileName;
        } else {
            path = "C:/img/" + type +"/" + year + "/" + month + "/" + day + "/" + size +"/"+realFileName;
        }

        File img = new File(path);

        FileInputStream fls = null;

        int len = 0;
        byte[] fileArray = new byte[(int) img.length()];
        fls = new FileInputStream(img);
        fls.read(fileArray);

        fls.close();
        return fileArray;
    }

}