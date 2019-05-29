package com.theoxao.antlr.coffe

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptParser
import org.stringtemplate.v4.STGroupFile


/**
 * @author theo
 * @date 2019/5/29
 */
class CoffeeBeanListener : AsyncriptBaseListener() {

    private var currentAsyncMethodBody: AsyncriptParser.AsyncMethodBodyContext? = null
    private val code = StringBuilder()
    private val stg = STGroupFile("D:\\workspace\\prod\\script-as-service\\src\\main\\kotlin\\com\\theoxao\\antlr\\coffe\\coffe_bean.stg")

    override fun enterPackageDeclaration(ctx: AsyncriptParser.PackageDeclarationContext?) {
        ctx?.qualifiedName()?.let {
            code.append("package ${it.text}\n")
        }
    }

    override fun enterImportDeclaration(ctx: AsyncriptParser.ImportDeclarationContext?) {
        ctx?.qualifiedName()?.let {
            code.append("import ${it.text} \n")
        }
    }

    override fun enterClassDeclaration(ctx: AsyncriptParser.ClassDeclarationContext?) {
        ctx?.let {
            code.append("class ${it.Identifier()?.text}{\n")
        }
    }

    override fun enterMethodDeclaration(ctx: AsyncriptParser.MethodDeclarationContext?) {
        val typeType = ctx?.typeType()?.text ?: "void"
        if (ctx?.asyncMethodBody() != null) {
            currentAsyncMethodBody = ctx.asyncMethodBody()
            code.append(if (typeType == "void ") "$typeType " else "Mono<$typeType> ")
        } else
            code.append("$typeType ")
        code.append(ctx?.Identifier())
    }

    override fun enterFormalParameters(ctx: AsyncriptParser.FormalParametersContext?) {
        code.append("(")
        code.append(ctx?.formalParameterList()?.formalParameter()?.map {
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

    override fun enterVariableModifier(ctx: AsyncriptParser.VariableModifierContext?) {
        ctx?.let {
            code.append("${it.text} ")
        }
    }

    override fun enterVariableDeclaratorId(ctx: AsyncriptParser.VariableDeclaratorIdContext?) {
        ctx?.Identifier()?.let {
            code.append("${it.text} ")
        }
    }

    override fun enterClassOrInterfaceModifier(ctx: AsyncriptParser.ClassOrInterfaceModifierContext?) {
        code.append("${ctx?.text} ")
    }

    override fun enterAsyncMethodBody(ctx: AsyncriptParser.AsyncMethodBodyContext?) {
        code.append("{\n")
    }

    override fun exitAsyncMethodBody(ctx: AsyncriptParser.AsyncMethodBodyContext?) {
        code.append("\n}")
    }

    override fun enterMethodBody(ctx: AsyncriptParser.MethodBodyContext?) {
        code.append("{\n")
    }

    override fun exitMethodBody(ctx: AsyncriptParser.MethodBodyContext?) {
        code.append("\n}")
    }

    override fun exitClassDeclaration(ctx: AsyncriptParser.ClassDeclarationContext?) {
        code.append("\n}")
    }

    override fun exitTypeDeclaration(ctx: AsyncriptParser.TypeDeclarationContext?) {
        code.append("\n")
    }

    override fun exitCompilationUnit(ctx: AsyncriptParser.CompilationUnitContext?) {
        println(code.toString())
    }

}