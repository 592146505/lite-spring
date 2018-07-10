package com.roamer.litespring.service.v3;

import com.roamer.litespring.dao.v3.AccountDao;
import com.roamer.litespring.dao.v3.ItemDao;

/**
 * 测试构造器注入
 *
 * @author roamer
 * @version V3.0
 * @date 2018/7/8 9:10
 */
public class PetStoreService {

    private AccountDao accountDao;
    private ItemDao itemDao;
    private int version;

    public PetStoreService(AccountDao accountDao, ItemDao itemDao) {
        this(accountDao, itemDao, 0);
    }

    public PetStoreService(AccountDao accountDao, ItemDao itemDao, int version) {
        this.accountDao = accountDao;
        this.itemDao = itemDao;
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }


}