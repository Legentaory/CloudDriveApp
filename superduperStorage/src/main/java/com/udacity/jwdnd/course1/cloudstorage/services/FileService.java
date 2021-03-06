package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.DAOs.FileDao;
import com.udacity.jwdnd.course1.cloudstorage.pojos.File;
import com.udacity.jwdnd.course1.cloudstorage.pojos.data.FileForm;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class FileService {

    @Resource
    private FileDao fileDao;

    public String createFile(FileForm fileForm, int userId) throws IOException {
        String result = "error";
        MultipartFile multipartFile = fileForm.getFile();
        if(multipartFile.isEmpty()){
            return result;
        }
        File file = new File();
        file.setFileData(multipartFile.getBytes());
        file.setFileName(multipartFile.getOriginalFilename());
        file.setContentType(multipartFile.getContentType());
        file.setFileSize(String.valueOf(multipartFile.getSize()));
        file.setUserId(userId);
        if(fileDao.getFile(multipartFile.getOriginalFilename()) == null){
            int fileId = fileDao.addFile(file);
            result = fileId > 0? "success": "error";
        } else {
            fileDao.updateFile(file);
            result = "duplicated";
        }

        return result;
    }

    public File[] getFiles(int userId){
        return fileDao.getFiles(userId);
    }

    public String[] getFileListings(int userId){
        return fileDao.getFileListings(userId);
    }

    public File getFile(String filename){
        return fileDao.getFile(filename);
    }

    public void deleteFile(String fileName) {
        fileDao.deleteFile(fileName);
    }
}
