package com.radonn.axon.utils;

import java.io.File;

import net.dv8tion.jda.api.utils.FileUpload;

public class FileUploadBuilderByPath {

    private String outPutName;
    private File file;
    private FileUpload fileUpload;

    public FileUploadBuilderByPath(String outPutName, String path) {
        this.outPutName = outPutName;
        this.file = new File(path);
        this.fileUpload = FileUpload.fromData(file, outPutName);
    }

	public String getOutPutName() {
		return outPutName;
	}

	public FileUpload getFileUpload() {
		return fileUpload;
	}
}
