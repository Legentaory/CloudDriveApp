package com.udacity.jwdnd.course1.cloudstorage.DAOs;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.pojos.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class FileDao {

    @Resource
    private FileMapper fileMapper;

    public File[] getFiles(int userId) {
        return fileMapper.getFiles(userId);
    }

    public String[] getFileListings(int userId){
        return fileMapper.getFileListings(userId);
    }

    public int addFile(File file){
        return fileMapper.insert(file);
    }

    public File getFile(String fileName) {
        return fileMapper.getFile(fileName);
    }

    public void updateFile(File file){
        fileMapper.update(file);
    }

    public void deleteFile(String fileName) {
        fileMapper.deleteFile(fileName);
    }
}
