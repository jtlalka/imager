package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.File
import java.io.PrintWriter

@RunWith(MockitoJUnitRunner::class)
class CsvReportTest {

    @Mock
    lateinit var writer : PrintWriter

    @InjectMocks
    lateinit var csvReport : CsvReport

    @Test
    fun shouldWriteImageData() {

        // when
        csvReport.write(GeoImage(File("."), 12.0, 24.0))

        // then
        verify(writer, times(1)).println(ArgumentMatchers.anyString())
    }

    @Test
    fun shouldSaveDataFile() {

        // when
        csvReport.save()

        // then
        verify(writer, times(1)).flush()
        verify(writer, times(1)).close()
    }
}