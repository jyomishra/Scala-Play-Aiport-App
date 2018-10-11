package services

import model.{AirportDetail, Country, CountryWithAirportCount}

trait AirportDataService {

  def getAirportDetailsByName(name:String):List[AirportDetail]

  def getAirportDetailsByCode(code:String):List[AirportDetail]

  def getCountriesWithMaxAirports(num:Int):List[CountryWithAirportCount]

  def getCountriesWithMinAirports(num:Int):List[CountryWithAirportCount]

  def typeOfRunwaysPerCountry:Map[String,List[String]]

  def mostCommonRunwaysIdentification(num:Int):List[String]

}