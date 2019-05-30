package com.theoxao.antlr.asyncript

import com.theoxao.antlr.source.AsyncriptLexer
import com.theoxao.antlr.source.AsyncriptParser
import com.theoxao.antlr.target.AntlrDemoLexer
import com.theoxao.antlr.target.AntlrDemoParser
import org.antlr.v4.runtime.*
import org.junit.Test


/**
 * @author theo
 * @date 2019/5/30
 */
class DemoParser {

    @Test
    fun test() {
        val script = "root.trunkleaf"

        val parser = parse(script)
        val compilationUnit = parser.compilationUnit()
        println(compilationUnit.text)
    }


    private fun parse(input: String): AntlrDemoParser {
        val inputStream: CharStream = ANTLRInputStream(input)
        val lexer: TokenSource = AntlrDemoLexer(inputStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = AntlrDemoParser(tokenStream)
        parser.addErrorListener(DiagnosticErrorListener())
        parser.removeErrorListeners()
        parser.errorHandler = DefaultErrorStrategy()
        parser.addErrorListener(ConsoleErrorListener())
        return parser
    }

}