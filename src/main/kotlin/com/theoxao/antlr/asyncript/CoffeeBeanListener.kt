package com.theoxao.antlr.asyncript

import com.theoxao.antlr.target.JavaParser
import com.theoxao.antlr.target.JavaParserBaseListener
import java.lang.StringBuilder


/**
 * @author theo
 * @date 2019/6/10
 */
class CoffeeBeanListener : JavaParserBaseListener() {

    private val code: StringBuilder = StringBuilder()

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        code.append("package ${ctx?.qualifiedName()?.text}")
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        code.append("import ${ctx?.STATIC() ?: ""} ${ctx?.qualifiedName()?.text}")
    }

    override fun enterAnnotation(ctx: JavaParser.AnnotationContext?) {
        code.append("${ctx?.text} ") //TODO is there other situation;
    }

    override fun enterClassOrInterfaceModifier(ctx: JavaParser.ClassOrInterfaceModifierContext?) {
        ctx?.annotation() ?: code.append("${ctx?.text} ")
    }

    override fun enterClassDeclaration(ctx: JavaParser.ClassDeclarationContext?) {
        code.append("class ${ctx?.IDENTIFIER()?.text} ")
    }

    override fun enterTypeParameter(ctx: JavaParser.TypeParameterContext?) {
        code.append("<${ctx?.text}>")
    }

    override fun exitTypeDeclaration(ctx: JavaParser.TypeDeclarationContext?) {
        code.append("\n")
    }

    override fun exitPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        code.append("\n")
    }

    override fun exitImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        code.append("\n")
    }

    override fun exitCompilationUnit(ctx: JavaParser.CompilationUnitContext?) {
        println(code)
    }
}