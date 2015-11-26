package ru.antowka.stock.dao;

import ru.antowka.stock.model.Setting;

/**
 * Created by Anton Nik on 18.11.15.
 */
public interface SettingDao {

    Setting getSettingByName(String settingName);

    void updateSetting(Setting setting);
}
