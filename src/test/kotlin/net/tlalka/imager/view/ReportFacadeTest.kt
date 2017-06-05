package net.tlalka.imager.view

import net.tlalka.imager.data.GeoImage
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.io.File

@RunWith(MockitoJUnitRunner::class)
class ReportFacadeTest {

    @Mock
    lateinit var logReport: LogReport

    @Mock
    lateinit var csvReport: CsvReport

    @Test
    fun shouldCreateHeaderOnReportFactory() {

        // given
        val reportFacade = ReportFacade(logReport, csvReport)

        // when
        reportFacade.header("HEADER")

        // then
        verify(logReport, times(1)).header(anyString())
        verify(csvReport, times(1)).header(anyString())
    }

    @Test
    fun shouldCreateImageDataOnReportFactory() {

        // given
        val reportFacade = ReportFacade(logReport, csvReport)
        val geoImage = GeoImage(File("."), 12.0, 24.0, 1)

        // when
        reportFacade.write(geoImage)

        // then
        verify(logReport, times(1)).write(geoImage)
        verify(csvReport, times(1)).write(geoImage)
    }

    @Test
    fun shouldCreateFooterOnReportFactory() {

        // given
        val reportFacade = ReportFacade(logReport, csvReport)

        // when
        reportFacade.footer()

        // then
        verify(logReport, times(1)).footer()
        verify(csvReport, times(1)).footer()
    }

    @Test
    fun shouldWorkWithEmptyReportFactory() {

        // given
        val reportFacade = ReportFacade()

        // when
        reportFacade.header("HEADER")
        reportFacade.write(GeoImage(File("."), 12.0, 24.0, 1))
        reportFacade.footer()

        // then
        verify(logReport, never()).header(anyString())
        verify(csvReport, never()).header(anyString())
    }
}