package com.roamer.litespring.core.type.classreading;

import com.roamer.litespring.core.type.ClassMetadata;
import com.roamer.litespring.util.ClassUtils;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Opcodes;

/**
 * class元数据读取 Visitor
 *
 * @author roamer
 * @version V1.0
 * @date 2018/7/19 23:42
 */
public class ClassMetadataReadingVisitor extends ClassVisitor implements ClassMetadata {

    private String className;

    private boolean isInterface;

    private boolean isAbstract;

    private boolean isFinal;

    private String superClassName;

    private String[] interfaces;

    public ClassMetadataReadingVisitor() {
        super(Opcodes.ASM6);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String supername, String[] interfaces) {
        // 转换资源名称为类名
        className = ClassUtils.convertResourcePathToClassName(name);
        // 是否接口
        isInterface = ((access & Opcodes.ACC_INTERFACE) != 0);
        // 是否抽象类
        isAbstract = ((access & Opcodes.ACC_ABSTRACT) != 0);
        // 是否final修饰
        isFinal = ((access & Opcodes.ACC_FINAL) != 0);
        // 父类
        if (supername != null) {
            superClassName = ClassUtils.convertResourcePathToClassName(supername);
        }
        // 实现接口
        this.interfaces = new String[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            this.interfaces[i] = ClassUtils.convertResourcePathToClassName(interfaces[i]);
        }
    }

    @Override
    public String getClassName() {
        return className;
    }

    @Override
    public boolean isInterface() {
        return isInterface;
    }

    @Override
    public boolean isAbstract() {
        return isAbstract;
    }

    @Override
    public boolean isFinal() {
        return isFinal;
    }

    @Override
    public boolean hasSuperClass() {
        return superClassName != null;
    }

    @Override
    public String getSuperClassName() {
        return superClassName;
    }

    @Override
    public String[] getInterfaceNames() {
        return interfaces;
    }
}
