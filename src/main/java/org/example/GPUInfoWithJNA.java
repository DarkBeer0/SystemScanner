package org.example;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;

public class GPUInfoWithJNA {
    public interface User32 extends Library {
        User32 INSTANCE = Native.load("user32", User32.class);

        int GetSystemMetrics(int nIndex);
    }

    public interface Kernel32 extends Library {
        Kernel32 INSTANCE = Native.load("kernel32", Kernel32.class);

        void GetSystemInfo(SYSTEM_INFO lpSystemInfo);

        class SYSTEM_INFO extends Structure {
            public static class ByReference extends SYSTEM_INFO implements Structure.ByReference {
            }

            public int dwOemId;
            public int dwPageSize;
            public Pointer lpMinimumApplicationAddress;
            public Pointer lpMaximumApplicationAddress;
            public int dwActiveProcessorMask;
            public int dwNumberOfProcessors;
            public int dwProcessorType;
            public int dwAllocationGranularity;
            public short wProcessorLevel;
            public short wProcessorRevision;
        }
    }

    public static String getGPUInfo() {
        int nIndex = 80;
        int remoteSession = User32.INSTANCE.GetSystemMetrics(nIndex);

        if (remoteSession != 0) {
            return "Cannot retrieve GPU information in remote session.";
        }
        return "GPU information not available.";
    }
}