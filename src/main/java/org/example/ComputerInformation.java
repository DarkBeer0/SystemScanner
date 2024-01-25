package org.example;

import javax.swing.filechooser.FileSystemView;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class ComputerInformation {
    public void GetOS(){
        System.out.println("Operating System: " + System.getProperty("os.name"));
    }

    public void GetRAM(){
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        if (osBean instanceof com.sun.management.OperatingSystemMXBean sunOsBean)
        {
            long totalPhysicalMemory = sunOsBean.getTotalMemorySize();
            long freePhysicalMemory = sunOsBean.getFreeMemorySize();

            System.out.println("Total RAM: " + formatSize(totalPhysicalMemory));
            System.out.println("Free RAM: " + formatSize(freePhysicalMemory));
        }
        else
            System.out.println("Failed to retrieve memory information.");
    }

    public void GetHardDrive() {
        File[] paths;
        FileSystemView fsv = FileSystemView.getFileSystemView();
        paths = File.listRoots();
        for(File path: paths)
        {
            long totalSpace = path.getTotalSpace();
            long freeSpace = path.getFreeSpace();
            System.out.println("Drive Name: " + path);
            System.out.println("Description: " + fsv.getSystemTypeDescription(path));
            System.out.println("Total Space on C Drive: " + formatSize(totalSpace));
            System.out.println("Free Space on C Drive: " + formatSize(freeSpace));
        }
    }

    public void CPUInfo() {
        try {
            Process process = Runtime.getRuntime().exec("wmic cpu get caption, maxclockspeed, name, numberofcores, numberoflogicalprocessors");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void GPUInfo() {
        try {
            Process process = Runtime.getRuntime().exec("wmic path win32_videocontroller get caption");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String formatSize(long bytes) {
        long kilobytes = bytes / 1024;
        long megabytes = kilobytes / 1024;
        long gigabytes = megabytes / 1024;

        return String.format("%d GB, %d MB", gigabytes, megabytes % 1024);
    }
}
