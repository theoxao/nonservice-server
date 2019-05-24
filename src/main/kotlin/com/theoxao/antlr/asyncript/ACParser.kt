package com.theoxao.antlr.asyncript

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptLexer
import com.theoxao.antlr.source.AsyncriptParser
import org.antlr.v4.runtime.*
import org.intellij.lang.annotations.Language
import org.junit.Test


/**
 * @author theo
 * @date 2019/5/23
 */
//@Service
class ACParser : AsyncriptBaseListener() {


    @Test
    fun test() {
        @Language("Groovy")
        val script = "package com.example;\n\nimport com.theoxao.common.ParamWrap;\nimport com.theoxao.common.CommonResult;\n\nstatic CommonResult service(ParamWrap paramWrap) async{\n    List<String> result = new ArrayList<String>();\n    String localUser = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    result.add(localUser);\n    String response = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    def user = ObjectMapper().readValue(response, User.class);\n    result.add(user);\n    return new CommonResult(result);\n}"
        val parser = parse(script)
        val compilationUnit = parser.compilationUnit()
        compilationUnit.typeDeclaration().forEach {
            it.classBodyDeclaration()
                    .memberDeclaration()
                    .methodDeclaration()
                    .asyncMethodBody()
                    .block()
                    .blockStatement().forEach { bs ->
                        bs.statement()
                                .statementExpression()
                                .expression()
                                .awaitExpression()
                    }
        }
        println(1)
    }


    fun parse(input: String): AsyncriptParser {
        val inputStream: CharStream = ANTLRInputStream(input)
        val lexer: TokenSource = AsyncriptLexer(inputStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = AsyncriptParser(tokenStream)
        parser.removeErrorListeners()
        parser.errorHandler = BailErrorStrategy()
        parser.addErrorListener(ConsoleErrorListener())
        return parser
    }
}