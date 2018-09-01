package web.controllers

import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc.{AbstractController, ControllerComponents}
import project.information.BuildInfo
import web.responses.HealthCheck

@Singleton
class HomeController @Inject()(controllerComponents: ControllerComponents)
  extends AbstractController(controllerComponents)
{
  def healthCheck() =
    Action {
      Ok {
        json(HealthCheck(BuildInfo.name, BuildInfo.version, DateTime.now()))
      }
    }
}
