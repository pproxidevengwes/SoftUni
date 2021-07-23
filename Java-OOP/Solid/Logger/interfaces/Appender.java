package interfaces;

import enums.ReportLevel;

public interface Appender {
    void appendMessage(String dateTine, ReportLevel reportLevel, String message);

    void setReportLevel(ReportLevel reportLevel);
}
