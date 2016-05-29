package com.morder.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by amis on 16-5-29.
 */
@Component
public class TemplateConfig {
    @Value("${execl.templatepath}")
    private String execltemplatepath;
    @Value("${execl.templatetemppath}")
    private String execltemplatetemppath;

    public String getExecltemplatepath() {
        return execltemplatepath;
    }

    public void setExecltemplatepath(String execltemplatepath) {
        this.execltemplatepath = execltemplatepath;
    }

    public String getExecltemplatetemppath() {
        return execltemplatetemppath;
    }

    public void setExecltemplatetemppath(String execltemplatetemppath) {
        this.execltemplatetemppath = execltemplatetemppath;
    }
}
