package com.roamer.litespring.test.v4;

import com.roamer.litespring.beans.factory.support.BeanDefinitionRegistry;
import com.roamer.litespring.beans.factory.support.DefaultBeanFactory;
import com.roamer.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import com.roamer.litespring.core.io.ClassPathResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class XmlBeanDefinitionReaderTest {

    private BeanDefinitionRegistry registry;
    private XmlBeanDefinitionReader reader;
    private Verify verify;

    @Before
    public void setUp() throws Exception {
        registry = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(registry);
        verify = new Verify(registry);
    }

    @After
    public void tearDown() throws Exception {
        verify = null;
        reader = null;
        registry = null;
    }

    @Test
    public void testParseScannerBean() {
        reader.loadBeanDefinition(new ClassPathResource("petstore-v4.xml"));
        verify.verifyResult("petStore", "petStore");
        verify.verifyResult("accountDao", null);
        verify.verifyResult("itemDao", null);
    }

}