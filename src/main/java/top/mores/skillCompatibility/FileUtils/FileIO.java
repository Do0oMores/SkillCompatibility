package top.mores.skillCompatibility.FileUtils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class FileIO {

    ConfigIO configIO = new ConfigIO();
    private final String path = configIO.getStringPath();
    private final String fileName = configIO.getFileName();
    private JSONObject jsonInfo = new JSONObject();

    private String getFullPath() {
        return path + File.separator + fileName;
    }

    public JSONObject getJsonInfo() {
        return jsonInfo;
    }

    /**
     * 载入类时加载JSON
     */
    public FileIO() {
        jsonInfo = new JSONObject();
        load();
    }

    /**
     * 加载JSON
     */
    private void load() {
        File file = new File(getFullPath());
        try {
            if (!file.exists()) {
                save();
                return;
            }
            String content = Files.readString(file.toPath(),
                    StandardCharsets.UTF_8);
            if (content.trim().isEmpty()) {
                return;
            }
            jsonInfo = JSON.parseObject(content);
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }

    /**
     * 创建目录和文件
     *
     * @param file 文件
     * @throws IOException 抛出异常
     */
    private void createFile(File file) throws IOException {
        File parent = file.getParentFile();
        if (!parent.exists()) {
            parent.mkdirs();
        }
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * 保存JSON
     */
    public void save() {
        File file = new File(getFullPath());
        try {
            createFile(file);
            Files.writeString(
                    file.toPath(),
                    jsonInfo.toJSONString(),
                    StandardCharsets.UTF_8
            );
        } catch (IOException e) {
            e.fillInStackTrace();
        }
    }
}
