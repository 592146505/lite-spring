package com.roamer.litespring.service.v2;


import com.roamer.litespring.dao.v2.AccountDao;
import com.roamer.litespring.dao.v2.ItemDao;

/**
 * 测试用PetStoreService
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/29 13:10
 */
public class PetStoreService {
    private AccountDao accountDao;

    private ItemDao itemDao;

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
