package forkMode;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;

import org.testng.annotations.Test;

public class Test1
{

    @Test
    public void test1() throws IOException {
        dumpPidFile( "test1" );
    }
    
    public static void dumpPidFile( String name )
        throws IOException
    {
        String fileName = name + "-pid";
        File target = new File("target");
        if (! (target.exists() && target.isDirectory()) ) {
            target = new File (".");
        }
        File pidFile = new File(target, fileName);
        FileWriter fw = new FileWriter(pidFile);
        // DGF little known trick... this is guaranteed to be unique to the PID
        // In fact, it usually contains the pid and the local host name!
        String pid = ManagementFactory.getRuntimeMXBean().getName();
        fw.write( pid );
        fw.flush();
        fw.close();
    }

}
