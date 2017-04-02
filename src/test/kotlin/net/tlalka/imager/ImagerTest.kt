package net.tlalka.imager

import org.junit.Test

class ImagerTest {

    @Test
    fun startApplication() {
        Imager.main(getArgs())
    }

    @Test
    fun startApplicationsArgs() {
        Imager.main(getArgs("/tmp/"))
    }

    private fun getArgs(vararg args: String): Array<String> {
        return args.distinct().toTypedArray()
    }
}
