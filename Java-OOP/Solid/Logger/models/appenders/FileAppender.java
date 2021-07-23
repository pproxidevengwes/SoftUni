package models.appenders;

import enums.ReportLevel;
import interfaces.File;
import interfaces.Layout;
import models.files.LogFile;

public class FileAppender extends BaseAppender {
    private File file;

    public FileAppender(Layout layout) {
        super(layout);
        this.file = new LogFile();
    }

    @Override
    public void appendMessage(String dateTine, ReportLevel reportLevel, String message) {

    }

    @Override
    protected void append(String text) {
        this.file.write(text);
    }

    @Override
    public void setReportLevel(ReportLevel reportLevel) {

    }
}
