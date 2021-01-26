package com.katrasolutions.gjethja.model;

public class CertificateModel {

    private String id;

    private String fileName;

    private String contentType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public CertificateModel(String fileName, String contentType) {
        this.fileName = fileName;
        this.contentType = contentType;
    }

    public CertificateModel() {

    }
}
