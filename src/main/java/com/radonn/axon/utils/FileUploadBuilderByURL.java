package com.radonn.axon.utils;

import net.dv8tion.jda.api.utils.FileUpload;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class FileUploadBuilderByURL {

    private String outPutName;
    private FileUpload fileUpload;

    public FileUploadBuilderByURL(String outPutName, String url) {
        this.outPutName = outPutName;
        try {
            InputStream fileStream = new URL(url).openStream();
            this.fileUpload = FileUpload.fromData(fileStream, outPutName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

	public String getOutPutName() {
		return outPutName;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}
}