package ru.antowka.stock.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antowka.stock.dao.SettingDao;
import ru.antowka.stock.model.Setting;

/**
 * Created by Anton Nik on 18.11.15.
 */
@Repository
public class SettingDaoImpl implements SettingDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Setting getSettingByName(String settingName) {

       return sessionFactory.getCurrentSession()
               .get(Setting.class, settingName);
    }

    @Override
    public void updateSetting(Setting setting) {
        sessionFactory.getCurrentSession().update(setting);
    }
}
