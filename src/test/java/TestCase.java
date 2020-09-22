import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCase {

    private static Logger logger = LoggerFactory.getLogger(TestCase.class);

    @Before
    public void init() throws Exception {
        System.out.println("init");
    }

    @Test
    public void testCreate() throws Exception {
        System.out.println("创建----");
        StringBuffer stringBuffer = new StringBuffer();
        List<Object> longList = new ArrayList<>();
        longList.add(91L);
        longList.add(13L);
        longList.add(78888881L);
        longList.add("71L");
        longList.addAll(Arrays.asList("12,111,ooo,rrr".split(",")));
        longList.forEach(id -> {
            if (id instanceof String) {
                stringBuffer.append("'").append(id).append("',");
            } else {
                stringBuffer.append(id).append(",");
            }
            System.out.println(id.getClass().getTypeName());
        });
        System.out.println(stringBuffer.deleteCharAt(stringBuffer.length() - 1).toString());
    }

    @Test
    public void testWrite() throws IOException, InterruptedException {
        String path = "C:\\filebeat-7.9.1-windows-x86_64\\data\\testFileBeat.log";

        int nm = 1;
        while (nm++ <= 100) {
            appendWrite(path, true);
        }

//        writeFile(path);

        RandomAccessFile randomAccessFile = new RandomAccessFile(path, "rw");
        randomAccessFile.seek(randomAccessFile.length());
        randomAccessFile.writeBytes(RandomStringUtils.randomNumeric(6) + "\r\n");
        randomAccessFile.close();

        readFile(path);
    }

    private void appendWrite(String path, boolean append) throws IOException {
        FileWriter fileWriter = new FileWriter(path, append);
        Writer writer = fileWriter.append(RandomStringUtils.randomAlphanumeric(10)).append("\r\n");
        writer.flush();
        writer.close();
        fileWriter.close();
    }

    private void readFile(String path) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(path);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bfReader = new BufferedReader(inputStreamReader);
        String line = null;
        int lineNum = 1;
        while ((line = bfReader.readLine()) != null) {
            logger.warn((lineNum++) + "、{}", line);
        }
        bfReader.close();
        inputStreamReader.close();
        fileInputStream.close();
        System.out.println("file read over...");
    }

    private void writeFile(String path) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        OutputStreamWriter out = new OutputStreamWriter(fileOutputStream);
        BufferedWriter bfWriter = new BufferedWriter(out);
        String str = RandomStringUtils.randomAlphanumeric(100);
        bfWriter.write(str);
        System.out.println("write==>>" + str);
        bfWriter.close();
        out.close();
        fileOutputStream.close();
    }

    private void joinThread() throws InterruptedException {
        System.out.println("joinThread...");
        Thread.currentThread().join(5000);
    }
}