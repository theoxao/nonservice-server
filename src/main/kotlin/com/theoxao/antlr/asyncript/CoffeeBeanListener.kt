package com.theoxao.antlr.asyncript

import com.theoxao.antlr.target.JavaParser
import com.theoxao.antlr.target.JavaParserBaseListener
import org.antlr.v4.runtime.tree.TerminalNodeImpl


/**
 * @author theo
 * @date 2019/6/10
 */
class CoffeeBeanListener : JavaParserBaseListener() {

    private val code: StringBuilder = StringBuilder()
    private var firstTypeParameter: Boolean = true

    override fun enterPackageDeclaration(ctx: JavaParser.PackageDeclarationContext?) {
        code.append("package ${ctx?.qualifiedName()?.text}")
    }

    override fun enterImportDeclaration(ctx: JavaParser.ImportDeclarationContext?) {
        //TODO "." "*" are not include
        code.append("import ${ctx?.STATIC() ?: ""} ${ctx?.qualifiedName()?.text}")
    }

    override fun enterClassBody(ctx: JavaParser.ClassBodyContext?) {
        code.append("{\n")
    }

    override fun exitClassBody(ctx: JavaParser.ClassBodyContext?) {
        code.append("\n}")
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

    override fun enterTypeParameters(ctx: JavaParser.TypeParametersContext?) {
        code.append("<")
    }

    override fun enterTypeParameter(ctx: JavaParser.TypeParameterContext?) {
        val param = ctx?.IDENTIFIER()?.text + " " + (ctx?.EXTENDS()?.text ?: "") + " " + (ctx?.typeBound()?.text ?: "")
        code.append("${if (firstTypeParameter) "" else ","}$param")
        this.firstTypeParameter = false
    }

    override fun enterMethodDeclaration(ctx: JavaParser.MethodDeclarationContext?) {
        val type = ctx?.typeTypeOrVoid()
        type!!.children.forEach {
            when (it) {
                is TerminalNodeImpl -> code.append(it.symbol.text + " ")
                is JavaParser.TypeTypeContext -> {
                    code.append(it.annotation()?.text ?: "" + " ")
                    code.append((it.primitiveType()?.text ?: " ") + " ")

                }
            }
        }
    }


    override fun exitTypeParameters(ctx: JavaParser.TypeParametersContext?) {
        code.append(">")
        this.firstTypeParameter = true
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