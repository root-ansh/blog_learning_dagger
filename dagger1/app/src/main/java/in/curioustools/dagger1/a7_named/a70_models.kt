package `in`.curioustools.dagger1.a7_named

import javax.inject.Inject
import javax.inject.Named


/*
* A simple example of using @Named for avoiding dagger duplicate paths binding error.
*
* Here, we have a PencilBox class that is dependent on a pencil and rubber class.
* here all the models are annotable with @Inject, thus dagger can generate a complete object of
* PencilBox by itself
*
* But we do have to provide a module for String dependency since String class is not annotable
* And as per dagger's std rules, all only 1 function among all the provider modules could be used
* for returning an instance of a data type.  thus even if a component depend on a 1000 modules ,
*  only 1 can return a string and would be used to provide a string wherever it is required.
*
* This would mean that the object generated by dagger for both pencil and rubber
* will have the same company, but that's not what we want.
* so we use @Named annotation for the parameters of both the rubber and pencil's company. in the models , and
* @Named instance on 2 different string providers to seperate them . now string provider 1 will *ONLY*
* be used to get a string when generating an instance of Pencil and string provider 2 will *ONLY*
* be used to get a string when generating an instance of Rubber.
*
* BUT. there could be other dependencies where String is needed. AND NEITHER OF THE 2 @NAMED METHODS
* WOULD BE USED TO PROVIDE STRING FOR THEM . instead we have to create another @Provides method
* without any named bindings , that would serve as the default endpoint for non named string
* requests. checkout other files for other usecases of @Named
*
* */


const val named_pencil = "str_pencil_company"
const val named_rubber = "str_rubber_company"


data class PencilBox @Inject constructor(
    private val pencil: Pencil,
    private val rubber: Rubber,
    private val sharpner:Sharpner
)

data class Pencil @Inject constructor(@Named(value = named_pencil) private val company: String)
data class Rubber @Inject constructor(@Named(value = named_rubber) private val company: String)
data class Sharpner @Inject constructor(private val company: String)