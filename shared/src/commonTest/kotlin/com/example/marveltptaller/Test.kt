package com.example.marveltptaller

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class test {
    @Test
    fun TestSuma() {
        val resultado = Suma(4,2)
        assertEquals(6, resultado)
    }

    @Test
    fun TestDeUnBooleano(){
        val resultado = MayorA5(6)
        assertTrue(resultado)
    }

    @Test
    fun TestNull(){
        val resultado = MenorA0Null(-6)
        assertNull(resultado)
    }

    private fun Suma(numero : Int, numero2: Int) : Int{
        return numero + numero2
    }

    private fun MayorA5(numero : Int) : Boolean{
        return numero > 5
    }

    private fun MenorA0Null(numero : Int) : Int?{
        if(numero<0){
            return null
        }else{
            return numero
        }
    }

}