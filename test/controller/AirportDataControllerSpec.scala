package controller

import controllers.AirportDataController
import model.AirportDetail
import org.mockito.Matchers
import org.mockito.Mockito._
import org.scalatest.words._
import org.scalatest.mockito.MockitoSugar
import org.scalatestplus.play.PlaySpec
import play.api.inject._
import play.api.inject.guice.GuiceApplicationBuilder
import play.api.mvc.AnyContentAsEmpty
import play.api.test.{FakeHeaders, FakeRequest}
import play.api.test.Helpers._
import services.AirportDataService


class AirportDataControllerSpec extends PlaySpec with MockitoSugar{

  val mockAirportDataService = mock[AirportDataService]


  val appMock = new GuiceApplicationBuilder()
    .overrides(bind[AirportDataService].toInstance(mockAirportDataService))
    .build

  "AirportDataController" should {
    "return list of Airports if proper country name is given" in {
      val apDetail = new AirportDetail("9906", "AAFA", "heliport",
        "App Airport", "70.56", "32.43", "43", "EU",
        "IT", "EU", "ITA", "no", "00CO","",
        "ACD", "", "", "")
      when(mockAirportDataService.getAirportDetailsByName(Matchers.any())).thenReturn(List(apDetail))
      val controller = new AirportDataController(stubControllerComponents(),mockAirportDataService)

      val airportData = controller.getAirportDataByQuery.apply(FakeRequest(GET, "/airport/query?name=zimb", FakeHeaders(Seq.empty), AnyContentAsEmpty))

      status(airportData) mustBe(OK)
      val output = contentAsString(airportData)
      output must MatcherWords.include("App Airport")
    }
  }

  "AirportDataController" should {
    "return list of Airports if proper country code is given" in {
      val apDetail = new AirportDetail("9906", "AAFA", "heliport",
        "App Airport", "70.56", "32.43", "43", "EU",
        "IT", "EU", "ITA", "no", "00CO","",
        "ACD", "", "", "")
      when(mockAirportDataService.getAirportDetailsByCode(Matchers.any())).thenReturn(List(apDetail))
      val controller = new AirportDataController(stubControllerComponents(),mockAirportDataService)

      val airportData = controller.getAirportDataByQuery.apply(FakeRequest(GET, "/airport/query?code=IT", FakeHeaders(Seq.empty), AnyContentAsEmpty))

      status(airportData) mustBe(OK)
      val output = contentAsString(airportData)
      output must MatcherWords.include("App Airport")
    }
  }

  "AirportDataController" should {
    "return BadRequest if proper country code is not given" in {
      when(mockAirportDataService.getAirportDetailsByCode(Matchers.any())).thenReturn(List.empty)
      val controller = new AirportDataController(stubControllerComponents(),mockAirportDataService)

      val airportData = controller.getAirportDataByQuery.apply(FakeRequest(GET, "/airport/query?code=XYZ", FakeHeaders(Seq.empty), AnyContentAsEmpty))

      status(airportData) mustBe BAD_REQUEST
    }
  }
}
