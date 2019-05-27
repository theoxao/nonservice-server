package com.theoxao.antlr.asyncript

import com.theoxao.antlr.coffe.BaseParseTreeListener
import com.theoxao.antlr.source.AsyncriptBaseListener
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
        @Language("Groovy")
        val script = "package com.example;import com.theoxao.common.ParamWrap;import com.theoxao.common.CommonResult;static CommonResult service(ParamWrap paramWrap) async{    List<String> result = new ArrayList<String>();    String localUser = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");    result.add(localUser);    String response = expect paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");    def user = ObjectMapper().readValue(response, User.class);    result.add(user);    return new CommonResult(result);}"
        val parser = parse(script)
        val compilationUnit = parser.compilationUnit()
        ParseTreeWalker.DEFAULT.walk(BaseParseTreeListener(parser), compilationUnit)
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