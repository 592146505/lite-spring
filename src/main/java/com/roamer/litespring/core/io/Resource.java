package com.roamer.litespring.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源接口
 *
 * @author roamer
 * @version V1.0
 * @date 2018/6/21 20:19
 */
public interface Resource {

    /**
     * 获取资源输入流
     *
     * @return
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

    /**
     * 获取资源描述
     *
     * @return
     */
    String getDescription();
}
