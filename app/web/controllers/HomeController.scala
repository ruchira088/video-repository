package web.controllers

import controllers.Assets
import javax.inject.{Inject, Singleton}
import org.joda.time.DateTime
import play.api.libs.json.Json.{toJson => json}
import play.api.mvc._
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

  def watchVideo(videoId: String): Action[AnyContent] =
    Action {
      Ok(web.views.html.player(videoId))
    }
}