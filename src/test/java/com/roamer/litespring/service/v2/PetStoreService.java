package com.roamer.litespring.service.v2;


import com.roamer.litespring.dao.v2.AccountDao;
import com.roamer.litespring.dao.v2.ItemDao;

/**
 * 测试用PetStoreService1
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/3 13:10
 */
public class PetStoreService {
    private AccountDao accountDao;
    private ItemDao itemDao;
    private String owner;
    private int version;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public ItemDao getItemDao() {
        return itemDao;
    }

    public void setItemDao(ItemDao itemDao) {
        this.itemDao = itemDao;
    }
}
