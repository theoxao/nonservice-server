package com.theoxao.antlr.coffe

import org.junit.Test
import org.stringtemplate.v4.STGroupFile


/**
 * @author theo
 * @date 2019/5/28
 */
class STAider {

    @Test
    fun test() {
        val stg = STGroupFile("D:\\workspace\\prod\\script-as-service\\src\\main\\kotlin\\com\\theoxao\\antlr\\coffe\\coffe_bean.stg")
        val st = stg.getInstanceOf("packageTemp")
        st.add("qualifiedName", "com.theoxao.service")
        val st2 = stg.getInstanceOf("importGroup")
        st2.add("importHolders", CompilationUnit.ImportDeclaration("com.theoxao.CommonResult"))
        st2.add("importHolders", CompilationUnit.ImportDeclaration("com.theoxao.ParamWrap"))
        println(st.render())
        println(st2.render())
    }
}