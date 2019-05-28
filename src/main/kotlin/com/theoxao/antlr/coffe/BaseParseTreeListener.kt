package com.theoxao.antlr.coffe

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptParser
import org.stringtemplate.v4.STGroupFile


/**
 * @author theo
 * @date 2019/5/27
 */
class BaseParseTreeListener(val parser: AsyncriptParser) : AsyncriptBaseListener() {


    val code = StringBuilder()
    val stg = STGroupFile("D:\\workspace\\prod\\script-as-service\\src\\main\\kotlin\\com\\theoxao\\antlr\\coffe\\coffe_bean.stg")

    override fun enterPackageDeclaration(ctx: AsyncriptParser.PackageDeclarationContext?) {
        val st = stg.getInstanceOf("packageTemp")
        st.add("qualifiedName", ctx?.qualifiedName()?.text)
        code.append(st.render())
    }

    override fun exitPackageDeclaration(ctx: AsyncriptParser.PackageDeclarationContext?) {
        code.append("\n")
    }

    override fun enterImportDeclaration(ctx: AsyncriptParser.ImportDeclarationContext?) {
        val st = stg.getInstanceOf("importTemp")
        st.add("importHolder", CompilationUnit.ImportDeclaration(ctx?.qualifiedName()?.text))
        code.append(st.render())
    }

    override fun enterTypeDeclaration(ctx: AsyncriptParser.TypeDeclarationContext?) {
        code.append("\n")
        val st = stg.getInstanceOf("typeDeclarationTemp")
        st.add("classOrInterfaceModifier", ctx?.classOrInterfaceModifier()?.map {
            it.text
        })
        code.append(st.render())
    }

    override fun enterClassDeclaration(ctx: AsyncriptParser.ClassDeclarationContext?) {
        code.append(" class ")
        code.append(ctx?.Identifier()?.text + "{\n")
    }

    override fun exitClassDeclaration(ctx: AsyncriptParser.ClassDeclarationContext?) {
        code.append("\n}")
    }

    override fun enterClassBodyDeclaration(ctx: AsyncriptParser.ClassBodyDeclarationContext?) {
        ctx?.modifier()
    }

    override fun enterMethodDeclaration(ctx: AsyncriptParser.MethodDeclarationContext?) {
        val typeType = ctx?.typeType()?.text ?: "void"
        code.append(" $typeType")
    }

    override fun exitCompilationUnit(ctx: AsyncriptParser.CompilationUnitContext?) {
        println(code.toString())
    }
}