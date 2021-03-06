package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.pojos.File;
import org.apache.ibatis.annotations.*;

@Mapper
public interface FileMapper {
    @Select("SELECT * FROM FILES WHERE filename = #{fileName}")
    File getFile(String fileName);

    @Select("SELECT * FROM FILES WHERE userid = #{userId}")
    File[] getFiles(Integer userId);

    @Select("SELECT filename FROM FILES WHERE userid = #{userId}")
    String[] getFileListings(int userId);

    @Insert("INSERT INTO FILES (filename, contenttype, filesize, userid, filedata) " +
            "VALUES(#{fileName}, #{contentType}, #{fileSize}, #{userId}, #{fileData})")
    @Options(useGeneratedKeys = true, keyProperty = "fileId")
    int insert(File file);

    @Update("UPDATE FILES SET contentType = #{contenttype}, filesize = #{fileSize}, userid = #{userId}, filedata = #{fileData} " +
            "WHERE filename = #{fileName}")
    void update(File file);

    @Delete("DELETE FROM FILES WHERE filename = #{fileName}")
    void deleteFile(String fileName);
}
