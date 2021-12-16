package com.example.web_shopapi.utils;

import com.example.web_shopapi.model.enums.LogMode;
import com.example.web_shopapi.model.enums.LogStatus;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.FileWriter;
import java.text.SimpleDateFormat;

import java.util.Date;


@Data
@Component
public class Logger {
    private final static String LOG_FILE = "log.txt";

    public void writeLogs(LogMode mode, LogStatus status, String description, String ipAddress) {
        try {
                FileWriter writer = new FileWriter(LOG_FILE, true);
                String line = String.format("%s|%s|%s|%s|%s\n",
                        new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss").format(new Date()),
                        mode, status, ipAddress, description);
                writer.write(line);
                writer.close();
        }
        catch (Exception e) {
                e.printStackTrace();
        }
    }

}
