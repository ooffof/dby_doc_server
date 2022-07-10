package top.cuizilin.dby.utils;

import com.alibaba.fastjson.JSONObject;
import io.netty.util.internal.ResourcesUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;


import java.io.*;

public class InfoUtil {

    static final String SYSTEM = System.getProperty("os.name")
            .toLowerCase().indexOf("windows") >= 0 ? "windows" : "linux";
    static final String WINDOWS_NAME = "C:/info.json";

    static final String LINUX_NAME = "/usr/local/dby/info.json";

    public static final String PATH = ("windows".equals(SYSTEM) ? WINDOWS_NAME : LINUX_NAME);





    public static String readJson() throws IOException {
        File file = new File(PATH);
        if(!file.exists()){
            file.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("{\"type\": [\"高渤专题\",\"边疆方志\",\"金石铭文\",\"数字边疆\", \"考古发现\", \"域外文献\"]}");
            writer.flush();
            writer.close();

        }
        BufferedReader reader = new BufferedReader(new FileReader(file));

        StringBuilder sb = new StringBuilder();
        String s = null;
        while ((s = reader.readLine()) != null){
           sb.append(s);
        }
        JSONObject object = JSONObject.parseObject(sb.toString());

        return object != null ? object.toJSONString() : "";
    }

    public static String writeJson(String json) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(PATH));
        writer.write(json);
        writer.flush();
        writer.close();
        return json;
    }

//    public static void main(String[] args) throws IOException {
//        System.out.println(JSON.toJSONString(readJson()));
//
//    }
}
