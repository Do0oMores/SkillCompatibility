package top.mores.skillCompatibility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import top.mores.skillCompatibility.PlayerListener.PlayerListen;

import java.io.File;

public final class SkillCompatibility extends JavaPlugin {

    private static SkillCompatibility instance;
    private FileConfiguration config;
    private File configFile;

    @Override
    public void onEnable() {
        instance = this;
        initFiles();

        this.getServer().getPluginManager().registerEvents(new PlayerListen(),this);
        getLogger().info("SkillCompatibility enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("SkillCompatibility disabled!");
    }

    public static SkillCompatibility getInstance() {
        return instance;
    }

    private void reloadConfigFile() {
        config= YamlConfiguration.loadConfiguration(configFile);
    }

    public @NotNull FileConfiguration getConfigFile(){
        if (config==null){
            reloadConfigFile();
        }
        return config;
    }

    private void initFiles(){
        configFile=new File(getDataFolder(),"config.yml");
        if(!configFile.exists()){
            boolean isCreated=configFile.getParentFile().mkdirs();
            if(!isCreated){
                getLogger().severe("Couldn't create config.yml");
                return;
            }
            saveResource("config.yml",false);
        }
        reloadConfigFile();
    }
}
