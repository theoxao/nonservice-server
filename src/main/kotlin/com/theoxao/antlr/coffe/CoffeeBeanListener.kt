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
    private var currentFieldNameElement: Element? = null
    private var fieldList: MutableMap<String, Pair<Element?, Element?>?> = mutableMapOf()
    private var asyncFieldList: MutableMap<String, Pair<Element?, Element?>?> = mutableMapOf()
    private var currentField: Pair<Element?, Element?>? = null
    private var currentTypeDeclarationElement: Element? = null
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

//    override fun enterVariableModifier(ctx: AsyncriptParser.VariableModifierContext?) {
//        ctx?.let {
//            code.append("${it.text} ")
//        }
//    }

    override fun enterLocalVariableDeclaration(ctx: AsyncriptParser.LocalVariableDeclarationContext?) {
        val modifiers = ctx?.variableModifier()?.map { it.text }
        if (modifiers != null && modifiers.isNotEmpty()) {
            code.append("${modifiers.reduce { acc, s -> "$acc $s" }} ")
        }
        ctx?.typeType()?.let {
            val start = code.length
            code.append("${it.text} ")
            currentTypeDeclarationElement = Element(it.text, start, code.length - 1)
        }
    }

    override fun enterVariableDeclaratorId(ctx: AsyncriptParser.VariableDeclaratorIdContext?) {
        if (ctx?.parent?.parent?.parent !is AsyncriptParser.FormalParametersContext)  //ignore since its already append in there
            ctx?.Identifier()?.let {
                val start = code.length
                code.append(it.text)
                currentFieldNameElement = Element(it.text, start, code.length)
                code.append(" = ")
                currentField = Pair(currentTypeDeclarationElement, currentFieldNameElement)
                fieldList[it.text] = (currentField)
            }
    }

//    override fun enterVariableInitializer(ctx: AsyncriptParser.VariableInitializerContext?) {
//        code.append(ctx?.expression()?.text?.replace("<missing WS>", ""))
//    }

    override fun enterClassOrInterfaceModifier(ctx: AsyncriptParser.ClassOrInterfaceModifierContext?) {
        code.append("${ctx?.text} ")
    }

    override fun enterExpectExpression(ctx: AsyncriptParser.ExpectExpressionContext?) {
        currentFieldNameElement?.end?.let { code.insert(it, "Mono") }
        currentTypeDeclarationElement?.end?.let { code.insert(it, ">") }
        currentTypeDeclarationElement?.start?.let { code.insert(it, "Mono<") }
        currentFieldNameElement?.let {
            asyncFieldList[it.text] = currentField
        }
        code.append(" ${ctx?.expression()?.text} ")
    }

    override fun enterAsyncMethodBody(ctx: AsyncriptParser.AsyncMethodBodyContext?) {
        code.append("{\n")
    }

    override fun exitLocalVariableDeclarationStatement(ctx: AsyncriptParser.LocalVariableDeclarationStatementContext?) {
        code.append("\n")
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

data class Element(var text: String, var start: Int, var end: Int)