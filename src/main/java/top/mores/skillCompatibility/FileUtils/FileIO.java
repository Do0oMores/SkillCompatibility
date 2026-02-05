package top.mores.skillCompatibility.FileUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import static java.nio.file.Files.createFile;

public class FileIO {

    ConfigIO configIO=new ConfigIO();
    private final String path=configIO.getStringPath();
    private final String fileName= configIO.getFileName();
    private JSONObject jsonInfo=new JSONObject();

    public JSONObject getJsonInfo() {
        return jsonInfo;
    }

    /**
     * 完整的文件路径
     * @return Full Path
     */
    private String getFullPath(){
        return path+ File.separator + fileName;
    }

    /**
     * 读取 JSON 文件
     */
    public void load() {
        File file = new File(getFullPath());

        try {
            // 不存在则创建
            if (!file.exists()) {
                createFile(file.toPath());
                jsonInfo = new JSONObject();
                save(); // 写入空 JSON
                return;
            }

            String content = Files.readString(file.toPath(), StandardCharsets.UTF_8);

            // 空文件保护
            if (content == null || content.trim().isEmpty()) {
                jsonInfo = new JSONObject();
                return;
            }

            jsonInfo = JSON.parseObject(content);

        } catch (Exception e) {
            e.printStackTrace();
            jsonInfo = new JSONObject();
        }
    }

    /**
     * 保存 JSON 到文件
     */
    public void save() {
        File file = new File(getFullPath());

        try {
            createFile(file.toPath());
            Files.writeString(
                    file.toPath(),
                    jsonInfo.toJSONString(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
