package com.test.asmplugin

import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.commons.AdviceAdapter

/**
 *
 * @author jun.wang04
 * created at 2023/11/25 22:00
 */
class TestMethodVisitor(
    api: Int,
    methodVisitor: MethodVisitor,
    access: Int,
    name: String,
    descriptor: String
): AdviceAdapter(api, methodVisitor, access, name, descriptor) {

    override fun onMethodEnter() {
        println("onMethodEnter")
        super.onMethodEnter()
        mv.visitLdcInsn("Test.class")
        mv.visitLdcInsn("aaa start")
        mv.visitMethodInsn(
            INVOKESTATIC, "android/util/Log", "d",
            "(Ljava/lang/String;Ljava/lang/String;)I", false
        )
        mv.visitInsn(POP)
    }

    override fun onMethodExit(opcode: Int) {
        mv.visitLdcInsn("Test.class")
        mv.visitLdcInsn("aaa end")
        mv.visitMethodInsn(
            INVOKESTATIC, "android/util/Log", "d",
            "(Ljava/lang/String;Ljava/lang/String;)I", false
        )
        mv.visitInsn(POP)
        super.onMethodExit(opcode)
    }
}