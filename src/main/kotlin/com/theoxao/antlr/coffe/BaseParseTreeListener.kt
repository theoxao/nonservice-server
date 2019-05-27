package com.theoxao.antlr.coffe

import com.theoxao.antlr.source.AsyncriptBaseListener
import com.theoxao.antlr.source.AsyncriptParser
import com.theoxao.antlr.target.JavaParser


/**
 * @author theo
 * @date 2019/5/27
 */
class BaseParseTreeListener(val parser: AsyncriptParser) : AsyncriptBaseListener() {

    private val treeHolder = TreeHolder()

    override fun enterPackageDeclaration(ctx: AsyncriptParser.PackageDeclarationContext?) {
        ctx?.qualifiedName()?.text
    }
}