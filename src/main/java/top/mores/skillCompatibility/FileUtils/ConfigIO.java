package top.mores.skillCompatibility.FileUtils;

import org.bukkit.configuration.file.FileConfiguration;
import top.mores.skillCompatibility.SkillCompatibility;

public class ConfigIO {

    private FileConfiguration getConfig(){
        return SkillCompatibility.getInstance().getConfigFile();
    }

    public String getStringPath(){
        return getConfig().getString("path");
    }
}
