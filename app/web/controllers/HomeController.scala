package web.controllers

import controllers.Assets
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc.{AbstractController, Action, AnyContent, ControllerComponents}
import project.information.BuildInfo
import web.responses.HealthCheck

@Singleton
class HomeController @Inject()(controllerComponents: ControllerComponents, assets: Assets)
  extends AbstractController(controllerComponents)
{
  def healthCheck() =
    Action {
      Ok {
        json(HealthCheck(BuildInfo.name, BuildInfo.version, DateTime.now()))
      }
    }

  def resources(file: String): Action[AnyContent] =
    assets.at(path = "/public", file)
}