package com.theoxao.antlr.coffe

import org.stringtemplate.v4.ST


/**
 * @author theo
 * @date 2019/5/27
 */
open class TreeHolder {

    val st: ST? = null
    init {

    }

    var packageHolder: PackageHolder? = null
    var importHolders: MutableList<ImportHolder> = mutableListOf()
    var typeDeclarationHolder: TypeDeclarationHolder? = null

    open fun text(): String {
        return ""
    }

    class PackageHolder(var qualifiedName: String?) : TreeHolder() {
        val identifier: String = "package "
        override fun text(): String {
            return qualifiedName?.let {
                "$identifier$it;\n"
            } ?: ""
        }
    }

    class ImportHolder : TreeHolder() {
        override fun text(): String {
            return ""
        }
    }

    class TypeDeclarationHolder {

    }
}
