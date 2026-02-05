package top.mores.skillCompatibility.Message;

import org.bukkit.configuration.file.FileConfiguration;
import top.mores.skillCompatibility.SkillCompatibility;

public class MessageUtil {
    private FileConfiguration getConfig(){
        return SkillCompatibility.getInstance().getConfigFile();
    }


}
