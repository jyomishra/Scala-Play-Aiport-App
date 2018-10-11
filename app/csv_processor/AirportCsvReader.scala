package csv_processor

import java.nio.charset.{Charset, CodingErrorAction}

import model.{AirportDetail, Country, Runway}

import scala.io.Source

class AirportCsvReader extends AirportReader {

  val airportsFileName = "resource/airports.csv"
  val countriesFileName = "resource/countries.csv"
  val runwaysFileName = "resource/runways.csv"
  val charsetDecoder = Charset.forName("UTF-8").newDecoder
  charsetDecoder.onMalformedInput(CodingErrorAction.IGNORE)

  override def readAirports: Seq[AirportDetail] = {
    val data = Source.fromFile(airportsFileName)(charsetDecoder).getLines().map(_.split(",").map(_.trim))

    data.map(x => removeNull(x,18)).drop(1).map(AirportDetail.fromStringArray).toSeq
  }

  override def readCountries: Seq[Country] = {
    Source.fromFile(countriesFileName)(charsetDecoder).getLines().drop(1)
      .map(_.split(",").map(_.trim)).map(Country.fromStringArray).toSeq
  }

  private  def removeNull(values:Array[String], length: Int):Array[String] = {
    if(values.length < length) {
      val updatedValues : Array[String] = new Array[String](length)
      values.copyToArray(updatedValues)
      (values.length to length-1).foreach(i => {
          updatedValues(i) = ""
      })
      return updatedValues
    }
    values
  }

  override def readRunways: Seq[Runway] = {
    Source.fromFile(runwaysFileName)(charsetDecoder).getLines().map(_.split(",").map(_.trim))
        .map(x => removeNull(x, 20)).drop(1).map(Runway.fromStringArray).toSeq
  }
}
