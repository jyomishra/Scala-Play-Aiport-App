package controllers
import javax.inject._
import model._
import play.api.libs.json.Json
import play.api.mvc._
import services.AirportDataService

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class AirportDataController @Inject()(cc: ControllerComponents, airportService: AirportDataService)(implicit assetsFinder: AssetsFinder)
  extends AbstractController(cc) {

  implicit val airportWrites = Json.writes[AirportDetail]
  implicit val countryWrites = Json.writes[Country]
  implicit val runwaysWrites = Json.writes[Runway]
  implicit val countryWithAirportCountWrites = Json.writes[CountryWithAirportCount]
  implicit val reportWrites = Json.writes[Report]

  def getAirportDataByQuery = Action { implicit request:Request[AnyContent] => {
      val requestParam = request.queryString
      if(requestParam.get("name").isDefined) {
        Ok(Json.toJson(airportService.getAirportDetailsByName(requestParam("name").head)))
      } else if(requestParam.get("code").isDefined) {
        Ok(Json.toJson(airportService.getAirportDetailsByCode(requestParam("code").head)))
      } else {
        BadRequest("wrong Input. Give country name or fuzzy name or code.")
      }
    }
  }

  def getAirportDataInReport = Action { implicit request:Request[AnyContent] => {
      Ok(Json.toJson(Report(airportService.getCountriesWithMaxAirports(10), airportService.getCountriesWithMinAirports(10),
        airportService.typeOfRunwaysPerCountry, airportService.mostCommonRunwaysIdentification(10))))
    }
  }

  def getAirportDataInReportById(id : String) = Action { implicit request:Request[AnyContent] => {
      if(id.equalsIgnoreCase("1")) {
        Ok(Json.toJson(airportService.getCountriesWithMaxAirports(10)))
      } else if(id.equalsIgnoreCase("2")) {
        Ok(Json.toJson(airportService.getCountriesWithMinAirports(10)))
      } else if(id.equalsIgnoreCase("3")) {
        Ok(Json.toJson(airportService.typeOfRunwaysPerCountry))
      } else if(id.equalsIgnoreCase("4")) {
        Ok(Json.toJson(airportService.mostCommonRunwaysIdentification(10)))
      } else {
        BadRequest("wrong Input. Report Id not found :" + id)
      }
    }
  }

}
