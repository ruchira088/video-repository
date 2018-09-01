package web.responses

import json.Formats.DateTimeFormat
import org.joda.time.DateTime
import play.api.libs.json.{Json, OFormat}

case class HealthCheck(serviceName: String, serviceVersion: String, currentTime: DateTime)

object HealthCheck
{
  implicit val healthCheckFormat: OFormat[HealthCheck] = Json.format[HealthCheck]
}