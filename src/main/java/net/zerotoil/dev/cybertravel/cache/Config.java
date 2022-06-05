package net.zerotoil.dev.cybertravel.cache;

import lombok.Getter;
import net.zerotoil.dev.cybertravel.CyberTravel;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;

public class Config {

    private final CyberTravel main;

    @Getter private boolean autoUpdateConfig;
    @Getter private boolean autoUpdateLang;

    @Getter private boolean countdownEnabled;
    @Getter private long countdownSeconds;

    @Getter private boolean regionCooldownEnabled;
    @Getter private long regionCooldownSeconds;

    @Getter private boolean globalCooldownEnabled;
    @Getter private long globalCooldownSeconds;

    @Getter private boolean roundDecimalsEnabled;
    @Getter private short roundDecimalsAmount;

    @Getter private boolean displayBorderEnabled;
    @Getter private List<Short> displayBorderRGB;
    @Getter private long displayBorderSeconds;

    @Getter private boolean debugPlayerJoinLeave;

    public Config(CyberTravel main) {
        this.main = main;

        ConfigurationSection section = main.core().files().getConfig("config").getConfigurationSection("config");
        if (section == null) return;

        // set prefix of plugin
        main.core().getTextUtilities().setPrefix(main.core().files().getConfig("lang").getString("messages.prefix"));

        // auto update
        autoUpdateConfig = section.getBoolean("auto-update.config");
        autoUpdateLang = section.getBoolean("auto-update.lang");
        if (autoUpdateConfig) main.core().files().get("config").updateConfig();
        if (autoUpdateLang) main.core().files().get("lang").updateConfig();

        // remaining settings
        countdownEnabled = section.getBoolean("countdown.enabled", true);
        countdownSeconds = section.getLong("countdown.seconds", 5L);

        regionCooldownEnabled = section.getBoolean("region-cooldown.enabled", false);
        regionCooldownSeconds = section.getLong("region-cooldown.seconds", 300L);

        globalCooldownEnabled = section.getBoolean("global-cooldown.enabled", false);
        globalCooldownSeconds = section.getLong("global-cooldown.seconds", 150L);

        roundDecimalsEnabled = section.getBoolean("round-coordinates.enabled", true);
        roundDecimalsAmount = (short) section.getInt("round-coordinates.amount", 0);

        displayBorderEnabled = section.getBoolean("display-border.enabled", true);
        displayBorderRGB = section.getShortList("display-border.rgb");
        displayBorderSeconds = section.getLong("display-border.seconds");

        debugPlayerJoinLeave = section.getBoolean("debug.player-join-leave");

    }


}
