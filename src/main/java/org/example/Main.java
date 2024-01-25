package org.example;

public class Main {
    public static void main(String[] args) {
        ComputerInformation info = new ComputerInformation();
        System.out.println("-----------------------------");
        info.GetOS();
        System.out.println("-----------------------------");
        info.CPUInfo();
        System.out.println("-----------------------------");
        info.GPUInfo();
        String gpuInfo = GPUInfoWithJNA.getGPUInfo();
        System.out.println("GPU Information: " + gpuInfo);
        System.out.println("-----------------------------");
        info.GetRAM();
        System.out.println("-----------------------------");
        info.GetHardDrive();
    }
}