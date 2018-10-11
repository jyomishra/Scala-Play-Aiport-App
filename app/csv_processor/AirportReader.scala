package csv_processor

import model.{AirportDetail, Country, Runway}

trait AirportReader {

  def readAirports:Seq[AirportDetail]

  def readCountries:Seq[Country]

  def readRunways:Seq[Runway]

}
