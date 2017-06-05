package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.eq
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.io.PrintStream

@RunWith(MockitoJUnitRunner::class)
class LogReportTest {

    @Mock
    lateinit var writer : PrintStream

    @InjectMocks
    lateinit var logReport: LogReport

    lateinit var outWriter: PrintStream

    @Before
    fun setup() {
        outWriter = System.out
        System.setOut(writer)
    }

    @After
    fun clean() {
        System.setOut(outWriter)
    }

    @Test
    fun shouldWriteHeader() {

        // given
        val header = "HEADER"

        // when
        logReport.header(header)

        // then
        verify(writer, times(1)).printf(LogReport.HEADER, header)
    }

    @Test
    fun shouldWriteImageData() {

        // when
        logReport.write(GeoImage(File("."), 12.0, 24.0, 1))

        // then
        verify(writer, times(1)).printf(eq(LogReport.FORMAT), any())
    }

    @Test
    fun shouldWriteFooter() {

        // when
        logReport.footer()

        // then
        verify(writer, times(1)).printf(LogReport.FOOTER)
    }
}