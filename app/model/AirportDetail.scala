package model

case class AirportDetail(id:String, ident: String, a_type:String, name:String, longitude:String,
                         latitude:String, elevation:String, continent:String, country:String,
                         region:String, muncipality:String, schld_service:String, gps_code:String,
                         iata_code:String, local_code:String, home_link:String, wikipedia_link:String,
                         keywords:String)

object AirportDetail {
  def fromStringArray(values:Array[String]): AirportDetail= {
    AirportDetail(values(0),values(1), values(2), values(3) ,values(4),values(5),values(6),
      values(7),values(8),values(9),values(10),values(11),values(12),values(13),values(14),
      values(15),values(16),values(17))
  }
}

case class Runway(id:String, airport_ref:String, airport_id:String, length_ft:String,
                  width_ft:String, surface:String, lighted:Boolean, closed:Boolean, le_indent:String,
                  le_latitude:String, le_longitude:String, le_elevation:String, le_heading_degT:String,
                  le_displaced_threshold_ft:String, he_ident:String, he_latitude:String, he_longitude:String,
                  he_elevation:String, he_heading_degT:String, he_displaced_threshold_ft:String) {
}

object Runway {
  def fromStringArray(values:Array[String]) : Runway= {
    Runway(values(0),values(1),values(2),values(3),values(4),
      values(5),values(6).equals("1"),values(7).equals("1"),values(8),values(9),values(10),values(11),values(12),
      values(13),values(14),values(15),values(16),values(17),values(18),values(19))
  }
}

case class Country(id:String, code:String, name:String, continent:String, wikiLink:String, keywords:List[String])

object Country {
  def fromStringArray(values:Array[String]) = {
    Country(values(0),values(1),values(2),values(3),values(4), values.takeRight(5).toList)
  }
}

case class Report(countriesWithMaxAirport:List[CountryWithAirportCount], countriesWithMinAirport:List[CountryWithAirportCount],
                  listOfRunwayTypesPerCountry : Map[String, List[String]], mostCommonRunwayIdentifications: List[String])

case class CountryWithAirportCount(country: Country, airportCount:Int)