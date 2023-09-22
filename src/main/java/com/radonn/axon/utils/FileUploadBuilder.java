package com.radonn.axon.utils;

import net.dv8tion.jda.api.utils.FileUpload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUploadBuilder {

  private String outPutName;
  private FileUpload fileUpload;

  private FileUploadBuilder() {
  }

  // Static factory method for path
  public static FileUploadBuilder fromPath(String outPutName, String path) {
    FileUploadBuilder builder = new FileUploadBuilder();
    builder.outPutName = outPutName;
    File file = new File(path);
    builder.fileUpload = FileUpload.fromData(file, outPutName);
    return builder;
  }

  // Static factory method for URL
  public static FileUploadBuilder fromURL(String outPutName, String url) {
    FileUploadBuilder builder = new FileUploadBuilder();
    builder.outPutName = outPutName;
    try {
      InputStream fileStream = new URL(url).openStream();
      builder.fileUpload = FileUpload.fromData(fileStream, outPutName);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return builder;
  }

  public String getOutPutName() {
    return outPutName;
  }

  public FileUpload getFileUpload() {
    return fileUpload;
  }
}
