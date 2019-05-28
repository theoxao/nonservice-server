package com.theoxao.antlr.coffe


/**
 * @author theo
 * @date 2019/5/27
 */
open class CompilationUnit {

    var packageDeclaration: PackageDeclaration? = null
    var importDeclaration: MutableList<ImportDeclaration> = mutableListOf()
//    var typeDeclaration: MutableList<TypeDeclaration> = mutableListOf()

    open fun text(): String {
        return ""
    }

    abstract class AlternativeDeclaration()

    data class PackageDeclaration(var qualifiedName: String?)

    data class ImportDeclaration(var qualifiedName: String?)


    data class ModifierDeclaration(var modifier: Modifier, var declaration: AlternativeDeclaration)

    abstract class AbstractDeclaration()

//    data class  ClassBodyDeclaration()
//
//    data class MethodDeclaration()

    data class Modifier(var text: String)
}
