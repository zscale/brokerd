package com.paulasmuth.fyrehose

import java.util.concurrent._
import java.util.concurrent.atomic._
import java.io._
import java.net._

class TCPListener(port: Int) {

  val sock = new ServerSocket(port)
  val clients = Executors.newCachedThreadPool() // evil ~paul

  var num_connections = new AtomicInteger

  def listen = {
    Fyrehose.log("listening on tcp/0.0.0.0:" + port.toString)

    clients.execute(new Runnable{ def run = {
      while(true){
        try{
          val conn = new Endpoint(sock.accept())
          clients.execute(conn)
        } catch {
          case e: Exception => Fyrehose.exception(e, true)
        }
      }
    }})
  }

}