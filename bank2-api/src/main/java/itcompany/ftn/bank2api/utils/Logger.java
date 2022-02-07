package itcompany.ftn.bank2api.utils;

import itcompany.ftn.bank2api.model.enums.LogMode;
import itcompany.ftn.bank2api.model.enums.LogStatus;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;



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
