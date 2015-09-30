package twitter4s.statuses

import scala.concurrent.Future

import twitter4s.entities.Tweet
import twitter4s.http.clients.OAuthClient
import twitter4s.util.Configurations

trait TwitterStatusClient extends OAuthClient with Configurations {

  val baseUrl = s"$twitterUrl/$apiVersion/statuses"

  def mentionsTimeline(count: Int = 200,
                       since_id: Option[Long] = None,
                       max_id: Option[Long] = None,
                       trim_user: Boolean = false,
                       contributor_details: Boolean = false,
                       include_entities: Boolean = true): Future[Seq[Tweet]] = {
    val parameters = MentionsParameters(count, since_id, max_id, trim_user, contributor_details, include_entities)
    Get(s"$baseUrl/mentions_timeline.json?$parameters").respondAs[Seq[Tweet]]
  }

  def userTimeline(user_id: Option[Long] = None,
                   screen_name: Option[String] = None,
                   since_id: Option[Long] = None,
                   count: Int = 200,
                   max_id: Option[Long] = None,
                   trim_user: Boolean = false,
                   exclude_replies: Boolean = false,
                   contributor_details: Boolean = false,
                   include_rts: Boolean = true): Future[Seq[Tweet]] = {
    val parameters = UserTimelineParameters(user_id, screen_name, since_id, count, max_id, trim_user, exclude_replies, contributor_details, include_rts)
    Get(s"$baseUrl/user_timeline.json?$parameters").respondAs[Seq[Tweet]]
  }

  def homeTimeline(count: Int = 200,
                   since_id: Option[Long] = None,
                   max_id: Option[Long] = None,
                   trim_user: Boolean = false,
                   exclude_replies: Boolean = false,
                   contributor_details: Boolean = false,
                   include_entities: Boolean = true): Future[Seq[Tweet]] = {
    val parameters = HomeTimelineParameters(count, since_id, max_id, trim_user, exclude_replies, contributor_details, include_entities)
    Get(s"$baseUrl/home_timeline.json?$parameters").respondAs[Seq[Tweet]]
  }

  def retweetsOfMe(count: Int = 200,
                   since_id: Option[Long] = None,
                   max_id: Option[Long] = None,
                   trim_user: Boolean = false,
                   exclude_replies: Boolean = false,
                   contributor_details: Boolean = false,
                   include_entities: Boolean = true): Future[Seq[Tweet]] = {
    val parameters = RetweetsOfMeParameters(count, since_id, max_id, trim_user, exclude_replies, contributor_details, include_entities)
    Get(s"$baseUrl/retweets_of_me.json?$parameters").respondAs[Seq[Tweet]]
  }

  def retweets(id: Long,
               count: Int = 100,
               trim_user: Boolean = false): Future[Seq[Tweet]] = {
    val parameters = RetweetsParameters(count, trim_user)
    Get(s"$baseUrl/retweets/$id.json?$parameters").respondAs[Seq[Tweet]]
  }

  def show(id: Long,
          trim_user: Boolean = false,
          include_my_retweet: Boolean = false,
          include_entities: Boolean = true): Future[Tweet] = {
    val parameters = ShowParameters(id, trim_user, include_my_retweet, include_entities)
    Get(s"$baseUrl/show.json?$parameters").respondAs[Tweet]
  }

}

