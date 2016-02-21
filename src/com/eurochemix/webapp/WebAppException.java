package com.eurochemix.webapp;

import com.eurochemix.webapp.model.Resume;

/**
 * Created by Ilya on 19.02.2016.
 */
public class WebAppException extends RuntimeException {
    private Resume resume = null;
    private String uuid = null;

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable e) {
        super(message, e);
    }

    public WebAppException(String message, Resume resume) {
        super(message);
        this.resume = resume;
    }

    public WebAppException(String message, Resume resume, Throwable e) {
        super(message, e);
        this.resume = resume;
    }


    public WebAppException(String message, String uuid) {
        super(message);
        this.uuid = uuid;
    }

    public Resume getResume() {
        return resume;
    }

    public String getUuid() {
        return uuid;
    }


}
