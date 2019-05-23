package com.theoxao.antlr.asyncript

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptLexer
import com.theoxao.antlr.source.AsyncriptParser
import org.antlr.v4.runtime.*


/**
 * @author theo
 * @date 2019/5/23
 */
class ACParser : AsyncriptBaseListener() {

    fun parse(input: String) {
        val inputStream: CharStream = ANTLRInputStream(input)
        val lexer: TokenSource = AsyncriptLexer(inputStream)
        val tokenStream: TokenStream = CommonTokenStream(lexer)
        val parser = AsyncriptParser(tokenStream)
        parser.removeErrorListeners()
        parser.errorHandler = BailErrorStrategy()
        parser.addErrorListener(ConsoleErrorListener())
        parser.stat()
    }
}