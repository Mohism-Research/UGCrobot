package com.kunyan.dispatcher.logger

import org.apache.log4j.{PropertyConfigurator, BasicConfigurator, Logger}

/**
  * Created by niujiaojiao on 2016/6/22.
  */
object RbtLogger {

  val logger = Logger.getLogger("Robot")
  BasicConfigurator.configure()

  PropertyConfigurator.configure("/home/robot/conf/log4j.properties")

  def exception(e: Exception) = {
    logger.error(e.getLocalizedMessage)
    logger.error(e.getStackTraceString)
  }

  def error(msg: String): Unit = {
    logger.error(msg)
  }

  def warn(msg: String): Unit = {
    logger.warn(msg)
  }

  def info(msg: String): Unit = {
    logger.info(msg)
  }

  def debug(msg: String): Unit = {
    logger.debug(msg)
  }
}
