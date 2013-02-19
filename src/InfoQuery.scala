package com.paulasmuth.fyrehose

import scala.actors._

class InfoQuery() extends Query{

  override def execute(endpoint: Actor) = {
    recv = endpoint

    recv ! QueryResponseChunk("fnord\n".getBytes)
    recv ! QueryExitSig(this)
  }

  def data(msg: Message) = ()

  def eval(token: FQL_TOKEN) =
    throw new ParseException("invalid query token: " + token.getClass.getName)

}