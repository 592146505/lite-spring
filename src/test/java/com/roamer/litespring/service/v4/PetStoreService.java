package com.roamer.litespring.service.v4;

import com.roamer.litespring.beans.factory.annotation.Autowired;
import com.roamer.litespring.dao.v4.AccountDao;
import com.roamer.litespring.dao.v4.ItemDao;
import com.roamer.litespring.stereotype.Component;

@Component(value = "petStore")
public class PetStoreService {

    @Autowired
    private AccountDao accountDao;

    @Autowired
    private ItemDao itemDao;

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }


}