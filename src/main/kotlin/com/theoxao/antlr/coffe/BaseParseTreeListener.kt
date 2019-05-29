package com.theoxao.antlr.coffe

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptParser
import org.stringtemplate.v4.STGroupFile


/**
 * @author theo
 * @date 2019/5/27
 */
class BaseParseTreeListener(val parser: AsyncriptParser) : AsyncriptBaseListener() {

    private var currentMethodDeclaration: AsyncriptParser.MethodDeclarationContext? = null
    private var currentAsyncMethodBody: AsyncriptParser.AsyncMethodBodyContext? = null
    private val code = StringBuilder()
    private val stg = STGroupFile("D:\\workspace\\prod\\script-as-service\\src\\main\\kotlin\\com\\theoxao\\antlr\\coffe\\coffe_bean.stg")

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

    override fun enterClassBodyDeclaration(ctx: AsyncriptParser.ClassBodyDeclarationContext?) {
        val st = stg.getInstanceOf("modifierTemp")
        ctx?.modifier()?.forEach {
            st.add("modifiers", it.classOrInterfaceModifier().text)
        }
        code.append(st.render())
    }

    override fun enterMethodBody(ctx: AsyncriptParser.MethodBodyContext?) {
        code.append("{\n")
    }

    override fun enterMethodDeclaration(ctx: AsyncriptParser.MethodDeclarationContext?) {
        val typeType = ctx?.typeType()?.text ?: "void"
        if (ctx?.asyncMethodBody() != null) {
            currentAsyncMethodBody = ctx.asyncMethodBody()
            code.append(" Mono<$typeType> ")
        } else
            code.append(" $typeType ")
        code.append(ctx?.Identifier())
        val parameters = ctx?.formalParameters()?.formalParameterList()?.formalParameter()
        code.append("(")
        code.append(parameters?.map {
            val st = stg.getInstanceOf("modifierTemp")
            it.variableModifier().forEach { m ->
                st.add("modifiers", m.text)
            }
            return@map StringBuilder()
                    .append("${st.render()} ")
                    .append("${it.typeType().text} ")
                    .append(it.variableDeclaratorId().text).toString()
        }?.reduce { acc, s -> "$acc,$s" })
        code.append(")")
    }

    override fun enterAsyncMethodBody(ctx: AsyncriptParser.AsyncMethodBodyContext?) {
        code.append("{\n")
    }

    override fun enterLocalVariableDeclaration(ctx: AsyncriptParser.LocalVariableDeclarationContext?) {
        val st = stg.getInstanceOf("modifierTemp")
        ctx?.variableModifier()?.forEach {
            st.add("modifiers", it.text)
        }
        code.append(st.render())
        code.append(" ${ctx?.typeType()?.text} ")
        ctx?.variableDeclarators()?.variableDeclarator()?.map {
            it.variableDeclaratorId()
        }
    }

    override fun exitLocalVariableDeclaration(ctx: AsyncriptParser.LocalVariableDeclarationContext?) {
        code.append("\n")
    }

    override fun exitAsyncMethodBody(ctx: AsyncriptParser.AsyncMethodBodyContext?) {
        code.append("\n}")
    }

    override fun exitMethodBody(ctx: AsyncriptParser.MethodBodyContext?) {
        code.append("\n}")
    }

    override fun exitClassDeclaration(ctx: AsyncriptParser.ClassDeclarationContext?) {
        code.append("\n}")
    }


    override fun exitCompilationUnit(ctx: AsyncriptParser.CompilationUnitContext?) {
        println(code.toString())
    }
}