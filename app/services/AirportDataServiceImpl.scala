package services

import javax.inject.{Inject, Singleton}
import csv_processor.AirportReader
import model.{AirportDetail, CountryWithAirportCount, Runway}

class AirportDataServiceImpl @Inject()(reader:AirportReader) extends AirportDataService {

  private val countries = reader.readCountries
  private val airports = reader.readAirports
  private val runways = reader.readRunways
  private val sortedCountriesByNumOfAirport = airports.groupBy(_.country).map(x => (x._1,x._2.length)).
    toSeq.sortBy(_._2)

  override def getAirportDetailsByName(name:String) = {
    (for{
      country <- countries
      if country.name.toLowerCase.startsWith(name.toLowerCase)
      airport <- airports
      if country.code.equalsIgnoreCase(airport.country)
    } yield airport).toList
  }

  override def getAirportDetailsByCode(code:String) = {
    (for{
      airport <- airports
      if code.toLowerCase.equalsIgnoreCase(airport.country.toLowerCase)
    } yield airport).toList
  }

  override def getCountriesWithMaxAirports(num:Int) = {
    val countryCodes = sortedCountriesByNumOfAirport.reverse.take(num)
    (for {
      code <- countryCodes
      country <- countries
      if country.code.equalsIgnoreCase(code._1)
    } yield CountryWithAirportCount(country, code._2)).toList
  }

  override def getCountriesWithMinAirports(num:Int) = {
    val countryCodes = sortedCountriesByNumOfAirport.take(num)
    (for {
      code <- countryCodes
      country <- countries
      if country.code.equalsIgnoreCase(code._1)
    } yield CountryWithAirportCount(country, code._2)).toList
  }

  override def typeOfRunwaysPerCountry = {
    val countryByRunwayMap = buildMapOfRunwaysIndentByCountryCodes(airports, runways)
    countryByRunwayMap.map(x => (getCountryNameFromCountryCode(x._1), x._2.toList.distinct))
  }

  private def buildMapOfRunwaysIndentByCountryCodes(airports:Seq[AirportDetail], runways: Seq[Runway]): Map[String, Seq[String]] = {
    val runwayByAirport = runways.groupBy(_.airport_ref.trim)
    val countryByRunwayMap = airports.groupBy(_.country.trim).map(x => (x._1, x._2.flatMap(a => runwayByAirport.getOrElse(a.id.trim,Seq.empty))))
      .map(x => (x._1,x._2.map(_.surface)))
    countryByRunwayMap
  }

  private def getCountryNameFromCountryCode(code: String) = {
    val country = countries.filter(_.code.equalsIgnoreCase(code))
    if(country.isEmpty) {
      println("Country not found for code : " + code)
      ""
    }
    else country.head.name
  }

  override def mostCommonRunwaysIdentification(num:Int) = {
    runways.groupBy(_.le_indent).map(x => (x._1,x._2.length)).toSeq.sortBy(_._2).take(num).map(_._1).toList
  }
}
