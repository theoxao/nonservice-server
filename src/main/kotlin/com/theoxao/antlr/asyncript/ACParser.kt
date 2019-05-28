package com.theoxao.antlr.asyncript

import com.theoxao.antlr.coffe.BaseParseTreeListener
import com.theoxao.antlr.source.AsyncriptLexer
import com.theoxao.antlr.source.AsyncriptParser
import org.antlr.v4.runtime.*
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.intellij.lang.annotations.Language
import org.junit.Test


/**
 * @author theo
 * @date 2019/5/23
 */
//@Service
class ACParser {


    @Test
    fun test() {
        print(.1 + .2)
        @Language("Groovy")
        val script = "package com.example;\nimport com.theoxao.common.CommonResult;\nimport com.theoxao.common.ParamWrap;\nimport org.springframework.web.bind.annotation.RequestMapping;\n\n@RequestMapping\npublic  class Foo{\n    static CommonResult service(ParamWrap paramWrap) async {\n        List<String> result = new ArrayList<String>();\n        String localUser = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n        result.add(localUser);\n        String response = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n        def user = ObjectMapper().readValue(response, User.class); result.add(user); return new CommonResult(result);\n    }\n}\n\nstatic CommonResult service2(ParamWrap paramWrap) async {\n    List<String> result = new ArrayList<String>();\n    String localUser = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    result.add(localUser);\n    String response = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    def user = ObjectMapper().readValue(response, User.class); result.add(user); return new CommonResult(result);\n}\n"
        val parser = parse(script)
        val compilationUnit = parser.compilationUnit()
        ParseTreeWalker.DEFAULT.walk(BaseParseTreeListener(parser), compilationUnit)
    }


    private fun parse(input: String): AsyncriptParser {
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