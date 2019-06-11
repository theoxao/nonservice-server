package com.theoxao.antlr.asyncript

import com.theoxao.antlr.target.JavaLexer
import com.theoxao.antlr.target.JavaParser
import com.theoxao.antlr.target.JavaParserBaseListener
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
        val script = "package com.example;\nimport com.theoxao.common.CommonResult;\nimport com.theoxao.common.*;\n\nimport javax.management.modelmbean.RequiredModelMBean;\n\n@RequestMapping(\"/\")\npublic  static class Foo<T, R extends Object>{\n  public  static List<String> service(@Validated ParamWrap paramWrap, String s) async {\n        @Field final List<String> result = new ArrayList<String>();\n        String url = \"http://git.theoxao.com\";\n        String localUser =  paramWrap.servicesHolder.httpClient.getFuture(url);\n        result.add(localUser);\n        String response = paramWrap.servicesHolder.httpClient.getFuture(url);\n        def user = ObjectMapper().readValue(response, User.class); result.add(user); \n        return new CommonResult(result);\n    }\n}\n\nstatic CommonResult service2( ParamWrap paramWrap ,@Validated String str)  {\n    List<String> result = new ArrayList<String>();\n    String localUser =  paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    result.add(localUser);\n    String response =  paramWrap.servicesHolder.httpClient.getFuture(\"http://git.theoxao.com\");\n    def user = ObjectMapper().readValue(response, User.class); result.add(user); \n    return new CommonResult(result);\n}\n"
        val parser = parse(script)
        val compilationUnit = parser.compilationUnit()
        ParseTreeWalker.DEFAULT.walk(CoffeeBeanListener(), compilationUnit)
    }


    private fun parse(input: String): JavaParser {
        val inputStream: CharStream = ANTLRInputStream(input)
        val lexer: TokenSource = JavaLexer(inputStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = JavaParser(tokenStream)
        parser.removeErrorListeners()
        parser.errorHandler = BailErrorStrategy()
        parser.addErrorListener(ConsoleErrorListener())
        return parser
    }
}