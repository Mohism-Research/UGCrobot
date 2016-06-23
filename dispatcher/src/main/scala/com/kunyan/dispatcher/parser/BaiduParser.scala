package com.kunyan.dispatcher.parser

import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

import scala.collection.mutable

/**
  * Created by yang on 4/28/16.
  */
object BaiduParser {

  def topBars(html: String): mutable.Set[String] = {

    val set = mutable.Set[String]()
    val doc = Jsoup.parse(html, "UTF-8")
    val tags = doc.getElementById("ba_list").getElementsByTag("a")

    for (i <- 0 until tags.size()) {
      val url = "http://tieba.baidu.com" + tags.get(i).attr("href")
      set.+=(url)
    }

    set
  }

  def getHotPosts(html: String): mutable.Set[String] = {

    val set = mutable.Set[String]()
    val map = mutable.Map[Int, Element]()
    val removeStr = html.replace("<!--", "").replace("-->", "")
    val doc = Jsoup.parse(removeStr, "UTF-8")
    var tags: Elements = null

    try {

      if (doc.toString.isEmpty) {
        return null
      }

      tags = doc.getElementById("thread_list").getElementsByAttributeValue("class", " j_thread_list clearfix")

      if (tags.isEmpty) {
        return null
      }

      for (i <- 0 until tags.size()) {

        val iTag = tags.get(i)

        try {

          val readCount = StringToInt(iTag.getElementsByAttributeValue("class", "row").get(0).getElementsByTag("span").get(1).text)
          val commentCount = StringToInt(iTag.getElementsByAttributeValue("class", "row").get(1).getElementsByTag("span").get(1).text)
          map.put(readCount + commentCount, iTag)

        } catch {
          case e: Exception =>
            val replyCount = StringToInt(iTag.getElementsByAttributeValue("class", "threadlist_rep_num center_text").get(0).text)
            map.put(replyCount, iTag)
        }

      }

      val sortedMap = map.toSeq.sortWith(_._1 > _._1)

      for (i <- 0 until 3) {
        val url = sortedMap(i)._2.getElementsByTag("a").attr("href")
        set.add("http://tieba.baidu.com" + url)
      }

      set
    } catch {
      case e: NullPointerException =>
        null
    }

  }

  def getFirstPost(html: String): String = {

    val doc = Jsoup.parse(html, "UTF-8")
    val href = doc.getElementById("content").getElementsByClass("simple_block_container").first().firstElementSibling().firstElementSibling().getElementsByTag("a").attr("href")

    "http://tieba.baidu.com" + href

  }

  def getTitle(html: String): String = {
    Jsoup.parse(html, "UTF-8").title()
  }

  def getUserInfo(html: String): (String, String, String, String) = {

    val doc = Jsoup.parse(html, "UTF-8")
    val title = doc.title

    try {

      var pid = ""

      if (title.contains("_")) {

        var barName = title.split("_")(1)
        barName = barName.substring(0, barName.length - 1)
        val fid = html.split("fid: '")(1).split("'")(0)
        val tbs = html.split("tbs:'")(1).split("',")(0)

        if (doc.select("cc div").size >= 2) {

          val text = doc.select("cc div").get(1).toString

          if (text.nonEmpty && text.contains("content_")) {

            pid = text.split("content_")(1).split("\"")(0)

            (barName, fid, tbs, pid)

          } else {
            null
          }

        } else null

      } else null

    } catch {
      case e: Exception =>
        null
    }

  }

  def StringToInt(text: String): Int = {

    if (text.contains("万")) {
      (text.replace("万", "").toFloat * 10000).toInt
    } else {
      text.toInt
    }

  }

  /**
    * 获取贴吧网页的所有repostid和贴吧名
    *
    * @author niujiaojiao
    * @param html 将要解析的页面信息字符串
    * @return 元组集合
    */
  def getName(html: String): (String, mutable.Set[String]) = {

    val set = mutable.Set[String]()
    val doc = Jsoup.parse(html, "UTF-8")
    val title = doc.title()
    var barName = ""

    try {

      if (title.contains("_")) {

        barName = title.split("_")(1)
        barName = barName.substring(0, barName.length - 1)

      }

      val list = doc.select("div#j_p_postlist  > div")

      for(i<- 1 until list.size){ //百度贴吧首层楼不再发送相关信息

        var pid = ""
        val text = list.get(i).select("div.d_post_content_main").select("cc div").get(0).toString

        if (text.nonEmpty && text.contains("content_")) {

          pid = text.split("content_")(1).split("\"")(0)
          set.add(pid)

        }

      }

      (barName, set)
    } catch {
      case e: NullPointerException =>
        null
    }

  }

}
